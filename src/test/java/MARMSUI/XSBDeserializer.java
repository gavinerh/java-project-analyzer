//package MARMSUI;
//
//
//import java.io.File;
//
//public class XSBDeserializer {
//    public static void main(String[] args) {
//        try {
//            // Path to the .xsb file
//            String xsbFilePath = "path/to/your/schema.xsb";
//
//            // Create a SchemaBindingFactory
//            SchemaBindingFactory factory = SchemaBindingFactory.newInstance();
//
//            // Load the .xsb file
//            SchemaBindingLoader loader = factory.createSchemaBindingLoader();
//            SchemaBinding schemaBinding = loader.load(new File(xsbFilePath));
//
//            // Use the SchemaBinding object as needed
//            System.out.println("Schema Namespace: " + schemaBinding.getNamespaceURI());
//            // Further processing of the schemaBinding object...
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}