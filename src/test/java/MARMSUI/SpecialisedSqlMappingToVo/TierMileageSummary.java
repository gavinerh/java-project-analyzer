package MARMSUI.SpecialisedSqlMappingToVo;

import java.io.Serializable;


/**
 * The ValueObject class to hold TierMileageSummary record by customer.
 * 
 * @author sachin_datar
 */
public class TierMileageSummary 
        implements Serializable {
    
    private String intID;
    private String programCode;
    private String tierType;
    
    //private String tierStatus;
    private long accumulatedPoints;
    private long currentPoints;
    private float currentSectorCount;
    private float accumulatedSectorCount;

    // Added by Ramzan - 16/11/2006 
    private long currentValues;
    private long accumulatedValues;
    private long accumulatedLifetimeValues;
    
    public TierMileageSummary(){
        super();
    }
    

    
	/**
	 * Returns the intID.
	 * @return String
	 */
	public String getIntID() {
		return intID;
	}


    /**
	 * Returns the programCode.
	 * @return String
	 */
	public String getProgramCode() {
		return programCode;
	}


	/**
	 * Returns the tierStatusInd.
	 * @return String
	 */
	//public String getTierStatus() {
	//	return tierStatus;
	//}

	/**
	 * Sets the intID.
	 * @param intID The intID to set
	 */
	public void setIntID(String intID) {
		this.intID = intID;
	}


	/**
	 * Sets the programCode.
	 * @param programCode The programCode to set
	 */
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}


	/**
	 * Sets the tierStatusInd.
	 * @param tierStatusInd The tierStatusInd to set
	 */
	//public void setTierStatus(String tierStatusInd) {
	//	this.tierStatus = tierStatusInd;
	//}

	/**
	 * Returns the accumulatedPoints.
	 * @return long
	 */
	public long getAccumulatedPoints() {
		return accumulatedPoints;
	}

	/**
	 * Returns the accumulatedSectorCount.
	 * @return float
	 */
	public float getAccumulatedSectorCount() {
		return accumulatedSectorCount;
	}

	/**
	 * Returns the currentPoints.
	 * @return long
	 */
	public long getCurrentPoints() {
		return currentPoints;
	}

	/**
	 * Returns the currentSectorCount.
	 * @return float
	 */
	public float getCurrentSectorCount() {
		return currentSectorCount;
	}

	/**
	 * Sets the accumulatedPoints.
	 * @param accumulatedPoints The accumulatedPoints to set
	 */
	public void setAccumulatedPoints(long accumulatedPoints) {
		this.accumulatedPoints = accumulatedPoints;
	}

	/**
	 * Sets the accumulatedSectorCount.
	 * @param accumulatedSectorCount The accumulatedSectorCount to set
	 */
	public void setAccumulatedSectorCount(float accumulatedSectorCount) {
		this.accumulatedSectorCount = accumulatedSectorCount;
	}

	/**
	 * Sets the currentPoints.
	 * @param currentPoints The currentPoints to set
	 */
	public void setCurrentPoints(long currentPoints) {
		this.currentPoints = currentPoints;
	}

	/**
	 * Sets the currentSectorCount.
	 * @param currentSectorCount The currentSectorCount to set
	 */
	public void setCurrentSectorCount(float currentSectorCount) {
		this.currentSectorCount = currentSectorCount;
	}

	/**
	 * Returns the tierType.
	 * @return String
	 */
	public String getTierType() {
		return tierType;
	}

	/**
	 * Sets the tierType.
	 * @param tierType The tierType to set
	 */
	public void setTierType(String tierType) {
		this.tierType = tierType;
	}


    public boolean validateDebitPoints(long milesEarned){    
        boolean retVal = true;
        if(milesEarned < 0){
            //debit transaction. ensure sufficient miles.
            if(this.getCurrentPoints() < Math.abs(milesEarned)){
                retVal = false;
            }
        }
        return retVal;
    } 


    public boolean validateDebitPointsAccumulated(float milesEarned){    
        boolean retVal = true;
        if(milesEarned < 0){
            //debit transaction. ensure sufficient miles.
            if(this.getAccumulatedPoints() < Math.abs(milesEarned)){
                retVal = false;
            }
        }
        return retVal;
    } 

    public boolean validateDebitSectors(float sectorsEarned){    
        boolean retVal = true;
        if(sectorsEarned < 0){
            //debit transaction. ensure sufficient sectors.
            if(this.getCurrentSectorCount() < Math.abs(sectorsEarned)){
                retVal = false;
            }
        }
        return retVal;
    } 


    public boolean validateDebitSectorsAccumulated(float sectorsEarned){    
        boolean retVal = true;
        if(sectorsEarned < 0){
            //debit transaction. ensure sufficient miles.
            if(this.getAccumulatedSectorCount() < Math.abs(sectorsEarned)){
                retVal = false;
            }
        }
        return retVal;
    } 

    // Added by Ramzan - 16/11/2006
    /**
	 * Sets the currentValues.
	 * @param currentValues The currentValues to set
	 */
	public void setCurrentValues(long currentValues) 
	{
		this.currentValues = currentValues;
	}
	
	/**
	 * Gets the currentValues.
	 * @return long
	 */
	public long getCurrentValues() 
	{
		return this.currentValues;
	}
	
	/**
	 * Sets the accumulatedValues.
	 * @param accumulatedValues The accumulatedValues to set
	 */
	public void setAccumulatedValues(long accumulatedValues) 
	{
		this.accumulatedValues = accumulatedValues;
	}
	
	/**
	 * Gets the accumulatedValues.
	 * @return long
	 */
	public long getAccumulatedValues() 
	{
		return this.accumulatedValues;
	}
	
	/**
	 * Sets the accumulatedLifetimeValues.
	 * @param accumulatedLifetimeValues The accumulatedLifetimeValues to set
	 */
	public void setAccumulatedLifetimeValues(long accumulatedLifetimeValues) 
	{
		this.accumulatedLifetimeValues = accumulatedLifetimeValues;
	}
	
	/**
	 * Gets the accumulatedLifetimeValues.
	 * @return long
	 */
	public long getAccumulatedLifetimeValues() 
	{
		return this.accumulatedLifetimeValues;
	}
}
