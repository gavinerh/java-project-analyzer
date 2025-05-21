package MARMSUI.migration.hierarchyGeneratorImproved;

import MARMSUI.migration.hierarchyGenerator.ExtractListOfMethodInCallingMethod;
import MARMSUI.migration.hierarchyGenerator.model.MethodChain;
import MARMSUI.migration.hierarchyGenerator.util.CheckIfThereAreInterfacesImplemented;
import MARMSUI.migration.hierarchyGenerator.util.SavingHierarchyInFile;
import MARMSUI.migration.hierarchyGeneratorImproved.util.ImportStatementExtractor;
import MARMSUI.migration.hierarchyGeneratorImproved.util.LinkClassNameToFileLocationImproved;
import com.github.javaparser.ParseProblemException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Todo: to change
        String startingMethod = "updateSelectiveDetails";
        // Todo: to change
        String startingFilePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service/impl/UpdateCustomerSelectiveServiceImpl.java";
        // from the starting file, get all the files that are required from the import statements
        String importPrefix = "import com.sg.sq.marmsui";
        String[] stopLine = new String[]{"public class", "public interface"};
        Set<String> fileSet = new HashSet<>();
        // Todo: to change
        String absoluteBasePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/";
        // Todo: to change
        String[] packageNotToSearchInService = new String[0];
//                new String[]{"accountSummary", "adminfee", "airredemption", "alteaservices", "common", "corporate", "customer", "customerservicing", "dataintegrity", "ecertrevalidation", "ecertservice", "eventlog", "ignoreupgradeonOAL", "milesadmin", "milesconversion", "mua", "nonairredemption", "promotion", "redemptionenquiry", "redemptionupgrade", "reference", "requestcertificate", "requestupgrade", "requestupgradeonOAL", "rewardVouchers", "sslinterface", "starmwservice", "upgradeonsq", "useraccess", "validation"};
        String servicePackagePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service";

        // extract all files to be inspected
        ImportStatementExtractor.executeAndExtractFilePaths(startingFilePath, importPrefix, stopLine, absoluteBasePath, fileSet, packageNotToSearchInService, servicePackagePath);

        Map<String, String> mapOfClassNameToFileLocation = new HashMap<>();
        Map<String, String> mapOfInterfaceToFileLocation = new HashMap<>();

        String[] classesToSkip = hardCodeClassesToSkip();

        LinkClassNameToFileLocationImproved.execute(fileSet, mapOfClassNameToFileLocation, mapOfInterfaceToFileLocation, classesToSkip);

        Map<String, String> mapOfInterfaceToImplementation = new HashMap<>();
        linkClassImplToInterface(mapOfClassNameToFileLocation, mapOfInterfaceToFileLocation, mapOfInterfaceToImplementation);

        MethodChain chain = ExtractListOfMethodInCallingMethod.getMethodChainImproved(startingFilePath, startingMethod, mapOfClassNameToFileLocation, mapOfInterfaceToImplementation);
        System.out.println(chain);
//        // Todo: to change
        SavingHierarchyInFile.saveHierarchyInFile(chain,"/Users/macuser/Desktop/hierarchy-generator/forcequal-map");

        System.out.println(mapOfInterfaceToImplementation);
    }


    private static String[] hardCodeClassesToSkip() {
        return new String[]{"/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service/impl/RedemptionUpgradeServiceImpl",
                "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service/impl/CustomerPinPwdServiceImpl.java",
                "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service/impl/PointsHandlerServiceImpl.java"};
    }

    private static void linkClassImplToInterface(Map<String, String> mapOfClassNameToFileLocation, Map<String, String> mapOfInterfaceToFileLocation, Map<String, String> mapOfInterfaceToImplementation) throws IOException {
        for (String cls : mapOfClassNameToFileLocation.keySet()) {
            if (cls.endsWith("Vo") || cls.endsWith("Key")) {
                continue;
            }
            List<String> interfaceList = null;
            try {
                interfaceList = CheckIfThereAreInterfacesImplemented.execute(mapOfClassNameToFileLocation.get(cls));
            } catch (ParseProblemException e) {
                interfaceList = fallback(mapOfClassNameToFileLocation.get(cls));
            }
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

    private static List<String> fallback(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Scanner scanner = new Scanner(fileInputStream);
        List<String> interfaceList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("implements")) {
                String[] split = line.split("implements");
                String[] interfaces = split[1].split(",");
                for (String interfaceName : interfaces) {
                    String name = interfaceName.trim();
                    if(name.substring(name.length()-1).equals("{")) {
                        name = name.substring(0, name.length()-1);
                    }
                    interfaceList.add(name.trim());
                }
            }
        }
        scanner.close();
        return interfaceList;
    }
}
