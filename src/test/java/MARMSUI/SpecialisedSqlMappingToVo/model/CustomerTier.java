package MARMSUI.SpecialisedSqlMappingToVo.model;


import MARMSUI.SpecialisedSqlMappingToVo.model.EliteParticipant;
import MARMSUI.SpecialisedSqlMappingToVo.model.PPSReserveVal;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomerTier {
    public static final String PENDING_AUTOREQUAL = "P";
    public static final String COMPLETED_AUTOREQUAL = "C";

    private String intID = null;
    private String userID = null;
    private String programCD = null;
    private String tierStatus = null;
    private String currentTierStatus = null;
    private String previousTierStatus = null;
    private String newTierStatus = null;
    private String qlfyInd = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date qlfyStartDate = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date qlfyEndDate = null;
    private long curMileage;
    private float curSectCount;
    private int noYearsQlfd;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date forceQlfyDate = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date forceQlfyExtendedDate = null;
    private int noOfExtendedMonth;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date origExp = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date qlfyDate = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date tierBonusStartDate = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date tierBonusEndDate = null;
    private String disctryTierFlag = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date tierExp = null;

    private String cardCreateCode = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date rundate = null;
    private boolean firstTimer = false;
    private boolean insertToHistory = false;
    private boolean updateAccount = false;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date tierBonusEndDateInHistory;

    private TierQual tierQual;

    public TierQual getTierQual() {
        return tierQual;
    }


    private List<PPSParticipant> ppsParticipantList = null;
    private List<EliteParticipant> eliteParticipantList = null;

    private boolean yearsToIncrement = false;

    public boolean getYearsToIncrement() {
        return yearsToIncrement;
    }

    public void setYearsToIncrement(boolean yearsToIncrement) {
        this.yearsToIncrement = yearsToIncrement;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date promoCardExpDt = null;

    private int yearsInQPP;
    private long curValue;
    private long cumulativeValue;

    private PPSYearQual ppsYearQual;

    private String autoRequalInd;

    //MKP91044----------------
    private boolean incrementYrsInQpp = false;
    private boolean isOnlineForceQualify = false;
    //-----------------------

    private long hisCurValue;
    private boolean reinstateCumVal = false;
    private PPSReserveVal ppsReserveVal = null;
    private TransReserveVal transReserveVal = null;
    private long reserveValToTrack;
    private int reserveValPeriod;
    private String qlfyWthRsrvVal;
    private long valReqFromRsrvVal;
    private boolean reinstateRsrvVal = false;
    private long ppsValReqForRequal;

    private String forceQualType;

    //<Added> by <Meena> on <17-Nov-2012> for <MKP91697> <Starts>
    private String qlfyWthRules = "N";
    //<Added> by <Meena> on <17-Nov-2012> for <MKP91697> <Ends>

    //Added for MKP91420 start
    private String emailAddress;

    //Added <Anila> on <17-May-2013> for <MKP91864> <starts>
    private String highValued = "N";
    //Added <Anila> on <17-May-2013> for <MKP91864> <ends>
    // Added by bhaskar MKP91867 starts
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date qlfyGEndDate = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date qlfyGStartDate = null;
    private long curGoldMileage = 0;
    private boolean isInterimTPP = false; //Added by Aravind for MKP92600
    //Added by Vinod for MKP92600 Starts
    private String interimFlg = null;
    //Added by Vinod for MKP92600 Ends

    private String prevPPSTier = null; //Added by Deepa for MKP92708 - TPP Phase 2

    //Added by Logesh for MKP92708 - TPP Phase 2 - Starts
    private String qualScheme = null;

    private boolean isQualSchemeTPP = false;

    private int consecutiveYrsInPPS;

    private long rvForRI = 0;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date qlfyExpDate = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date qlfyEndDateRI = null;

    //Added by Logesh for MKP92708 - TPP Phase 2 - Ends
    //Added by Vinod for MKP92708 Starts
    private boolean isDwngrdMbr = false;
    //Added by Vinod for MKP92708 Ends

    //Added by Aravind for MKP92759 - Starts
    private boolean isForceDwngQPP = false;
    //Added by Aravind for MKP92759 - Ends
    //Added by Dhanuja for MKP92708 CR - Starts
    private boolean recalReserveVal = false;


    //Added By KAVI for KFPROG-3463 - STARTS
    private long naKfmrCurMileage = 0;
    private long naCurMileage = 0;
    //Added By KAVI for KFPROG-3463 - ENDS

    //Added By Keerthana for KFPROG-3647 - STARTS
    private long naCurVal = 0;
    private long naRwdVal = 0;
    private long naGKfmrCurMileage = 0;

    private long naGCurMileage = 0;
    //Added By Keerthana for KFPROG-3647 - ENDS

    public boolean getRecalReserveVal() {
        return recalReserveVal;
    }

    public void setRecalReserveVal(boolean recalReserveVal) {
        this.recalReserveVal = recalReserveVal;
    }

    //Added by Dhanuja for MKP92708 CR - Ends
    //Added by santhosh for MKP92749 starts
    private int tierDuration;

    public int getTierDuration() {
        return tierDuration;
    }

    public void setTierDuration(int tierDuration) {
        this.tierDuration = tierDuration;
    }

    private String tierGroup = null;

    public String getTierGroup() {
        return tierGroup;
    }

    public void setTierGroup(String tierGroup) {
        this.tierGroup = tierGroup;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date actualQlfyStartDate = null;

    public Date getActualQlfyStartDate() {
        return actualQlfyStartDate;
    }

    public void setActualQlfyStartDate(Date actualQlfyStartDate) {
        this.actualQlfyStartDate = actualQlfyStartDate;
    }

    //Added by santhosh for MKP92749 ends
    public long getCurGoldMileage() {
        return curGoldMileage;
    }

    public void setCurGoldMileage(long curGoldMileage) {
        this.curGoldMileage = curGoldMileage;
    }

    public Date getQlfyGEndDate() {
        return qlfyGEndDate;
    }

    public void setQlfyGEndDate(Date qlfyGEndDate) {
        this.qlfyGEndDate = qlfyGEndDate;
    }

    public Date getQlfyGStartDate() {
        return qlfyGStartDate;
    }

    public void setQlfyGStartDate(Date qlfyGStartDate) {
        this.qlfyGStartDate = qlfyGStartDate;
    }

    private boolean eliteGold = false;
    private boolean prequal = false;

    public boolean isPrequal() {
        return prequal;
    }

    public void setPrequal(boolean prequal) {
        this.prequal = prequal;
    }

    public boolean isEliteGold() {
        return eliteGold;
    }

    public void setEliteGold(boolean eliteGold) {
        this.eliteGold = eliteGold;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String aEmail1) {
        emailAddress = aEmail1;
    }

    public long getPpsValReqForRequal() {
        return ppsValReqForRequal;
    }

    public void setPpsValReqForRequal(long ppsValReqForRequal) {
        this.ppsValReqForRequal = ppsValReqForRequal;
    }

    public boolean isReinstateRsrvVal() {
        return reinstateRsrvVal;
    }

    public void setReinstateRsrvVal(boolean reinstateRsrvVal) {
        this.reinstateRsrvVal = reinstateRsrvVal;
    }

    public String getQlfyWthRsrvVal() {
        return qlfyWthRsrvVal;
    }

    public void setQlfyWthRsrvVal(String qlfyWthRsrvVal) {
        this.qlfyWthRsrvVal = qlfyWthRsrvVal;
    }


    public void init(CustomerTier cus) {
        this.setIntID(cus.getIntID());
        this.setTierStatus(cus.getTierStatus());
    }

    public final String getIntID() {
        return this.intID;
    }


    public String getTierStatus() {
        return this.tierStatus;
    }



    public String getCurrentTierStatus() {
        return this.currentTierStatus;
    }

    public String getPreviousTierStatus() {
        return this.previousTierStatus;
    }

    public String getNewTierStatus() {
        return this.newTierStatus;
    }


    public Date getQlfyStartDate() {
        return this.qlfyStartDate;
    }

    public String getQlfyInd() {
        return this.qlfyInd;
    }

    public Date getQlfyEndDate() {
        return this.qlfyEndDate;
    }

    public long getCurMileage() {
        return this.curMileage;
    }

    public float getCurSectCount() {
        return this.curSectCount;
    }

    public int getNoYearsQlfd() {
        return this.noYearsQlfd;
    }

    public Date getForceQlfyDate() {
        return this.forceQlfyDate;
    }

    public Date getForceQlfyExtendedDate() {
        return this.forceQlfyExtendedDate;
    }

    public int getNoOfExtendedMonth() {
        return this.noOfExtendedMonth;
    }

    public Date getOrigExp() {
        return this.origExp;
    }

    public Date getQlfyDate() {
        return this.qlfyDate;
    }

    public Date getTierExp() {
        return this.tierExp;
    }

    public Date getTierBonusStartDate() {
        return this.tierBonusStartDate;
    }

    public Date getTierBonusEndDate() {
        return this.tierBonusEndDate;
    }

    public String getDisctryTierFlag() {
        return this.disctryTierFlag;
    }

    public Date getRundate() {
        return this.rundate;
    }

    public boolean isFirstTimer() {
        return this.firstTimer;
    }


    public boolean isInsertToHistory() {
        return this.insertToHistory;
    }

    public boolean isUpdateAccount() {
        return this.updateAccount;
    }

    public final void setIntID(String sIntid) {
        this.intID = sIntid;
    }


    public void setTierStatus(String sTierStatus) {
        this.tierStatus = sTierStatus;
    }

    public void setCurrentTierStatus(String currentTierStatus) {
        this.currentTierStatus = currentTierStatus;
    }

    public void setPreviousTierStatus(String previousTierStatus) {
        this.previousTierStatus = previousTierStatus;
    }

    public void setNewTierStatus(String snewTierStatus) {
        this.newTierStatus = snewTierStatus;
    }

    public void setUserID(String sUserid) {
        this.userID = sUserid;
    }

    public void setQlfyInd(String qlfyInd) {
        this.qlfyInd = qlfyInd;
    }

    public void setQlfyStartDate(Date qlfyStartDate) {
        this.qlfyStartDate = qlfyStartDate;
    }

    public void setQlfyEndDate(Date qlfyEndDate) {
        this.qlfyEndDate = qlfyEndDate;
    }

    public void setCurMileage(long curMileage) {
        this.curMileage = curMileage;
    }

    public void setCurSectCount(float curSectCount) {
        this.curSectCount = curSectCount;
    }

    public void setNoYearsQlfd(int noYearsQlfd) {
        this.noYearsQlfd = noYearsQlfd;
    }

    public void setForceQlfyDate(Date forceQlfyDate) {
        this.forceQlfyDate = forceQlfyDate;
    }

    public void setForceQlfyExtendedDate(Date forceQlfyExtendedDate) {
        this.forceQlfyExtendedDate = forceQlfyExtendedDate;
    }

    public void setNoOfExtendedMonth(int noOfExtendedMonth) {
        this.noOfExtendedMonth = noOfExtendedMonth;
    }

    public void setOrigExp(Date origExp) {
        this.origExp = origExp;
    }

    public void setQlfyDate(Date qlfyDate) {
        this.qlfyDate = qlfyDate;
    }

    public void setTierExp(Date tierExp) {
        this.tierExp = tierExp;
    }

    public void setTierBonusStartDate(Date tierBonusStartDate) {
        this.tierBonusStartDate = tierBonusStartDate;
    }

    public void setTierBonusEndDate(Date tierBonusEndDate) {
        this.tierBonusEndDate = tierBonusEndDate;
    }

    public void setDisctryTierFlag(String disctryTierFlag) {
        this.disctryTierFlag = disctryTierFlag;
    }

    public void setRundate(Date rundate) {
        this.rundate = rundate;
    }

    public void setFirstTimer(boolean firstTimer) {
        this.firstTimer = firstTimer;
    }

    public void setInsertToHistory(boolean insertToHistory) {
        this.insertToHistory = insertToHistory;
    }

    public void setUpdateAccount(boolean updateAccount) {
        this.updateAccount = updateAccount;
    }


    public List<PPSParticipant> getPPSParticipantList() {
        return this.ppsParticipantList;
    }

    public void setTierQual(TierQual cTierQual) {
        this.tierQual = cTierQual;
    }

    public void setPPSParticipantList(List<PPSParticipant> qualPrtList) {
        this.ppsParticipantList = qualPrtList;
    }

    public Date getPromoCardExpDt() {
        return promoCardExpDt;
    }

    public void setPromoCardExpDt(Date promoCardExpDt) {
        this.promoCardExpDt = promoCardExpDt;
    }

    public int getYearsInQPP() {
        return yearsInQPP;
    }

    public void setYearsInQPP(int yearsInQPP) {
        this.yearsInQPP = yearsInQPP;
    }

    public long getCurValue() {
        return curValue;
    }

    public void setCurValue(long curValue) {
        this.curValue = curValue;
    }

    public long getCumulativeValue() {
        return cumulativeValue;
    }

    public void setCumulativeValue(long cumulativeValue) {
        this.cumulativeValue = cumulativeValue;
    }



    /**
     * Given a date checks if it falls between qualification start and end date.
     *
     * @author sachin_datar
     */
    public boolean isInQualificationRange(Date d) {
        boolean retVal = false;
        if (qlfyStartDate != null && qlfyEndDate != null && d != null) {
            if (d.after(qlfyStartDate) && d.before(qlfyEndDate)) {
                retVal = true;
            } else {
                Calendar cal = Calendar.getInstance();

                cal.setTime(d);
                int year1 = cal.get(Calendar.YEAR);
                int month1 = cal.get(Calendar.MONTH);
                int day1 = cal.get(Calendar.DAY_OF_MONTH);

                cal.setTime(qlfyStartDate);
                int year2 = cal.get(Calendar.YEAR);
                int month2 = cal.get(Calendar.MONTH);
                int day2 = cal.get(Calendar.DAY_OF_MONTH);

                if (year1 == year2 && month1 == month2 && day1 == day2) {
                    retVal = true;
                } else {
                    cal.setTime(qlfyEndDate);
                    int year3 = cal.get(Calendar.YEAR);
                    int month3 = cal.get(Calendar.MONTH);
                    int day3 = cal.get(Calendar.DAY_OF_MONTH);

                    if (year1 == year3 && month1 == month3 && day1 == day3) {
                        retVal = true;
                    }
                }
            }
        }
        return retVal;
    }

    /**
     * Given a date checks if it falls between tier bonus start and end date.
     *
     * @author sachin_datar
     */
    public boolean isInBonusAwardRange(Date d) {
        boolean retVal = false;
        if (tierBonusStartDate != null && tierBonusEndDate != null && d != null) {
            if (d.after(tierBonusStartDate) && d.before(tierBonusEndDate)) {
                retVal = true;
            } else {
                Calendar cal = Calendar.getInstance();

                cal.setTime(d);
                int year1 = cal.get(Calendar.YEAR);
                int month1 = cal.get(Calendar.MONTH);
                int day1 = cal.get(Calendar.DAY_OF_MONTH);

                cal.setTime(tierBonusStartDate);
                int year2 = cal.get(Calendar.YEAR);
                int month2 = cal.get(Calendar.MONTH);
                int day2 = cal.get(Calendar.DAY_OF_MONTH);

                if (year1 == year2 && month1 == month2 && day1 == day2) {
                    retVal = true;
                } else {
                    cal.setTime(tierBonusEndDate);
                    int year3 = cal.get(Calendar.YEAR);
                    int month3 = cal.get(Calendar.MONTH);
                    int day3 = cal.get(Calendar.DAY_OF_MONTH);

                    if (year1 == year3 && month1 == month3 && day1 == day3) {
                        retVal = true;
                    }
                }
            }
        }
        return retVal;
    }

    /**
     * Check if the passed tier staus is same as current tier of
     * current bucket.
     */
    public boolean isHoldingTierStatus(String checkTierStatus) {
        boolean retVal = false;
        if (checkTierStatus != null &&
                this.tierStatus != null &&
                checkTierStatus.equals(this.tierStatus) &&
                (this.qlfyInd != null &&
                        !this.qlfyInd.equals("EP") &&
                        !this.qlfyInd.equals("PP"))) {
            retVal = true;
        }
        return retVal;
    }

    public Date getTierBonusEndDateInHistory() {
        return tierBonusEndDateInHistory;
    }

    /**
     * Sets the tierBonusEndDateInHistory.
     *
     * @param tierBonusEndDateInHistory The tierBonusEndDateInHistory to set
     */
    public void setTierBonusEndDateInHistory(Date tierBonusEndDateInHistory) {
        this.tierBonusEndDateInHistory = tierBonusEndDateInHistory;
    }

    /**
     * Returns the cardCreateCode.
     *
     * @return String
     */
    public String getCardCreateCode() {
        return cardCreateCode;
    }

    /**
     * Sets the cardCreateCode.
     *
     * @param cardCreateCode The cardCreateCode to set
     */
    public void setCardCreateCode(String cardCreateCode) {
        this.cardCreateCode = cardCreateCode;
    }

    /**
     * @return
     */
    public List<EliteParticipant> getEliteParticipantList() {
        return eliteParticipantList;
    }

    /**
     * @param list
     */
    public void setEliteParticipantList(List<EliteParticipant> list) {
        eliteParticipantList = list;
    }

    public PPSYearQual getPpsYearQual() {
        return ppsYearQual;
    }

    public void setPpsYearQual(PPSYearQual ppsYearQual) {
        this.ppsYearQual = ppsYearQual;
    }

    public String getAutoRequalInd() {
        return autoRequalInd;
    }

    public void setAutoRequalInd(String autoRequalInd) {
        this.autoRequalInd = autoRequalInd;
    }

    //MKP91044-----------------
    public boolean isIncrementYrsInQpp() {
        return incrementYrsInQpp;
    }

    /**
     * @param b boolean value
     */
    public void setIncrementYrsInQpp(boolean b) {
        incrementYrsInQpp = b;
    }
    //-----------------------------


    public boolean getIsOnlineForceQualify() {
        return isOnlineForceQualify;
    }

    /**
     * @param b boolean value
     */
    public void setIsOnlineForceQualify(boolean b) {
        isOnlineForceQualify = b;
    }

    public long getHisCurValue() {
        return hisCurValue;
    }

    public void setHisCurValue(long hisCurValue) {
        this.hisCurValue = hisCurValue;
    }

    public boolean isReinstateCumVal() {
        return reinstateCumVal;
    }

    public void setReinstateCumVal(boolean reinstateCumVal) {
        this.reinstateCumVal = reinstateCumVal;
    }

    public PPSReserveVal getPpsReserveVal() {
        return ppsReserveVal;
    }

    public void setPpsReserveVal(PPSReserveVal ppsReserveVal) {
        this.ppsReserveVal = ppsReserveVal;
    }

    public TransReserveVal getTransReserveVal() {
        return transReserveVal;
    }

    public void setTransReserveVal(TransReserveVal transReserveVal) {
        this.transReserveVal = transReserveVal;
    }

    public int getReserveValPeriod() {
        return reserveValPeriod;
    }

    public void setReserveValPeriod(int reserveValPeriod) {
        this.reserveValPeriod = reserveValPeriod;
    }

    public long getReserveValToTrack() {
        return reserveValToTrack;
    }

    public void setReserveValToTrack(long reserveValToTrack) {
        this.reserveValToTrack = reserveValToTrack;
    }

    public long getValReqFromRsrvVal() {
        return valReqFromRsrvVal;
    }

    public void setValReqFromRsrvVal(long valReqFromRsrvVal) {
        this.valReqFromRsrvVal = valReqFromRsrvVal;
    }

    public String getForceQualType() {
        return forceQualType;
    }

    public void setForceQualType(String forceQualType) {
        this.forceQualType = forceQualType;
    }

    //<Added> by <Meena> on <17-Nov-2012> for <MKP91697> <Starts>
    public String getQlfyWthRules() {
        return qlfyWthRules;
    }

    public void setQlfyWthRules(String qlfyWthRules) {
        this.qlfyWthRules = qlfyWthRules;
    }
    //<Added> by <Meena> on <17-Nov-2012> for <MKP91697> <Ends>

    //Added by <Anila> on <17-May-2013> for <MKP91864> <Starts>
    public String getHighValued() {
        return highValued;
    }

    public void setHighValued(String highValued) {
        this.highValued = highValued;
    }

    //Added by <Anila> on <17-May-2013> for <MKP91864> <Ends>
    //Added by Aravind for MKP92600 - STARTS
    public boolean getIsInterimTPP() {
        return isInterimTPP;
    }

    public void setIsInterimTPP(boolean isInterimTPP) {
        this.isInterimTPP = isInterimTPP;
    }

    //Added by Aravind for MKP92600 - ENDS
    //Added by Vinod for MKP92600 Starts
    public String getInterimFlg() {
        return interimFlg;
    }

    public void setInterimFlg(String interimFlg) {
        this.interimFlg = interimFlg;
    }
    //Added by Vinod for MKP92600 Ends
    //Added by Deepa for MKP92708 - TPP Phase 2 Starts

    public String getPrevPPSTier() {
        return prevPPSTier;
    }

    public void setPrevPPSTier(String prevPPSTier) {
        this.prevPPSTier = prevPPSTier;
    }

    //Added by Deepa for MKP92708 - TPP Phase 2 Ends
    //Added by Logesh for MKP92708 - TPP Phase 2 starts

    public boolean getIsQualSchemeTPP() {
        return isQualSchemeTPP;
    }

    public void setIsQualSchemeTPP(boolean isQualSchemeTPP) {
        this.isQualSchemeTPP = isQualSchemeTPP;
    }

    public String getQualScheme() {

        return qualScheme;

    }

    public void setQualScheme(String qualScheme) {
        this.qualScheme = qualScheme;

    }


    public int getconsecutiveYrsInPPS() {
        return consecutiveYrsInPPS;
    }

    public void setconsecutiveYrsInPPS(int consecutiveYrsInPPS) {
        this.consecutiveYrsInPPS = consecutiveYrsInPPS;
    }

    public void setRvForRI(long rvForRI) {
        this.rvForRI = rvForRI;
    }

    public long getRvForRI() {
        return this.rvForRI;
    }

    public void setQlfyExpDate(Date qlfyExpDate) {
        this.qlfyExpDate = qlfyExpDate;
    }

    public Date getQlfyExpDate() {
        return this.qlfyExpDate;
    }


    public void setQlfyEndDateRI(Date qlfyEndDateRI) {
        this.qlfyEndDateRI = qlfyEndDateRI;
    }

    public Date getQlfyEndDateRI() {
        return this.qlfyEndDateRI;
    }

    //Added by Logesh for MKP92708 - TPP Phase 2 Ends
    //Added by Vinod for MKP92708 Starts
    public boolean getIsDwngrdMbr() {
        return isDwngrdMbr;
    }

    public void setIsDwngrdMbr(boolean isDwngrdMbr) {
        this.isDwngrdMbr = isDwngrdMbr;
    }
    //Added by Vinod for MKP92708 Ends

    //Added by Aravind for MKP92759 - Starts
    public boolean getIsForceDwngQPP() {
        return isForceDwngQPP;
    }

    public void setIsForceDwngQPP(boolean isForceDwngQPP) {
        this.isForceDwngQPP = isForceDwngQPP;
    }

    //Added by Aravind for MKP92759 - Ends
    //Added by Vinod for MKP92708 -CR2 phase 2 Starts
    private boolean isOldScheme = false;

    public boolean getIsOldScheme() {
        return isOldScheme;
    }

    public void setIsOldScheme(boolean isOldScheme) {
        this.isOldScheme = isOldScheme;
    }

    private boolean isInvalidate = false;

    public boolean getIsInvalidate() {
        return isInvalidate;
    }

    public void setIsInvalidate(boolean isInvalidate) {
        this.isInvalidate = isInvalidate;
    }
    //Added by Vinod for MKP92708 -CR2 phase 2 ends

    //Added By KAVI for KFPROG-3463 - STARTS
    public long getNaKfmrCurMileage() {
        return naKfmrCurMileage;
    }

    public void setNaKfmrCurMileage(long naKfmrCurMileage) {
        this.naKfmrCurMileage = naKfmrCurMileage;
    }

    public long getNaCurMileage() {
        return naCurMileage;
    }

    public void setNaCurMileage(long naCurMileage) {
        this.naCurMileage = naCurMileage;
    }

    //Added By KAVI for KFPROG-3463 - ENDS
    //Added By Keerthana for KFPROG-3647 - STARTS
    public long getNaCurVal() {
        return naCurVal;
    }

    public void setNaCurVal(long naCurVal) {
        this.naCurVal = naCurVal;
    }

    public long getNaRwdVal() {
        return naRwdVal;
    }

    public void setNaRwdVal(long naRwdVal) {
        this.naRwdVal = naRwdVal;
    }

    //Added By Keerthana for KFPROG-3647 - ENDS
    //Added by Keerthana for KFPROG-3759 starts
    public long getNaGKfmrCurMileage() {
        return naGKfmrCurMileage;
    }

    public void setNaGKfmrCurMileage(long naGKfmrCurMileage) {
        this.naGKfmrCurMileage = naGKfmrCurMileage;
    }

    public long getNaGCurMileage() {
        return naGCurMileage;
    }

    public void setNaGCurMileage(long naGCurMileage) {
        this.naGCurMileage = naGCurMileage;
    }

    //Added by Keerthana for KFPROG-3759 ends
    //Added by Binzila for testing

    public String getProgramCD() {
        return programCD;
    }

    public void setProgramCD(String programCD) {
        this.programCD = programCD;
    }
    @Override
    public String toString() {
        return "CustomerTier{" +
                "intID='" + intID + '\'' +
                ", userID='" + userID + '\'' +
                ", programCD='" + programCD + '\'' +
                ", tierStatus='" + tierStatus + '\'' +
                ", currentTierStatus='" + currentTierStatus + '\'' +
                ", previousTierStatus='" + previousTierStatus + '\'' +
                ", newTierStatus='" + newTierStatus + '\'' +
                ", qlfyInd='" + qlfyInd + '\'' +
                ", qlfyStartDate=" + qlfyStartDate +
                ", qlfyEndDate=" + qlfyEndDate +
                ", curMileage=" + curMileage +
                ", curSectCount=" + curSectCount +
                ", noYearsQlfd=" + noYearsQlfd +
                ", forceQlfyDate=" + forceQlfyDate +
                ", forceQlfyExtendedDate=" + forceQlfyExtendedDate +
                ", noOfExtendedMonth=" + noOfExtendedMonth +
                ", origExp=" + origExp +
                ", qlfyDate=" + qlfyDate +
                ", tierBonusStartDate=" + tierBonusStartDate +
                ", tierBonusEndDate=" + tierBonusEndDate +
                ", disctryTierFlag='" + disctryTierFlag + '\'' +
                ", tierExp=" + tierExp +
                ", cardCreateCode='" + cardCreateCode + '\'' +
                ", rundate=" + rundate +
                ", firstTimer=" + firstTimer +
                ", insertToHistory=" + insertToHistory +
                ", updateAccount=" + updateAccount +
                ", tierBonusEndDateInHistory=" + tierBonusEndDateInHistory +
                ", theTier_Qual=" + tierQual +
                ", ppsParticipantList=" + ppsParticipantList +
                ", eliteParticipantList=" + eliteParticipantList +
                ", yearsToIncrement=" + yearsToIncrement +
                ", promoCardExpDt=" + promoCardExpDt +
                ", yearsInQPP=" + yearsInQPP +
                ", curValue=" + curValue +
                ", cumulativeValue=" + cumulativeValue +
                ", ppsYearQual=" + ppsYearQual +
                ", autoRequalInd='" + autoRequalInd + '\'' +
                ", incrementYrsInQpp=" + incrementYrsInQpp +
                ", isOnlineForceQualify=" + isOnlineForceQualify +
                ", hisCurValue=" + hisCurValue +
                ", reinstateCumVal=" + reinstateCumVal +
                ", ppsReserveVal=" + ppsReserveVal +
                ", transReserveVal=" + transReserveVal +
                ", reserveValToTrack=" + reserveValToTrack +
                ", reserveValPeriod=" + reserveValPeriod +
                ", qlfyWthRsrvVal=" + qlfyWthRsrvVal +
                ", valReqFromRsrvVal=" + valReqFromRsrvVal +
                ", reinstateRsrvVal=" + reinstateRsrvVal +
                ", ppsValReqForRequal=" + ppsValReqForRequal +
                ", forceQualType='" + forceQualType + '\'' +
                ", qlfyWthRules=" + qlfyWthRules +
                ", m_emailAddress='" + emailAddress + '\'' +
                ", highValued=" + highValued +
                ", qlfyGEndDate=" + qlfyGEndDate +
                ", qlfyGStartDate=" + qlfyGStartDate +
                ", curGoldMileage=" + curGoldMileage +
                ", isInterimTPP=" + isInterimTPP +
                ", interimFlg='" + interimFlg + '\'' +
                ", prevPPSTier='" + prevPPSTier + '\'' +
                ", qualScheme='" + qualScheme + '\'' +
                ", isQualSchemeTPP=" + isQualSchemeTPP +
                ", consecutiveYrsInPPS=" + consecutiveYrsInPPS +
                ", rvForRI=" + rvForRI +
                ", qlfyExpDate=" + qlfyExpDate +
                ", qlfyEndDateRI=" + qlfyEndDateRI +
                ", isDwngrdMbr=" + isDwngrdMbr +
                ", isForceDwngQPP=" + isForceDwngQPP +
                ", recalReserveVal=" + recalReserveVal +
                ", naKfmrCurMileage=" + naKfmrCurMileage +
                ", naCurMileage=" + naCurMileage +
                ", naCurVal=" + naCurVal +
                ", naRwdVal=" + naRwdVal +
                ", tierDuration=" + tierDuration +
                ", tierGroup='" + tierGroup + '\'' +
                ", actualQlfyStartDate=" + actualQlfyStartDate +
                ", eliteGold=" + eliteGold +
                ", prequal=" + prequal +
                ", isOldScheme=" + isOldScheme +
                ", isInvalidate=" + isInvalidate +
                '}';
    }
    //Added by Binzila for testing
}
