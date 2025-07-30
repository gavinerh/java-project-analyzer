package MARMSUI.migration.GenerateDependentFieldsForTest;

import MARMSUI.migration.GenerateDependentFieldsForTest.model.ClassDeclaration;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ArrangeConstructorOrder {
    public static void execute(List<ClassDeclaration> constr, Map<String, ClassDeclaration> classDeclarationMap, Map<String, ClassDeclaration> interfaceDeclarationMap) {
        for (ClassDeclaration classDeclaration : constr) {
            List<String> dependencies = classDeclaration.getConstructorTypeList();
            if (!dependencies.isEmpty()) {
                for (String dependency : dependencies) {
                    ClassDeclaration depClass = classDeclarationMap.get(dependency);
                    if (depClass == null) {
                        ClassDeclaration inter = interfaceDeclarationMap.get(dependency);
                        if( inter != null) {
                            depClass = classDeclarationMap.get(inter.getImplementationName());
                            if(depClass != null) {
                                classDeclaration.addHierarchyPosition(depClass);
                            }
                        }
                    } else {
                        // its a concrete class
                        classDeclaration.addHierarchyPosition(depClass);
                    }
                }
            }
        }
    }

    public static void order(List<ClassDeclaration> constructorHierarchy) {
        // This method can be used to order the constructor hierarchy if needed
        // For now, it just returns the input list as is
        for(ClassDeclaration classDeclaration : constructorHierarchy) {
            classDeclaration.getPosition();
        }
        constructorHierarchy.sort(Comparator.comparingInt(ClassDeclaration::getPosition));
    }
}
