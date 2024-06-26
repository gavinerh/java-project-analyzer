package MARMSUI.generateJsonFieldsFromSqlCols;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class SqlToResultMapToJavaFieldsToJsonField {
    // this class will use sql select statement columns and map to resultmap
    // from resultmap, it will map to the java class fields and finally to the json field
    // if @JsonProperty is present
    public static void main(String[] args) {
        String sqlSelectCols = "'OVERDRAFT' as TRANSACTION, BATCH_DT, TRANS_CD, nat.PRT_CD, AWD_DT, PTS_AWDED, AWD_DESC, AMDMNT_RSN_CD, PRT_REF_CD,\n" +
                "        OD_RSN_CD, REMARKS, PTS_VALIDITY_PRD, TRANS_XREF_ID, BATCH_ID, SUPERVISOR_ID, REVERSED_FLG, info.user_name as USER_NAME,\n" +
                "        OD_EXP_DT,\n" +
                "        case\n" +
                "        when OD_DUE_DT is null then sysdate\n" +
                "        else OD_DUE_DT end as OD_DUE_DT,\n" +
                "        OD_GRANTED_PTS, OD_USED_PTS,\n" +
                "        OD_PAYBACK_PTS, OD_RESET_PTS,  LAST_PAYBACK_DT, EXP_DT_OVERWRITE_FLG,\n" +
                "        DUE_DT_OVERWRITE_FLG, MILES_OVERWRITE_FLG";


        String baseSqlColsWithColName = "'OVERDRAFT' as TRANSACTION, BATCH_DT as BATCH_DT, TRANS_CD as TRANS_CD, nat.PRT_CD as PRT_CD, AWD_DT as AWD_DT," +
                " PTS_AWDED as PTS_AWDED, AWD_DESC as AWD_DESC, AMDMNT_RSN_CD as AMDMNT_RSN_CD, PRT_REF_CD as PRT_REF_CD,\n" +
                "        OD_RSN_CD as OD_RSN_CD, REMARKS as REMARKS, PTS_VALIDITY_PRD as PTS_VALIDITY_PRD, TRANS_XREF_ID as TRANS_XREF_ID," +
                " BATCH_ID as BATCH_ID, SUPERVISOR_ID as SUPERVISOR_ID, REVERSED_FLG as REVERSED_FLG, info.user_name as USER_NAME,\n" +
                "        OD_EXP_DT as OD_EXP_DT,\n" +
                "        case\n" +
                "        when OD_DUE_DT is null then sysdate\n" +
                "        else OD_DUE_DT end as OD_DUE_DT,\n" +
                "        OD_GRANTED_PTS AS OC_GRANTED_PTS, OD_USED_PTS as OD_USED_PTS,\n" +
                "        OD_PAYBACK_PTS as OD_PAYBACK_PTS, OD_RESET_PTS as OD_RESET_PTS,  LAST_PAYBACK_DT as LAST_PAYBACK_DT, EXP_DT_OVERWRITE_FLG as EXP_DT_OVERWRITE_FLG,\n" +
                "        DUE_DT_OVERWRITE_FLG as DUE_DT_OVERWRITE_FLG, MILES_OVERWRITE_FLG as MILES_OVERWRITE_FLG";

        // =============================================================
        String resultMapString = "<result column=\"TRANSACTION\" jdbcType=\"VARCHAR\" property=\"transactionType\"/>\n" +
                "        <result column=\"BATCH_DT\" jdbcType=\"TIMESTAMP\" property=\"batchDate\"/>\n" +
                "        <result column=\"TRANS_CD\" jdbcType=\"VARCHAR\" property=\"transCd\" />\n" +
                "        <result column=\"PRT_CD\" jdbcType=\"VARCHAR\" property=\"prtCd\" />\n" +
                "        <result column=\"AWD_DT\" jdbcType=\"TIMESTAMP\" property=\"transDate\" />\n" +
                "        <result column=\"PTS_AWDED\" jdbcType=\"NUMERIC\" property=\"ptsAwarded\"/>\n" +
                "        <result column=\"AWD_DESC\" jdbcType=\"VARCHAR\" property=\"awdDesc\" />\n" +
                "        <result column=\"AMDMNT_RSN_CD\" jdbcType=\"VARCHAR\" property=\"amdmntRsnCd\" />\n" +
                "        <result column=\"PRT_REF_CD\" jdbcType=\"VARCHAR\" property=\"prtRefCd\" />\n" +
                "        <result column=\"OD_RSN_CD\" jdbcType=\"VARCHAR\" property=\"odRsnCd\"/>\n" +
                "        <result column=\"REMARKS\" jdbcType=\"VARCHAR\" property=\"remark\"/>\n" +
                "        <result column=\"PTS_VALIDITY_PRD\" jdbcType=\"NUMERIC\" property=\"ptsValidityPrd\"/>\n" +
                "        <result column=\"TRANS_XREF_ID\" jdbcType=\"VARCHAR\" property=\"transXrefId\" />\n" +
                "        <result column=\"BATCH_ID\" jdbcType=\"VARCHAR\" property=\"batchId\"/>\n" +
                "        <result column=\"SUPERVISOR_ID\" jdbcType=\"VARCHAR\" property=\"supervisorId\"/>\n" +
                "        <result column=\"REVERSED_FLG\" jdbcType=\"VARCHAR\" property=\"reversedFlg\" />\n" +
                "        <result column=\"USER_NAME\" jdbcType=\"VARCHAR\" property=\"userName\" />\n" +
                "        <result column=\"OD_EXP_DT\" jdbcType=\"TIMESTAMP\" property=\"odExpDt\" />\n" +
                "        <result column=\"OD_DUE_DT\" jdbcType=\"TIMESTAMP\" property=\"odDueDt\" />\n" +
                "        <result column=\"OD_GRANTED_PTS\" jdbcType=\"NUMERIC\" property=\"grantedPts\" />\n" +
                "        <result column=\"OD_USED_PTS\" jdbcType=\"NUMERIC\" property=\"usedPts\" />\n" +
                "        <result column=\"OD_PAYBACK_PTS\" jdbcType=\"NUMERIC\" property=\"paybackPts\" />\n" +
                "        <result column=\"OD_RESET_PTS\" jdbcType=\"NUMERIC\" property=\"resetPts\" />\n" +
                "        <result column=\"LAST_PAYBACK_DT\" jdbcType=\"TIMESTAMP\" property=\"paybackDt\" />\n" +
                "        <result column=\"EXP_DT_OVERWRITE_FLG\" jdbcType=\"VARCHAR\" property=\"expOverrideFlg\" />\n" +
                "        <result column=\"DUE_DT_OVERWRITE_FLG\" jdbcType=\"VARCHAR\" property=\"dueOverrideFlg\" />\n" +
                "        <result column=\"MILES_OVERWRITE_FLG\" jdbcType=\"VARCHAR\" property=\"milesOverrideFlg\" />";


        String javaFields = "@JsonProperty(\"type\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String transactionType;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date batchDate;\n" +
                "    @JsonProperty(\"trxn\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String transCd;\n" +
                "    @JsonProperty(\"participant\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String prtCd;\n" +
                "    @JsonProperty(\"date\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
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
                "    private String amdmntRsnDesc;\n" +
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
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
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
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long totalMilesReq;\n" +
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
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
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
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
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
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private double totAmt;\n" +
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
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String feesDesc;\n" +
                "    @JsonProperty(\"tierIndex\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String feesTierInd;\n" +
                "    @JsonProperty(\"creationDate\")\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date rcreDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String feesTransXrefId;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date frmBucketDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
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
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date mergeEliteQualDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date mergeEliteExpDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date mergePpsQualDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
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
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date unmergeEliteQualDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date unmergeEliteExpDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date unmergePpsQualDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
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
                "    private boolean ffpCrBeforePPS; // accrual\n" +
                "@JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String odRsnCd; // overdraft\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private int ptsValidityPrd; // overdraft\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String userName;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date odExpDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date odDueDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long grantedPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long usedPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long paybackPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private long resetPts;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"Asia/Singapore\")\n" +
                "    private Date paybackDt;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String expOverrideFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String dueOverrideFlg;\n" +
                "    @JsonInclude(JsonInclude.Include.NON_DEFAULT)\n" +
                "    private String milesOverrideFlg;";
        String[] javaFieldsArr = javaFields.split("\n");
        Map<String, String> mapOfJavaFieldToJsonField = new HashMap<>(); // key: java field, value: json value
        Map<String, String> mapOfJavaFieldToDataType = new HashMap<>();
        generateJavaJsonMapping(mapOfJavaFieldToJsonField, javaFieldsArr, mapOfJavaFieldToDataType);
        List<String> baseColList = separateSqlSelectIntoColumns(baseSqlColsWithColName);
        List<String> colList = separateSqlSelectIntoColumns(sqlSelectCols);
        Map<String, String> mapOfPropertyToColumnName = mapColumnNameToJavaField(resultMapString);
        List<String> listOfColumnNames = extractColNames(baseColList);

        // generate the list of sql col that are not returning null or 0
        List<String> usefulCols = extractColumnsThatDoesReturnNull(colList, listOfColumnNames);

        // map the useful cols to the resultmap map
        List<String> usefulJavaFields = extractUsefulJavaFields(usefulCols, mapOfPropertyToColumnName);
//        System.out.println(usefulJavaFields.size());
        // print out the json field and data type
        printJsonFieldAndDataType(usefulJavaFields, mapOfJavaFieldToJsonField, mapOfJavaFieldToDataType);
    }

    private static void printJsonFieldAndDataType(List<String> useFields, Map<String, String> jsonMap, Map<String, String> dataTypeMap) {
        for (String field : useFields) {
            if(jsonMap.get(field) != null) {
                System.out.println(jsonMap.get(field));
            }
        }
        System.out.println("============================================");
        for (String field : useFields) {
            if(jsonMap.get(field) != null) {
                System.out.println(dataTypeMap.get(field));
            }
        }
    }

    private static List<String> extractUsefulJavaFields(List<String> usefulCols, Map<String, String> map) {
        List<String> listOfJavaFields = new ArrayList<>();
        for (String col : usefulCols) {
            listOfJavaFields.add(map.get(col));
        }
        return listOfJavaFields;
    }

    private static Map<String, String> mapColumnNameToJavaField(String resultMapString) {
        Map<String, String> map = new HashMap<>();
        String[] arr = resultMapString.split("\n");
        String columnIden = "column=\"";
        String propertyIden = "property=\"";
        String endIden = "\"";
        for (String row : arr) {
            int startInd = row.indexOf(columnIden) + columnIden.length();
            int endInd = row.indexOf(endIden, startInd);
            String sqlCol = row.substring(startInd, endInd);
            startInd = row.indexOf(propertyIden) + propertyIden.length();
            endInd = row.indexOf(endIden, startInd);
            String propName = row.substring(startInd, endInd);
            map.put(sqlCol, propName);
        }
        return map;
    }

    private static List<String> extractColumnsThatDoesReturnNull(List<String> listToExtract, List<String> listOfColNames) {
        List<String> names = new ArrayList<>();
        Pattern patternForQuotes = Pattern.compile("[\\s]*[']{2}[\\s]{1,}[a][s][\\s]{1,}[\\w]*", Pattern.CASE_INSENSITIVE);
        Pattern patternForNull = Pattern.compile("[\\s]*\\bnull\\b[\\s]{1,}[a][s][\\s]{1,}[\\w]*", Pattern.CASE_INSENSITIVE);
        Pattern patternForZero = Pattern.compile("[\\s]*[0][\\s]{1,}[a][s][\\s]{1,}[\\w]*", Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < listToExtract.size(); i++) {
            String val = listToExtract.get(i).trim();

            if (!patternForNull.matcher(val).matches() && !patternForZero.matcher(val).matches() && !patternForQuotes.matcher(val).matches()
            && !val.equals("''") && !val.equals("0") && !val.equals("null") && !val.equals("NULL")) {
                names.add(listOfColNames.get(i));
            }
        }
        return names;
    }

    private static List<String> extractColNames(List<String> baseColList) {
        List<String> colNames = new ArrayList<>();
        for (String col : baseColList) {
            String val = col.toUpperCase();
            int startInd = val.indexOf("AS ") + 3;
            colNames.add(col.substring(startInd).trim());
        }
        return colNames;
    }

    private static List<String> separateSqlSelectIntoColumns(String cols) {
        List<String> colList = new ArrayList<>();
        boolean isInsideParam = false;
        String colValue = "";
        for (char c : cols.toCharArray()) {
            if (c == '(') {
                isInsideParam = true;
            }
            if (c == ')') {
                isInsideParam = false;
            }
            if (c != '\n') {
                colValue += c;
            } else {
                colValue += ' ';
            }

            if (c == ',') {
                if (!isInsideParam) {
                    colList.add(colValue.substring(0, colValue.length() - 1).trim());
                    colValue = "";
                }
            }
        }
        if (!colValue.trim().isEmpty()) {
            colList.add(colValue);
        }
        return colList;
    }

    private static void generateJavaJsonMapping(Map<String, String> map, String[] javaFieldArr, Map<String, String> mapFieldToDataType) {
        String identifier = "@JsonProperty(\"";
        boolean toIgnore = false;
        String jsonString = "";
        String javaFieldString = "";
        for (String row : javaFieldArr) {
            if (row.trim().isEmpty()) {
                continue;
            }
            if (row.contains(identifier)) {
                int startInd = row.indexOf(identifier) + identifier.length();
                int endInd = row.indexOf("\"", startInd);
                jsonString = row.substring(startInd, endInd);
            }
            if(row.contains("@JsonIgnore")) {
                toIgnore = true;
            }
            if (row.contains("private ")) {
                if(toIgnore) {
                    jsonString = "";
                    javaFieldString = "";
                    toIgnore = false;
                    continue;
                }
                String[] fieldArr = row.trim().split(" ");
                javaFieldString = fieldArr[2].trim();
                javaFieldString = javaFieldString.substring(0, javaFieldString.length() - 1);
                map.put(javaFieldString, jsonString.isEmpty() ? javaFieldString : jsonString);
                mapFieldToDataType.put(javaFieldString, fieldArr[1].trim());
                jsonString = "";
                javaFieldString = "";
            }
        }
    }
}
