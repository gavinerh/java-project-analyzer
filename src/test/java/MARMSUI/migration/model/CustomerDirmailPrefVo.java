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

/**
 * 
 * Value Object to hold customer's mailing preference
 * for each mailer type
 *
 */
public class CustomerDirmailPrefVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String lchgDt;
	private String mailerType;
	private String mailPref;
	private String emailContentType;
	private Long notificationSent;
	private String displayFlag = "Y";
	private boolean defaultBoolean = false;
	private String mailerSubscribeInd;
	private String prgOrSubPrgCd;
	
	public String getEmailContentType() {
		return emailContentType;
	}
	public void setEmailContentType(String emailContentType) {
		this.emailContentType = emailContentType;
	}
	public void setDefaultBoolean(boolean defaultBoolean) {
		this.defaultBoolean = defaultBoolean;
	}
	public String getMailerSubscribeInd() {
		return mailerSubscribeInd;
	}
	public String getMailerType() {
		return mailerType;
	}
	public void setMailerType(String mailerType) {
		this.mailerType = mailerType;
	}
	public String getMailPref() {
		return mailPref;
	}
	public void setMailPref(String mailPref) {
		this.mailPref = mailPref;
	}	
	public Long getNotificationSent() {
		return notificationSent;
	}
	public void setNotificationSent(Long notificationSent) {
		this.notificationSent = notificationSent;
	}
	public String getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	public String getLchgDt() {
		return lchgDt;
	}
	public void setLchgDt(String lchgDt) {
		this.lchgDt = lchgDt;
	}
	public boolean isDefaultBoolean() {
		return defaultBoolean;
	}
	
	public void setMailerSubscribeInd(String mailerSubscribeInd) {
		this.mailerSubscribeInd = mailerSubscribeInd;
	}
	public String getPrgOrSubPrgCd() {
		return prgOrSubPrgCd;
	}
	public void setPrgOrSubPrgCd(String prgOrSubPrgCd) {
		this.prgOrSubPrgCd = prgOrSubPrgCd;
	}
}
