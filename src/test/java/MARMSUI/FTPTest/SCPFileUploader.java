package MARMSUI.FTPTest;

import com.jcraft.jsch.*;
import java.io.*;

// Configuration class for SCP
class SCPConfig {
    private String host;
    private int port;
    private String username;
    private String password;
    
    public SCPConfig(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }
    
    // Getters
    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

public class SCPFileUploader {
    
    public static final String LOCAL_FILE = "/Users/macuser/Desktop/holdingForTempFiles/RMC_Classified.csv";
    public static final String REMOTE_FILE = "/prod/applc/wls/marms/logs/config.txt";
    
    private SCPConfig config;
    private int connectionTimeout = 10000; // 10 seconds
    private int sessionTimeout = 30000; // 30 seconds
    
    public SCPFileUploader(SCPConfig config) {
        this.config = config;
    }
    
    public SCPFileUploader(SCPConfig config, int connectionTimeout, int sessionTimeout) {
        this.config = config;
        this.connectionTimeout = connectionTimeout;
        this.sessionTimeout = sessionTimeout;
    }
    
    /**
     * Upload a file to remote server using SCP
     */
    public boolean uploadFile(String localFilePath, String remoteFilePath) {
        Session session = null;
        ChannelExec channel = null;
        
        try {
            // Validate local file
            File localFile = new File(localFilePath);
            if (!localFile.exists() || !localFile.isFile()) {
                System.err.println("Local file does not exist or is not a file: " + localFilePath);
                return false;
            }
            
            System.out.println("========================================");
            System.out.println("Starting SCP Upload");
            System.out.println("========================================");
            System.out.println("Local file:  " + localFilePath);
            System.out.println("Remote file: " + remoteFilePath);
            System.out.println("File size:   " + localFile.length() + " bytes");
            System.out.println("Server:      " + config.getHost() + ":" + config.getPort());
            
            // Create SSH session
            session = createSession();
            
            // Connect
            System.out.println("\nConnecting to remote server...");
            session.connect(connectionTimeout);
            System.out.println("✓ SSH connection established");
            
            // Upload file
            boolean success = performSCPUpload(session, localFile, remoteFilePath);
            
            if (success) {
                System.out.println("\n✓ File uploaded successfully!");
            } else {
                System.err.println("\n✗ File upload failed!");
            }
            
            return success;
            
        } catch (JSchException e) {
            System.err.println("SSH/SCP error: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Clean up
            if (channel != null && channel.isConnected()) {
                channel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
                System.out.println("Disconnected from server");
            }
            System.out.println("========================================");
        }
    }
    
    /**
     * Create and configure SSH session
     */
    private Session createSession() throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(config.getUsername(), config.getHost(), config.getPort());
        session.setPassword(config.getPassword());
        
        // Configure session
        java.util.Properties sessionConfig = new java.util.Properties();
        sessionConfig.put("StrictHostKeyChecking", "no"); // Accept unknown hosts
        sessionConfig.put("PreferredAuthentications", "password");
        sessionConfig.put("ConnectTimeout", String.valueOf(connectionTimeout));
        
        session.setConfig(sessionConfig);
        session.setTimeout(sessionTimeout);
        
        return session;
    }
    
    /**
     * Perform the actual SCP upload using exec channel
     */
    private boolean performSCPUpload(Session session, File localFile, String remoteFilePath) 
            throws JSchException, IOException {
        
        String command = "scp -t " + remoteFilePath;
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        
        // Get I/O streams
        OutputStream out = channel.getOutputStream();
        InputStream in = channel.getInputStream();
        
        channel.connect();
        
        // Check initial response
        if (checkAck(in) != 0) {
            System.err.println("Server did not acknowledge SCP command");
            return false;
        }
        
        // Send "C0644 filesize filename" command
        long fileSize = localFile.length();
        String fileName = new File(remoteFilePath).getName();
        command = "C0644 " + fileSize + " " + fileName + "\n";
        out.write(command.getBytes());
        out.flush();
        
        if (checkAck(in) != 0) {
            System.err.println("Server did not acknowledge file transfer initialization");
            return false;
        }
        
        // Send file content
        System.out.println("\nTransferring file...");
        FileInputStream fis = new FileInputStream(localFile);
        byte[] buffer = new byte[8192];
        long totalSent = 0;
        int bytesRead;
        
        while ((bytesRead = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            totalSent += bytesRead;
            
            // Progress indicator
            int progress = (int) ((totalSent * 100) / fileSize);
            if (totalSent % (fileSize / 10 + 1) == 0 || totalSent == fileSize) {
                System.out.print("\rProgress: " + progress + "% (" + totalSent + "/" + fileSize + " bytes)");
            }
        }
        System.out.println(); // New line after progress
        
        fis.close();
        
        // Send '\0' to indicate end of file
        buffer[0] = 0;
        out.write(buffer, 0, 1);
        out.flush();
        
        if (checkAck(in) != 0) {
            System.err.println("Server reported error after file transfer");
            return false;
        }
        
        out.close();
        channel.disconnect();
        
        return true;
    }
    
    /**
     * Check acknowledgment from SCP server
     * 0 - success
     * 1 - error
     * 2 - fatal error
     * -1 - no response
     */
    private int checkAck(InputStream in) throws IOException {
        int b = in.read();
        
        if (b == 0) {
            return b;
        }
        
        if (b == -1) {
            return b;
        }
        
        if (b == 1 || b == 2) {
            StringBuilder sb = new StringBuilder();
            int c;
            do {
                c = in.read();
                sb.append((char) c);
            } while (c != '\n');
            
            if (b == 1) { // error
                System.err.println("SCP Error: " + sb.toString());
            }
            if (b == 2) { // fatal error
                System.err.println("SCP Fatal Error: " + sb.toString());
            }
        }
        
        return b;
    }
    
    public static void main(String[] args) {
        SCPConfig config = new SCPConfig(
            "192.168.180.108",
            22,  // SSH default port
            "marms",
            "Qwezxcv@1234"
        );
        
        SCPFileUploader uploader = new SCPFileUploader(config);
        
        boolean result = uploader.uploadFile(
            LOCAL_FILE,
            REMOTE_FILE
        );
        
        System.out.println("\n" + (result ? "SUCCESS" : "FAILED"));
        System.exit(result ? 0 : 1);
    }
}
