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

public class SubProgramVo {

	private String subProgramCode;
	private String subProgramTierCode;
	private String subProgramTier;
	private double shortfall;
	private double cumulativeSpent;
	private String subProgramTierExpiryDate;
	private String shippingAddressIndicator;
	private String billingAddressIndicator;
	private CustomerLifestyleVo[] lifestyleDetailsVo;
	private CustomerLifestyleResponseVo[] lifestyleDetails;
	
	public String getSubProgramCode() {
		return subProgramCode;
	}
	public void setSubProgramCode(String subProgramCode) {
		this.subProgramCode = subProgramCode;
	}
	public String getSubProgramTierCode() {
		return subProgramTierCode;
	}
	public void setSubProgramTierCode(String subProgramTierCode) {
		this.subProgramTierCode = subProgramTierCode;
	}
	public String getSubProgramTier() {
		return subProgramTier;
	}
	public void setSubProgramTier(String subProgramTier) {
		this.subProgramTier = subProgramTier;
	}
	public String getSubProgramTierExpiryDate() {
		return subProgramTierExpiryDate;
	}
	public void setSubProgramTierExpiryDate(String subProgramTierExpiryDate) {
		this.subProgramTierExpiryDate = subProgramTierExpiryDate;
	}
	public String getShippingAddressIndicator() {
		return shippingAddressIndicator;
	}
	public void setShippingAddressIndicator(String shippingAddressIndicator) {
		this.shippingAddressIndicator = shippingAddressIndicator;
	}
	public String getBillingAddressIndicator() {
		return billingAddressIndicator;
	}
	public void setBillingAddressIndicator(String billingAddressIndicator) {
		this.billingAddressIndicator = billingAddressIndicator;
	}
	@JsonIgnore
	public CustomerLifestyleVo[] getLifestyleDetailsVo() {
		return lifestyleDetailsVo;
	}
	public void setLifestyleDetailsVo(CustomerLifestyleVo[] lifestyleDetailsVo) {
		this.lifestyleDetailsVo = lifestyleDetailsVo;
	}
	public CustomerLifestyleResponseVo[] getLifestyleDetails() {
		return lifestyleDetails;
	}
	public void setLifestyleDetails(CustomerLifestyleResponseVo[] lifestyleDetails) {
		this.lifestyleDetails = lifestyleDetails;
	}
	public double getShortfall() {
		return shortfall;
	}
	public void setShortfall(double shortfall) {
		this.shortfall = shortfall;
	}
	public double getCumulativeSpent() {
		return cumulativeSpent;
	}
	public void setCumulativeSpent(double cumulativeSpent) {
		this.cumulativeSpent = cumulativeSpent;
	}
	
}
