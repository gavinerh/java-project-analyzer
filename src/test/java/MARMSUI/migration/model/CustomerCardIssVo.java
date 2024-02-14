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

/**
 * Value Object for keeping customer 
 * card issuance details
 *
 */
public class CustomerCardIssVo {

	private String rcreUserId;
	private String cardTypeMediaInd;
	private String lchgUserId;
	private String vndFulfillDt;
	private String lchgDt;
	private Long intId;
	private String rcreDt;
	private String prgCd;
	private String cusId;

	private String cardStatusChgDt;
	
	
	private String cardCreateDt;
	
	private String cardValidTillDt;
	private String ignoreFlg;
	private String cardSentToVndDt;
	private String stockCtrlNo;
	private String cardExpDt;
	private String qlfyInd;
	private String cardInd;
	private String cardStatusInd;
	private String issuanceReqFlg;
	private String cardReplDesc;
	private String oldCardName;
	private String cardReplInd;
	private String mailToAddrInd;
	private String cardNameChgInd;
	private String kitsResendInd;
	private String cardNoChgInd;
	private String lostCardInd;
	private String cardCreateCd;
	private String actionInd;
	private String approvalCd;
	private String supervisorId;
	private String supervisorDesc;
	private short cardSeqNo;
	private String prevTierStatusInd;
	
	private String auditId;
	private String vndFileName;
	private String downgradeSupFlg = "N";
	private String tierStatusInd;
	private Date cardDateSendToPrintersDate;



	public CustomerCardIssVo (CustomerCardIssVo cc){

		rcreUserId	= cc.rcreUserId;
		auditId = cc.auditId;
		rcreDt = cc.rcreDt;
		lchgUserId = cc.lchgUserId;
		intId= cc.intId;
		prgCd = cc.prgCd;
		cusId = cc.cusId;
		cardStatusInd = cc.cardStatusInd;
		cardTypeMediaInd = cc.cardTypeMediaInd;
		cardCreateCd = cc.cardCreateCd;
		cardSentToVndDt = cc.cardSentToVndDt;
		cardDateSendToPrintersDate= cc.cardDateSendToPrintersDate;
		stockCtrlNo = cc.stockCtrlNo;
		cardInd = cc.cardInd;
		issuanceReqFlg = cc.issuanceReqFlg;
		cardReplDesc = cc.cardReplDesc;
		oldCardName = cc.oldCardName;
		cardReplInd = cc.cardReplInd;
		cardNameChgInd = cc.cardNameChgInd;
		cardNoChgInd = cc.cardNoChgInd;
		lostCardInd = cc.lostCardInd;
		actionInd= cc.actionInd;
		approvalCd = cc.approvalCd;
		supervisorId= cc.supervisorId;
		supervisorDesc = cc.supervisorDesc;
		ignoreFlg = cc.ignoreFlg;
		mailToAddrInd = cc.mailToAddrInd;
		kitsResendInd = cc.kitsResendInd;
		tierStatusInd = cc.tierStatusInd;
		prevTierStatusInd= cc.prevTierStatusInd;
		qlfyInd = cc.qlfyInd;
		vndFileName= cc.vndFileName;
		downgradeSupFlg = cc.downgradeSupFlg;

	}

