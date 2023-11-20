package MARMSUI.SpecialisedSqlMappingToVo;


import java.util.Date;


/**
 * Valueobject representing the TIER_STAT table record.
 * Each instance of this represent one rule like elite qualification rule,
 * elite requalification rule or pps qualification rule...etc.
 * <p>
 * Various variable which can be tunned in rule are.
 * 1. Current miles required - (Current miles offset).
 * 2. Current sectors reuired - (Current sectors offset).
 * 3. Accumulated miles required - (Accumulated miles offset).
 * 4. Accumulated sectors required - (Accumulated sectors offset).
 * 5. Minimum continous years requirement.
 * <p>
 * The values are user configurable but not the operators itself.
 * Ex:
 * Should the current miles <b> & (and)<b> sectors requirement to be mate to attain a tier status.
 * or Should the current miles <b> | (or) <b> sectors requirement to be mate to attain a tier status.
 * these operators logic is hardcoded inside the programs for each rule and is based on user requirement.
 *
 * <B>Please note that currently though data structure provides the scalability of specifying any of
 * above mentioned variable for any tier status rules they may not be supported by program.
 * Refer to use case document to find out what variables are applicale to what tier status and under
 * what preconditions.<B>
 *
 * @author sachin_datar
 */
public class Tierstat {

    public static final String MILE_SECTOR_RULE = "M";
    public static final String PPS_VALUE_RULE = "V";

    /**
     * The seq no unique among the records of same tier status and qualification proc indicator.
     * Ex. Currently there are 3 rules to attain tier status PPS 'T'.
     */
    private int seqNo;

    /**
     * The tier status to which this rule applies.
     */
    private String tierStatus;

    /**
     * The program code to which the rule applies.
     */
    private String tierTypeInd;

    /**
     * The program code to which the rule applies.
     */
    private String programCd;

    /**
     * Indicate the overall standing of this tier status irrespective of
     * qualification type Elite/PPS it belongs.
     * Ex. Among tier status S/G/Q/T/L, PPS 'L' membership is highest hence
     * hirachy 5...where as Elite 'S' is lowest hence hirachcy 1.
     */
    private int hierarchy;

    /**
     * The minimum no of continous years requirement.
     * Ex. Currently some of PPS 'Q' to 'T' updgrade rules have minimum years requirement like
     * member should be continous member for 5 years...etc.
     */
    private long minPeriod;


    //sachin, 14Mar03. New fields
    /**
     * Var to store the process category to which this rule belongs.
     * ex.
     * EP/PP indicate rule for Elite/PPS qualification.
     * ER/PR indicate rule for Elite/PPS Re qualification.
     * PU	   indicate PPS  for PPS upgrade.
     */
    private String qualProcID;

    private float sectReq;

    /**
     * Var to store the miles to be offseted from current miles requirement.
     */
    private long pointOffset;

    /**
     * Var to store the sectors to be offseted from current sectors requirement.
     */
    private float sectorOffset;

    /**
     * Var to store the accumulated miles requirement.
     * Ex. Currently some of PPS 'Q' to PPS 'T' upgrade rule are based on accumulated miles/sectors.
     */
    private long accPtsReq;

    /**
     * Var to store the accumulated sectors requirement.
     * Ex. Currently some of PPS 'Q' to PPS 'T' upgrade rule are based on accumulated miles/sectors.
     */
    private float accSectReq;

    private long ptsReq;

    /**
     * Var to store the miles to be offseted from accumulated miles requirement.
     */
    private long accPtsOffset;

    /**
     * Var to store the sectors to be offseted from accumulated sectors requirement.
     */
    private float acctSectOffset;
    //end sachin.


    //--Sachin new fields 29Sep2003.
    /**
     * To store is rule is active or deactivated by user.
     * default is true which means all rules by default are activated.
     */
    private String active = "Y";

    /**
     * To store the applicable qualification indiacators.
     */
    private String applQualInd = null;


    /**
     * Store the applicable qualification indicator as list.
     */
    private java.util.List applQualIndList = null;


