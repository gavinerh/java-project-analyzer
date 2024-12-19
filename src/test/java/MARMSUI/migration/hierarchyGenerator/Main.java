package MARMSUI.migration.hierarchyGenerator;

import MARMSUI.migration.hierarchyGenerator.model.MethodChain;
import MARMSUI.migration.hierarchyGenerator.util.CheckIfThereAreInterfacesImplemented;
import MARMSUI.migration.hierarchyGenerator.util.SavingHierarchyInFile;

import java.io.IOException;
import java.util.*;

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
        for (String key : mapOfInterfaceToImplementation.keySet()) {
            System.out.println(key + ":" + mapOfInterfaceToImplementation.get(key));
        }
        // all the interfaces are now linked to their implementations
        // can continue to find the starting method and populate the hierarchy from there
        Map<String, Map<String, String>> mapOfClassNameToMapOfFieldVariableToType = new HashMap<>();
        String startingClass = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/QualificationServiceImpl.java";
        String startingMethod = "forceQualifyByOnline";
        String cName = "QualificationServiceImpl";


        MethodChain chain = ExtractListOfMethodInCallingMethod.getMethodChain(startingClass, startingMethod, mapOfClassNameToFileLocation, mapOfInterfaceToImplementation);
        System.out.println(chain);
        SavingHierarchyInFile.saveHierarchyInFile(chain,"/Users/macuser/Desktop/hierarchy-generator/forcequal-map");
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

        //  Todo: to restore extractHierarchyGivenEndMethodDetails(chain);
    }

    private static void extractHierarchyGivenEndMethodDetails(MethodChain chain) {
        String className = "";
        String methodName = "";
        Scanner scanner = new Scanner(System.in);
        Stack<String> hierarchyList = new Stack<>();
        while (true) {
            className = getUserInput("Enter class Name: ", scanner);
            methodName = getUserInput("Enter method Name: ", scanner);
            if (className == null || methodName == null) {
                break;
            }
            MethodChain dupChain = new MethodChain(null, null);
            // Todo: need to consider multiple parents calling this method u enter into program to search
//            extractHierarchyFromChain(className, methodName, chain, hierarchyList);
            extractHierarchyFromChainModified(className, methodName, chain, dupChain);
            System.out.println(dupChain);
            int count = 1;
//            for(String val : hierarchyList) {
//                System.out.println(count + ": " + val);
//                count++;
//            }
//            hierarchyList.clear();
        }
        scanner.close();
    }



    private static boolean extractHierarchyFromChainModified(String className, String methodName, MethodChain chain, MethodChain newChain) {
        if (newChain.getClassName() == null && newChain.getMethodName() == null) {
            // initialise the dup chain
            newChain.setClassName(chain.getClassName());
            newChain.setMethodName(chain.getMethodName());
        }
        boolean flag = false;
        if (chain.getClassName().equalsIgnoreCase(className) && chain.getMethodName().equalsIgnoreCase(methodName)) {
//            newChain.addChildMethodChain(chain);
            return true;
        } else {
            for (MethodChain child : chain.getChildMethodChains()) {
                MethodChain dupChild = new MethodChain(child.getMethodName(), child.getClassName());
                newChain.addChildMethodChain(dupChild);
                boolean val = extractHierarchyFromChainModified(className, methodName, child, dupChild);
                if(val) {
                    flag = true;
                }
                if (!val) {
                    newChain.removeLastChild();
                }
            } // after adding flag to preserve the state that the method was found, need to add a condition to remove the last child if the method was not found
            // Todo: Test with more complex data
            return flag;
        }
    }

    // Todo: modify this method to allow for multiple parents calling the same child method
    // Todo: work on the MethodChain class and create method to remove the child that is not relevant
    private static String extractHierarchyFromChain(String className, String methodName, MethodChain chain, Stack<String> hierarchyList) {
        if (chain.getClassName().equalsIgnoreCase(className) && chain.getMethodName().equalsIgnoreCase(methodName)) {
            hierarchyList.add(chain.getClassName() + "." + chain.getMethodName());
            return chain.getClassName() + "." + chain.getMethodName();
        } else {
            for (MethodChain child : chain.getChildMethodChains()) {
                hierarchyList.add(chain.getClassName() + "." + chain.getMethodName());
                String val = extractHierarchyFromChain(className, methodName, child, hierarchyList);
                if (val == null) {
                    hierarchyList.pop();
                } else {
                    return val;
                }
            }
            return null;
        }
    }

    private static String getUserInput(String toPrintToConsole, Scanner scanner) {
        System.out.print(toPrintToConsole);
        String val = scanner.nextLine();
        if (val.equals("-")) {
            return null;
        }
        return val;
    }

    private static void generateTemporaryLinksFromInterfaceToImpl(Map<String, String> mapOfInterfaceToClass) {
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
        for (String link : linkArray) {
            String[] linkSplit = link.split(":");
            mapOfInterfaceToClass.put(linkSplit[0], linkSplit[1]);
        }
    }

    private static void linkClassImplToInterface(Map<String, String> mapOfClassNameToFileLocation, Map<String, String> mapOfInterfaceToFileLocation, Map<String, String> mapOfInterfaceToImplementation) throws IOException {
        for (String cls : mapOfClassNameToFileLocation.keySet()) {
            if (cls.endsWith("Vo") || cls.endsWith("Key")) {
                continue;
            }
            List<String> interfaceList = CheckIfThereAreInterfacesImplemented.execute(mapOfClassNameToFileLocation.get(cls));
            if (interfaceList != null) {
                if (interfaceList.size() > 1) {
                    System.out.println("Multiple interfaces implemented in " + cls + " were " + interfaceList.toString());
                }
                // link the interface to the implementation
                if (mapOfInterfaceToFileLocation.containsKey(interfaceList.get(0))) {
                    if (mapOfInterfaceToImplementation.containsKey(interfaceList.get(0))) {
                        System.out.println("Duplicate implementation found for the interface " + interfaceList.get(0));
                    }
                    mapOfInterfaceToImplementation.put(interfaceList.get(0), cls);
                }
            }

        }
    }
}
