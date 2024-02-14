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

import java.io.Serializable;
import java.util.Date;

public class CusPin extends CusPinKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.RCRE_USER_ID
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String rcreUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.RCRE_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private Date rcreDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.LCHG_USER_ID
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String lchgUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.LCHG_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private Date lchgDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.PIN_STATUS_IND
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String pinStatusInd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.PIN_STATUS_CHG_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private Date pinStatusChgDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.PIN_REPL_REQ_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private Date pinReplReqDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.SECRET_QUESTION
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String secretQuestion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.SECRET_ANSWER
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String secretAnswer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.CUS_ID
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String cusId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.LAST_LOGIN_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private Date lastLoginDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.LAST_MSTR_LOGIN_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private Date lastMstrLoginDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.INVALID_LOGIN_CNT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private Short invalidLoginCnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.SUCCESS_LOGIN_CNT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private Integer successLoginCnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.CUS_LOGIN_THRESHOLD
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private Integer cusLoginThreshold;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.PWD_STATUS_IND
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String pwdStatusInd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.PWD_STATUS_CHG_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private Date pwdStatusChgDt;


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.CMPLX_AUTH
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String cmplxAuth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.NUM_AUTH
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String numAuth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.SALT_1
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String salt1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUS_PIN.SALT_2
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    private String salt2;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.RCRE_USER_ID
     *
     * @return the value of CUS_PIN.RCRE_USER_ID
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getRcreUserId() {
        return rcreUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.RCRE_USER_ID
     *
     * @param rcreUserId the value for CUS_PIN.RCRE_USER_ID
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setRcreUserId(String rcreUserId) {
        this.rcreUserId = rcreUserId == null ? null : rcreUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.RCRE_DT
     *
     * @return the value of CUS_PIN.RCRE_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public Date getRcreDt() {
        return rcreDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.RCRE_DT
     *
     * @param rcreDt the value for CUS_PIN.RCRE_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setRcreDt(Date rcreDt) {
        this.rcreDt = rcreDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.LCHG_USER_ID
     *
     * @return the value of CUS_PIN.LCHG_USER_ID
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getLchgUserId() {
        return lchgUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.LCHG_USER_ID
     *
     * @param lchgUserId the value for CUS_PIN.LCHG_USER_ID
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setLchgUserId(String lchgUserId) {
        this.lchgUserId = lchgUserId == null ? null : lchgUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.LCHG_DT
     *
     * @return the value of CUS_PIN.LCHG_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public Date getLchgDt() {
        return lchgDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.LCHG_DT
     *
     * @param lchgDt the value for CUS_PIN.LCHG_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setLchgDt(Date lchgDt) {
        this.lchgDt = lchgDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.PIN_STATUS_IND
     *
     * @return the value of CUS_PIN.PIN_STATUS_IND
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getPinStatusInd() {
        return pinStatusInd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.PIN_STATUS_IND
     *
     * @param pinStatusInd the value for CUS_PIN.PIN_STATUS_IND
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setPinStatusInd(String pinStatusInd) {
        this.pinStatusInd = pinStatusInd == null ? null : pinStatusInd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.PIN_STATUS_CHG_DT
     *
     * @return the value of CUS_PIN.PIN_STATUS_CHG_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public Date getPinStatusChgDt() {
        return pinStatusChgDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.PIN_STATUS_CHG_DT
     *
     * @param pinStatusChgDt the value for CUS_PIN.PIN_STATUS_CHG_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setPinStatusChgDt(Date pinStatusChgDt) {
        this.pinStatusChgDt = pinStatusChgDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.PIN_REPL_REQ_DT
     *
     * @return the value of CUS_PIN.PIN_REPL_REQ_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public Date getPinReplReqDt() {
        return pinReplReqDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.PIN_REPL_REQ_DT
     *
     * @param pinReplReqDt the value for CUS_PIN.PIN_REPL_REQ_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setPinReplReqDt(Date pinReplReqDt) {
        this.pinReplReqDt = pinReplReqDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.SECRET_QUESTION
     *
     * @return the value of CUS_PIN.SECRET_QUESTION
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getSecretQuestion() {
        return secretQuestion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.SECRET_QUESTION
     *
     * @param secretQuestion the value for CUS_PIN.SECRET_QUESTION
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion == null ? null : secretQuestion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.SECRET_ANSWER
     *
     * @return the value of CUS_PIN.SECRET_ANSWER
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getSecretAnswer() {
        return secretAnswer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.SECRET_ANSWER
     *
     * @param secretAnswer the value for CUS_PIN.SECRET_ANSWER
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer == null ? null : secretAnswer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.CUS_ID
     *
     * @return the value of CUS_PIN.CUS_ID
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getCusId() {
        return cusId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.CUS_ID
     *
     * @param cusId the value for CUS_PIN.CUS_ID
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setCusId(String cusId) {
        this.cusId = cusId == null ? null : cusId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.LAST_LOGIN_DT
     *
     * @return the value of CUS_PIN.LAST_LOGIN_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public Date getLastLoginDt() {
        return lastLoginDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.LAST_LOGIN_DT
     *
     * @param lastLoginDt the value for CUS_PIN.LAST_LOGIN_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setLastLoginDt(Date lastLoginDt) {
        this.lastLoginDt = lastLoginDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.LAST_MSTR_LOGIN_DT
     *
     * @return the value of CUS_PIN.LAST_MSTR_LOGIN_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public Date getLastMstrLoginDt() {
        return lastMstrLoginDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.LAST_MSTR_LOGIN_DT
     *
     * @param lastMstrLoginDt the value for CUS_PIN.LAST_MSTR_LOGIN_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setLastMstrLoginDt(Date lastMstrLoginDt) {
        this.lastMstrLoginDt = lastMstrLoginDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.INVALID_LOGIN_CNT
     *
     * @return the value of CUS_PIN.INVALID_LOGIN_CNT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public Short getInvalidLoginCnt() {
        return invalidLoginCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.INVALID_LOGIN_CNT
     *
     * @param invalidLoginCnt the value for CUS_PIN.INVALID_LOGIN_CNT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setInvalidLoginCnt(Short invalidLoginCnt) {
        this.invalidLoginCnt = invalidLoginCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.SUCCESS_LOGIN_CNT
     *
     * @return the value of CUS_PIN.SUCCESS_LOGIN_CNT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public Integer getSuccessLoginCnt() {
        return successLoginCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.SUCCESS_LOGIN_CNT
     *
     * @param successLoginCnt the value for CUS_PIN.SUCCESS_LOGIN_CNT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setSuccessLoginCnt(Integer successLoginCnt) {
        this.successLoginCnt = successLoginCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.CUS_LOGIN_THRESHOLD
     *
     * @return the value of CUS_PIN.CUS_LOGIN_THRESHOLD
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public Integer getCusLoginThreshold() {
        return cusLoginThreshold;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.CUS_LOGIN_THRESHOLD
     *
     * @param cusLoginThreshold the value for CUS_PIN.CUS_LOGIN_THRESHOLD
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setCusLoginThreshold(Integer cusLoginThreshold) {
        this.cusLoginThreshold = cusLoginThreshold;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.PWD_STATUS_IND
     *
     * @return the value of CUS_PIN.PWD_STATUS_IND
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getPwdStatusInd() {
        return pwdStatusInd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.PWD_STATUS_IND
     *
     * @param pwdStatusInd the value for CUS_PIN.PWD_STATUS_IND
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setPwdStatusInd(String pwdStatusInd) {
        this.pwdStatusInd = pwdStatusInd == null ? null : pwdStatusInd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.PWD_STATUS_CHG_DT
     *
     * @return the value of CUS_PIN.PWD_STATUS_CHG_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public Date getPwdStatusChgDt() {
        return pwdStatusChgDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.PWD_STATUS_CHG_DT
     *
     * @param pwdStatusChgDt the value for CUS_PIN.PWD_STATUS_CHG_DT
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setPwdStatusChgDt(Date pwdStatusChgDt) {
        this.pwdStatusChgDt = pwdStatusChgDt;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.CMPLX_AUTH
     *
     * @return the value of CUS_PIN.CMPLX_AUTH
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getCmplxAuth() {
        return cmplxAuth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.CMPLX_AUTH
     *
     * @param cmplxAuth the value for CUS_PIN.CMPLX_AUTH
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setCmplxAuth(String cmplxAuth) {
        this.cmplxAuth = cmplxAuth == null ? null : cmplxAuth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.NUM_AUTH
     *
     * @return the value of CUS_PIN.NUM_AUTH
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getNumAuth() {
        return numAuth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.NUM_AUTH
     *
     * @param numAuth the value for CUS_PIN.NUM_AUTH
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setNumAuth(String numAuth) {
        this.numAuth = numAuth == null ? null : numAuth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.SALT_1
     *
     * @return the value of CUS_PIN.SALT_1
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getSalt1() {
        return salt1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.SALT_1
     *
     * @param salt1 the value for CUS_PIN.SALT_1
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setSalt1(String salt1) {
        this.salt1 = salt1 == null ? null : salt1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUS_PIN.SALT_2
     *
     * @return the value of CUS_PIN.SALT_2
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public String getSalt2() {
        return salt2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUS_PIN.SALT_2
     *
     * @param salt2 the value for CUS_PIN.SALT_2
     *
     * @mbggenerated Tue Mar 17 14:21:01 IST 2020
     */
    public void setSalt2(String salt2) {
        this.salt2 = salt2 == null ? null : salt2.trim();
    }
}