package MARMSUI.migration.newer1;

import MARMSUI.migration.ExtractFieldsFromClass;
import MARMSUI.migration.model.Type;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class ExtractMethodNameAndParams {
    static String[] whitelistedTypes = new String[]{"String", "Date", "Long", "long", "int", "Integer", "Timestamp", "double", "float", "Double", "Float", "Map<String,String>", "boolean", "java.sql.Date", "String[]", "Map<String,Object>"};
    static String[] correctJdbcTypes = new String[]{"TIMESTAMP", "VARCHAR", "NUMERIC", "DECIMAL"};

    public static void main(String[] args) throws Exception {
        String baseJava = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/database/sql/persistence/mappers";
        String baseXml = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/resources/com/sg/sq/marmsui/database/sql/persistence/mappers";
        File filePathForJava = new File(baseJava);
        File filePathForXml = new File(baseXml);
        File[] javaMapperFiles = filePathForJava.listFiles();
        File[] xmlMapperFiles = filePathForXml.listFiles();
        if (javaMapperFiles.length != xmlMapperFiles.length) {
            throw new RuntimeException("File sizes are not equal");
        }
        // create map of value object location
        Map<String, List<String>> mapOfFileNamesAndLocation = new HashMap<>();
        String valueObjLocation = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/vo";
        String modelLocation = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/database/sql/persistence/model";
        iterateAllFiles(new File(valueObjLocation), mapOfFileNamesAndLocation);
        iterateAllFiles(new File(modelLocation), mapOfFileNamesAndLocation);

        Set<String> resultMapDeclared = new HashSet<>();
        // capture file location for corresponding java and xml mapper files
        for (File javaFile : javaMapperFiles) {
            Type type = extractFilePathsForMappers(javaFile, baseJava, ".java", baseXml);

            Map<String, List<Type>> mapOfMapperMethods = new HashMap<>(); // key: name of method, value: list of params
            // extract java file contents
            List<String> listOfMethodInOrder = new ArrayList<>();
            executeToExtractMtdParamsUsingParser(type.javaFilePath, mapOfMapperMethods, listOfMethodInOrder);
            // extract xml variable in java file
            extractXmlVariablesInJavaClass(mapOfMapperMethods, javaFile, listOfMethodInOrder);
            // populate the parameter types file location string
            populateClassLocationOfType(mapOfFileNamesAndLocation, mapOfMapperMethods);
            // validate the xml file, take file path from type variable
            // Todo check if need to populate resultMap
            parseDocumentAndValidate(type.xmlFilePath, mapOfMapperMethods, mapOfFileNamesAndLocation);
        }
    }

    private static void populateClassLocationOfType(Map<String, List<String>> fileLocations, Map<String, List<Type>> mapOfMapperMtds) {

        for (String key : mapOfMapperMtds.keySet()) {
            List<Type> typeList = mapOfMapperMtds.get(key);
            for (Type type : typeList) {
                try {
                    String typeString = type.type;
                    if (Arrays.stream(whitelistedTypes).anyMatch(typeString::equals)) continue;
                    if (fileLocations.get(typeString).size() > 1) {
                        System.out.println(String.format("Type %s has more than 1 file location", typeString));
                    }
                    type.filePath = fileLocations.get(typeString).get(0);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void parseDocumentAndValidate(String filePath, Map<String, List<Type>> mapOfMapperMethods, Map<String,List<String>> locationMap) throws Exception {
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

        Map<String, Type> extractedFieldValuesFromCustomTypes = new HashMap<>();
        // Get all elements with a specific tag name
        extractCRUDSqlContent(document, "select", mapOfMapperMethods, extractedFieldValuesFromCustomTypes, locationMap);
        extractCRUDSqlContent(document, "insert", mapOfMapperMethods, extractedFieldValuesFromCustomTypes, locationMap);
        extractCRUDSqlContent(document, "update", mapOfMapperMethods, extractedFieldValuesFromCustomTypes, locationMap);
        extractCRUDSqlContent(document, "delete", mapOfMapperMethods, extractedFieldValuesFromCustomTypes, locationMap);
    }

    private static void extractCRUDSqlContent(Document document, String operationType, Map<String, List<Type>> mapOfMapperMethods, Map<String, Type> extractedFieldValuesFromCustomTypes, Map<String,List<String>> locationMap) throws Exception {
        NodeList nodeList = document.getElementsByTagName(operationType);
        // Iterate through the elements
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                if (!operationType.equalsIgnoreCase("resultMap")) {
                    // Extract information from the element
                    String methodName = element.getAttribute("id");
                    List<Type> types = mapOfMapperMethods.get(methodName); // contain the list of params for that method
                    if (types == null || types.isEmpty()) {
                        continue;
                    }
                    Set<Type> typeSet = types.stream().collect(Collectors.toSet()); // convert to set to access values faster
                    String textContent = element.getTextContent();
                    List<String> paramsInXml = extractVariableInCurlyParenthesis(textContent);
                    // populate field names in set
                    populateFieldNamesOfCustomTypes(extractedFieldValuesFromCustomTypes, typeSet, locationMap);
                    // check if the variables in java and xml are the same and field name are the same
                    checkIfVariablesAndFieldNamesAreMatching(typeSet, paramsInXml, extractedFieldValuesFromCustomTypes, methodName);
                }
            }
        }
    }

    private static void populateFieldNamesOfCustomTypes(Map<String, Type> customTypes, Set<Type> typeSet, Map<String,List<String>> locationMap) throws Exception {
        for (Type type : typeSet) {
            ExtractFieldsFromClass.execute(new FileInputStream(type.filePath), customTypes);
            String parentName = GetParentClassName.execute(locationMap.get(type.type).get(0));
            if(parentName != null) {
                ExtractFieldsFromClass.execute(new FileInputStream(locationMap.get(parentName).get(0)),customTypes);
            }
        }
    }

    private static void checkIfVariablesAndFieldNamesAreMatching(Set<Type> typeSet, List<String> paramsInXml, Map<String, Type> fieldValues, String methodName) {
        for (String params : paramsInXml) {
            String[] paramToBeChecked = extractParamAndTypeFromMybatisFormat(params);
            if (!typeSet.contains(paramToBeChecked[0])) { // check variable is correct
                System.out.println(String.format("Error: param %s cannot be found in method: %s", paramToBeChecked[0], methodName));
            }
            // check if datatype is correct
            if (!Arrays.stream(correctJdbcTypes).anyMatch(paramToBeChecked[2]::equals)) {
                System.out.println(String.format("JdbcType error: %s in param %s, method: %s", paramToBeChecked[2], paramToBeChecked[0], methodName));
            }
           // Todo: check if field name is correct

        }
    }

    private static String[] extractParamAndTypeFromMybatisFormat(String param) {
        String option = "";
        String[] response = new String[3];
        if (param.contains(".")) {
            option += "1";
        }
        if (param.contains("jdbcType")) {
            option += "2";
        }
        if (option.isBlank()) {
            response[0] = param;
        }
        boolean isAfterDot = false;
        if (option.contains("1")) {
            String toReturn = "";
            for (char c : param.toCharArray()) {
                if (c == '.') {
                    isAfterDot = true;
                } else if (isAfterDot) {
                    String fieldVal = "";
                    if (c == ' ' || c == ',') {
                        response[1] = fieldVal.trim();
                    } else {
                        fieldVal += c;
                    }
                } else
                    toReturn += c;
            }
            response[0] = toReturn.trim();
        }
        if (option.contains("2")) {
            String jdbcType = "jdbcType=";
            int start = param.indexOf(jdbcType);
            if (start == -1) {
                System.out.println("change format declaration for jdbcType");
            } else {
                int end = param.indexOf("}", start);
                response[2] = param.substring(start, end).trim();
            }
        }
        return response;
    }

    private static List<String> extractVariableInCurlyParenthesis(String content) {
        // Todo: extract the values including the jdbcType value in e.g. #{effDt,jdbcType=TIMESTAMP}
        return extractParamVariable(content, "#{", "}");
    }

    private static void extractXmlVariablesInJavaClass(Map<String, List<Type>> mapOfMapperFileContent, File file, List<String> orderedMethodList) throws FileNotFoundException {
        Map<String, String> mapOfMethodDeclaration = new HashMap<>();
        InterfaceContentPrinter.execute(file.getAbsolutePath(), mapOfMethodDeclaration);
        for (String mtdName : mapOfMapperFileContent.keySet()) {
            String interfaceDeclaration = mapOfMethodDeclaration.get(mtdName);
            List<String> paramList = extractParamVariable(interfaceDeclaration, "@Param(\"", "\"");
            List<Type> typeList = mapOfMapperFileContent.get(mtdName);
            if (!paramList.isEmpty() && paramList.size() != typeList.size()) {
                throw new RuntimeException("Param list not match typelist");
            }
            for (int i = 0; i < paramList.size(); i++) {
                typeList.get(i).xmlVariable = paramList.get(i);
            }
        }
    }

    private static List<String> extractParamVariable(String line, String identifier, String endIdentifier) {

        String subString = line;
        List<String> paramList = new ArrayList<>();
        while (subString.contains(identifier)) {
            int start = subString.indexOf(identifier) + identifier.length();
            int end = subString.indexOf(endIdentifier, start);
            paramList.add(subString.substring(start, end));
            subString = subString.substring(end) + endIdentifier.length();
        }
        return paramList;
    }

    private static void iterateAllFiles(File file, Map<String, List<String>> map) { // key: name of file, value: location
        if (file.getName().equals(".DS_Store")) {
            return;
        }
        if (file.isDirectory()) {
            if (file.getAbsolutePath().contains("reserveval")) {
                System.out.println("here");
            }
            File[] files = file.listFiles();
            for (File innerFile : files) {
                iterateAllFiles(innerFile, map);
            }
        } else {
            if (map.containsKey(file.getName())) {
                map.get(removeFileExtension(file.getName(), ".java")).add(file.getAbsolutePath());
            } else {
                List<String> list = new ArrayList<>();
                list.add(file.getAbsolutePath());
                map.put(removeFileExtension(file.getName(), ".java"), list);
            }
        }
    }

    private static String removeFileExtension(String name, String extension) {
        int end = name.indexOf(extension);
        return name.substring(0, end);
    }

    private static Type extractFilePathsForMappers(File file, String basePath, String extension, String xmlBase) {
        Type type = new Type();
        String path = file.getAbsolutePath();
        type.javaFilePath = path;
        String pathForCorresXmlFile = xmlBase + "/" + extractFileName(path, extension, basePath) + ".xml";
        type.xmlFilePath = pathForCorresXmlFile;
        return type;
    }

    private static String extractFileName(String path, String extension, String base) {
        int start = path.indexOf(base + "/") + base.length() + 1;
        int end = path.indexOf(extension);
        return path.substring(start, end);
    }

    public static void executeToExtractMtdParamsUsingParser(String filePath, Map<String, List<Type>> map, List<String> methodList) {
        try {
            FileInputStream in = new FileInputStream(filePath);
            CompilationUnit cu = StaticJavaParser.parse(in);
            cu.accept(new MethodVisitor(map, methodList), null);
            System.out.println(map.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        private Map<String, List<Type>> mapOfMethodParams;
        private List<String> methodList;

        public MethodVisitor(Map<String, List<Type>> mapOfMethodParams, List<String> methodList) {
            this.mapOfMethodParams = mapOfMethodParams;
            this.methodList = methodList;
        }

        @Override
        public void visit(MethodDeclaration md, Void arg) {
            super.visit(md, arg);
//            System.out.println("Method Name: " + md.getName());
            mapOfMethodParams.put(md.getNameAsString(), new ArrayList<>());
            methodList.add(md.getNameAsString());

            List<Parameter> parameters = md.getParameters();
            if (!parameters.isEmpty()) {
                List<Type> typeList = mapOfMethodParams.get(md.getNameAsString());
//                System.out.print("Parameters: ");
                for (Parameter parameter : parameters) {
                    Type type = new Type();
                    type.type = parameter.getTypeAsString();
                    type.javaVariable = parameter.getNameAsString();
                    typeList.add(type);
//                    System.out.print(parameter.getType() + " " + parameter.getName() + ", ");
                }
//                System.out.println();
            }
        }
    }

}