    /**
     * To store the rule information text.
     */
    private String ruleInfo = null;

    //end sachin

    private String addFlag;
    private String deleteFlag;
    private String updateFlag;
    private String viewFlag;
    private String allFlag;

    /**
     * The tier status to which this rule applies.
     */
    private String classCode;

    // Added by Ramzan, 08/12/2006
    // - Start

    /**
     * Qualification period in months (E.g. 12 or 24)
     */
    private int tierQuadPrd;

    /**
     * PPS Values required to qualify the status.
     */
    private long valReq;

    /**
     * Discounted criteria as an actual value. (e.g. if 90% or 80% of
     * the full PPS Values is 500, the column will have 500 and not 80% or 90%)
     */
    private long valOffset;

    /**
     * Accumulated PPS Values required to qualify the status.
     */
    private long accValReq;

    /**
     * Discounted criteria as an actual value. (e.g. if 90% or 80% of
     * the full accumulatedPPS Values is 500, the column will have 500 and not 80% or 90%)
     */
    private long accValOffset;

    /**
     * V / M. Value 'V' - If the rule is based on the PPS Value. 'M' -
     * If the rule is based on Miles and Sectors.
     */
    private String ruleInd;

    /**
     * Effective date of tier rule
     */
	/*private Date startDate;
	
	/**
	 * Expiry date of tier rule
	 *	
	private Date endDate;*/

    /**
     * No of consecutive years required for qualification.
     */
    private int yrsInTier;

    private int eliteOrPPSpercentage;

    // - End

    //Added by Dhanuja for TPP Qual Requal - Starts
    private String qualInterim;

    public String getQualInterim() {
        return qualInterim;
    }

    public void setQualInterim(String interim) {
        qualInterim = interim;
    }


    //Added by Dhanuja for TPP Qual Requal - Ends