	public CustomerCardIssVo() {
	}
	
	
	
	
	
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public String getDowngradeSupFlg() {
		return downgradeSupFlg;
	}
	public void setDowngradeSupFlg(String downgradeSupFlg) {
		this.downgradeSupFlg = downgradeSupFlg;
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
	public String getLchgUserId() {
		return lchgUserId;
	}
	public void setLchgUserId(String lchgUserId) {
		this.lchgUserId = lchgUserId;
	}
	public Date getCardDateSendToPrintersDate() {
		return cardDateSendToPrintersDate;
	}
	public void setCardDateSendToPrintersDate(Date cardDateSendToPrintersDate) {
		this.cardDateSendToPrintersDate = cardDateSendToPrintersDate;
	}

	public String getLchgDt() {
		return lchgDt;
	}
	public void setLchgDt(String lchgDt) {
		this.lchgDt = lchgDt;
	}
	public String getCardCreateCd() {
		return cardCreateCd;
	}
	public void setCardCreateCd(String cardCreateCd) {
		this.cardCreateCd = cardCreateCd;
	}
	public Long getIntId() {
		return intId;
	}
	public void setIntId(Long intId) {
		this.intId = intId;
	}
	public String getPrgCd() {
		return prgCd;
	}
	public void setPrgCd(String prgCd) {
		this.prgCd = prgCd;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getCardStatusInd() {
		return cardStatusInd;
	}
	public void setCardStatusInd(String cardStatusInd) {
		this.cardStatusInd = cardStatusInd;
	}
	public String getCardStatusChgDt() {
		return cardStatusChgDt;
	}
	public void setCardStatusChgDt(String cardStatusChgDt) {
		this.cardStatusChgDt = cardStatusChgDt;
	}
	public String getCardTypeMediaInd() {
		return cardTypeMediaInd;
	}
	public void setCardTypeMediaInd(String cardTypeMediaInd) {
		this.cardTypeMediaInd = cardTypeMediaInd;
	}
	
	public String getCardCreateDt() {
		return cardCreateDt;
	}
	public void setCardCreateDt(String cardCreateDt) {
		this.cardCreateDt = cardCreateDt;
	}
	public String getCardExpDt() {
		return cardExpDt;
	}
	public void setCardExpDt(String cardExpDt) {
		this.cardExpDt = cardExpDt;
	}
	public String getCardValidTillDt() {
		return cardValidTillDt;
	}
	public void setCardValidTillDt(String cardValidTillDt) {
		this.cardValidTillDt = cardValidTillDt;
	}
	public String getCardSentToVndDt() {
		return cardSentToVndDt;
	}
	public void setCardSentToVndDt(String cardSentToVndDt) {
		this.cardSentToVndDt = cardSentToVndDt;
	}
	public String getVndFulfillDt() {
		return vndFulfillDt;
	}
	public void setVndFulfillDt(String vndFulfillDt) {
		this.vndFulfillDt = vndFulfillDt;
	}
	public String getStockCtrlNo() {
		return stockCtrlNo;
	}
	public void setStockCtrlNo(String stockCtrlNo) {
		this.stockCtrlNo = stockCtrlNo;
	}
	public String getCardInd() {
		return cardInd;
	}
	public void setCardInd(String cardInd) {
		this.cardInd = cardInd;
	}
	public String getIssuanceReqFlg() {
		return issuanceReqFlg;
	}
	public void setIssuanceReqFlg(String issuanceReqFlg) {
		this.issuanceReqFlg = issuanceReqFlg;
	}
	public String getCardReplDesc() {
		return cardReplDesc;
	}
	public void setCardReplDesc(String cardReplDesc) {
		this.cardReplDesc = cardReplDesc;
	}
	public String getOldCardName() {
		return oldCardName;
	}
	public void setOldCardName(String oldCardName) {
		this.oldCardName = oldCardName;
	}
	public String getCardReplInd() {
		return cardReplInd;
	}
	public void setCardReplInd(String cardReplInd) {
		this.cardReplInd = cardReplInd;
	}
	public String getCardNameChgInd() {
		return cardNameChgInd;
	}
	public void setCardNameChgInd(String cardNameChgInd) {
		this.cardNameChgInd = cardNameChgInd;
	}
	public String getCardNoChgInd() {
		return cardNoChgInd;
	}
	public void setCardNoChgInd(String cardNoChgInd) {
		this.cardNoChgInd = cardNoChgInd;
	}
	public String getLostCardInd() {
		return lostCardInd;
	}
	public void setLostCardInd(String lostCardInd) {
		this.lostCardInd = lostCardInd;
	}
	public String getActionInd() {
		return actionInd;
	}
	public void setActionInd(String actionInd) {
		this.actionInd = actionInd;
	}
	public String getApprovalCd() {
		return approvalCd;
	}
	public void setApprovalCd(String approvalCd) {
		this.approvalCd = approvalCd;
	}
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getSupervisorDesc() {
		return supervisorDesc;
	}
	public void setSupervisorDesc(String supervisorDesc) {
		this.supervisorDesc = supervisorDesc;
	}
	public String getIgnoreFlg() {
		return ignoreFlg;
	}
	public void setIgnoreFlg(String ignoreFlg) {
		this.ignoreFlg = ignoreFlg;
	}
	public short getCardSeqNo() {
		return cardSeqNo;
	}
	public void setCardSeqNo(short cardSeqNo) {
		this.cardSeqNo = cardSeqNo;
	}
	public String getMailToAddrInd() {
		return mailToAddrInd;
	}
	public void setMailToAddrInd(String mailToAddrInd) {
		this.mailToAddrInd = mailToAddrInd;
	}
	public String getKitsResendInd() {
		return kitsResendInd;
	}
	public void setKitsResendInd(String kitsResendInd) {
		this.kitsResendInd = kitsResendInd;
	}
	public String getTierStatusInd() {
		return tierStatusInd;
	}
	public void setTierStatusInd(String tierStatusInd) {
		this.tierStatusInd = tierStatusInd;
	}
	public String getPrevTierStatusInd() {
		return prevTierStatusInd;
	}
	public void setPrevTierStatusInd(String prevTierStatusInd) {
		this.prevTierStatusInd = prevTierStatusInd;
	}
	public String getQlfyInd() {
		return qlfyInd;
	}
	public void setQlfyInd(String qlfyInd) {
		this.qlfyInd = qlfyInd;
	}
	public String getVndFileName() {
		return vndFileName;
	}
	public void setVndFileName(String vndFileName) {
		this.vndFileName = vndFileName;
	}
}
