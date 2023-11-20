// PPSParticipant.java
package MARMSUI.SpecialisedSqlMappingToVo;

import java.io.Serializable;

/**
 * ValueObject used to store the pps miles and sectors of customer categorized 
 * by participant. (++cheewei: PPSParticipant now stores pps Values as well)
 * 
 * @author sachin_datar
 */public class PPSParticipant
     extends QualificationValueObject
  implements Serializable {

    private String intID;
    
    //Added new column PRG_CD.
    private String prgCD = null;
        
    private String participantCode;
    private String tierStatus;
    
    private long currentMiles;
    private long accumulatedMiles;
    private long lastQualifyingMiles;

    private float currentSectors;
    private float accumulatedSectors;
    private float lastQualifyingSectors;

    private long currentValues;
    private long accumulatedValues;
    private long cumLifetimeValue;
    
    // Added by Ramzan, 19/12/2006
    private long lastQualifyingValues;
    
    private String prtAnaInd; //Added By Chandima for KFPROG-3707

    public PPSParticipant(){
        
    }
  
    
    /**
     * Returns the intID.
     * @return String
     */
    public String getIntID() {
        return intID;
    }

    /**
     * Returns the tierStatusInd.
     * @return String
     */
    public String getTierStatus() {
        return tierStatus;
    }

    /**
     * Sets the intID.
     * @param intID The intID to set
     */
    public void setIntID(String intID) {
        this.intID = intID;
    }

     /**
     * Sets the tierStatusInd.
     * @param tierStatusInd The tierStatusInd to set
     */
    public void setTierStatus(String tierStatusInd) {
        this.tierStatus = tierStatusInd;
    }

    /**
     * Returns the participantCode.
     * @return String
     */
    public String getParticipantCode() {
        return participantCode;
    }

    /**
     * Sets the participantCode.
     * @param participantCode The participantCode to set
     */
    public void setParticipantCode(String participantCode) {
        this.participantCode = participantCode;
    }
        
    /**
     * Returns the accumulatedMiles.
     * @return long
     */
    public long getAccumulatedMiles() {
        return accumulatedMiles;
    }

    /**
     * Returns the currentMiles.
     * @return long
     */
    public long getCurrentMiles() {
        return currentMiles;
    }

    /**
     * Sets the accumulatedMiles.
     * @param accumulatedMiles The accumulatedMiles to set
     */
    public void setAccumulatedMiles(long accumulatedMiles) {
        this.accumulatedMiles = accumulatedMiles;
    }

    /**
     * Sets the currentMiles.
     * @param currentMiles The currentMiles to set
     */
    public void setCurrentMiles(long currentMiles) {
        this.currentMiles = currentMiles;
    }

    /**
     * Returns the lastQualifyingMiles.
     * @return long
     */
    public long getLastQualifyingMiles() {
        return lastQualifyingMiles;
    }

    /**
     * Sets the lastQualifyingMiles.
     * @param lastQualifyingMiles The lastQualifyingMiles to set
     */
    public void setLastQualifyingMiles(long lastQualifyingMiles) {
        this.lastQualifyingMiles = lastQualifyingMiles;
    }

    /**
     * Returns the accumulatedSectors.
     * @return float
     */
    public float getAccumulatedSectors() {
        return accumulatedSectors;
    }

    /**
     * Returns the currentSectors.
     * @return float
     */
    public float getCurrentSectors() {
        return currentSectors;
    }

    /**
     * Returns the lastQualifyingSectors.
     * @return float
     */
    public float getLastQualifyingSectors() {
        return lastQualifyingSectors;
    }

    /**
     * Sets the accumulatedSectors.
     * @param accumulatedSectors The accumulatedSectors to set
     */
    public void setAccumulatedSectors(float accumulatedSectors) {
        this.accumulatedSectors = accumulatedSectors;
    }

    /**
     * Sets the currentSectors.
     * @param currentSectors The currentSectors to set
     */
    public void setCurrentSectors(float currentSectors) {
        this.currentSectors = currentSectors;
    }

    /**
     * Sets the lastQualifyingSectors.
     * @param lastQualifyingSectors The lastQualifyingSectors to set
     */
    public void setLastQualifyingSectors(float lastQualifyingSectors) {
        this.lastQualifyingSectors = lastQualifyingSectors;
    }

    /**
     * Returns the currentValues.
     * @return long
     */
    public long getCurrentValues() {
        return currentValues;
    }

    /**
     * Returns the accumulatedValues.
     * @return long
     */
    public long getAccumulatedValues() {
        return accumulatedValues;
    }

    /**
     * Sets the currentValues.
     * @param currentValues The currentValues to set
     */
    public void setCurrentValues(long currentValues) {
        this.currentValues = currentValues;
    }

    /**
     * Sets the accumulatedValues.
     * @param accumulatedValues The accumulatedValues to set
     */
    public void setAccumulatedValues(long accumulatedValues) {
        this.accumulatedValues = accumulatedValues;
    }

    /**
     * @return Returns the cumLifetimeValue.
     */
    public long getCumLifetimeValue()
    {
        return cumLifetimeValue;
    }

    /**
     * @param cumLifetimeValue The cumLifetimeValue to set.
     */
    public void setCumLifetimeValue(long cumLifetimeValue)
    {
        this.cumLifetimeValue = cumLifetimeValue;
    }

    //----Added new column PRG_CD
    /**
     * Returns the prgCD.
     * @return String
     */
    public String getPrgCD() {
        return prgCD;
    }

    /**
     * Sets the prgCD.
     * @param prgCD The prgCD to set
     */
    public void setPrgCD(String prgCD) {
        this.prgCD = prgCD;
    }
    //--------

	/**
	 * @return Returns the lastQualifyingValues.
	 */
	public long getLastQualifyingValues()
	{
		return lastQualifyingValues;
	}

	/**
	 * @param lastQualifyingValues The lastQualifyingValues to set.
	 */
	public void setLastQualifyingValues(long lastQualifyingValues)
	{
		this.lastQualifyingValues = lastQualifyingValues;
	}

	//Added By Chandima for KFPROG-3707 - Starts
	public String getPrtAnaInd() {
		return prtAnaInd;
	}

	public void setPrtAnaInd(String prtAnaInd) {
		this.prtAnaInd = prtAnaInd;
	}
	
	//Added By Chandima for KFPROG-3707 - Ends

}