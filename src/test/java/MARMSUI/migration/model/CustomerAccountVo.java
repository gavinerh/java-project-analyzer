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

import java.util.List;

/**
 * 
 * Value object to store customer account details
 *
 */
public class CustomerAccountVo {

	private String rcreUserId;

	private String rcreDt;

	private String lchgUserId;

	private String lchgDt;

	private Long intId;

	private String prgCd;

	private String cusId;

	private String acctStatusInd;

	private String acctStatusChgDt;

	private String acctStatusRsnCd;

	private String acctStatusRsnDesc;

	private String tierStatusInd;

	private String tierStatusChgDt;

	private String isYecFlg;

	private String cardValidTillDt;

	private String ignoreFlg;

	private String lastActDt;

	private String hvFlg;

	private String acvValue;

	private String acvOverrideFlg;

	private String airlineTier;

	private String hvTag;

	private String principalIntId;

	private List<CustomerCardIssVo> customerCard;

	public String getPrincipalIntId() {

		return principalIntId;
	}

	public void setPrincipalIntId(String principalIntId) {
		this.principalIntId = principalIntId;
	}

	public String getHvTag() {
		return hvTag;
	}

	public void setHvTag(String hvTag) {
		this.hvTag = hvTag;
	}

	public String getRcreUserId() {
		return rcreUserId;
	}

	public void setRcreUserId(String rcreUserId) {
		this.rcreUserId = rcreUserId;
	}

	public String getRcreDt() {
		return rcreDt;
	}

	public void setRcreDt(String rcreDt) {
		this.rcreDt = rcreDt;
	}

	public Long getIntId() {
		return intId;
	}

	public void setIntId(Long intId) {
		this.intId = intId;
	}

	public String getLchgDt() {
		return lchgDt;
	}

	public void setLchgDt(String lchgDt) {
		this.lchgDt = lchgDt;
	}

	public String getPrgCd() {
		return prgCd;
	}

	public void setPrgCd(String prgCd) {
		this.prgCd = prgCd;
	}

	public String getLchgUserId() {
		return lchgUserId;
	}

	public void setLchgUserId(String lchgUserId) {
		this.lchgUserId = lchgUserId;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getTierStatusChgDt() {
		return tierStatusChgDt;
	}

	public void setTierStatusChgDt(String tierStatusChgDt) {
		this.tierStatusChgDt = tierStatusChgDt;
	}

	public String getAcctStatusInd() {
		return acctStatusInd;
	}

	public void setAcctStatusInd(String acctStatusInd) {
		this.acctStatusInd = acctStatusInd;
	}

	public String getIsYecFlg() {
		return isYecFlg;
	}

	public void setIsYecFlg(String isYecFlg) {
		this.isYecFlg = isYecFlg;
	}

	public String getAcctStatusChgDt() {
		return acctStatusChgDt;
	}

	public void setAcctStatusChgDt(String acctStatusChgDt) {
		this.acctStatusChgDt = acctStatusChgDt;
	}

	public String getAcctStatusRsnCd() {
		return acctStatusRsnCd;
	}

	public void setAcctStatusRsnCd(String acctStatusRsnCd) {
		this.acctStatusRsnCd = acctStatusRsnCd;
	}

	public String getTierStatusInd() {
		return tierStatusInd;
	}

	public void setTierStatusInd(String tierStatusInd) {
		this.tierStatusInd = tierStatusInd;
	}

	public String getAcctStatusRsnDesc() {
		return acctStatusRsnDesc;
	}

	public void setAcctStatusRsnDesc(String acctStatusRsnDesc) {
		this.acctStatusRsnDesc = acctStatusRsnDesc;
	}

	public List<CustomerCardIssVo> getCustomerCard() {
		return customerCard;
	}

	public void setCustomerCard(List<CustomerCardIssVo> cardIssVos) {
		this.customerCard = cardIssVos;
	}

	public String getCardValidTillDt() {
		return cardValidTillDt;
	}

	public void setCardValidTillDt(String cardValidTillDt) {
		this.cardValidTillDt = cardValidTillDt;
	}

	public String getIgnoreFlg() {
		return ignoreFlg;
	}

	public void setIgnoreFlg(String ignoreFlg) {
		this.ignoreFlg = ignoreFlg;
	}

	public String getLastActDt() {
		return lastActDt;
	}

	public void setLastActDt(String lastActDt) {
		this.lastActDt = lastActDt;
	}

	public String getHvFlg() {
		return hvFlg;
	}

	public void setHvFlg(String hvFlg) {
		this.hvFlg = hvFlg;
	}

	public String getAcvValue() {
		return acvValue;
	}

	public void setAcvValue(String acvValue) {
		this.acvValue = acvValue;
	}

	public String getAcvOverrideFlg() {
		return acvOverrideFlg;
	}

	public void setAcvOverrideFlg(String acvOverrideFlg) {
		this.acvOverrideFlg = acvOverrideFlg;
	}

	public String getAirlineTier() {
		return airlineTier;
	}

	public void setAirlineTier(String airlineTier) {
		this.airlineTier = airlineTier;
	}
}
