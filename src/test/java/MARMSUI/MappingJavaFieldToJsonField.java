package MARMSUI;

import java.util.*;

public class MappingJavaFieldToJsonField {
    public static void main(String[] args) {
        Set<String> setOfFieldsMarkedIgnored = new HashSet<>();
        Map<String, String> mapOfKeyJavaValueJson = new HashMap<>();
        String resultMap = "<result column=\"TRANS_CD\" jdbcType=\"VARCHAR\" property=\"transCd\"/>\n" +
                "        <result column=\"BATCH_DATE\" jdbcType=\"TIMESTAMP\" property=\"batchDate\"/>\n" +
                "        <result column=\"PTS_AWDED\" jdbcType=\"NUMERIC\" property=\"ptsAwded\"/>\n" +
                "        <result column=\"TRANS_XREF_ID\" jdbcType=\"VARCHAR\" property=\"transXrefId\"/>\n" +
                "        <result column=\"PRT_CD\" jdbcType=\"VARCHAR\" property=\"prtCd\"/>\n" +
                "        <result column=\"TRANS_DATE\" jdbcType=\"TIMESTAMP\" property=\"transDate\"/>\n" +
                "        <result column=\"FRM_BUCKET_DT\" jdbcType=\"TIMESTAMP\" property=\"frmBucketDt\"/>\n" +
                "        <result column=\"REMARKS\" jdbcType=\"VARCHAR\" property=\"remarks\"/>\n" +
                "        <result column=\"BATCH_ID\" jdbcType=\"VARCHAR\" property=\"batchId\"/>\n" +
                "        <result column=\"SUPERVISOR_ID\" jdbcType=\"VARCHAR\" property=\"supervisorId\"/>\n" +
                "        <result column=\"PTS_EXTENDED\" jdbcType=\"NUMERIC\" property=\"ptsExtended\"/>\n" +
                "        <result column=\"TRANSACTION_TYPE\" jdbcType=\"VARCHAR\" property=\"transactionType\"/>\n" +
                "        <result column=\"AWD_DESC\" jdbcType=\"VARCHAR\" property=\"awdDesc\"/>";



        String javaFields = "@JsonProperty(\"type\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String transactionType;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date batchDate;\n" +
                "    @JsonProperty(\"trxn\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String transCd;\n" +
                "    @JsonProperty(\"participant\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String prtCd;\n" +
                "    @JsonProperty(\"date\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date transDate;\n" +
                "    @JsonProperty(\"kfMiles\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long ptsAwarded;\n" +
                "    @JsonProperty(\"description\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String awdDesc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String desc2;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long fltNo;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String origin;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String destination;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String cabinClass;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long distance;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String propLocCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String ffpBucketFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String eliteBucketFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String ppsBucketFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long sectorCnt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String amdmntRsnCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String offpPrtCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String offpMbrNo;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String promoCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String promoAwdDesc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long eliteBonusMilesAwded;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String anaInd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String promoXrefId;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long ppsVal;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String crSrcInd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String interlineInd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long ppsXrefId;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String ppsOnholdFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String transXrefId;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String billToPrt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String cdSharePrt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long cdShareFltNo;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String tktNo;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String familyName;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String givenName;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date chkOutDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String tourCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String series;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long tourAmt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String inputModeInd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String prtRefCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String batchId;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String reversedFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String nameMismatchFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String refDesc;\n" +
                "    @JsonProperty(\"corporateID\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String cpsIdCr;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long fscValue;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long ppsBonusValueAwded;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String defFscCrInd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String ppsPromoFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String paxId;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String atTransTktNo;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long dollarValInSgd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long amtPaidInCash;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long totAmtPaid;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String accCrInd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private List<TravelDetails> flightDetailsList;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private List<BucketPoints> bucketPointsList;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private OnlineRedemptionDetails onlineRedemptionDetails;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private List<RdpnPayment> rdpnPaymentList;\n" +
                "\n" +
                "    // air-redemption\n" +
                "    @JsonProperty(\"approvalCode\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String approvalCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String adjApprovalCd;\n" +
                "    @JsonProperty(\"netMiles\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long netPtsReq;\n" +
                "    @JsonProperty(\"actionCode\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String actionCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String tktMcoNo;\n" +
                "    @JsonProperty(\"redemptionType\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String rdpnType;\n" +
                "    @JsonProperty(\"zone\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String awdZone;\n" +
                "    @JsonProperty(\"promotionMiles\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long promoSavings;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String billingPrt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String rcrePcc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String rcreSalesOff;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String rcreAgentId;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String tktStock1;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String itinXrefId;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String pnrName;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String actionCd2;\n" +
                "    @JsonProperty(\"pnr\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String pnrRef;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String turnptsBoard;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String tktSrcInd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String awdType;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private Date tktValidityDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long rdpnNetPtsReq;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long forfeitPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long transPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String promoName;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long totStopoverPts;\n" +
                "    @JsonProperty(\"certificate\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String certificateNumber;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String mmkInd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String pymtRfndLc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double totalFareInLc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double fareWoTaxInLc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double taxInLc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double netFarePaidInLc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double netKfMilesValInLc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double transFarePaidInLc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double transKfMilesValInLc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double totalFareInSgd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double fareWoTaxInSgd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double taxInSgd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double netFarePaidInSgd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double netKfMilesValInSgd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double transFarePaidInSgd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double transKfMilesValInSgd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double netTaxPaidInLc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double netTaxPaidInSgd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String origCurrencyCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String initialActionCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String rficCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String rficDesc;\n" +
                "    @JsonProperty(\"emdNumber\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String emd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String ticketNumber;\n" +
                "    @JsonIgnore\n" +
                "    private long normalPts;\n" +
                "    @JsonIgnore\n" +
                "    private long extendedPts;\n" +
                "    @JsonIgnore\n" +
                "    private Date expiryDt;\n" +
                "    @JsonIgnore\n" +
                "    private String fltApprovalCd;\n" +
                "    @JsonIgnore\n" +
                "    private String carrierCd;\n" +
                "    @JsonIgnore\n" +
                "    private String subClass;\n" +
                "    @JsonIgnore\n" +
                "    private Date fltDepDt;\n" +
                "    @JsonIgnore\n" +
                "    private Date fltArrDt;\n" +
                "    @JsonIgnore\n" +
                "    private String destinationCd;\n" +
                "    @JsonIgnore\n" +
                "    private String originCd;\n" +
                "    @JsonIgnore\n" +
                "    private String transitInd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String distPnrFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double exchangeRate;\n" +
                "    @JsonProperty(\"etFlg\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String et;\n" +
                "    @JsonProperty(\"commercialFlg\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String comT;\n" +
                "\n" +
                "\n" +
                "    // not to include in response\n" +
                "    @JsonIgnore\n" +
                "    private String ppsBktFlg1;\n" +
                "    @JsonIgnore\n" +
                "    private String ffpBktFlg1;\n" +
                "    @JsonIgnore\n" +
                "    private String eliteBktFlg1;\n" +
                "    @JsonIgnore\n" +
                "    private long ppsVal1;\n" +
                "    @JsonIgnore\n" +
                "    private long sectorCnt1;\n" +
                "    @JsonIgnore\n" +
                "    private String refDesc1;\n" +
                "    @JsonIgnore\n" +
                "    private long fscValue1;\n" +
                "    @JsonIgnore\n" +
                "    private String bktnPresent;\n" +
                "    @JsonIgnore\n" +
                "    private String bktyPresent;\n" +
                "\n" +
                "    // NON-AIR-REDEMPTION\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String eventId;\n" +
                "    @JsonProperty(\"nonAirRedemptionId\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String naRdpnId;\n" +
                "    @JsonProperty(\"quantity\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unitRedeem;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long noOfGuest;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String mealType;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String winePref;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String transportOpt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String remark;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String dressCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String venue;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String eventCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String categoryCd;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private Date fulfilDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String eventDesc1;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String eventDesc2;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String eventName;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String eventSubtype;\n" +
                "    @JsonIgnore\n" +
                "    private String generalIdentifier;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_EMPTY)\n" +
                "    private String bucketType;\n" +
                "    @JsonIgnore\n" +
                "    private String quartBktIdentifier;\n" +
                "    @JsonIgnore\n" +
                "    private String orderNum;\n" +
                "    @JsonIgnore\n" +
                "    private long bsktTotValue;\n" +
                "    @JsonIgnore\n" +
                "    private long redeemedValue;\n" +
                "    @JsonIgnore\n" +
                "    private long bsktTotMiles;\n" +
                "    @JsonIgnore\n" +
                "    private long bsktRedeemedMiles;\n" +
                "    @JsonIgnore\n" +
                "    private String bsktPromoCd;\n" +
                "    @JsonIgnore\n" +
                "    private String bsktDelMode;\n" +
                "    @JsonIgnore\n" +
                "    private String rdpnBktDetIdentifier;\n" +
                "    @JsonIgnore\n" +
                "    private long itemNum;\n" +
                "    @JsonIgnore\n" +
                "    private String itemName;\n" +
                "    @JsonIgnore\n" +
                "    private String itemCategory;\n" +
                "    @JsonIgnore\n" +
                "    private String itemBrand;\n" +
                "    @JsonIgnore\n" +
                "    private long itemQty;\n" +
                "    @JsonIgnore\n" +
                "    private long itemValue;\n" +
                "    @JsonIgnore\n" +
                "    private String rdpnItemsIdentifier;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String paymntType;\n" +
                "    @JsonIgnore\n" +
                "    private String guestPaymnt;\n" +
                "    @JsonIgnore\n" +
                "    private long totPts;\n" +
                "    @JsonIgnore\n" +
                "    private long totAmt;\n" +
                "    @JsonIgnore\n" +
                "    private String currencyCd;\n" +
                "    @JsonIgnore\n" +
                "    private String voucherNo;\n" +
                "    @JsonIgnore\n" +
                "    private String mcoNo;\n" +
                "    @JsonIgnore\n" +
                "    private String rdpnPaymentIdentifier;\n" +
                "\n" +
                "\n" +
                "    // misc transactions\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long ptsAwded;\n" +
                "    @JsonProperty(\"feeCode\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String feesCd;\n" +
                "    @JsonProperty(\"tierIndex\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String feesTierInd;\n" +
                "    @JsonProperty(\"creationDate\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date rcreDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String feesTransXrefId;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date frmBucketDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date newExpDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String remarks;\n" +
                "    @JsonProperty(\"eliteMiles\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long elitePts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long ppsPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long sectCnt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String supervisorId;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long waivedPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long waivedAmt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long expPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String refundFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String suppressFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long balExtended;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long ptsExtended;\n" +
                "    @JsonIgnore\n" +
                "    private Date newExpDt1;\n" +
                "\n" +
                "    // merge transaction (misc)\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long mergeAccumNatPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long mergeCurElitePts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long mergeCurPpsPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long mergeCurPpsSectCnt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long mergeAccumElitePts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long mergeAccumPpsPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double mergeAccumPpsSectCnt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date mergeEliteQualDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date mergeEliteExpDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date mergePpsQualDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date mergePpsExpDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unmergeAccumNatPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unmergeCurElitePts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unmergeCurPpsPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unmergeCurPpsSectCnt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unmergeAccumElitePts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unmergeAccumPpsPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unmergeAccumPpsSectCnt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date unmergeEliteQualDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date unmergeEliteExpDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date unmergePpsQualDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private Date unmergePpsExpDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long mergeCurPpsVal;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long mergeCumPpsVal;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long mergeCumLifetimePpsVal;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unmergeCurPpsVal;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unmergeCumPpsVal;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long unmergeCumLifetimePpsVal;\n" +
                "\n" +
                "\n" +
                "    // not inside the sql return object\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String ptyLocDesc;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private boolean ffpCrBeforePPS;";

        // ---------------------------------------------------------------------------------------------

        populateMapAndSet(setOfFieldsMarkedIgnored, mapOfKeyJavaValueJson, javaFields);
        List<String> fieldListFromResultMap = generateListOfPropertyFields(resultMap);
        printFieldsReturnedAsJson(fieldListFromResultMap,mapOfKeyJavaValueJson,setOfFieldsMarkedIgnored);

    }

