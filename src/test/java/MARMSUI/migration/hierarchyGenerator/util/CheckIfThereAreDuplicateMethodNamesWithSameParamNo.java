package MARMSUI.migration.hierarchyGenerator.util;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class CheckIfThereAreDuplicateMethodNamesWithSameParamNo {
    private static Set<String> toIgnore;
//    private static Set<String> methodsToIgnore = new HashSet<>();

    public static void main(String[] args) {
        setUpIgnoredFiles();
        File baseFile = new File("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui");
        getFileObject(baseFile);
    }

    private static void setUpIgnoredFiles() {
        toIgnore = new HashSet<>();
        toIgnore.add("RequestValidator.java");
    }

    private static void getFileObject(File currentFile) {
        if (toIgnore.contains(currentFile.getName())) {
            return;
        }
        if (!currentFile.isDirectory()) {
//            System.out.println("Now entering: " + currentFile.getAbsolutePath());
//            methodsToIgnore.clear();
            execute(currentFile.getAbsolutePath());
        } else {
            // is a directory
            if(currentFile.getName().equals("vo")){
                return;
            }
            File[] fileList = currentFile.listFiles();
            for (File file : fileList) {
                getFileObject(file);
            }
        }

    }

    public static void execute(String filePath) {

//        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/QualificationServiceImpl.java";
        try {
            FileInputStream in = new FileInputStream(filePath);
            CompilationUnit cu = StaticJavaParser.parse(in);

            Map<String,Integer> methodDeclarationList = new HashMap<>();
            MethodVisitor methodVisitor = new MethodVisitor(methodDeclarationList);
            methodVisitor.visit(cu, null);

//            System.out.println(methodDeclarationList.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        private Map<String,Integer> methodDeclarationList;

        public MethodVisitor(Map<String,Integer> methodDeclarationList) {
            this.methodDeclarationList = methodDeclarationList;
        }

        @Override
        public void visit(MethodDeclaration md, Void arg) {
            super.visit(md, arg);
            if(methodDeclarationList.containsKey(md.getNameAsString() + md.getParameters().size())) {
                System.out.println("Duplicate method name: " + md.getNameAsString() + " with same number of parameters: " + md.getParameters().size());
            }
//            methodDeclarationList.put(md.getNameAsString() + md.getParameters().size(), md.getParameters().size());
        }
    }
}
