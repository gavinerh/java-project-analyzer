package MARMSUI.IdentifyTableNamesFromMapperResponse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Generate {

    public static final String UPDATE = "update";
    public static final String INSERT = "insert";



    public static Map<String, String> getUserContentById(File xmlFile) {
        Map<String, String> result = new HashMap<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            NodeList userList = doc.getElementsByTagName(UPDATE);
            extractTableNamesFromTag(userList, result, UPDATE);

            userList = doc.getElementsByTagName(INSERT);
            extractTableNamesFromTag(userList,result,"into");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void extractTableNamesFromTag(NodeList userList, Map<String, String> result, String identifier) {
        for (int i = 0; i < userList.getLength(); i++) {
            Node node = userList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element userElement = (Element) node;
                String id = userElement.getAttribute("id");

                // Get raw XML (inner) content without comments
                StringBuilder rawContent = new StringBuilder();
                NodeList children = userElement.getChildNodes();

                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    if (child.getNodeType() != Node.COMMENT_NODE) {
                        rawContent.append(nodeToString(child));
                    }
                }
                String tableName = extractTableName(rawContent.toString(), identifier);
                result.put(id, tableName);
            }
        }
    }

    private static String extractTableName(String content, String type) {
        content = content.toLowerCase();
        return extractTableNameFromContent(content, type);
    }

    private static String extractTableNameFromContent(String content, String identifier) {
        StringBuilder stringBuilder = new StringBuilder();
        int startIndex = content.indexOf(identifier) + identifier.length();
        boolean startedWithNonSpace = false;
        for (char c : content.substring(startIndex).toCharArray()) {
            if (c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '(') {
                if (startedWithNonSpace) {
                    break;
                }
                continue;
            }
            startedWithNonSpace = true;
            stringBuilder.append(c);

        }
        return stringBuilder.toString().trim();
    }

    private static String nodeToString(Node node) {
        StringBuilder sb = new StringBuilder();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            sb.append("<").append(node.getNodeName()).append(">");
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                sb.append(nodeToString(children.item(i)));
            }
            sb.append("</").append(node.getNodeName()).append(">");
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            sb.append(node.getTextContent());
        }
        return sb.toString();
    }

    public static String getFileNameWithoutExtension(String filePath) {
        int lastSlashIndex = filePath.lastIndexOf(File.separator);
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex > lastSlashIndex) {
            return filePath.substring(lastSlashIndex + 1, lastDotIndex);
        } else {
            return filePath.substring(lastSlashIndex + 1);
        }
    }
}
