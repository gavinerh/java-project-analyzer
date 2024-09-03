package MARMSUI.migration.newer1;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ValidateMapperXmlAfterMigration {

    static Set<String> valueObjectLocationSet = new HashSet<>();

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String xmlFileLocation = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/resources/com/sg/sq/marmsui/database/sql/persistence/mappers";
        String javaMapperLocation = "";
        String valueObjLocation = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/vo";
        String modelLocation = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/database/sql/persistence/model";

        String basePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/";

        Set<String> resultMapDeclared = new HashSet<>();
        iterateAllFiles(new File(valueObjLocation), basePath);
        iterateAllFiles(new File(modelLocation), basePath);

        parseXml(xmlFileLocation,resultMapDeclared);
    }

    private static void parseXml(String xmlFileLocation, Set<String> resultMapDeclared) throws ParserConfigurationException, IOException, SAXException {
        File baseXmlFile = new File(xmlFileLocation);
        for(File file : baseXmlFile.listFiles()) {
            parseDocumentAndValidate(file.getAbsolutePath(), "resultMap",resultMapDeclared,valueObjectLocationSet);
            parseDocumentAndValidate(file.getAbsolutePath(), "select",resultMapDeclared,valueObjectLocationSet);
            parseDocumentAndValidate(file.getAbsolutePath(),"insert",resultMapDeclared,valueObjectLocationSet);
            parseDocumentAndValidate(file.getAbsolutePath(),"delete",resultMapDeclared,valueObjectLocationSet);
            parseDocumentAndValidate(file.getAbsolutePath(),"update",resultMapDeclared,valueObjectLocationSet);
        }
    }

    private static String removeBasePathAndExtension(String path, String basePath) {
        if(!path.contains(basePath)) {
            throw new RuntimeException("Base path should be inside path");
        }
        int start = basePath.length();
        String partialComplete = path.substring(start);
        if(!partialComplete.contains(".java")) {
            throw new RuntimeException("Path does not contain file extension");
        }
        int end = partialComplete.indexOf(".java");
        return partialComplete.substring(0,end);
    }

    private static String removeFileExtension(String name) {
        String pattern = ".java";
        int end = name.indexOf(pattern);
        if(end == -1) {
            throw new RuntimeException("Should not be -1");
        }
        return name.substring(0,end);
    }

    public static void parseDocumentAndValidate(String filePath, String operationType, Set<String> resultMapDeclared, Set<String> valueObjectLocationMap) throws ParserConfigurationException, IOException, SAXException {
        // Create a DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Create a DocumentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML file and build the Document object
        Document document = builder.parse(filePath);

        // Normalize the XML structure
        document.getDocumentElement().normalize();

        // Get the root element
        Element root = document.getDocumentElement();
//        System.out.println("Root element: " + root.getNodeName());

        // Get all elements with a specific tag name
        NodeList nodeList = document.getElementsByTagName(operationType);


        // Iterate through the elements
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // Extract information from the element
                String attributeValue = element.getAttribute("id");
                String type = null;
                String resultMap = null;

                if(operationType.equalsIgnoreCase("resultMap")) {
                    type = element.getAttribute("type");
                    if(!validateTypeIsPresent(type, valueObjectLocationMap)){
                        System.out.println("Error in file: " + filePath);
                    }
//                    validateResultMapContents(element);
                    resultMapDeclared.add(element.getAttribute("id"));
                }
                if(operationType.equalsIgnoreCase("select")) {
                    resultMap = element.getAttribute("resultMap");
                    if(StringUtils.isNotBlank(resultMap) && !resultMapDeclared.contains(resultMap)) {
                        System.out.println(String.format("Error in %s, method %s has missing resultMap %s",filePath,attributeValue.toUpperCase(),resultMap.toUpperCase()));
                    }
                }

                String textContent = null;
                if(!operationType.equalsIgnoreCase("resultMap")) {
                   textContent = element.getTextContent();
                    if(!checkIfOperationTagMatchQueryType(operationType, textContent)){
                        System.out.println("operation does not match for " + attributeValue + " in file : " + filePath);
                    }
                    String parameterType = element.getAttribute("parameterType");
                    if(parameterType != null && !checkIfParamTypeIsInsideValueObjectSet(valueObjectLocationMap, parameterType, attributeValue)) {
                        System.out.println("Error in " + filePath);
                    }
                }
            }
        }
    }

    private static boolean checkIfParamTypeIsInsideValueObjectSet(Set<String> valueObjectLocationSet, String paramType, String name) {
        if(paramType.startsWith("java.") || paramType.equalsIgnoreCase("map") || paramType.isBlank()) {
            return true;
        }
        String type = convertTypeToDirectoryPath(paramType);
        if(!valueObjectLocationSet.contains(type)) {
            System.out.println("Type is not found: " + type + " for method: " + name);
            return false;
        }
        return true;
    }

    private static void validateResultMapContents(Element element) {
        // Print the element's tag name
//        System.out.println("Element: " + element.getTagName());

        // Print the element's attributes
        if (element.hasAttributes()) {
            String type = element.getAttribute("type");

        }

        // Todo: continue to uncomment the below code to allow for contents of <result /> tag testing against java classes
        // Print the element's text content if it has no child elements
//        if (element.getChildNodes().getLength() == 1 && element.getChildNodes().item(0).getNodeType() == Node.TEXT_NODE) {
//            System.out.println("Text Content: " + element.getTextContent());
//        }
//
//        // Recursively parse child elements
//        NodeList nodeList = element.getChildNodes();
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                validateResultMapContents((Element) node); // change the method to extract result tag
//            }
//        }
    }

    private static boolean checkIfOperationTagMatchQueryType(String type, String sql) {
        String trimmed = sql.trim().toLowerCase();
        if(!trimmed.startsWith(type.toLowerCase())) {
            return false;
        }
        return true;
    }

    private static boolean validateTypeIsPresent(String type, Set<String> objectLocationMap) {
        String path = convertTypeToDirectoryPath(type);
        if(!objectLocationMap.contains(path)) {
            System.out.println("type is not found " + type);
            return false;
        }
        return true;
    }

    private static String convertTypeToDirectoryPath(String type) {
        String toReturn = "";
        for(char c : type.toCharArray()) {
            if(c == '.') {
                toReturn += "/";
            } else {
                toReturn += c;
            }
        }
        return toReturn;
    }


    private static void iterateAllFiles(File file, String basePath) {
        if(file.getName().equals(".DS_Store")){
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File innerFile : files) {
                iterateAllFiles(innerFile, basePath);
            }
        } else {
            String path = file.getAbsolutePath();
            valueObjectLocationSet.add(removeBasePathAndExtension(path, basePath));
        }
    }
}
