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
 * Value Object which holds customer pin details
 *
 */
public class CustomerPinVo {

	private Long intId;
	private String prgCd;
	private String rcreUserId;
	private String rcreDt;
	private String lchgUserId;
	private String lchgDt;
	private String pin;
	private String pinStatusInd;
	private String pinStatusChgDt;
	private String pinReplReqDt;
	private String secretQuestion;
	private String secretAnswer;
	private String cusId;
	private String lastLoginDt;
	private String lastMstrLoginDt;
	private Long invalidLoginCnt;
	private Long successLoginCnt;
	private Long cusLoginThreshold;
	private String pinMigrated;
	private String salt;
	private Long iterations;
	private String pwdStatusInd;
	private String complexPwd;

	public String getPrgCd() {
		return prgCd;
	}

	public void setPrgCd(String prgCd) {
		this.prgCd = prgCd;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getRcreUserId() {
		return rcreUserId;
	}

	public void setRcreUserId(String rcreUserId) {
		this.rcreUserId = rcreUserId;
	}

	public String getComplexPwd() {
		return complexPwd;
	}

	public void setComplexPwd(String complexPwd) {
		this.complexPwd = complexPwd;
	}

	public String getRcreDt() {
		return rcreDt;
	}

	public void setRcreDt(String rcreDt) {
		this.rcreDt = rcreDt;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	public Long getIntId() {
		return intId;
	}

	public void setIntId(Long intId) {
		this.intId = intId;
	}

	public String getLchgUserId() {
		return lchgUserId;
	}

	public void setLchgUserId(String lchgUserId) {
		this.lchgUserId = lchgUserId;
	}

	public String getPinStatusInd() {
		return pinStatusInd;
	}

	public void setPinStatusInd(String pinStatusInd) {
		this.pinStatusInd = pinStatusInd;
	}

	public String getLchgDt() {
		return lchgDt;
	}

	public void setLchgDt(String lchgDt) {
		this.lchgDt = lchgDt;
	}

	public String getPinStatusChgDt() {
		return pinStatusChgDt;
	}

	public void setPinStatusChgDt(String pinStatusChgDt) {
		this.pinStatusChgDt = pinStatusChgDt;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getPinReplReqDt() {
		return pinReplReqDt;
	}

	public void setPinReplReqDt(String pinReplReqDt) {
		this.pinReplReqDt = pinReplReqDt;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getLastLoginDt() {
		return lastLoginDt;
	}

	public void setLastLoginDt(String lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}

	public Long getInvalidLoginCnt() {
		return invalidLoginCnt;
	}

	public void setInvalidLoginCnt(Long invalidLoginCnt) {
		this.invalidLoginCnt = invalidLoginCnt;
	}

	public Long getSuccessLoginCnt() {
		return successLoginCnt;
	}

	public void setSuccessLoginCnt(Long successLoginCnt) {
		this.successLoginCnt = successLoginCnt;
	}

	public String getLastMstrLoginDt() {
		return lastMstrLoginDt;
	}

	public void setLastMstrLoginDt(String lastMstrLoginDt) {
		this.lastMstrLoginDt = lastMstrLoginDt;
	}

	public Long getCusLoginThreshold() {
		return cusLoginThreshold;
	}

	public void setCusLoginThreshold(Long cusLoginThreshold) {
		this.cusLoginThreshold = cusLoginThreshold;
	}

	public String getPinMigrated() {
		return pinMigrated;
	}

	public void setPinMigrated(String pinMigrated) {
		this.pinMigrated = pinMigrated;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Long getIterations() {
		return iterations;
	}

	public void setIterations(Long iterations) {
		this.iterations = iterations;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column CUS_PIN.PWD_STATUS_IND
	 *
	 * @return the value of CUS_PIN.PWD_STATUS_IND
	 *
	 * @mbggenerated Mon Nov 19 18:37:34 IST 2018
	 */
	public String getPwdStatusInd() {
		return pwdStatusInd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column CUS_PIN.PWD_STATUS_IND
	 *
	 * @param pwdStatusInd
	 *            the value for CUS_PIN.PWD_STATUS_IND
	 *
	 * @mbggenerated Mon Nov 19 18:37:34 IST 2018
	 */
	public void setPwdStatusInd(String pwdStatusInd) {
		this.pwdStatusInd = pwdStatusInd == null ? null : pwdStatusInd.trim();
	}
}
