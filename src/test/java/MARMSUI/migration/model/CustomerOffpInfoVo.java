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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 *Value Object that holds customer's other frequent flyer program information. 
 *
 */
public class CustomerOffpInfoVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String partnerTier;
	private String offpCode;
	private Long intId;
	private String lchgUserId;
	private String lchgDt;
	private String offpNumber;
	private String linkStatus;
	private String statusMatch;
	private String offpName;
	private String offpNumberOld;
	
	public String getPartnerTier() {
		return partnerTier;
	}
	public void setPartnerTier(String partnerTier) {
		this.partnerTier = partnerTier;
	}
	public String getLchgUserId() {
		return lchgUserId;
	}
	public void setLchgUserId(String lchgUserId) {
		this.lchgUserId = lchgUserId;
	}
	public String getStatusMatch() {
		return statusMatch;
	}
	public void setStatusMatch(String statusMatch) {
		this.statusMatch = statusMatch;
	}
	
	public String getLchgDt() {
		return lchgDt;
	}
	public void setLchgDt(String lchgDt) {
		this.lchgDt = lchgDt;
	}
	public Long getIntId() {
		return intId;
	}
	public void setIntId(Long intId) {
		this.intId = intId;
	}
	
	@JsonProperty(value="offpCode")
	public String getOffpCode() {
		return offpCode;
	}
	public void setOffpCode(String offpCode) {
		this.offpCode = offpCode;
	}
	public String getLinkStatus() {
		return linkStatus;
	}
	public void setLinkStatus(String linkStatus) {
		this.linkStatus = linkStatus;
	}
	@JsonProperty(value="offpNumber")
	public String getOffpNumber() {
		return offpNumber;
	}
	public void setOffpNumber(String offpNumber) {
		this.offpNumber = offpNumber;
	}
	public String getOffpName() {
		return offpName;
	}
	public void setOffpName(String offpName) {
		this.offpName = offpName;
	}
	public String getOffpNumberOld() {
		return offpNumberOld;
	}
	public void setOffpNumberOld(String offpNumberOld) {
		this.offpNumberOld = offpNumberOld;
	}
}
