package MARMSUI.methodcopier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/codeupdate/forceQualify_and_called_methods.java";
        List<MethodSpec> methodSpecList = new ArrayList<>();
        extractMethodSpecs(fileName,methodSpecList);
        PrintListOfMethodUsed.execute(methodSpecList,"/Users/macuser/Documents/updated-lsl-app/marms/MARMS/Source Code/Business Components/EJB/QualificationService/com/singaporeair/marms/abacus/business/customer/tier/QualificationServiceBean.java");
    }

    private static void extractMethodSpecs(String filename, List<MethodSpec> methodSpecList) throws IOException {
        // hard coding for now
        ExtractMethodSpecFrmCopilotOutput.execute(filename,methodSpecList);
    }
}
