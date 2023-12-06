// EliteParticipant.java

package MARMSUI.SpecialisedSqlMappingToVo.model;

import java.io.Serializable;

/**
 * ValueObject for storing the elite points of customer categorized by participant.
 * 
 * @author sachin_datar
 */
public class EliteParticipant extends QualificationValueObject implements Serializable {

    private String intID;
    
    //Added new column PRG_CD.
    private String prgCD = null;
    
    private String participantCode;
    private String tierStatus;
    
    private long currentMiles;
    private long accumulatedMiles;
    private long lastQualifyingMiles;
    private String prtAnaInd; //Added By KAVI for KFPROG-3463 

    public EliteParticipant(){
        // blank
    }

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


    //---Added new column PRG_CD
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
	// ----
	
	//Added By KAVI for KFPROG-3463 - STARTS

	public String getPrtAnaInd() {
		return prtAnaInd;
	}

	public void setPrtAnaInd(String prtAnaInd) {
		this.prtAnaInd = prtAnaInd;
	}
    //Added for testing by Binzila for 3770
	@java.lang.Override
	public java.lang.String toString() {
		return "EliteParticipant{" +
				"intID='" + intID + '\'' +
				", prgCD='" + prgCD + '\'' +
				", participantCode='" + participantCode + '\'' +
				", tierStatus='" + tierStatus + '\'' +
				", currentMiles=" + currentMiles +
				", accumulatedMiles=" + accumulatedMiles +
				", lastQualifyingMiles=" + lastQualifyingMiles +
				", prtAnaInd='" + prtAnaInd + '\'' +
				'}';
	}
    //Added for testing by Binzila for 3770
	//Added By KAVI for KFPROG-3463 - ENDS
	
}
