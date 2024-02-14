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
 * Value object class for manipulating
 *  credit card details
 *
 */
public class CustomerCreditCardVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String rcreUserId;
	private String crCardBrandCd;
	private String lchgUserId;
	private String crCardExpDt;
	private String lchgDt;
	private String crCardBankTxt;
	private Long intId;
	private String crCardTypeCd;
	private String rcreDt;
	private String crCardBankCd;
	private String token;
	private String crCardNo;
	private String cardholdername;
	private String crCardCobrandFlg;
	private String paymentcurrency;
	private String crCardBankCtryCd;
	private String defaultcardflag;
	private String crCardBankCtryTxt;
	private String cardCompany;
	
	public String getRcreDt() {
		return rcreDt;
	}
	public void setRcreDt(String rcreDt) {
		this.rcreDt = rcreDt;
	}
	public String getCrCardBrandCd() {
		return crCardBrandCd;
	}
	public void setCrCardBrandCd(String crCardBrandCd) {
		this.crCardBrandCd = crCardBrandCd;
	}
	public String getLchgUserId() {
		return lchgUserId;
	}
	public void setLchgUserId(String lchgUserId) {
		this.lchgUserId = lchgUserId;
	}
	public String getCrCardNo() {
		return crCardNo;
	}
	public void setCrCardNo(String crCardNo) {
		this.crCardNo = crCardNo;
	}
	public Long getIntId() {
		return intId;
	}
	public void setIntId(Long intId) {
		this.intId = intId;
	}
	public String getRcreUserId() {
		return rcreUserId;
	}
	public void setRcreUserId(String rcreUserId) {
		this.rcreUserId = rcreUserId;
	}

	public String getCrCardTypeCd() {
		return crCardTypeCd;
	}
	public void setCrCardTypeCd(String crCardTypeCd) {
		this.crCardTypeCd = crCardTypeCd;
	}
	
	public String getLchgDt() {
		return lchgDt;
	}
	public void setLchgDt(String lchgDt) {
		this.lchgDt = lchgDt;
	}

	public String getCrCardExpDt() {
		return crCardExpDt;
	}
	public void setCrCardExpDt(String crCardExpDt) {
		this.crCardExpDt = crCardExpDt;
	}
	public String getCrCardCobrandFlg() {
		return crCardCobrandFlg;
	}
	public void setCrCardCobrandFlg(String crCardCobrandFlg) {
		this.crCardCobrandFlg = crCardCobrandFlg;
	}
	public String getCrCardBankCtryCd() {
		return crCardBankCtryCd;
	}
	public void setCrCardBankCtryCd(String crCardBankCtryCd) {
		this.crCardBankCtryCd = crCardBankCtryCd;
	}
	public String getCrCardBankTxt() {
		return crCardBankTxt;
	}
	public void setCrCardBankTxt(String crCardBankTxt) {
		this.crCardBankTxt = crCardBankTxt;
	}
	public String getCrCardBankCtryTxt() {
		return crCardBankCtryTxt;
	}
	public void setCrCardBankCtryTxt(String crCardBankCtryTxt) {
		this.crCardBankCtryTxt = crCardBankCtryTxt;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPaymentcurrency() {
		return paymentcurrency;
	}
	public void setPaymentcurrency(String paymentcurrency) {
		this.paymentcurrency = paymentcurrency;
	}
	public String getCrCardBankCd() {
		return crCardBankCd;
	}
	public void setCrCardBankCd(String crCardBankCd) {
		this.crCardBankCd = crCardBankCd;
	}
	public String getDefaultcardflag() {
		return defaultcardflag;
	}
	public void setDefaultcardflag(String defaultcardflag) {
		this.defaultcardflag = defaultcardflag;
	}
	public String getCardholdername() {
		return cardholdername;
	}
	public void setCardholdername(String cardholdername) {
		this.cardholdername = cardholdername;
	}
	public String getCardCompany() {
		return cardCompany;
	}
	public void setCardCompany(String cardCompany) {
		this.cardCompany = cardCompany;
	}
}
