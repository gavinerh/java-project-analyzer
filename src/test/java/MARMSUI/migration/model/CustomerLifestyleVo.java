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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Value Object that holds customer's lifestyle and travel preferences
 *
 */
public class CustomerLifestyleVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String rcreUserId;
	private String lifestyleCd;
	
	private String lchgUserId;
	private String lifestyleCategory;
	private String lchgDt;
	private Long intId;
	private String lifestyleDesc;
	private String rcreDt;
	private String lifestyleType;
	private Long trvlRgnTotTripYr;
	private String lifestyleMbrNo;
	private Long trvlClsTotTripYr;
    private String lifestyleCategoryDesc;
	

    @JsonIgnore
	public String getRcreDt() {
		return rcreDt;
	}
	public void setRcreDt(String rcreDt) {
		this.rcreDt = rcreDt;
	}
	public String getLifestyleType() {
		return lifestyleType;
	}
	public void setLifestyleType(String lifestyleType) {
		this.lifestyleType = lifestyleType;
	}
	@JsonIgnore
	public String getLchgUserId() {
		return lchgUserId;
	}
	public void setLchgUserId(String lchgUserId) {
		this.lchgUserId = lchgUserId;
	}
	@JsonIgnore
	public Long getTrvlRgnTotTripYr() {
		return trvlRgnTotTripYr;
	}
	public void setTrvlRgnTotTripYr(Long trvlRgnTotTripYr) {
		this.trvlRgnTotTripYr = trvlRgnTotTripYr;
	}
	@JsonIgnore
	public String getLchgDt() {
		return lchgDt;
	}
	public void setLchgDt(String lchgDt) {
		this.lchgDt = lchgDt;
	}
	@JsonIgnore
	public String getLifestyleMbrNo() {
		return lifestyleMbrNo;
	}
	public void setLifestyleMbrNo(String lifestyleMbrNo) {
		this.lifestyleMbrNo = lifestyleMbrNo;
	}
	@JsonIgnore
	public Long getIntId() {
		return intId;
	}
	public void setIntId(Long intId) {
		this.intId = intId;
	}
	@JsonIgnore
	public Long getTrvlClsTotTripYr() {
		return trvlClsTotTripYr;
	}
	public void setTrvlClsTotTripYr(Long trvlClsTotTripYr) {
		this.trvlClsTotTripYr = trvlClsTotTripYr;
	}
	@JsonIgnore
	public String getRcreUserId() {
		return rcreUserId;
	}
	public void setRcreUserId(String rcreUserId) {
		this.rcreUserId = rcreUserId;
	}
	@JsonIgnore
	public String getLifestyleCd() {
		return lifestyleCd;
	}
	public void setLifestyleCd(String lifestyleCd) {
		this.lifestyleCd = lifestyleCd;
	}
	@JsonIgnore
	public String getLifestyleCategory() {
		return lifestyleCategory;
	}
	public void setLifestyleCategory(String lifestyleCategory) {
		this.lifestyleCategory = lifestyleCategory;
	}	
	public String getLifestyleDesc() {
		return lifestyleDesc;
	}
	public void setLifestyleDesc(String lifestyleDesc) {
		this.lifestyleDesc = lifestyleDesc;
	}
	@JsonIgnore
	public String getLifestyleCategoryDesc() {
		return lifestyleCategoryDesc;
	}
	public void setLifestyleCategoryDesc(String lifestyleCategoryDesc) {
		this.lifestyleCategoryDesc = lifestyleCategoryDesc;
	}
	


}
