package MARMSUI.migration.mapAllMethodsInProj.util;

import MARMSUI.migration.mapAllMethodsInProj.model.ClassDeclaration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static MARMSUI.migration.mapAllMethodsInProj.Main.rootPath;

public class ConnectInterfacesToImplClass {

    static String[] whitelistedInterfaces = new String[]{"Serializable", "Cloneable", "Comparable", "java.io.Serializable", "HandlerInterceptor", "WebMvcConfigurer"};

    public static void iterateMapAndConnectInterfacesToImpl(Map<String, ClassDeclaration> classDeclarationMap) {
        for (String key : classDeclarationMap.keySet()) {
            ClassDeclaration classDeclaration = classDeclarationMap.get(key);
            List<String> implementedInterfacesModified = new ArrayList<>();
            if (classDeclaration == null) {
                System.out.println("Class declaration is null for key: " + key);
                continue;
            }
            if (!classDeclaration.isInterface() && !classDeclaration.getImplementedInterfaces().isEmpty()) {
                List<String> importList = classDeclaration.getImportList();
                for (String interfaceName : classDeclaration.getImplementedInterfaces()) {
                    if (List.of(whitelistedInterfaces).contains(interfaceName)) {
                        implementedInterfacesModified.add(interfaceName);
                        continue;
                    }
                    try {
                        String fullPath = extractInterfaceFullPathFromImplImports(importList, interfaceName);
                        if (fullPath == null) {
                            fullPath = checkCurrentDirectoryForFile(key, interfaceName);
                        }
                        if (fullPath == null) {
                            throw new RuntimeException("Could not locate interface: " + interfaceName);
                        }
                        implementedInterfacesModified.add(fullPath);
                        ClassDeclaration interfaceClassDeclaration = classDeclarationMap.get(fullPath);
                        if (interfaceClassDeclaration == null) {
                            throw new RuntimeException("Could not find interface class declaration for: " + fullPath + " implemented in class: " + classDeclaration.getName());
                        }
                        interfaceClassDeclaration.setConcreteClassPath(key);
                    } catch (Exception e) {
                        throw e;
                    }
                }
                classDeclaration.setImplementedInterfaces(implementedInterfacesModified);
            }
        }
    }

    private static String extractInterfaceFullPathFromImplImports(List<String> importList, String interfaceName) {
        List<String> possibleImports = new ArrayList<>();
        String fullInterfacePath = null;
        boolean isFound = false;
        for (String importStatement : importList) {
            if (importStatement.contains(interfaceName)) {
                isFound = true;
                fullInterfacePath = importStatement;
                break;
            }
            if (importStatement.endsWith(".*")) {
                possibleImports.add(importStatement.substring(0, importStatement.length() - 2) + "." + interfaceName);
            }
        }
        if (!isFound) {
            fullInterfacePath = locateFileInPossibleImports(possibleImports);
        }
        return fullInterfacePath;
    }

    private static String locateFileInPossibleImports(List<String> possibleImports) {
        for (String importPath : possibleImports) {
            String filePath = rootPath + File.separator + importPath.replace(".", File.separator) + ".java";
            File file = new File(filePath);
            if (file.exists()) {
                return filePath.replace(rootPath + File.separator, "").replace(File.separator, ".").replace(".java", "");
            }
        }
        return null;
    }

    private static String checkCurrentDirectoryForFile(String implClassPath, String interfaceName) {
        String implDir = implClassPath.contains(".") ? implClassPath.substring(0, implClassPath.lastIndexOf(".")) : implClassPath;
        String possiblePath = rootPath + File.separator + implDir.replace(".", File.separator) + File.separator + interfaceName + ".java";
        File file = new File(possiblePath);
        if (file.exists()) {
            return implDir + "." + interfaceName;
        }
        return null;
    }

}
