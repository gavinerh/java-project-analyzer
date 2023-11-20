package MARMSUI.SpecialisedSqlMappingToVo;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author sachin_datar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class QualificationValueObject
        	implements Serializable {

    private String rcreUserID;
    private String rcreDate;
    private String lastChangeUserID;
    private Timestamp lastChangeDate;
    private Timestamp newChangeDate;            
     


    /**
     * Returns the rcreDate.
     * @return String
     */
    public String getRcreDate() {
        return rcreDate;
    }

    /**
     * Returns the lastChangeUserID.
     * @return String
     */
    public String getLastChangeUserID() {
        return lastChangeUserID;
    }


    /**
     * Returns the rcreUserID.
     * @return String
     */
    public String getRcreUserID() {
        return rcreUserID;
    }

    /**
     * Sets the lastChangeUserID.
     * @param lastChangeUserID The lastChangeUserID to set
     */
    public void setLastChangeUserID(String lastChangeUserID) {
        this.lastChangeUserID = lastChangeUserID;
    }

    /**
     * Sets the rcreDate.
     * @param rcreDate The rcreDate to set
     */
    public void setRcreDate(String rcreDate) {
        this.rcreDate = rcreDate;
    }

    /**
     * Sets the rcreUserID.
     * @param rcreUserID The rcreUserID to set
     */
    public void setRcreUserID(String rcreUserID) {
        this.rcreUserID = rcreUserID;
    }

    /**
     * Sets the lastChangeDate.
     * @param lastChangeDate The lastChangeDate to set
     */
    public void setLastChangeDate(Timestamp lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    /**
     * Returns the lastChangeDate.
     * @return Timestamp
     */
    public Timestamp getLastChangeDate() {
        return lastChangeDate;
    }

    /**
     * Returns the newChangeDate.
     * @return Timestamp
     */
    public Timestamp getNewChangeDate() {
        return newChangeDate;
    }

    /**
     * Sets the newChangeDate.
     * @param newChangeDate The newChangeDate to set
     */
    public void setNewChangeDate(Timestamp newChangeDate) {
        this.newChangeDate = newChangeDate;
    }


}
