package MARMSUI;

public class GenerateClassObjFromUniqueIdentifier {
    public static void main(String[] args) {
        String fields = "private String intId;\n" +
                "    private String transCd;\n" +
                "    private Date rcreDt;\n" +
                "    private String approvalCd;\n" +
                "    private long netPtsReq;\n" +
                "    private String actionCd;\n" +
                "    private long tktMcoNo;\n" +
                "    private String rdpnType;\n" +
                "    private String awdZone;\n" +
                "    private long promoSavings;\n" +
                "    private String billingPrt;\n" +
                "    private String rcrePcc;\n" +
                "    private String rcreSalesOff;\n" +
                "    private String rcreAgentId;\n" +
                "    private String tktStock1;\n" +
                "    private String itinXrefId;\n" +
                "    private String pnrName;\n" +
                "    private String pnrRef;\n" +
                "    private String turnptsBoard;\n" +
                "    private String tktSrcInd;\n" +
                "    private String awdType;\n" +
                "    private long rdpnNetPtsReq;\n" +
                "    private long forfeitPts;\n" +
                "    private long transPts;\n" +
                "    private String reversedFlg;\n" +
                "    private String batchId;\n" +
                "    private Date batchDt;\n" +
                "    private long totStopoverPts;\n" +
                "    private long totalFareInLc;\n" +
                "    private long fareWoTaxInLc;\n" +
                "    private long taxInLc;\n" +
                "    private long netFarePaidInLc;\n" +
                "    private long netKfMilesValInLc;\n" +
                "    private long transFarePaidInLc;\n" +
                "    private long transKfMilesValInLc;\n" +
                "    private long totalFareInSgd;\n" +
                "    private long fareWoTaxInSgd;\n" +
                "    private long taxInSgd;\n" +
                "    private long netFarePaidInSgd;\n" +
                "    private long netKfMilesValInSgd;\n" +
                "    private long transFarePaidInSgd;\n" +
                "    private long transKfMilesValInSgd;";
        String[] fieldArr = fields.split("\n");
        String arrName = "uniqueIdentifierArr";
        for(int i=0; i<fieldArr.length; i++) {
            String toPrint = "";
            String[] splitFieldString = fieldArr[i].trim().split(" ");
            String type = splitFieldString[1];
            int endIndex = splitFieldString[2].indexOf(";");
            String field = splitFieldString[2].substring(0,endIndex);
            if(type.equals("String")){
                toPrint = String.format("%s = Extractor.stringExtractor(%s[%d]);",field,arrName,i);
            } else if(type.equals("long")) {
                toPrint = String.format("%s = Extractor.longExtractor(%s[%d]);",field,arrName,i);
            } else {
                toPrint = String.format("%s = Extractor.dateExtractor(%s[%d]);",field,arrName,i);
            }
            System.out.println(toPrint);
        }
    }
}
