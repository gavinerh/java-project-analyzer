import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

public class ProjectFileScanner {
    private static String filePathDirectory = "/Users/macuser/eclipse-workspace/lsl-batch/src/main/java/com/sg/sq/lsl/batch/service/impl";

    public static void main(String[] args) {
//        File file = new File("/Users/macuser/Documents/projectAnalysis.txt");
//        String projectAnalysisFilePath = "/Users/macuser/Documents/projectAnalysis.txt";
        String methodListingFilePath = "/Users/macuser/Documents/methodListing.txt";
        String filepathForDataMtd = "/Users/macuser/Documents/methodListingforDataMtd.txt";
//        generateUnconvertedMethods(projectAnalysisFilePath);
//        String filePathForCommentedLines = "/Users/macuser/Documents/totalCommentedLines.txt";
//        findAllCommentedLines(filePathForCommentedLines);
//        try {
//            writeToFile(null, file.getPath());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        generateAllMethodsAndFields(methodListingFilePath, filepathForDataMtd);


    }

    private static void generateAllMethodsAndFields(String methodListingFilePath, String filepathForDataMtd){
        Map<String, String> mapOfAllMethodNames = new HashMap<>(); // key is the method signature
        File file = new File(filePathDirectory);
        FileInputStream fileInputStream = null;
        Scanner sc = null;
        File[] files = file.listFiles();
        for(File f : files){
            try{
                fileInputStream = new FileInputStream(f.getPath());
//                if(!f.getName().contains("QualificationServiceImpl")) continue;
                sc = new Scanner(fileInputStream, "UTF-8");
                int count = 0;
                String potentialMethod = "";
                boolean isMethodSignatureOngoing = false;
                boolean isLineCommentStart = false;
                while(sc.hasNextLine()){
                    count++;
//                    if(count == 2794 || count == 2884 || count == 3299){
//                        System.out.println("reached checkpoint");
//                    }
                    String line = sc.nextLine();
                    if (line.trim().startsWith("//")) continue;
                    int commentStartIndex = 0;
                    String linesNotCommented = "";
                    if(line.contains("//")){
                        commentStartIndex = line.indexOf("//");
                        linesNotCommented = line.substring(0,commentStartIndex).trim();
                    } else {
                        linesNotCommented = line.trim();
                    }
                    if(line.contains("/*")){
                        isLineCommentStart = true;
                        commentStartIndex = linesNotCommented.indexOf("/*");
                        if(linesNotCommented.length() > 0){
                            linesNotCommented = linesNotCommented.substring(0,commentStartIndex);
                        }
                    }
                    if (line.contains("*/")){
                        isLineCommentStart = false;
                        if(linesNotCommented.length() > 0){
                            int commentEndIndex = linesNotCommented.indexOf("*/")+2;
                            linesNotCommented = linesNotCommented.substring(commentEndIndex,linesNotCommented.length());
                        }
                    }
                    if(isLineCommentStart) continue;
                    if(linesNotCommented.contains("public") || linesNotCommented.contains("private") || linesNotCommented.contains("protected")){
                        if(linesNotCommented.contains("(")) isMethodSignatureOngoing = true;
                    }
                    if(isMethodSignatureOngoing){
                        potentialMethod += " " + linesNotCommented;
                    }
//                    if(potentialMethod.contains("Logger") || potentialMethod.contains("logger") || potentialMethod.contains("@Autowired") || potentialMethod.contains("getMapper")){
//                        potentialMethod = "";
//                        continue;
//                    }
                    if(linesNotCommented.contains(")") && isMethodSignatureOngoing) {
                        int endIndexOfClosingBracket = potentialMethod.indexOf(")");
                        potentialMethod = potentialMethod.substring(0,endIndexOfClosingBracket+1);
                        isMethodSignatureOngoing = false;
                        mapOfAllMethodNames.put(generateCleansedMethodName(potentialMethod, f.getName()), String.valueOf(count));
                        potentialMethod = "";
                    }

                }
                String[] arrOfUnwantedString = new String[]{"m_Logger", "logger", "getMapper", "MarmsTimer", "@Autowired"};
                mapOfAllMethodNames = removeUnwantedKey(arrOfUnwantedString,mapOfAllMethodNames);
                saveToFile(methodListingFilePath, filepathForDataMtd, mapOfAllMethodNames);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Map<String, String> removeUnwantedKey(String[] arrOfUnwantedString, Map<String, String> map){
        Map<String, String> newMap = new HashMap<>();
        for(String key : map.keySet()){
            boolean toRemove = false;
            for(String unwantedStr : arrOfUnwantedString){
                if(key.contains(unwantedStr)){
                    toRemove = true;
                    break;
                }
            }
            if(!toRemove){
                newMap.put(key, map.get(key));
            }
        }
        return newMap;
    }

    private static void saveToFile(String filenameForImplMtd, String filenameForDataMtd, Map<String, String> map){
        File file = new File(filenameForImplMtd);
        file.delete();
        FileOutputStream fos = null;
        File dataFile = new File(filenameForDataMtd);
        dataFile.delete();
        FileOutputStream dataFos = null;
        try{
            dataFos = new FileOutputStream(filenameForDataMtd, true);
            fos = new FileOutputStream(filenameForImplMtd, true);
            for (String nameOfMethod : map.keySet()){
                String toPrint = String.format("%s---%s\n", nameOfMethod, map.get(nameOfMethod));
                if(nameOfMethod.contains("Data.")){
                    dataFos.write(toPrint.getBytes());
                }else{
                    fos.write(toPrint.getBytes());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateCleansedMethodName(String methodSignature, String filename){
        return String.format("%s.[%s]", filename, methodSignature.trim());
    }
    private static void findAllCommentedLines(String filename){
        File file = new File(filePathDirectory);
        FileInputStream fileInputStream = null;
        Scanner sc = null;
        File[] files = file.listFiles();
        Map<String,List<String>> mapWithFilenameKey = new HashMap<>();
        for(File f : files){
            List<String> listOfCommentedLineNumber = new ArrayList<>();
            try{
                fileInputStream = new FileInputStream(f.getPath());
                sc = new Scanner(fileInputStream, "UTF-8");
                int count = 0;
                while (sc.hasNextLine()){
                    count++;
                    String line = sc.nextLine();
//                    if(line.startsWith("//")){
//                        listOfCommentedLineNumber.add(String.valueOf(count));
//                    }
                    if(line.contains("AlteaSync.properties")){
                        listOfCommentedLineNumber.add(String.valueOf(count));
                    }
                }
                mapWithFilenameKey.put(f.getName(),listOfCommentedLineNumber);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        saveMapToFile(filename, mapWithFilenameKey);
    }

    private static void saveMapToFile(String filename, Map<String,List<String>> map){
        File file = new File(filename);
        file.delete();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename, true);
            for(String nameOfFile : map.keySet()){
                for(String count : map.get(nameOfFile)){
                    String toPrint = String.format("Name: %s | Line number : %s\n", nameOfFile, count);
                    fos.write(toPrint.getBytes());
                }
            }
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listMethodsThatContainSetQuery(String marmsFilePath, String lslFilePath){
        Scanner sc = null;
        Map<String, Boolean> mapOfMarmsMtd = new HashMap<>();
        List<String> listOfLslMtd = new ArrayList<>();

        try{
            int countCommentSymbol = 0;
            FileInputStream marmsInput = new FileInputStream(marmsFilePath);
            sc = new Scanner(marmsInput);
            boolean methodStarts = false;
            while(sc.hasNextLine()){
                // find all methods and store them in map
                String line = sc.nextLine();
                String methodDeclaration = "";
                if(line.contains("public") || line.contains("private") || line.contains("protected")){
                    while(true){
                        // get the method declaration
                        methodDeclaration += line;
                        if(line.contains("{")){
                            mapOfMarmsMtd.put(methodDeclaration,false);
                            break;
                        }
                        line = sc.nextLine();
                    }
                }else{
                    continue;
                }
                // out of declaration but still inside method
                while(true){
                    line = sc.nextLine();
                    if(line.trim().startsWith("//")){
                        continue;
                    }
                    if (line.trim().startsWith("/*")){
                        countCommentSymbol = 1;
                    }
                    if(line.trim().contains("*/")){
                        countCommentSymbol = 0;
                    }
                    if(countCommentSymbol > 0){
                        continue;
                    }

                    // check for the setQuery thing
                    if(line.contains("load") || line.contains("base.setQuery") || line.contains("setReturnQuery") || line.contains("insert") || line.contains("update")){
                        mapOfMarmsMtd.put(methodDeclaration,true);
                        methodDeclaration = "";
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generateUnconvertedMethods(String filename){
        Map<String, Integer> mapOfUnaccountedMtd = returnListOfUnaccountedMtd();
        File file = new File(filePathDirectory);
        FileInputStream fileInputStream = null;
        Scanner sc = null;
        File[] files = file.listFiles();
        printToConsole("Number of file: " + files.length);
        List<MethodObject> map = new ArrayList<>();
        List<MethodObject> listOfCompletedMethods = new ArrayList<>();
        int totalLinesParsed = 0;
        int totalMethodsVisited = 0;
        List<String> listOfFiles = new ArrayList<>();
        Map<String, Integer> mapOfAnalysedMethods = new HashMap<>();
        List<String> listOfAnalysedMethods = new ArrayList<>();
        List<String> listOfVisitedMethods = new ArrayList<>();
        for (File f : files) {
            listOfFiles.add(f.getName());
//            if(f.getName().contains("Data")){
//                continue;
//            }
            boolean isPreviousAlsoCommented = false;
            // only store methods that has commented out lines
//            if (f.getName().equalsIgnoreCase("promotionawardingdata.java")) {
                try {
                    fileInputStream = new FileInputStream(f.getPath());
                    sc = new Scanner(fileInputStream, "UTF-8");
                    String methodName = "";
                    int count = 0;
                    double methodStartLine = 0.0;
                    double numberOfCommentedLines = 0.0;
                    boolean stillInsideMethod = false;
                    String completeMethodName = "";
                    boolean isFullCommentInitiated = false;
                    int bracketCount = 0;
                    MethodObject methodObject = new MethodObject();

                    while (sc.hasNextLine()) {
                        count++;
                        String line = sc.nextLine();
                        if(line.contains("/*")){
                            isFullCommentInitiated = true;
                        }
                        if (line.contains("*/")) {
                            isFullCommentInitiated = false;
                            continue;
                        }
                        if(isFullCommentInitiated){
                            continue;
                        }
                        if(line.contains("@")) continue;
                        if (line.startsWith("//")) {
                            numberOfCommentedLines++;
                            methodStartLine++;
                            continue;
                        }
                        if (line.contains("{")) {
                            if(line.contains("//")){
                                bracketCount += numBracketFoundAfterComment(line,"{");
                            }else{
                                bracketCount++;
                            }
                            if (bracketCount == 2) {
                                stillInsideMethod = true;
                            }
                        }
                        if ((line.contains("protected") || line.contains("private") || line.contains("public")) && line.contains("(") && !line.contains(";")) {
                            String[] methodDeclarationArr = line.trim().split(" ");
                            totalMethodsVisited++;
                            int end = 0;
                            try{
                                methodName = methodDeclarationArr[2];
                                end = methodName.indexOf("(");
                                methodName = methodName.substring(0, end).trim();
                            }catch (Exception e){
                                // error in constructor
                                printToConsole("FileName: " + f.getName() + "--" + "line Number : " + count);
                                methodName = methodDeclarationArr[1];
                                end = methodName.indexOf("(");
                                if(end != -1){
                                    methodName = methodDeclarationArr[1].substring(0,end).trim();
                                }
                            }

                            completeMethodName = f.getName() + "-" + methodName + "-" + count;
                            listOfVisitedMethods.add(completeMethodName);
                            methodObject.startLine = count;
                            methodObject.completedMethodName = completeMethodName;
                        }
                        if (!stillInsideMethod) continue;

                        if (line.contains("}")) {

                            bracketCount--;
                            if (bracketCount == 1) {
                                if(mapOfUnaccountedMtd.containsKey(methodObject.completedMethodName)){
                                    System.out.println("reached checkpoint");
                                }
                                try {
                                    methodObject.methodLength = count - methodObject.startLine;
                                    methodObject.linesOfCommentedCode = numberOfCommentedLines;
                                    mapOfAnalysedMethods.put(methodObject.completedMethodName, 1);

                                    mapOfAnalysedMethods.put(methodObject.completedMethodName, 1);

                                    if(methodObject.completedMethodName == null){
                                        System.out.println("null gen");
                                    }
                                    listOfAnalysedMethods.add(methodObject.completedMethodName);
                                    if ((numberOfCommentedLines / methodStartLine) > 0.8) {
                                        map.add(methodObject);
                                    }else{
                                        listOfCompletedMethods.add(methodObject);
                                    }
                                } catch (Exception e) {
                                    printToConsole(f.getName() + "--" + count);
                                    throw new RuntimeException();
                                }
                                completeMethodName = "";
                                numberOfCommentedLines = 0;
                                stillInsideMethod = false;
                                methodStartLine = 0;
                                methodObject = new MethodObject();
                                continue;
                            }
                        }
                        methodStartLine++;
                    }
                    totalLinesParsed += count;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
//            }
        }
        printToConsole("\n\n");
        deleteFile(filename);
        int totalLinesToConvert = 0;
        appendtoFile(filename,"Methods unconverted", map);
        for (MethodObject key : map) {
//            System.out.print(String.format("Name: %s | Length of method : %s\n", key.completedMethodName, key.methodLength));
            totalLinesToConvert += key.methodLength;
        }
        appendtoFile(filename,"Methods completed conversion", listOfCompletedMethods);
//        for(MethodObject object : listOfCompletedMethods){
//            String s = String.format("Name: %s | Length of method: %s | Number of commented lines: %s\n", object.completedMethodName,object.methodLength,object.linesOfCommentedCode);
//            System.out.print(s);
//        }
        printToConsole("");
        List<MethodObject> methodObjectList = new ArrayList<>();
        appendtoFile(filename,String.format("Total methods visited : " + totalMethodsVisited), methodObjectList);
//        System.out.println(String.format("Total methods visited : " + totalMethodsVisited));
        appendtoFile(filename, "Number of unconverted methods: " + map.size(), methodObjectList);
//        System.out.println("Number of unconverted methods: " + map.size());
        appendtoFile(filename,"Number of converted methods: " + listOfCompletedMethods.size(),methodObjectList);
//        System.out.println("Number of converted methods: " + listOfCompletedMethods.size());
        appendtoFile(filename,String.format("Total Lines checked: %s", totalLinesParsed),methodObjectList);
//        System.out.println(String.format("Total Lines checked: %s", totalLinesParsed));
        appendtoFile(filename,String.format("Total number of lines to convert: %s", totalLinesToConvert),methodObjectList);
//        System.out.println(String.format("Total number of lines to convert: %s", totalLinesToConvert));

        // check the unaccounted methods
//        if(listOfAnalysedMethods.size() != mapOfAnalysedMethods.size()){
//            System.out.println("Size does not match | Visited Methods " + listOfVisitedMethods.size() + " | Analysed methods: " + mapOfAnalysedMethods.size());
//            for(int i=0; i<Math.max(listOfAnalysedMethods.size(),mapOfAnalysedMethods.size()); i++){
//                String name = listOfAnalysedMethods.get(i);
//                if(!mapOfAnalysedMethods.containsKey(name)){
//                    System.out.println(name);
//                }
//            }
//        }
        // put the listofanalysedmtd in map
        System.out.println("printing duplicate j=keys===========");
        Map<String, Integer> test = new HashMap<>();
        int c = 0;
        for(String name : listOfAnalysedMethods){
            if(test.containsKey(name)){
                System.out.println(name);
                c++;
            }else{
                test.put(name, 1);
            }
        }
        System.out.println(c);

    }

    private static Map<String, Integer> returnListOfUnaccountedMtd(){
        String s = "AccrualValidatorImpl.java-determineCreditClsPaxRedemTag-99\n" +
                "AccrualValidatorImpl.java-checkCutOverForSINKUL-239\n" +
                "AccrualValidatorImpl.java-determineCreditCls-269\n" +
                "AccrualValidatorImpl.java-determineCreditClassBySellingCls-337\n" +
                "AccrualValidatorImpl.java-getRouteExclDetails-391\n" +
                "AccrualValidatorImpl.java-setAwardFlg-443\n" +
                "AccrualValidatorImpl.java-updateExclFlgs-488\n" +
                "AccrualValidatorImpl.java-checkExclDetails-511\n" +
                "AccrualValidatorImpl.java-getTravelCls-532\n" +
                "AccrualValidatorImpl.java-getOFFPTravelCls-566\n" +
                "AccrualValidatorImpl.java-setFlightExclusionFlg-601\n" +
                "AccrualValidatorImpl.java-chkFltExclLst-658\n" +
                "AccrualValidatorImpl.java-checkDayOfWeek-695\n" +
                "AccrualValidatorImpl.java-setResidencyExclusionFlg-707\n" +
                "AccrualValidatorImpl.java-getRegionCountry-794\n" +
                "AccrualValidatorImpl.java-check3Star-814\n" +
                "AccrualValidatorImpl.java-getPrtForMI-821\n" +
                "AccrualValidatorImpl.java-checkAutoRejNameMisMatch-840\n" +
                "AccrualValidatorImpl.java-checkNameMatch-865\n" +
                "AccrualValidatorImpl.java-checkNameMatchNew-930\n" +
                "AccrualValidatorImpl.java-performDupAccrlNameChk-998\n" +
                "AccrualValidatorImpl.java-performNameCheckLogicNew-1070\n" +
                "AccrualValidatorImpl.java-checkNameContains-1187\n" +
                "AccrualValidatorImpl.java-levenshteinCheck-1328\n" +
                "AccrualValidatorImpl.java-generateName-1346\n" +
                "AccrualValidatorImpl.java-determinePPSElig-1355\n" +
                "AccrualValidatorImpl.java-checkPEYCutOvr-1382\n" +
                "AccrualValidatorImpl.java-getStarPartnerFlag-1403\n" +
                "AccrualValidatorImpl.java-checkNonAccruableDuplicate-1427\n" +
                "AccrualValidatorImpl.java-getPrt-1450\n" +
                "AccrualValidatorImpl.java-getIATAPrt-1469\n" +
                "AccrualValidatorImpl.java-getCodeShareCombi-1496\n" +
                "AccrualValidatorImpl.java-checkAirDuplicate-1520\n" +
                "LSLServiceHandler.java-invokeLslApiHeader-43\n" +
                "VoucherInfoServiceImpl.java-issuePpsVoucher-84\n" +
                "VoucherInfoServiceImpl.java-getErrDescFromDB-407\n" +
                "VoucherInfoServiceImpl.java-createEventLog-429\n" +
                "VoucherInfoServiceImpl.java-processErrorForDgtlVchr-625\n" +
                "VoucherInfoServiceImpl.java-getErrorReferenceDesc-651\n" +
                "VoucherInfoServiceImpl.java-getDigitalVoucherDtls-666\n" +
                "PromotionAwardingImpl.java-reversePromotionAwarding-536\n" +
                "PromotionAwardingImpl.java-reverseEllitePPS-759\n" +
                "PromotionAwardingImpl.java-updateQualificationBucket-788\n" +
                "PromotionAwardingImpl.java-getBatchId-808\n" +
                "PromotionAwardingImpl.java-updatePoints-812\n" +
                "PointsHandlerImpl.java-creditBktsMilesTransfer-1267\n" +
                "PointsHandlerImpl.java-deductFromBuckets-1469\n" +
                "AlteaSyncManagerImpl.java-sendProfileSyncMessage-86\n" +
                "AlteaSyncManagerImpl.java-getSupplementaryCustomer-907\n" +
                "AlteaSyncManagerImpl.java-getAPUPrefixText-932\n" +
                "AlteaSyncManagerImpl.java-getSyncStatus-952\n" +
                "AlteaSyncManagerImpl.java-sendRefreshProfileSyncMessage-967\n" +
                "AlteaSyncManagerImpl.java-getPrincipalCustomer-1044\n" +
                "AlteaSyncManagerImpl.java-sendSupplProfileSyncMsg-1073\n" +
                "AlteaSyncManagerImpl.java-profileASyncMW-1351\n" +
                "AlteaSyncManagerImpl.java-handleError-1407\n" +
                "AlteaSyncManagerImpl.java-trackProfileSyncTrans-1422\n" +
                "AirAccrualImpl.java-createRejTxn-2656\n" +
                "AirAccrualImpl.java-createNonAccruableTrans-2817\n" +
                "AirAccrualImpl.java-createOrUpdatePndgAcct-2857\n" +
                "AirAccrualImpl.java-createNameUnMatchRec-2908\n" +
                "AirAccrualImpl.java-updateNonTransLastActivityDt-2927\n" +
                "AirAccrualImpl.java-updateAcctStatus-2960\n" +
                "AirAccrualImpl.java-nonAccrualTransDupChk-3054\n" +
                "AirAccrualImpl.java-validateAccrualAndSendMail-3098\n" +
                "AirAccrualImpl.java-sendEmailOnValidationFailure-3135\n" +
                "AirAccrualImpl.java-getReferenceCodes-3187\n" +
                "AirAccrualImpl.java-createRejTxn-3203\n" +
                "AirAccrualImpl.java-getNonAccruableRsnCd-3274\n" +
                "AirAccrualImpl.java-getATTransId-3300\n" +
                "AirAccrualImpl.java-chkAirPartnersElig-3317\n" +
                "AirAccrualImpl.java-checkCustomerIntegrity-3479\n" +
                "AirAccrualImpl.java-checkValidFFPCodeNo-3591\n" +
                "AirAccrualImpl.java-handlePPSPin-3681\n" +
                "AirAccrualImpl.java-setAwardFlgFromPrt-3745\n" +
                "AirAccrualImpl.java-updatePtsFlg-3754\n" +
                "AirAccrualImpl.java-airAccrualDupChk-3853\n" +
                "AirAccrualImpl.java-checkAccrualBucketFlg-3924\n" +
                "AirAccrualImpl.java-checkAccrualBucketFlgNoDup-4076\n" +
                "AirAccrualImpl.java-checkIfValidOFFPMbr-4148\n" +
                "AirAccrualImpl.java-checkIfValidKFMbr-4404\n" +
                "AirAccrualImpl.java-getCustomerDetails-4538\n" +
                "AirAccrualImpl.java-chkFoundInPaxManifest-4567\n" +
                "AirAccrualImpl.java-nonAirAccrualDupChk-4675\n" +
                "AirAccrualImpl.java-getOFFPIntID-4716\n" +
                "AirAccrualImpl.java-getCustomerID-4736\n" +
                "AirAccrualImpl.java-sendEmailToExpiredMembers-4755\n" +
                "AirAccrualImpl.java-getAcknowledgementEmailContentToExpMembers-4807\n" +
                "AirAccrualImpl.java-createEventLogForAccrualRequest-4921\n" +
                "AirAccrualImpl.java-checkAirEligibility-4950\n" +
                "AirAccrualImpl.java-checkAirlineCode-5174\n" +
                "AirAccrualImpl.java-validateTravelClsforCodeShare-5191\n" +
                "AirAccrualImpl.java-checkPPSEligibility-5231\n" +
                "AirAccrualImpl.java-matchDefSegVal-5249\n" +
                "AirAccrualImpl.java-getCabinClass-5297\n" +
                "AirAccrualImpl.java-deriveDefSegVal-5334\n" +
                "AirAccrualImpl.java-deriveDefSegValInternal-5355\n" +
                "AirAccrualImpl.java-determineClsForPPSCredit-5389\n" +
                "AirAccrualImpl.java-retrieveSubClass-5420\n" +
                "AirAccrualImpl.java-getBigDecimal-5454\n" +
                "QualificationServiceImpl.java-recalcTierBonus-2691\n" +
                "QualificationServiceImpl.java-fetchDebitedRsrvVal-3604\n" +
                "QualificationServiceImpl.java-getRefDescription-3639\n" +
                "QualificationServiceImpl.java-processEliteCustToBeReinstate-3665\n" +
                "QualificationServiceImpl.java-getMilesByParticipents-3885\n" +
                "QualificationServiceImpl.java-updateEliteInfo-3911\n" +
                "QualificationServiceImpl.java-moveEliteGoldTrkngToHis-3932\n" +
                "QualificationServiceImpl.java-moveRecordToHis-3956\n" +
                "QualificationServiceImpl.java-getLastPPSCreditedFlightDate-3970\n" +
                "QualificationServiceImpl.java-recalMilesAndSectorByParticipent-3994\n" +
                "QualificationServiceImpl.java-setPPSCustomerVO-4026\n" +
                "QualificationServiceImpl.java-processPPSCustToBeQualified-5173\n" +
                "QualificationServiceImpl.java-getValidRsrvVal-5649\n" +
                "QualificationServiceImpl.java-checkIf12MonthsGiven-5668\n" +
                "QualificationServiceImpl.java-getReserveValList-5682\n" +
                "QualificationServiceImpl.java-invalidatePPSReserveVal-5697\n" +
                "QualificationServiceImpl.java-getPpsQualRule-5717\n" +
                "QualificationServiceImpl.java-computePPSRsrvVal-5742\n" +
                "QualificationServiceImpl.java-updateCusBucketPts-5830\n" +
                "QualificationServiceImpl.java-getCtrlParaValue-5858\n" +
                "QualificationServiceImpl.java-getCustomerIDfrmIntID-5876\n" +
                "QualificationServiceImpl.java-updatePPSValue-5895\n" +
                "QualificationServiceImpl.java-debitReserveVal-5965\n" +
                "QualificationServiceImpl.java-processPPSCustToBeExtened-5985\n" +
                "QualificationServiceImpl.java-calNewCumValue-6217\n" +
                "QualificationServiceImpl.java-matchPPSYearQualRecord-6242\n" +
                "QualificationServiceImpl.java-getTierStatList-6269\n" +
                "QualificationServiceImpl.java-findEffectivePeriod-6293\n" +
                "QualificationServiceImpl.java-processEliteCustToBeExtened-6315\n" +
                "QualificationServiceImpl.java-sendCardRequest-6510\n" +
                "QualificationServiceImpl.java-sendEmail-6701\n" +
                "QualificationServiceImpl.java-getContentId-6776\n" +
                "QualificationServiceImpl.java-getReferenceCode-6893\n" +
                "QualificationServiceImpl.java-validateTierType-6906\n" +
                "QualificationServiceImpl.java-getTierStatList-6920\n" +
                "QualificationServiceImpl.java-getTierStatByName-6938\n" +
                "QualificationServiceImpl.java-valididateForceQualAccountStatus-6955\n" +
                "QualificationServiceImpl.java-validateAccountStatus-6976\n" +
                "QualificationServiceImpl.java-getPASOnHoldDays-6993\n" +
                "QualificationServiceImpl.java-getAccountStatus-7013\n" +
                "QualificationServiceImpl.java-updateQualificationBucket-7030\n" +
                "QualificationServiceImpl.java-updateQualificationBucket-7039\n" +
                "QualificationServiceImpl.java-updateQualificationBucket-7049\n" +
                "QualificationServiceImpl.java-processPPSBucket-7263\n" +
                "QualificationServiceImpl.java-getPPSParticipant-7356\n" +
                "QualificationServiceImpl.java-createPPSCustomerData-7418\n" +
                "QualificationServiceImpl.java-getPPSCustomerData-7459\n" +
                "QualificationServiceImpl.java-processMonthlyPoint-7464\n" +
                "QualificationServiceImpl.java-processTierMileageSummary-7503\n" +
                "QualificationServiceImpl.java-matchPPSReserveValRecord-7659\n" +
                "QualificationServiceImpl.java-updatePPSYearQualRecord-7682\n" +
                "QualificationServiceImpl.java-getPPSYearQualRecords-7709\n" +
                "QualificationServiceImpl.java-getPPSCustomerData-7734\n" +
                "QualificationServiceImpl.java-updatePPSReserveValRecord-7764\n" +
                "QualificationServiceImpl.java-getMonthlyPoint-7789\n" +
                "QualificationServiceImpl.java-getTierMileageSummary-7845\n" +
                "QualificationServiceImpl.java-processEliteGoldBucket-7902\n" +
                "QualificationServiceImpl.java-getEliteGldTrking-7976\n" +
                "QualificationServiceImpl.java-processEliteBucket-8007\n" +
                "QualificationServiceImpl.java-getEliteParticipant-8079\n" +
                "QualificationServiceImpl.java-createEliteCustomerData-8137\n" +
                "QualificationServiceImpl.java-getEnrolmentDate-8182\n" +
                "QualificationServiceImpl.java-getEliteCustomerData-8223\n" +
                "QualificationServiceImpl.java-getEliteCustomerData-8228\n" +
                "QualificationServiceImpl.java-createEventLog-8259\n" +
                "QualificationServiceImpl.java-getInternalId-8308";
        String[] arr = s.split("\n");
        Map<String, Integer> map = new HashMap<>();
        for(String str : arr){
            map.put(str,1);
        }
        System.out.println(arr.length);
        return map;
    }

    private static void printToConsole(String s){
        System.out.println(s);
    }
    private static void deleteFile(String filename){
        File file = new File(filename);
        file.delete();
    }
    private static void appendtoFile(String filename, String header, List<MethodObject> list){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename, true);
            fos.write(header.getBytes());
            fos.write("\n".getBytes());
            for(MethodObject methodObject : list){
                String toPrint = String.format("Name: %s | Length of method : %s | Number of commented lines: %s\n", methodObject.completedMethodName, methodObject.methodLength, methodObject.linesOfCommentedCode);
                fos.write(toPrint.getBytes());
            }
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static int numBracketFoundAfterComment(String line, String typeOfBracket){
        int countOfBracketBefore = 0;
        int indexOfComment = line.indexOf("//");
        int indexOfBracket = line.indexOf(typeOfBracket);
        while(true){
            if(indexOfBracket < indexOfComment){
                countOfBracketBefore++;
            }
            indexOfBracket = line.indexOf(typeOfBracket, indexOfBracket+1);
            if(indexOfBracket == -1) break;
        }
        // return countOfBefore
        return countOfBracketBefore;
    }
}

