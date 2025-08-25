import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ConvertPdfToBase64String {
    public static void main(String[] args) {
        String filePath = "/Users/macuser/Desktop/holdingForTempFiles/RMC_Classified.csv"; // specify the path to your PDF file
        try {
            // Read the PDF file into a byte array
            byte[] pdfBytes = Files.readAllBytes(Paths.get(filePath));

            // Encode byte array to Base64
            String base64Encoded = Base64.getEncoder().encodeToString(pdfBytes);

            // Print the Base64 encoded string (or use it as needed)
            System.out.println("printing out now:");
            System.out.println(base64Encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