    /**
     * Access method for the m_AddFlag property.
     *
     * @return the current value of the m_AddFlag property
     */
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String clsCode) {
        classCode = clsCode;
    }

    /**
     * The date this participant join KF partnership.
     */
    private Date startDate;

    /**
     * The end date the partnership with KF will end
     */
    private Date endDate;

    /**
     * Access method for the m_StartDate property.
     *
     * @return the current value of the m_StartDate property
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the m_StartDate property.
     *
     * @param aStartDate the new value of the m_StartDate property
     */
    public void setStartDate(Date aStartDate) {
        startDate = aStartDate;
    }

    /**
     * Access method for the m_EndDate property.
     *
     * @return the current value of the m_EndDate property
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the m_EndDate property.
     *
     * @param aEndDate the new value of the m_EndDate property
     */
    public void setEndDate(Date aEndDate) {
        endDate = aEndDate;
    }

    /**
     * The seq no unique among the records of same tier status and qualification proc indicator.
     * Ex. Currently there are 3 rules to attain tier status PPS 'T'.
     */
    private int tierBonusFactor;
    private int tierBonusValue;

    public int getTierBonusFactor() {
        return this.tierBonusFactor;
    }

    public void setTierBonusFactor(int tierBonusFct) {
        this.tierBonusFactor = tierBonusFct;
    }

    public int getTierBonusValue() {
        return this.tierBonusValue;
    }

    public void setTierBonusValue(int tierBonusVlu) {
        this.tierBonusValue = tierBonusVlu;
    }

    /**
     * Access method for the m_AddFlag property.
     *
     * @return the current value of the m_AddFlag property
     */
    public String getAddFlag() {
        return addFlag;
    }

    /**
     * Sets the value of the m_AddFlag property.
     *
     * @param aAddFlag the new value of the m_AddFlag property
     */
    public void setAddFlag(String aAddFlag) {
        addFlag = aAddFlag;
    }

    /**
     * Access method for the m_AllFlag property.
     *
     * @return the current value of the m_AllFlag property
     */
    public String getAllFlag() {
        return allFlag;
    }

    public void setAllFlag(String aAllFlag) {
        allFlag = aAllFlag;
    }

    /**
     * Access method for the m_DeleteFlag property.
     *
     * @return the current value of the m_DeleteFlag property
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * Sets the value of the m_DeleteFlag property.
     *
     * @param aDeleteFlag the new value of the m_DeleteFlag property
     */
    public void setDeleteFlag(String aDeleteFlag) {
        deleteFlag = aDeleteFlag;
    }

    /**
     * Access method for the m_UpdateFlag property.
     *
     * @return the current value of the m_UpdateFlag property
     */
    public String getUpdateFlag() {
        return updateFlag;
    }

    /**
     * Sets the value of the m_UpdateFlag property.
     *
     * @param aUpdateFlag the new value of the m_UpdateFlag property
     */
    public void setUpdateFlag(String aUpdateFlag) {
        updateFlag = aUpdateFlag;
    }

    /**
     * Access method for the m_ViewFlag property.
     *
     * @return the current value of the m_ViewFlag property
     */
    public String getViewFlag() {
        return viewFlag;
    }

    /**
     * Sets the value of the m_ViewFlag property.
     *
     * @param aViewFlag the new value of the m_ViewFlag property
     */
    public void setViewFlag(String aViewFlag) {
        viewFlag = aViewFlag;
    }


    public void init(Tierstat record) {
        this.setProgramCd(record.getProgramCd());
    }

    public int getSeqNo() {
        return this.seqNo;
    }

    public String getProgramCd() {
        return this.programCd;
    }

    public String getTierTypeInd() {
        return this.tierTypeInd;
    }

    public String getTierStatus() {
        return this.tierStatus;
    }

    public long getPtsReq() {
        return this.ptsReq;
    }

    public float getSectReq() {
        return this.sectReq;
    }

    public long getMinPeriod() {
        return this.minPeriod;
    }

    public int getHierarchy() {
        return this.hierarchy;
    }

    public void setSeqNo(int Seqno) {
        this.seqNo = Seqno;
    }

    public void setProgramCd(String ProgramCd) {
        this.programCd = ProgramCd;
    }

    public void setTierTypeInd(String Tiertypeind) {
        this.tierTypeInd = Tiertypeind;
    }

    public void setTierStatus(String Tierstatus) {
        this.tierStatus = Tierstatus;
    }

    public void setPtsReq(long PtsReq) {
        this.ptsReq = PtsReq;
    }

    public void setSectReq(float SectReq) {
        this.sectReq = SectReq;
    }

    public void setMinPeriod(long MinPeriod) {
        this.minPeriod = MinPeriod;
    }

    public void setHierarchy(int Hierarchy) {
        this.hierarchy = Hierarchy;
    }

    /**
     * Returns the pointOffest.
     *
     * @return long
     */
    public long getPointOffset() {
        return pointOffset;
    }

    /**
     * Returns the qualProcID.
     *
     * @return String
     */
    public String getQualProcID() {
        return qualProcID;
    }

    /**
     * Returns the sectorOffest.
     *
     * @return float
     */
    public float getSectorOffset() {
        return sectorOffset;
    }

    /**
     * Sets the pointOffest.
     *
     * @param pointOffest The pointOffest to set
     */
    public void setPointOffset(long pointOffest) {
        this.pointOffset = pointOffest;
    }

    /**
     * Sets the qualProcID.
     *
     * @param qualProcID The qualProcID to set
     */
    public void setQualProcID(String qualProcID) {
        this.qualProcID = qualProcID;
    }

    /**
     * Sets the sectorOffest.
     *
     * @param sectorOffest The sectorOffest to set
     */
    public void setSectorOffset(float sectorOffest) {
        this.sectorOffset = sectorOffest;
    }

    /**
     * Returns the accPtsOffset.
     *
     * @return long
     */
    public long getAccPtsOffset() {
        return accPtsOffset;
    }

    /**
     * Returns the accPtsReq.
     *
     * @return long
     */
    public long getAccPtsReq() {
        return accPtsReq;
    }

    /**
     * Returns the accSectReq.
     *
     * @return float
     */
    public float getAccSectReq() {
        return accSectReq;
    }

    /**
     * Returns the acctSectOffset.
     *
     * @return float
     */
    public float getAcctSectOffset() {
        return acctSectOffset;
    }

    /**
     * Sets the accPtsOffset.
     *
     * @param accPtsOffset The accPtsOffset to set
     */
    public void setAccPtsOffset(long accPtsOffset) {
        this.accPtsOffset = accPtsOffset;
    }

    /**
     * Sets the accPtsReq.
     *
     * @param accPtsReq The accPtsReq to set
     */
    public void setAccPtsReq(long accPtsReq) {
        this.accPtsReq = accPtsReq;
    }

    /**
     * Sets the accSectReq.
     *
     * @param accSectReq The accSectReq to set
     */
    public void setAccSectReq(float accSectReq) {
        this.accSectReq = accSectReq;
    }

    /**
     * Sets the acctSectOffset.
     *
     * @param acctSectOffset The acctSectOffset to set
     */
    public void setAcctSectOffset(float acctSectOffset) {
        this.acctSectOffset = acctSectOffset;
    }

    /**
     * @return - false if rule is deactivated by user. (defult is true.)
     */


    /**
     * @return
     */
    public String getApplQualInd() {
        return this.applQualInd;
    }

    public void setApplQualInd(String applQualInd) {
        this.applQualInd = applQualInd;
    }

    /**
     * Return the applicable qual indicators as list.
     *
     * @return
     */
    public java.util.List getApplQualIndList() {
        return this.applQualIndList;
    }


    public String getRuleInfo() {
        return ruleInfo;
    }

    /**
     * @param string
     */
    public void setRuleInfo(String string) {
        ruleInfo = string;
    }

    /**
     * @return Returns the accValOffset.
     */
    public long getAccValOffset() {
        return accValOffset;
    }

    /**
     * @param accValOffset The accValOffset to set.
     */
    public void setAccValOffset(long accValOffset) {
        this.accValOffset = accValOffset;
    }

    /**
     * @return Returns the accValReq.
     */
    public long getAccValReq() {
        return accValReq;
    }

    /**
     * @param accValReq The accValReq to set.
     */
    public void setAccValReq(long accValReq) {
        this.accValReq = accValReq;
    }

    /**
     * @return Returns the ruleInd.
     */
    public String getRuleInd() {
        return ruleInd;
    }

    /**
     * @param ruleInd The ruleInd to set.
     */
    public void setRuleInd(String ruleInd) {
        this.ruleInd = ruleInd;
    }

    /**
     * @return Returns the tierQuadPrd.
     */
    public int getTierQuadPrd() {
        return tierQuadPrd;
    }

    /**
     * @param tierQuadPrd The tierQuadPrd to set.
     */
    public void setTierQuadPrd(int tierQuadPrd) {
        this.tierQuadPrd = tierQuadPrd;
    }

    /**
     * @return Returns the valOffset.
     */
    public long getValOffset() {
        return valOffset;
    }

    /**
     * @param valOffset The valOffset to set.
     */
    public void setValOffset(long valOffset) {
        this.valOffset = valOffset;
    }

    /**
     * @return Returns the valReq.
     */
    public long getValReq() {
        return valReq;
    }

    /**
     * @param valReq The valReq to set.
     */
    public void setValReq(long valReq) {
        this.valReq = valReq;
    }

    /**
     * @return Returns the yrsInTier.
     */
    public int getYrsInTier() {
        return yrsInTier;
    }

    /**
     * @param yrsInTier The yrsInTier to set.
     */
    public void setYrsInTier(int yrsInTier) {
        this.yrsInTier = yrsInTier;
    }

    //Added by Vinod for MKP92600 Starts
    private String interim = "N";

    public String getInterim() {
        return interim;
    }

    public void setInterim(String b) {
        this.interim = b;
    }
    //Added by Vinod for MKP92600 Ends


    public int getEliteOrPPSpercentage() {
        return eliteOrPPSpercentage;
    }

    public void setEliteOrPPSpercentage(int eliteOrPPSpercentage) {
        this.eliteOrPPSpercentage = eliteOrPPSpercentage;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}