    private static void printFieldsReturnedAsJson(List<String> resultMapFieldList, Map<String,String> map, Set<String> ignoredSet) {
        List<String> fieldIgnored = new ArrayList<>();
        for(String field : resultMapFieldList) {
            // check if the field is inside ignored set
            if(ignoredSet.contains(field)) {
                fieldIgnored.add(field);
                continue;
            }
            String jsonVal = map.get(field);
            if(jsonVal != null) {
                System.out.println(jsonVal);
            } else {
                System.out.println(field);
            }
        }

        System.out.println("Fields ignored for this instance --------------");
        for(String field : fieldIgnored) {
            System.out.println(field);
        }
    }

    private static List<String> generateListOfPropertyFields(String resultMap) {
        String[] arr = resultMap.split("\n");
        List<String> fieldList = new ArrayList<>();
        for(String row : arr) {
            int startIndex = row.indexOf("property");
            startIndex = row.indexOf("\"", startIndex) + 1;
            int endIndex = row.indexOf("\"", startIndex);
            String val = row.substring(startIndex,endIndex);
            fieldList.add(val);
        }
        return fieldList;
    }

    private static void populateMapAndSet(Set<String> ignoredSet, Map<String,String> map, String fields) {
        String[] statementArr = fields.split("\n");
        String jsonIgnoredString = "JSON_IGNORED";
        boolean fieldIsIgnored = false;
        String jsonVal = null;
        for(String statement : statementArr) {
            String trimmed = statement.trim();
            if(trimmed.equals("") || trimmed.startsWith("//")){
                continue;
            }
            // detect for JsonIgnore
            if(trimmed.contains("@JsonIgnore")){
                fieldIsIgnored = true;
            }
            if(trimmed.contains("@JsonProperty")) {
                int startIndex = trimmed.indexOf("\"") + 1;
                int endIndex = trimmed.indexOf("\"", startIndex);
                jsonVal = trimmed.substring(startIndex,endIndex);
            }
            if(trimmed.contains("private")) {
                String field = getFieldName(trimmed);
                map.put(field,jsonVal);
                if(fieldIsIgnored) {
                    ignoredSet.add(field);
                }
                fieldIsIgnored = false;
                jsonVal = null;
            }
        }
    }

    private static String getFieldName(String row) {
        String[] rowArr = row.split(" ");
        return rowArr[2].substring(0,rowArr[2].length()-1);
    }
}
