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

public class CusAddr implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.RCRE_USER_ID
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String rcreUserId;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.RCRE_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private Date rcreDt;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.LCHG_USER_ID
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String lchgUserId;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.LCHG_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private Date lchgDt;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.INT_ID
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private Long intId;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.LANG_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String langCd;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.ADDR_TYPE
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String addrType;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.ADDR_LINE_1
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String addrLine1;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.ADDR_LINE_2
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String addrLine2;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.ADDR_LINE_3
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String addrLine3;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.ADDR_LINE_4
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String addrLine4;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.CTY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String ctyCd;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.OTH_CTY_DESC
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String othCtyDesc;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.STATE_PROV
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String stateProv;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.CTRY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String ctryCd;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.ZIP_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String zipCd;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.MAIL_CTRL_CTY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String mailCtrlCtyCd;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.MAIL_RGN_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String mailRgnCd;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.SVC_CTY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private String svcCtyCd;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.ADDR_EFF_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private Date addrEffDt;

	/**
	 * This field was generated by MyBatis Generator.
	 * This field corresponds to the database column CUS_ADDR.ADDR_END_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	private Date addrEndDt;

	// added for krishop changes start
	private String addrLabel;
	private String remarks;
	// added for krishop changes end
	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.RCRE_USER_ID
	 *
	 * @return the value of CUS_ADDR.RCRE_USER_ID
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getRcreUserId() {
		return rcreUserId;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.RCRE_USER_ID
	 *
	 * @param rcreUserId the value for CUS_ADDR.RCRE_USER_ID
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setRcreUserId(String rcreUserId) {
		this.rcreUserId = rcreUserId == null ? null : rcreUserId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.RCRE_DT
	 *
	 * @return the value of CUS_ADDR.RCRE_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public Date getRcreDt() {
		return rcreDt;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.RCRE_DT
	 *
	 * @param rcreDt the value for CUS_ADDR.RCRE_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setRcreDt(Date rcreDt) {
		this.rcreDt = rcreDt;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.LCHG_USER_ID
	 *
	 * @return the value of CUS_ADDR.LCHG_USER_ID
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getLchgUserId() {
		return lchgUserId;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.LCHG_USER_ID
	 *
	 * @param lchgUserId the value for CUS_ADDR.LCHG_USER_ID
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setLchgUserId(String lchgUserId) {
		this.lchgUserId = lchgUserId == null ? null : lchgUserId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.LCHG_DT
	 *
	 * @return the value of CUS_ADDR.LCHG_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public Date getLchgDt() {
		return lchgDt;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.LCHG_DT
	 *
	 * @param lchgDt the value for CUS_ADDR.LCHG_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setLchgDt(Date lchgDt) {
		this.lchgDt = lchgDt;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.INT_ID
	 *
	 * @return the value of CUS_ADDR.INT_ID
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public Long getIntId() {
		return intId;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.INT_ID
	 *
	 * @param intId the value for CUS_ADDR.INT_ID
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setIntId(Long intId) {
		this.intId = intId;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.LANG_CD
	 *
	 * @return the value of CUS_ADDR.LANG_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getLangCd() {
		return langCd;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.LANG_CD
	 *
	 * @param langCd the value for CUS_ADDR.LANG_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setLangCd(String langCd) {
		this.langCd = langCd == null ? null : langCd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.ADDR_TYPE
	 *
	 * @return the value of CUS_ADDR.ADDR_TYPE
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getAddrType() {
		return addrType;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.ADDR_TYPE
	 *
	 * @param addrType the value for CUS_ADDR.ADDR_TYPE
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setAddrType(String addrType) {
		this.addrType = addrType == null ? null : addrType.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.ADDR_LINE_1
	 *
	 * @return the value of CUS_ADDR.ADDR_LINE_1
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getAddrLine1() {
		return addrLine1;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.ADDR_LINE_1
	 *
	 * @param addrLine1 the value for CUS_ADDR.ADDR_LINE_1
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1 == null ? null : addrLine1.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.ADDR_LINE_2
	 *
	 * @return the value of CUS_ADDR.ADDR_LINE_2
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getAddrLine2() {
		return addrLine2;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.ADDR_LINE_2
	 *
	 * @param addrLine2 the value for CUS_ADDR.ADDR_LINE_2
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2 == null ? null : addrLine2.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.ADDR_LINE_3
	 *
	 * @return the value of CUS_ADDR.ADDR_LINE_3
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getAddrLine3() {
		return addrLine3;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.ADDR_LINE_3
	 *
	 * @param addrLine3 the value for CUS_ADDR.ADDR_LINE_3
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setAddrLine3(String addrLine3) {
		this.addrLine3 = addrLine3 == null ? null : addrLine3.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.ADDR_LINE_4
	 *
	 * @return the value of CUS_ADDR.ADDR_LINE_4
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getAddrLine4() {
		return addrLine4;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.ADDR_LINE_4
	 *
	 * @param addrLine4 the value for CUS_ADDR.ADDR_LINE_4
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setAddrLine4(String addrLine4) {
		this.addrLine4 = addrLine4 == null ? null : addrLine4.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.CTY_CD
	 *
	 * @return the value of CUS_ADDR.CTY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getCtyCd() {
		return ctyCd;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.CTY_CD
	 *
	 * @param ctyCd the value for CUS_ADDR.CTY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setCtyCd(String ctyCd) {
		this.ctyCd = ctyCd == null ? null : ctyCd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.OTH_CTY_DESC
	 *
	 * @return the value of CUS_ADDR.OTH_CTY_DESC
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getOthCtyDesc() {
		return othCtyDesc;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.OTH_CTY_DESC
	 *
	 * @param othCtyDesc the value for CUS_ADDR.OTH_CTY_DESC
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setOthCtyDesc(String othCtyDesc) {
		this.othCtyDesc = othCtyDesc == null ? null : othCtyDesc.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.STATE_PROV
	 *
	 * @return the value of CUS_ADDR.STATE_PROV
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getStateProv() {
		return stateProv;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.STATE_PROV
	 *
	 * @param stateProv the value for CUS_ADDR.STATE_PROV
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setStateProv(String stateProv) {
		this.stateProv = stateProv == null ? null : stateProv.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.CTRY_CD
	 *
	 * @return the value of CUS_ADDR.CTRY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getCtryCd() {
		return ctryCd;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.CTRY_CD
	 *
	 * @param ctryCd the value for CUS_ADDR.CTRY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setCtryCd(String ctryCd) {
		this.ctryCd = ctryCd == null ? null : ctryCd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.ZIP_CD
	 *
	 * @return the value of CUS_ADDR.ZIP_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getZipCd() {
		return zipCd;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.ZIP_CD
	 *
	 * @param zipCd the value for CUS_ADDR.ZIP_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd == null ? null : zipCd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.MAIL_CTRL_CTY_CD
	 *
	 * @return the value of CUS_ADDR.MAIL_CTRL_CTY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getMailCtrlCtyCd() {
		return mailCtrlCtyCd;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.MAIL_CTRL_CTY_CD
	 *
	 * @param mailCtrlCtyCd the value for CUS_ADDR.MAIL_CTRL_CTY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setMailCtrlCtyCd(String mailCtrlCtyCd) {
		this.mailCtrlCtyCd = mailCtrlCtyCd == null ? null : mailCtrlCtyCd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.MAIL_RGN_CD
	 *
	 * @return the value of CUS_ADDR.MAIL_RGN_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getMailRgnCd() {
		return mailRgnCd;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.MAIL_RGN_CD
	 *
	 * @param mailRgnCd the value for CUS_ADDR.MAIL_RGN_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setMailRgnCd(String mailRgnCd) {
		this.mailRgnCd = mailRgnCd == null ? null : mailRgnCd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.SVC_CTY_CD
	 *
	 * @return the value of CUS_ADDR.SVC_CTY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public String getSvcCtyCd() {
		return svcCtyCd;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.SVC_CTY_CD
	 *
	 * @param svcCtyCd the value for CUS_ADDR.SVC_CTY_CD
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setSvcCtyCd(String svcCtyCd) {
		this.svcCtyCd = svcCtyCd == null ? null : svcCtyCd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.ADDR_EFF_DT
	 *
	 * @return the value of CUS_ADDR.ADDR_EFF_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public Date getAddrEffDt() {
		return addrEffDt;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.ADDR_EFF_DT
	 *
	 * @param addrEffDt the value for CUS_ADDR.ADDR_EFF_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setAddrEffDt(Date addrEffDt) {
		this.addrEffDt = addrEffDt;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column CUS_ADDR.ADDR_END_DT
	 *
	 * @return the value of CUS_ADDR.ADDR_END_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public Date getAddrEndDt() {
		return addrEndDt;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column CUS_ADDR.ADDR_END_DT
	 *
	 * @param addrEndDt the value for CUS_ADDR.ADDR_END_DT
	 *
	 * @mbggenerated Mon Sep 24 10:28:53 IST 2018
	 */
	public void setAddrEndDt(Date addrEndDt) {
		this.addrEndDt = addrEndDt;
	}

	public String getAddrLabel() {
		return addrLabel;
	}

	public void setAddrLabel(String addrLabel) {
		this.addrLabel = addrLabel;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}