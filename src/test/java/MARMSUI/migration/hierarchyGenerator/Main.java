package MARMSUI.migration.hierarchyGenerator;

import MARMSUI.migration.hierarchyGenerator.model.MethodChain;
import MARMSUI.migration.hierarchyGenerator.util.CheckIfThereAreInterfacesImplemented;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    // 1. start with mapping class names to their respective file location and concurrently map their interfaces with implementation
    // 2. start off with mapping the methods of all the classes
    // 3. start off with file path of starting method
    // 4. list the method calls in the method and store it in a list
    // 5. for each method call in the current method, find that method be it inner methods or methods from field variables
    // 6. if the method is from the field name, then use map in part 2 to identify if it is an interface or implementation
    // 7. if it is an interface, then find the implementation of the interface, if cannot find then hierarchy stops there for that method call
    // 8. if it is an implementation, then find the method in the implementation class, and repeat the process from step 4
    public static void main(String[] args) throws IOException {
        Map<String, String> mapOfClassNameToFileLocation = new HashMap<>();
        Map<String, String> mapOfInterfaceToFileLocation = new HashMap<>();
        Map<String, String> mapOfInterfaceToImplementation = new HashMap<>();
        String baseFilePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui";
        LinkClassNameToFileLocation.execute(baseFilePath, mapOfClassNameToFileLocation, mapOfInterfaceToFileLocation);

//        linkClassImplToInterface(mapOfClassNameToFileLocation, mapOfInterfaceToFileLocation, mapOfInterfaceToImplementation);
        generateTemporaryLinksFromInterfaceToImpl(mapOfInterfaceToImplementation);
        for(String key : mapOfInterfaceToImplementation.keySet()) {
            System.out.println(key + ":" + mapOfInterfaceToImplementation.get(key));
        }
        // all the interfaces are now linked to their implementations
        // can continue to find the starting method and populate the hierarchy from there
        Map<String,Map<String,String>> mapOfClassNameToMapOfFieldVariableToType = new HashMap<>();
        String startingClass = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/QualificationServiceImpl.java";
        String startingMethod = "getDetailsFromServer";

        MethodChain chain = ExtractListOfMethodInCallingMethod.getMethodChain(startingClass, startingMethod, mapOfClassNameToFileLocation, mapOfInterfaceToImplementation);
        System.out.println(chain);
        // continue to create a method to loop through all the methods in the class and populate the hierarchy
        // use the MethodChain model to help create the chain
        // from the classes that are visited, populate the total methods declared in the class in a new map, in case it is called later in the method chain
        // if the method is called from a field variable, then visit the class of that variable and populate the methods in that class "IF NOT ALREADY ADDED"
        // initialise a map from here and use it to populate the map with the hierarchy
        // CONTINUE FROM HERE
        // must be able to segregate local and global methods (calling method from another class)
        //

        // allow the program to add in accept the class name and method name as input

        // the entered method name and class name will be found in the map and hierarchy will be generated frm there
    }

    private static void generateTemporaryLinksFromInterfaceToImpl(Map<String,String> mapOfInterfaceToClass) {
        String links = "CustomerUpdateAccountService:CustomerUpdateAccountServiceImpl\n" +
                "AdminFee:AdminFeeImpl\n" +
                "ReserveValService:ReserveValServiceImpl\n" +
                "TierHandlerService:TierHandlerServiceImpl\n" +
                "MenuService:MenuServiceImpl\n" +
                "QualificationService:QualificationServiceImpl\n" +
                "LSLServiceHandler:LSLServiceHandlerImpl\n" +
                "CustomerIDHandler:CustomerIDHandlerImpl\n" +
                "Collateral:CollateralImpl\n" +
                "AirAccrualService:AirAccrualServiceImpl\n" +
                "VoucherService:VoucherServiceImpl\n" +
                "ChangeEventListener:CustomEventListener";
        String[] linkArray = links.split("\n");
        for(String link : linkArray) {
            String[] linkSplit = link.split(":");
            mapOfInterfaceToClass.put(linkSplit[0],linkSplit[1]);
        }
    }

    private static void linkClassImplToInterface(Map<String, String> mapOfClassNameToFileLocation, Map<String, String> mapOfInterfaceToFileLocation, Map<String, String> mapOfInterfaceToImplementation) throws IOException {
        for (String cls : mapOfClassNameToFileLocation.keySet()) {
            if(cls.endsWith("Vo") || cls.endsWith("Key")) {
                continue;
            }
            List<String> interfaceList = CheckIfThereAreInterfacesImplemented.execute(mapOfClassNameToFileLocation.get(cls));
            if (interfaceList != null) {
                if(interfaceList.size() > 1) {
                    System.out.println("Multiple interfaces implemented in " + cls + " were " + interfaceList.toString());
                }
                // link the interface to the implementation
                if(mapOfInterfaceToFileLocation.containsKey(interfaceList.get(0))) {
                    if(mapOfInterfaceToImplementation.containsKey(interfaceList.get(0))) {
                        System.out.println("Duplicate implementation found for the interface " + interfaceList.get(0));
                    }
                    mapOfInterfaceToImplementation.put(interfaceList.get(0), cls);
                }
            }

        }
    }
}
