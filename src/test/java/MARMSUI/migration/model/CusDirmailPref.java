/* 
* =============================================================================== 
* For internal use of Singapore Airlines and TATA Consultancy Services only. 
* (C) 2019 Singapore Airlines. 
* Singapore Co. Reg. No. 197200078R. All Rights Reserved. 
* 
* Information in this file is the intellectual property of SINGAPORE AIRLINES. 
* =============================================================================== 
*/

package MARMSUI.migration.model;

import java.util.Date;

public class CusDirmailPref implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_DIRMAIL_PREF.RCRE_USER_ID
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    private String rcreUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_DIRMAIL_PREF.RCRE_DT
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    private Date rcreDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_DIRMAIL_PREF.LCHG_USER_ID
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    private String lchgUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_DIRMAIL_PREF.LCHG_DT
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    private Date lchgDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_DIRMAIL_PREF.INT_ID
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    private Long intId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_DIRMAIL_PREF.MAILER_TYPE
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    private String mailerType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_DIRMAIL_PREF.MAIL_PREF
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    private String mailPref;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_DIRMAIL_PREF.EMAIL_CONTENT_TYPE
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    private String emailContentType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_DIRMAIL_PREF.NOTIFICATION_SENT
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    private Short notificationSent;
    
    private String displayFlag = "Y";
	private boolean defaultBoolean = false;
	private String mailerSubscribeInd;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_DIRMAIL_PREF.RCRE_USER_ID
     *
     * @return the value of CUS_DIRMAIL_PREF.RCRE_USER_ID
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public String getRcreUserId() {
        return rcreUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_DIRMAIL_PREF.RCRE_USER_ID
     *
     * @param rcreUserId the value for CUS_DIRMAIL_PREF.RCRE_USER_ID
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public void setRcreUserId(String rcreUserId) {
        this.rcreUserId = rcreUserId == null ? null : rcreUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_DIRMAIL_PREF.RCRE_DT
     *
     * @return the value of CUS_DIRMAIL_PREF.RCRE_DT
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public Date getRcreDt() {
        return rcreDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_DIRMAIL_PREF.RCRE_DT
     *
     * @param rcreDt the value for CUS_DIRMAIL_PREF.RCRE_DT
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public void setRcreDt(Date rcreDt) {
        this.rcreDt = rcreDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_DIRMAIL_PREF.LCHG_USER_ID
     *
     * @return the value of CUS_DIRMAIL_PREF.LCHG_USER_ID
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public String getLchgUserId() {
        return lchgUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_DIRMAIL_PREF.LCHG_USER_ID
     *
     * @param lchgUserId the value for CUS_DIRMAIL_PREF.LCHG_USER_ID
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public void setLchgUserId(String lchgUserId) {
        this.lchgUserId = lchgUserId == null ? null : lchgUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_DIRMAIL_PREF.LCHG_DT
     *
     * @return the value of CUS_DIRMAIL_PREF.LCHG_DT
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public Date getLchgDt() {
        return lchgDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_DIRMAIL_PREF.LCHG_DT
     *
     * @param lchgDt the value for CUS_DIRMAIL_PREF.LCHG_DT
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public void setLchgDt(Date lchgDt) {
        this.lchgDt = lchgDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_DIRMAIL_PREF.INT_ID
     *
     * @return the value of CUS_DIRMAIL_PREF.INT_ID
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public Long getIntId() {
        return intId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_DIRMAIL_PREF.INT_ID
     *
     * @param intId the value for CUS_DIRMAIL_PREF.INT_ID
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public void setIntId(Long intId) {
        this.intId = intId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_DIRMAIL_PREF.MAILER_TYPE
     *
     * @return the value of CUS_DIRMAIL_PREF.MAILER_TYPE
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public String getMailerType() {
        return mailerType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_DIRMAIL_PREF.MAILER_TYPE
     *
     * @param mailerType the value for CUS_DIRMAIL_PREF.MAILER_TYPE
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public void setMailerType(String mailerType) {
        this.mailerType = mailerType == null ? null : mailerType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_DIRMAIL_PREF.MAIL_PREF
     *
     * @return the value of CUS_DIRMAIL_PREF.MAIL_PREF
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public String getMailPref() {
        return mailPref;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_DIRMAIL_PREF.MAIL_PREF
     *
     * @param mailPref the value for CUS_DIRMAIL_PREF.MAIL_PREF
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public void setMailPref(String mailPref) {
        this.mailPref = mailPref == null ? null : mailPref.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_DIRMAIL_PREF.EMAIL_CONTENT_TYPE
     *
     * @return the value of CUS_DIRMAIL_PREF.EMAIL_CONTENT_TYPE
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public String getEmailContentType() {
        return emailContentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_DIRMAIL_PREF.EMAIL_CONTENT_TYPE
     *
     * @param emailContentType the value for CUS_DIRMAIL_PREF.EMAIL_CONTENT_TYPE
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public void setEmailContentType(String emailContentType) {
        this.emailContentType = emailContentType == null ? null : emailContentType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_DIRMAIL_PREF.NOTIFICATION_SENT
     *
     * @return the value of CUS_DIRMAIL_PREF.NOTIFICATION_SENT
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    public Short getNotificationSent() {
        return notificationSent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_DIRMAIL_PREF.NOTIFICATION_SENT
     *
     * @param notificationSent the value for CUS_DIRMAIL_PREF.NOTIFICATION_SENT
     *
     * @mbggenerated Mon Sep 24 10:32:49 IST 2018
     */
    
    public void setNotificationSent(Short notificationSent) {
        this.notificationSent = notificationSent;
    }

	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	public boolean isDefaultBoolean() {
		return defaultBoolean;
	}

	public void setDefaultBoolean(boolean defaultBoolean) {
		this.defaultBoolean = defaultBoolean;
	}

	public String getMailerSubscribeInd() {
		return mailerSubscribeInd;
	}

	public void setMailerSubscribeInd(String mailerSubscribeInd) {
		this.mailerSubscribeInd = mailerSubscribeInd;
	}
    
}