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
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Value Object to manipulate customer address details 
 *
 */
public class CustomerAddressVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String createdUserID;
	private String mailCtrlCityCode;
	private String addrLastChgDate;
	private String addrType;
	private String languageCode;
	// equivalent to addrLine1
	private String line1;
	private String createdDate;
	private String line2;
	private String cityCd;
	private String addressEffDate;
	private String otherCityDesc;
	private String line3;
	private String state;
	private String countryCd;
	private String line4;
	private String postalCode;
	private String addrLastChgUserID;
	private String mailRegionCode;
	private String svcCityCode;
	private String addressEndDate;
	private boolean isAddressUpdated = false;
	private boolean mailCtrlCityCodeUpdated = false;
	private String cityDesc;
	private String countryDesc;
	private String addressTypeStr;
	private boolean isAddressStopped = false;
	private String lastChangeDate;
	// added for krishop changes start
	private String addrLabel;
	private String remarks;
	// added for krishop changes end
	
	@JsonProperty(value="createdUserID")
	public String getCreatedUserID() {
		return createdUserID;
	}
	public void setCreatedUserID(String createdUserID) {
		this.createdUserID = createdUserID;
	}
	@JsonProperty(value="createdDate")
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	@JsonProperty(value="addrLastChgUserID")
	public String getAddrLastChgUserID() {
		return addrLastChgUserID;
	}
	public void setAddrLastChgUserID(String addrLastChgUserID) {
		this.addrLastChgUserID = addrLastChgUserID;
	}
	@JsonProperty(value="addrLastChgDate")
	public String getAddrLastChgDate() {
		return addrLastChgDate;
	}
	public void setAddrLastChgDate(String addrLastChgDate) {
		this.addrLastChgDate = addrLastChgDate;
	}
	@JsonProperty(value="languageCode")
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@JsonProperty(value = "addressType")
	public String getAddrType() {
		return addrType;
	}
	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	@JsonProperty(value = "line1")
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	@JsonProperty(value = "line2")
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	@JsonProperty(value = "line3")
	public String getLine3() {
		return line3;
	}
	public void setLine3(String line3) {
		this.line3 = line3;
	}

	@JsonProperty(value = "line4")
	public String getLine4() {
		return line4;
	}
	public void setLine4(String line4) {
		this.line4 = line4;
	}
	@JsonProperty(value="cityCd")
	public String getCityCd() {
		return cityCd;
	}
	public void setCityCd(String cityCd) {
		this.cityCd = cityCd;
	}
	@JsonProperty(value="otherCityDesc")
	public String getOtherCityDesc() {
		return otherCityDesc;
	}
	public void setOtherCityDesc(String otherCityDesc) {
		this.otherCityDesc = otherCityDesc;
	}
	@JsonProperty(value="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@JsonProperty(value="countryCd")
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	@JsonProperty(value="postalCode")
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	@JsonProperty(value="mailCtrlCityCode")
	public String getMailCtrlCityCode() {
		return mailCtrlCityCode;
	}
	public void setMailCtrlCityCode(String mailCtrlCityCode) {
		this.mailCtrlCityCode = mailCtrlCityCode;
	}
	@JsonProperty(value="mailRegionCode")
	public String getMailRegionCode() {
		return mailRegionCode;
	}
	public void setMailRegionCode(String mailRegionCode) {
		this.mailRegionCode = mailRegionCode;
	}
	@JsonProperty(value="svcCityCode")
	public String getSvcCityCode() {
		return svcCityCode;
	}
	public void setSvcCityCode(String svcCityCode) {
		this.svcCityCode = svcCityCode;
	}
	@JsonProperty(value="addressEffDate")
	public String getAddressEffDate() {
		return addressEffDate;
	}
	public void setAddressEffDate(String addressEffDate) {
		this.addressEffDate = addressEffDate;
	}
	@JsonProperty(value="addressEndDate")
	public String getAddressEndDate() {
		return addressEndDate;
	}
	public void setAddressEndDate(String addressEndDate) {
		this.addressEndDate = addressEndDate;
	}
    @JsonProperty("isAddressUpdated")
	public boolean getIsAddressUpdated() {
		return isAddressUpdated;
	}
	public void setIsAddressUpdated(boolean isAddressUpdated) {
		this.isAddressUpdated = isAddressUpdated;
	}

    @JsonIgnore
	public boolean getIsMailCtrlCityCodeUpdated() {
		return mailCtrlCityCodeUpdated;
	}
	public void setIsMailCtrlCityCodeUpdated(boolean mailCtrlCityCodeUpdated) {
		this.mailCtrlCityCodeUpdated = mailCtrlCityCodeUpdated;
	}
	public boolean isMailCtrlCityCodeUpdated() {
		return mailCtrlCityCodeUpdated;
	}
	public void setMailCtrlCityCodeUpdated(boolean mailCtrlCityCodeUpdated) {
		this.mailCtrlCityCodeUpdated = mailCtrlCityCodeUpdated;
	}
	public String getCityDesc() {
		return cityDesc;
	}
	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}
	public String getCountryDesc() {
		return countryDesc;
	}
	public void setCountryDesc(String countryDesc) {
		this.countryDesc = countryDesc;
	}
	public String getAddressTypeStr() {
		return addressTypeStr;
	}
	public void setAddressTypeStr(String addressTypeStr) {
		this.addressTypeStr = addressTypeStr;
	}
	@JsonProperty("isAddressStopped")
	public boolean isAddressStopped() {
		return isAddressStopped;
	}
	public void setAddressStopped(boolean isAddressStopped) {
		this.isAddressStopped = isAddressStopped;
	}
	public String getLastChangeDate() {
		return lastChangeDate;
	}
	public void setLastChangeDate(String lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
	public void setAddressUpdated(boolean isAddressUpdated) {
		this.isAddressUpdated = isAddressUpdated;
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
