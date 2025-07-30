package MARMSUI.methodcopier;

import MARMSUI.methodcopier.model.MethodDetails;
import MARMSUI.methodcopier.model.MethodSignature;
import MARMSUI.methodcopier.util.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRunSecond {
    public static void main(String[] args) throws IOException {
        // change
        String starterMethod = "getTierSummary-4";
        // change
        String sourceFile = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/util/TierHandlerUtilTemp.java";
        // change
        String destinationFile = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/TierHandlerUtilTemp.java";
        String[] methods = starterMethod.split("\n");
        for(String mtd : methods) {
            execute(sourceFile,mtd,destinationFile);
        }
    }

    private static void execute(String sourceFile, String starterMethod, String destinationFile) {
        Map<String,MethodDetails> mapOfAllMtds = new HashMap<>();
        // from source file, map all the method calls and returns map of method calls
        MethodListExtractor.printListOfMethods(Path.of(sourceFile),mapOfAllMtds);

        // search for the starter method from result returned previously
        MethodDetails startMtdDetails = mapOfAllMtds.get(starterMethod);
        if(startMtdDetails == null) {
            throw new RuntimeException("Starter Method not found - " + starterMethod);
        }
        // iterate from starter method and retrieve a list of methods to copy over
        Map<String,MethodDetails> methodsToCopy = new HashMap<>();
        RecursiveSearch.execute(methodsToCopy,startMtdDetails);

        // search in destination file and retrieve any existing methods
        List<MethodDetails> destinationMethods = MethodExtractorOfFileToBeAppended.execute(destinationFile);
        // compare and remove methods from list of methods to copy over
        // that are alrdy present in the destination file
        for(MethodDetails methodPresent : destinationMethods) {
            String key = MethodListExtractor.getKey(methodPresent);
            methodsToCopy.remove(key);
        }
        // copy the methods over
        List<MethodSignature> methodsToAppend = ConvertMethodDetToSig.convert(methodsToCopy);
        MethodBodyExtractor.execute(sourceFile,destinationFile,methodsToAppend);
    }

}
