import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TestingPurposes {
    public static void main(String[] args) {
        String val = ".09";
        double valD = Double.parseDouble(val);
        System.out.println(valD);
//        String mappers = "@Mapping(target = \"expiredPtsExtPrd\", source = \"expPtsExtPrd\")\n" +
//                "    @Mapping(target = \"currencyThreshold\", source = \"currencyThreshold\")\n" +
//                "    @Mapping(target = \"ecertRfndStatusHskpPrd\", source = \"hskpEcertRfndPrd\")\n" +
//                "    @Mapping(target = \"noOfUpgradePax\", source = \"muaNoOfPax\")\n" +
//                "    @Mapping(target = \"webRtrValidityPrd\", source = \"webRtrValidityPrd\")\n" +
//                "    @Mapping(target = \"nomineesChangePeriod\", source = \"nomineesChgPrd\")\n" +
//                "    @Mapping(target = \"kfDormToExpPrd\", source = \"kfDormToExpPrd\")\n" +
//                "    @Mapping(target = \"kfEnrToDormPrd\", source = \"kfEnrToDormPrd\")\n" +
//                "    @Mapping(target = \"mailerTypeReqEmailack\", source = \"mailerTypeReqEmailack\")\n" +
//                "    @Mapping(target = \"ecertOpenStatusHskpPrd\", source = \"hskpEcertOpenPrd\")\n" +
//                "    @Mapping(target = \"naActToDormPrd\", source = \"naActToDormPrd\")\n" +
//                "    @Mapping(target = \"ptsValidityPrd\", source = \"ptsValidityPrd\")\n" +
//                "    @Mapping(target = \"totalPtsPerExtFee\", source = \"totPtsPerExtFee\")\n" +
//                "    @Mapping(target = \"forceExtentionLimit\", source = \"qualMaxExtensionPrd\")\n" +
//                "    @Mapping(target = \"dedupPattern\", source = \"dedupPattern\")\n" +
//                "    @Mapping(target = \"dedupWeightage\", source = \"dedupWeightage\")\n" +
//                "    @Mapping(target = \"maxNoOfCardLoss\", source = \"maxNoOfCardLoss\")\n" +
//                "    @Mapping(target = \"cardValidityPrd\", source = \"cardValidityPrd\")\n" +
//                "    @Mapping(target = \"maxUserFFPPts\", source = \"maxUserFfpPts\")\n" +
//                "    @Mapping(target = \"eliteQualStartDate\", source = \"eliteQualPrdStart\")\n" +
//                "    @Mapping(target = \"ecertUsedStatusHskpPrd\", source = \"hskpEcertUsedPrd\")\n" +
//                "    @Mapping(target = \"yECMaxAge\", source = \"yecMaxAge\")\n" +
//                "    @Mapping(target = \"ecertBkupHskpPrd\", source = \"hskpEcertBkupPrd\")\n" +
//                "    @Mapping(target = \"noOfUpgradeSegements\", source = \"muaNoOfSegment\")\n" +
//                "    @Mapping(target = \"segmentFilePath\", source = \"sgmtFilePath\")\n" +
//                "    @Mapping(target = \"maxSupPromoPts\", source = \"maxSupervisorPromoPts\")\n" +
//                "    @Mapping(target = \"enquiryMaxRecs\", source = \"enquiryMaxRec\")\n" +
//                "    @Mapping(target = \"chkNoOfCardLoss\", source = \"checkNoOfCardLoss\")\n" +
//                "    @Mapping(target = \"maxUserPromoPts\", source = \"maxUserPromoPts\")\n" +
//                "    @Mapping(target = \"maxNoOfRdpnNominees\", source = \"maxNoOfRdpnNominees\")\n" +
//                "    @Mapping(target = \"cardLossPrd\", source = \"cardLossPrd\")\n" +
//                "    @Mapping(target = \"kfActToDormPrd\", source = \"kfActToDormPrd\")\n" +
//                "    @Mapping(target = \"kfPndgToClosePrd\", source = \"kfPndgToClosePrd\")\n" +
//                "    @Mapping(target = \"extExpPrtCd\", source = \"extExpPrtCd\")";
//        String[] sentences = mappers.split("\n");
//        String getterVariable = "cp";
//        String setterVariable = "ctrlPara";
//        for(String s : sentences) {
//            String[] arr = extractFieldsFromStatement(s);
//            String toPrint = String.format("%s.%s(%s.%s());", setterVariable, getSetStatement(arr[0]),getterVariable,getGetStatement(arr[1]));
//            System.out.println(toPrint);
//        }

    }

    private static String[] extractFieldsFromStatement(String sentence) {
        int firstIndex = sentence.indexOf("\"") + 1;
        int finalIndex = sentence.indexOf("\"", firstIndex);
        String setField = sentence.substring(firstIndex,finalIndex);
        firstIndex = 0;
        finalIndex++;
        firstIndex = sentence.indexOf("\"", finalIndex) + 1;
        finalIndex = sentence.indexOf("\"", firstIndex);
        String getField = sentence.substring(firstIndex, finalIndex);
        return new String[]{setField,getField};
    }

    private static String getGetStatement(String field) {
        String temp = "get";
        field = field.substring(0,1).toUpperCase() + field.substring(1);
        temp += field;
        return temp;
    }

    private static String getSetStatement(String field) {
        String temp = "set";
        field = field.substring(0,1).toUpperCase() + field.substring(1);
        temp += field;
        return temp;
    }
}
