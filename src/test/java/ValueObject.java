import java.io.Serializable;

public abstract class ValueObject implements Serializable
{

    //Added of web service
    public ValueObject()
    {
        m_TimeStamp=System.currentTimeMillis();
    }

    /** The timestamp when the value object is created. */
    private long m_TimeStamp;

    /** The application user id for this value object. */
    private String m_AuditId;

    /**
     * The abstract method to be implemented by the concrete value object, to return a copy of the
     * value object.
     *
     * @return ValueObject  a duplicated copy of value object
     */
    public abstract ValueObject getData();

    /**
     * The abstract method to be implemented by the concrete value object, to set value of value object
     * that passed in as parameter to current one.
     *
     * @param ValueObject  a value object that value to be merged
     */
    public abstract void setData( ValueObject object );

    /**
     * Set application user id.
     *
     * @param String applicationUserId  the application user id
     */
    public void setAuditId( String auditId )
    {
        m_AuditId = auditId;
    }

    /**
     * Unset application user id.
     */
    public void unsetAuditId()
    {
        m_AuditId = null;
    }

    /**
     * Get application user id.
     *
     * @return String   the application user id
     */
    public String getAuditId()
    {
        return m_AuditId;
    }

    /**
     * The constructor to initialize the creation timestamp.
     *
     * @param long timeStamp    the creation timestamp
     */
    public ValueObject( long timeStamp )
    {
        m_TimeStamp =  timeStamp;
    }

    /**
     * Get value object creation timestamp.
     *
     * @return long     the creation timestamp
     */
    public long getCreatedTime()
    {
        return m_TimeStamp;
    }

    /**
     * Set value object creation timestamp.
     *
     * @param long timeStamp    the creation timestamp
     */
    public void setCreatedTime( long timeStamp )
    {
        m_TimeStamp = timeStamp;
    }


    /**
     * Clone the value object by deep copying the value into a new object using the data
     * stream.
     *
     * @return  the cloned object
     * @exception CloneNotSupportedException    the error thrown when clone failed.
     */

}