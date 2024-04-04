package MARMSUI.generateJsonFieldsFromSqlCols;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CombineJavaFieldForStatementsApi {
    public static void main(String[] args) {
        String val = "type\t\tString\tPromotion\n" +
                "batchDate\t\tDate\tPromotion\n" +
                "trxn\t\tString\tPromotion\n" +
                "participant\t\tString\tPromotion\n" +
                "date\t\tDate\tPromotion\n" +
                "kfMiles\t\tlong\tPromotion\n" +
                "description\t\tString\tPromotion\n" +
                "ffpBucketFlg\t\tString\tPromotion\n" +
                "eliteBucketFlg\t\tString\tPromotion\n" +
                "ppsBucketFlg\t\tString\tPromotion\n" +
                "amdmntRsnCd\t\tString\tPromotion\n" +
                "promoCd\t\tString\tPromotion\n" +
                "promoAwdDesc\t\tString\tPromotion\n" +
                "eliteBonusMilesAwded\t\tlong\tPromotion\n" +
                "anaInd\t\tString\tPromotion\n" +
                "promoXrefId\t\tString\tPromotion\n" +
                "transXrefId\t\tString\tPromotion\n" +
                "billToPrt\t\tString\tPromotion\n" +
                "cdSharePrt\t\tString\tPromotion\n" +
                "tktNo\t\tString\tPromotion\n" +
                "familyName\t\tString\tPromotion\n" +
                "givenName\t\tString\tPromotion\n" +
                "prtRefCd\t\tString\tPromotion\n" +
                "batchId\t\tString\tPromotion\n" +
                "reversedFlg\t\tString\tPromotion\n" +
                "nameMismatchFlg\t\tString\tPromotion\n" +
                "ppsBonusValueAwded\t\tlong\tPromotion\n" +
                "ppsPromoFlg\t\tString\tPromotion\n" +
                "type\t\tString\tAir-accrual\n" +
                "batchDate\t\tDate\tAir-accrual\n" +
                "trxn\t\tString\tAir-accrual\n" +
                "participant\t\tString\tAir-accrual\n" +
                "date\t\tDate\tAir-accrual\n" +
                "kfMiles\t\tlong\tAir-accrual\n" +
                "description\t\tString\tAir-accrual\n" +
                "fltNo\t\tlong\tAir-accrual\n" +
                "origin\t\tString\tAir-accrual\n" +
                "destination\t\tString\tAir-accrual\n" +
                "cabinClass\t\tString\tAir-accrual\n" +
                "distance\t\tlong\tAir-accrual\n" +
                "ffpBucketFlg\t\tString\tAir-accrual\n" +
                "eliteBucketFlg\t\tString\tAir-accrual\n" +
                "ppsBucketFlg\t\tString\tAir-accrual\n" +
                "sectorCnt\t\tlong\tAir-accrual\n" +
                "amdmntRsnCd\t\tString\tAir-accrual\n" +
                "offpPrtCd\t\tString\tAir-accrual\n" +
                "offpMbrNo\t\tString\tAir-accrual\n" +
                "anaInd\t\tString\tAir-accrual\n" +
                "promoXrefId\t\tString\tAir-accrual\n" +
                "ppsVal\t\tlong\tAir-accrual\n" +
                "crSrcInd\t\tString\tAir-accrual\n" +
                "interlineInd\t\tString\tAir-accrual\n" +
                "ppsXrefId\t\tlong\tAir-accrual\n" +
                "ppsOnholdFlg\t\tString\tAir-accrual\n" +
                "transXrefId\t\tString\tAir-accrual\n" +
                "billToPrt\t\tString\tAir-accrual\n" +
                "cdSharePrt\t\tString\tAir-accrual\n" +
                "cdShareFltNo\t\tlong\tAir-accrual\n" +
                "tktNo\t\tString\tAir-accrual\n" +
                "familyName\t\tString\tAir-accrual\n" +
                "givenName\t\tString\tAir-accrual\n" +
                "inputModeInd\t\tString\tAir-accrual\n" +
                "prtRefCd\t\tString\tAir-accrual\n" +
                "batchId\t\tString\tAir-accrual\n" +
                "reversedFlg\t\tString\tAir-accrual\n" +
                "nameMismatchFlg\t\tString\tAir-accrual\n" +
                "refDesc\t\tString\tAir-accrual\n" +
                "corporateID\t\tString\tAir-accrual\n" +
                "fscValue\t\tlong\tAir-accrual\n" +
                "defFscCrInd\t\tString\tAir-accrual\n" +
                "paxId\t\tString\tAir-accrual\n" +
                "atTransTktNo\t\tString\tAir-accrual\n" +
                "dollarValInSgd\t\tlong\tAir-accrual\n" +
                "amtPaidInCash\t\tlong\tAir-accrual\n" +
                "totAmtPaid\t\tlong\tAir-accrual\n" +
                "accCrInd\t\tString\tAir-accrual\n" +
                "ppsBktFlg1\t\tString\tAir-accrual\n" +
                "ffpBktFlg1\t\tString\tAir-accrual\n" +
                "eliteBktFlg1\t\tString\tAir-accrual\n" +
                "ppsVal1\t\tlong\tAir-accrual\n" +
                "sectorCnt1\t\tlong\tAir-accrual\n" +
                "refDesc1\t\tString\tAir-accrual\n" +
                "fscValue1\t\tlong\tAir-accrual\n" +
                "bktyPresent\t\tString\tAir-accrual\n" +
                "bktnPresent\t\tString\tAir-accrual\n" +
                "type\t\tString\tnon-air accrual\n" +
                "batchDate\t\tDate\tnon-air accrual\n" +
                "trxn\t\tString\tnon-air accrual\n" +
                "participant\t\tString\tnon-air accrual\n" +
                "date\t\tDate\tnon-air accrual\n" +
                "kfMiles\t\tlong\tnon-air accrual\n" +
                "description\t\tString\tnon-air accrual\n" +
                "propLocCd\t\tString\tnon-air accrual\n" +
                "ffpBucketFlg\t\tString\tnon-air accrual\n" +
                "eliteBucketFlg\t\tString\tnon-air accrual\n" +
                "ppsBucketFlg\t\tString\tnon-air accrual\n" +
                "amdmntRsnCd\t\tString\tnon-air accrual\n" +
                "anaInd\t\tString\tnon-air accrual\n" +
                "promoXrefId\t\tString\tnon-air accrual\n" +
                "ppsVal\t\tlong\tnon-air accrual\n" +
                "transXrefId\t\tString\tnon-air accrual\n" +
                "familyName\t\tString\tnon-air accrual\n" +
                "givenName\t\tString\tnon-air accrual\n" +
                "chkOutDt\t\tDate\tnon-air accrual\n" +
                "tourCd\t\tString\tnon-air accrual\n" +
                "series\t\tString\tnon-air accrual\n" +
                "tourAmt\t\tlong\tnon-air accrual\n" +
                "inputModeInd\t\tString\tnon-air accrual\n" +
                "prtRefCd\t\tString\tnon-air accrual\n" +
                "batchId\t\tString\tnon-air accrual\n" +
                "reversedFlg\t\tString\tnon-air accrual\n" +
                "nameMismatchFlg\t\tString\tnon-air accrual\n" +
                "ptyLocDesc\t\tString\tnon-air accrual\n" +
                "type\t\tString\tair redemption\n" +
                "trxn\t\tString\tair redemption\n" +
                "date\t\tDate\tair redemption\n" +
                "approvalCode\t\tString\tair redemption\n" +
                "adjApprovalCd\t\tString\tair redemption\n" +
                "netMiles\t\tlong\tair redemption\n" +
                "actionCode\t\tString\tair redemption\n" +
                "tktMcoNo\t\tString\tair redemption\n" +
                "redemptionType\t\tString\tair redemption\n" +
                "zone\t\tString\tair redemption\n" +
                "promotionMiles\t\tlong\tair redemption\n" +
                "billingPrt\t\tString\tair redemption\n" +
                "rcrePcc\t\tString\tair redemption\n" +
                "rcreSalesOff\t\tString\tair redemption\n" +
                "rcreAgentId\t\tString\tair redemption\n" +
                "tktStock1\t\tString\tair redemption\n" +
                "itinXrefId\t\tString\tair redemption\n" +
                "pnrName\t\tString\tair redemption\n" +
                "actionCd2\t\tString\tair redemption\n" +
                "pnr\t\tString\tair redemption\n" +
                "turnptsBoard\t\tString\tair redemption\n" +
                "tktSrcInd\t\tString\tair redemption\n" +
                "awdType\t\tString\tair redemption\n" +
                "tktValidityDt\t\tDate\tair redemption\n" +
                "rdpnNetPtsReq\t\tlong\tair redemption\n" +
                "forfeitPts\t\tlong\tair redemption\n" +
                "transPts\t\tlong\tair redemption\n" +
                "reversedFlg\t\tString\tair redemption\n" +
                "promoCd\t\tString\tair redemption\n" +
                "promoName\t\tString\tair redemption\n" +
                "batchId\t\tString\tair redemption\n" +
                "batchDate\t\tDate\tair redemption\n" +
                "totStopoverPts\t\tlong\tair redemption\n" +
                "certificate\t\tString\tair redemption\n" +
                "mmkInd\t\tString\tair redemption\n" +
                "pymtRfndLc\t\tString\tair redemption\n" +
                "totalFareInLc\t\tdouble\tair redemption\n" +
                "fareWoTaxInLc\t\tdouble\tair redemption\n" +
                "taxInLc\t\tdouble\tair redemption\n" +
                "netFarePaidInLc\t\tdouble\tair redemption\n" +
                "netKfMilesValInLc\t\tdouble\tair redemption\n" +
                "transFarePaidInLc\t\tdouble\tair redemption\n" +
                "transKfMilesValInLc\t\tdouble\tair redemption\n" +
                "totalFareInSgd\t\tdouble\tair redemption\n" +
                "fareWoTaxInSgd\t\tdouble\tair redemption\n" +
                "taxInSgd\t\tdouble\tair redemption\n" +
                "netFarePaidInSgd\t\tdouble\tair redemption\n" +
                "netKfMilesValInSgd\t\tdouble\tair redemption\n" +
                "transFarePaidInSgd\t\tdouble\tair redemption\n" +
                "transKfMilesValInSgd\t\tdouble\tair redemption\n" +
                "netTaxPaidInLc\t\tdouble\tair redemption\n" +
                "netTaxPaidInSgd\t\tdouble\tair redemption\n" +
                "origCurrencyCd\t\tString\tair redemption\n" +
                "initialActionCd\t\tString\tair redemption\n" +
                "rficCd\t\tString\tair redemption\n" +
                "rficDesc\t\tString\tair redemption\n" +
                "emdNumber\t\tString\tair redemption\n" +
                "ticketNumber\t\tString\tair redemption\n" +
                "fltNo\t\tlong\tair redemption\n" +
                "distPnrFlg\t\tString\tair redemption\n" +
                "exchangeRate\t\tdouble\tair redemption\n" +
                "etFlg\t\tString\tair redemption\n" +
                "commercialFlg\t\tString\tair redemption\n" +
                "type\t\tString\tnon air redemption\n" +
                "trxn\t\tString\tnon air redemption\n" +
                "date\t\tDate\tnon air redemption\n" +
                "approvalCode\t\tString\tnon air redemption\n" +
                "netMiles\t\tlong\tnon air redemption\n" +
                "actionCode\t\tString\tnon air redemption\n" +
                "promotionMiles\t\tlong\tnon air redemption\n" +
                "reversedFlg\t\tString\tnon air redemption\n" +
                "batchId\t\tString\tnon air redemption\n" +
                "batchDate\t\tDate\tnon air redemption\n" +
                "eventId\t\tString\tnon air redemption\n" +
                "nonAirRedemptionId\t\tString\tnon air redemption\n" +
                "quantity\t\tlong\tnon air redemption\n" +
                "noOfGuest\t\tlong\tnon air redemption\n" +
                "mealType\t\tString\tnon air redemption\n" +
                "winePref\t\tString\tnon air redemption\n" +
                "transportOpt\t\tString\tnon air redemption\n" +
                "remark\t\tString\tnon air redemption\n" +
                "dressCd\t\tString\tnon air redemption\n" +
                "venue\t\tString\tnon air redemption\n" +
                "eventCd\t\tString\tnon air redemption\n" +
                "categoryCd\t\tString\tnon air redemption\n" +
                "fulfilDt\t\tDate\tnon air redemption\n" +
                "eventDesc1\t\tString\tnon air redemption\n" +
                "eventDesc2\t\tString\tnon air redemption\n" +
                "eventName\t\tString\tnon air redemption\n" +
                "eventSubtype\t\tString\tnon air redemption\n" +
                "bucketType\t\tString\tnon air redemption\n" +
                "billToPrt\t\tString\tnon air redemption\n" +
                "paymntType\t\tString\tnon air redemption\n" +
                "totAmt\t\tdouble\tnon air redemption\n" +
                "trxn\t\tString\tmisc\n" +
                "batchDate\t\tDate\tmisc\n" +
                "ptsAwded\t\tlong\tmisc\n" +
                "feeCode\t\tString\tmisc\n" +
                "feesDesc\t\tString\tmisc\n" +
                "participant\t\tString\tmisc\n" +
                "tierIndex\t\tString\tmisc\n" +
                "date\t\tDate\tmisc\n" +
                "feesTransXrefId\t\tString\tmisc\n" +
                "remarks\t\tString\tmisc\n" +
                "batchId\t\tString\tmisc\n" +
                "supervisorId\t\tString\tmisc\n" +
                "paymntType\t\tString\tmisc\n" +
                "totAmt\t\tdouble\tmisc\n" +
                "waivedPts\t\tlong\tmisc\n" +
                "waivedAmt\t\tlong\tmisc\n" +
                "expPts\t\tlong\tmisc\n" +
                "refundFlg\t\tString\tmisc\n" +
                "suppressFlg\t\tString\tmisc\n" +
                "type\t\tString\tmisc\n" +
                "desc2\t\tString\tmisc\n" +
                "trxn\t\tString\txc transactions\n" +
                "batchDate\t\tDate\txc transactions\n" +
                "ptsAwded\t\tlong\txc transactions\n" +
                "transXrefId\t\tString\txc transactions\n" +
                "participant\t\tString\txc transactions\n" +
                "date\t\tDate\txc transactions\n" +
                "frmBucketDt\t\tDate\txc transactions\n" +
                "newExpDt\t\tDate\txc transactions\n" +
                "remarks\t\tString\txc transactions\n" +
                "batchId\t\tString\txc transactions\n" +
                "supervisorId\t\tString\txc transactions\n" +
                "balExtended\t\tlong\txc transactions\n" +
                "type\t\tString\txc transactions\n" +
                "description\t\tString\txc transactions\n" +
                "desc2\t\tString\txc transactions\n" +
                "trxn\t\tString\txd transactions\n" +
                "batchDate\t\tDate\txd transactions\n" +
                "ptsAwded\t\tlong\txd transactions\n" +
                "transXrefId\t\tString\txd transactions\n" +
                "participant\t\tString\txd transactions\n" +
                "date\t\tDate\txd transactions\n" +
                "frmBucketDt\t\tDate\txd transactions\n" +
                "remarks\t\tString\txd transactions\n" +
                "batchId\t\tString\txd transactions\n" +
                "supervisorId\t\tString\txd transactions\n" +
                "ptsExtended\t\tlong\txd transactions\n" +
                "type\t\tString\txd transactions\n" +
                "description\t\tString\txd transactions\n" +
                "type\t\tString\tmerge\n" +
                "trxn\t\tString\tmerge\n" +
                "batchDate\t\tDate\tmerge\n" +
                "ptsAwded\t\tlong\tmerge\n" +
                "transXrefId\t\tString\tmerge\n" +
                "participant\t\tString\tmerge\n" +
                "description\t\tString\tmerge\n" +
                "date\t\tDate\tmerge\n" +
                "remarks\t\tString\tmerge\n" +
                "eliteMiles\t\tlong\tmerge\n" +
                "ppsPts\t\tlong\tmerge\n" +
                "sectCnt\t\tlong\tmerge\n" +
                "ppsVal\t\tlong\tmerge\n" +
                "batchId\t\tString\tmerge\n" +
                "supervisorId\t\tString\tmerge\n" +
                "mergeAccumNatPts\t\tlong\tmerge\n" +
                "mergeCurElitePts\t\tlong\tmerge\n" +
                "mergeCurPpsPts\t\tlong\tmerge\n" +
                "mergeCurPpsSectCnt\t\tlong\tmerge\n" +
                "mergeAccumElitePts\t\tlong\tmerge\n" +
                "mergeAccumPpsPts\t\tlong\tmerge\n" +
                "mergeAccumPpsSectCnt\t\tdouble\tmerge\n" +
                "mergeEliteQualDt\t\tDate\tmerge\n" +
                "mergeEliteExpDt\t\tDate\tmerge\n" +
                "mergePpsQualDt\t\tDate\tmerge\n" +
                "mergePpsExpDt\t\tDate\tmerge\n" +
                "unmergeAccumNatPts\t\tlong\tmerge\n" +
                "unmergeCurElitePts\t\tlong\tmerge\n" +
                "unmergeCurPpsPts\t\tlong\tmerge\n" +
                "unmergeCurPpsSectCnt\t\tlong\tmerge\n" +
                "unmergeAccumElitePts\t\tlong\tmerge\n" +
                "unmergeAccumPpsPts\t\tlong\tmerge\n" +
                "unmergeAccumPpsSectCnt\t\tlong\tmerge\n" +
                "unmergeEliteQualDt\t\tDate\tmerge\n" +
                "unmergeEliteExpDt\t\tDate\tmerge\n" +
                "unmergePpsQualDt\t\tDate\tmerge\n" +
                "unmergePpsExpDt\t\tDate\tmerge\n" +
                "mergeCurPpsVal\t\tlong\tmerge\n" +
                "mergeCumPpsVal\t\tlong\tmerge\n" +
                "mergeCumLifetimePpsVal\t\tlong\tmerge\n" +
                "unmergeCurPpsVal\t\tlong\tmerge\n" +
                "unmergeCumPpsVal\t\tlong\tmerge\n" +
                "unmergeCumLifetimePpsVal\t\tlong\tmerge";
        String[] arr = val.split("\n");
        Map<String,String> map = new HashMap<>();
        Map<String,String> mapOfDataType = new HashMap<>();
        for(String row : arr) {
            String[] rowArr = row.split("\t");
            String valueToEnterMap = "";
            if(!map.containsKey(rowArr[0])) {
                valueToEnterMap = rowArr[3];
                mapOfDataType.put(rowArr[0],rowArr[2]);
            } else {
                valueToEnterMap = map.get(rowArr[0]) + "," + rowArr[3];
            }
            map.put(rowArr[0],valueToEnterMap);
        }
        List<String> keySet = map.keySet().stream().collect(Collectors.toList());
        for(String key : keySet) {
            System.out.println(key);
        }
        System.out.println("====================================");
        for(String key : keySet) {
            System.out.println(map.get(key));
        }
        System.out.println("======================================");
        for(String key : keySet) {
            System.out.println(mapOfDataType.get(key));
        }
    }
}
