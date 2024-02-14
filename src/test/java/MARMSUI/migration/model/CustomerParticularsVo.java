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
 * Value Object to store customer particulars
 *
 */
public class CustomerParticularsVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private long  internalId;
	private String title;
	private String familyName;
	private String givenName;
	private String cardName;
	private String nationality;
	private String birthDate;
	private String gender;
	private String countryOfResidence;
	private String email;
	private String countryOfBirth;
	private String maritalStatus;
	private String mailingAddressInd;
	private String passportName;
	private String passportNo;
	private String passportExpDate;
	private String corporateCode;
	private String altEmailAddress;
	private String companyNameLine1;
	private String companyNameLine2;
	private String positionHeld;
	private int numberOfChildren;
	private String staffID;
	private String companySecretaryName;
	private String companyHQCityCode;
	private String companyHQCountryCode;
	private String weddingAnnivDate;
	private String incomeLevel;
	private String stopMailInd;
	private long principalInternalID;
	private boolean pending;
	private String partnerPromoCode;
	private String countryOfIssuance;
	private String socialMedia;
	private boolean updateBySAA;
	private String lastChangeUserID;
	private String lastChangeDate;
	private String lastChangeUserIdCusAddnInfo;
	private String lastChangeDateCusAddnInfo;
	private String principalCustomerID;
	private String principalProgramCode;
	private String passagesID;
	private String correspondenceName;
	private String specialCustomerType;
	private String mailingName;
	private String secretaryTitle;
	private String secretaryPhoneCtryCd;
	private String secretaryPhoneAreaCd;
	private String secretaryPhoneNumber;
	private String secretaryEmail;
	private boolean isPrefEmailUpdated;
	private boolean isAltEmailUpdated;
	private boolean isMailIndUpdated;
	private boolean updatedWithPreference;
	private String dirMailerInd;
	private String studentID;
	private String university;
	private String graduationYear;
	private String studentInd;
	private String prevPrefEmailAddress;
	private String prevAltEmailAddress;    
	private List<CusAddr> previousAddress;
	private String prevPrefEmailAddressChgDt;
	private String prevAltEmailAddressChgDt;
	private String principalTierStatus;
	private String country;
	private String area;
	private String phoneNo;
	private boolean customerRecordModified;
	private String eCardOption;
	private String isEUresident;
	private String redressNo;
	private String ktnNo;
	private String enrollmtDt;	
	
	private String mailAdrCntry;
	private String mailAdrCntrlCity;
	private String mobileCntryCd;
	private String mobileAreaCd;
	private String studentValidTill;
	//Added by Aubrey for KFPROG-1560 starts
	private boolean emailVerified;
	private boolean mobileVerified;
	private String lastEmailVerified;
	private String lastMobileVerified;
	//Added by Aubrey for KFPROG-1560 Ends
	private String lastReadDate;
	private String rcreDate;
	
	/*Added by Jeff for KFPROG-2298 */
	// Getting EnrolmtSrcInfo and adding in getter and setter
	private String srcOfEnr;
	private String pnrRef;
	private String etLastDigits;
	
	public String getPnrRef() {
		return pnrRef;
	}
	public void setPnrRef(String pnrRef) {
		this.pnrRef = pnrRef;
	}
	public String getEtLastDigits() {
		return etLastDigits;
	}
	public void setEtLastDigits(String etLastDigits) {
		this.etLastDigits = etLastDigits;
	}
	
	
	public String getSrcOfEnr() {
		return srcOfEnr;
	}
	public void setSrcOfEnr(String srcOfEnr) {
		this.srcOfEnr = srcOfEnr;
	}
	
	public String getStudentValidTill() {
		return studentValidTill;
	}
	public void setStudentValidTill(String studentValidTill) {
		this.studentValidTill = studentValidTill;
	}
	public long getInternalId() {
		return internalId;
	}
	public void setInternalId(long internalId) {
		this.internalId = internalId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountryOfBirth() {
		return countryOfBirth;
	}
	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getCountryOfResidence() {
		return countryOfResidence;
	}
	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getMailingAddressInd() {
		return mailingAddressInd;
	}
	public void setMailingAddressInd(String mailingAddressInd) {
		this.mailingAddressInd = mailingAddressInd;
	}
	public String getPassportName() {
		return passportName;
	}
	public void setPassportName(String passportName) {
		this.passportName = passportName;
	}

	public String getCorporateCode() {
		return corporateCode;
	}
	public void setCorporateCode(String corporateCode) {
		this.corporateCode = corporateCode;
	}
	public String getAltEmailAddress() {
		return altEmailAddress;
	}
	public void setAltEmailAddress(String altEmailAddress) {
		this.altEmailAddress = altEmailAddress;
	}
	public String getCompanyNameLine1() {
		return companyNameLine1;
	}
	public void setCompanyNameLine1(String companyNameLine1) {
		this.companyNameLine1 = companyNameLine1;
	}
	public String getCompanyNameLine2() {
		return companyNameLine2;
	}
	public void setCompanyNameLine2(String companyNameLine2) {
		this.companyNameLine2 = companyNameLine2;
	}
	public String getPositionHeld() {
		return positionHeld;
	}
	public void setPositionHeld(String positionHeld) {
		this.positionHeld = positionHeld;
	}
	public int getNumberOfChildren() {
		return numberOfChildren;
	}
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public String getCompanySecretaryName() {
		return companySecretaryName;
	}
	public void setCompanySecretaryName(String companySecretaryName) {
		this.companySecretaryName = companySecretaryName;
	}
	public String getCompanyHQCityCode() {
		return companyHQCityCode;
	}
	public void setCompanyHQCityCode(String companyHQCityCode) {
		this.companyHQCityCode = companyHQCityCode;
	}
	public String getCompanyHQCountryCode() {
		return companyHQCountryCode;
	}
	public void setCompanyHQCountryCode(String companyHQCountryCode) {
		this.companyHQCountryCode = companyHQCountryCode;
	}
	public String getIncomeLevel() {
		return incomeLevel;
	}
	public void setIncomeLevel(String incomeLevel) {
		this.incomeLevel = incomeLevel;
	}
	public String getStopMailInd() {
		return stopMailInd;
	}
	public void setStopMailInd(String stopMailInd) {
		this.stopMailInd = stopMailInd;
	}
	public long getPrincipalInternalID() {
		return principalInternalID;
	}
	public void setPrincipalInternalID(long principalInternalID) {
		this.principalInternalID = principalInternalID;
	}
	public String getPartnerPromoCode() {
		return partnerPromoCode;
	}
	public void setPartnerPromoCode(String partnerPromoCode) {
		this.partnerPromoCode = partnerPromoCode;
	}
	public String getCountryOfIssuance() {
		return countryOfIssuance;
	}
	public void setCountryOfIssuance(String countryOfIssuance) {
		this.countryOfIssuance = countryOfIssuance;
	}
	public String getSocialMedia() {
		return socialMedia;
	}
	public void setSocialMedia(String socialMedia) {
		this.socialMedia = socialMedia;
	}
	public boolean isUpdateBySAA() {
		return updateBySAA;
	}
	public void setUpdateBySAA(boolean updateBySAA) {
		this.updateBySAA = updateBySAA;
	}
	public String getLastChangeUserID() {
		return lastChangeUserID;
	}
	public void setLastChangeUserID(String lastChangeUserID) {
		this.lastChangeUserID = lastChangeUserID;
	}
	public String getPrincipalCustomerID() {
		return principalCustomerID;
	}
	public void setPrincipalCustomerID(String principalCustomerID) {
		this.principalCustomerID = principalCustomerID;
	}
	public String getPrincipalProgramCode() {
		return principalProgramCode;
	}
	public void setPrincipalProgramCode(String principalProgramCode) {
		this.principalProgramCode = principalProgramCode;
	}
	public String getPassagesID() {
		return passagesID;
	}
	public void setPassagesID(String passagesID) {
		this.passagesID = passagesID;
	}
	public String getCorrespondenceName() {
		return correspondenceName;
	}
	public void setCorrespondenceName(String correspondenceName) {
		this.correspondenceName = correspondenceName;
	}
	public String getSpecialCustomerType() {
		return specialCustomerType;
	}
	public void setSpecialCustomerType(String specialCustomerType) {
		this.specialCustomerType = specialCustomerType;
	}
	public String getMailingName() {
		return mailingName;
	}
	public void setMailingName(String mailingName) {
		this.mailingName = mailingName;
	}
	public String getSecretaryTitle() {
		return secretaryTitle;
	}
	public void setSecretaryTitle(String secretaryTitle) {
		this.secretaryTitle = secretaryTitle;
	}
	public String getSecretaryPhoneCtryCd() {
		return secretaryPhoneCtryCd;
	}
	public void setSecretaryPhoneCtryCd(String secretaryPhoneCtryCd) {
		this.secretaryPhoneCtryCd = secretaryPhoneCtryCd;
	}
	public String getSecretaryPhoneAreaCd() {
		return secretaryPhoneAreaCd;
	}
	public void setSecretaryPhoneAreaCd(String secretaryPhoneAreaCd) {
		this.secretaryPhoneAreaCd = secretaryPhoneAreaCd;
	}
	public String getSecretaryPhoneNumber() {
		return secretaryPhoneNumber;
	}
	public void setSecretaryPhoneNumber(String secretaryPhoneNumber) {
		this.secretaryPhoneNumber = secretaryPhoneNumber;
	}
	public String getSecretaryEmail() {
		return secretaryEmail;
	}
	public void setSecretaryEmail(String secretaryEmail) {
		this.secretaryEmail = secretaryEmail;
	}
	public boolean isPrefEmailUpdated() {
		return isPrefEmailUpdated;
	}
	public void setPrefEmailUpdated(boolean isPrefEmailUpdated) {
		this.isPrefEmailUpdated = isPrefEmailUpdated;
	}
	public boolean isAltEmailUpdated() {
		return isAltEmailUpdated;
	}
	public void setAltEmailUpdated(boolean isAltEmailUpdated) {
		this.isAltEmailUpdated = isAltEmailUpdated;
	}
	public boolean isMailIndUpdated() {
		return isMailIndUpdated;
	}
	public void setMailIndUpdated(boolean isMailIndUpdated) {
		this.isMailIndUpdated = isMailIndUpdated;
	}
	public boolean isUpdatedWithPreference() {
		return updatedWithPreference;
	}
	public void setUpdatedWithPreference(boolean updatedWithPreference) {
		this.updatedWithPreference = updatedWithPreference;
	}
	public String getDirMailerInd() {
		return dirMailerInd;
	}
	public void setDirMailerInd(String dirMailerInd) {
		this.dirMailerInd = dirMailerInd;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getGraduationYear() {
		return graduationYear;
	}
	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}
	public String getStudentInd() {
		return studentInd;
	}
	public void setStudentInd(String studentInd) {
		this.studentInd = studentInd;
	}
	public String getPrevPrefEmailAddress() {
		return prevPrefEmailAddress;
	}
	public void setPrevPrefEmailAddress(String prevPrefEmailAddress) {
		this.prevPrefEmailAddress = prevPrefEmailAddress;
	}
	public String getPrevAltEmailAddress() {
		return prevAltEmailAddress;
	}
	public void setPrevAltEmailAddress(String prevAltEmailAddress) {
		this.prevAltEmailAddress = prevAltEmailAddress;
	}
	public String getPassportExpDate() {
		return passportExpDate;
	}
	public void setPassportExpDate(String passportExpDate) {
		this.passportExpDate = passportExpDate;
	}
	public String getWeddingAnnivDate() {
		return weddingAnnivDate;
	}
	public void setWeddingAnnivDate(String weddingAnnivDate) {
		this.weddingAnnivDate = weddingAnnivDate;
	}
	public boolean isPending() {
		return pending;
	}
	public void setPending(boolean pending) {
		this.pending = pending;
	}
	public String getLastChangeDate() {
		return lastChangeDate;
	}
	public void setLastChangeDate(String lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
	public String getPrevPrefEmailAddressChgDt() {
		return prevPrefEmailAddressChgDt;
	}
	public void setPrevPrefEmailAddressChgDt(String prevPrefEmailAddressChgDt) {
		this.prevPrefEmailAddressChgDt = prevPrefEmailAddressChgDt;
	}
	public String getPrevAltEmailAddressChgDt() {
		return prevAltEmailAddressChgDt;
	}
	public void setPrevAltEmailAddressChgDt(String prevAltEmailAddressChgDt) {
		this.prevAltEmailAddressChgDt = prevAltEmailAddressChgDt;
	}
	public String getPrincipalTierStatus() {
		return principalTierStatus;
	}
	public void setPrincipalTierStatus(String principalTierStatus) {
		this.principalTierStatus = principalTierStatus;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public boolean isCustomerRecordModified() {
		return customerRecordModified;
	}
	public void setCustomerRecordModified(boolean customerRecordModified) {
		this.customerRecordModified = customerRecordModified;
	}

	public String getIsEUresident() {
		return isEUresident;
	}
	public void setIsEUresident(String isEUresident) {
		this.isEUresident = isEUresident;
	}
	public String getRedressNo() {
		return redressNo;
	}
	public void setRedressNo(String redressNo) {
		this.redressNo = redressNo;
	}
	public String geteCardOption() {
		return eCardOption;
	}
	public void seteCardOption(String eCardOption) {
		this.eCardOption = eCardOption;
	}
	public String getKtnNo() {
		return ktnNo;
	}
	public void setKtnNo(String ktnNo) {
		this.ktnNo = ktnNo;
	}
	public String getEnrollmtDt() {
		return enrollmtDt;
	}
	public void setEnrollmtDt(String enrollmtDt) {
		this.enrollmtDt = enrollmtDt;
	}
	public String getLastChangeUserIdCusAddnInfo() {
		return lastChangeUserIdCusAddnInfo;
	}
	public void setLastChangeUserIdCusAddnInfo(String lastChangeUserIdCusAddnInfo) {
		this.lastChangeUserIdCusAddnInfo = lastChangeUserIdCusAddnInfo;
	}
	public String getLastChangeDateCusAddnInfo() {
		return lastChangeDateCusAddnInfo;
	}
	public void setLastChangeDateCusAddnInfo(String lastChangeDateCusAddnInfo) {
		this.lastChangeDateCusAddnInfo = lastChangeDateCusAddnInfo;
	}
	public List<CusAddr> getPreviousAddress() {
		return previousAddress;
	}
	public void setPreviousAddress(List<CusAddr> previousAddress) {
		this.previousAddress = previousAddress;
	}
	public String getMailAdrCntry() {
		return mailAdrCntry;
	}
	public void setMailAdrCntry(String mailAdrCntry) {
		this.mailAdrCntry = mailAdrCntry;
	}
	public String getMailAdrCntrlCity() {
		return mailAdrCntrlCity;
	}
	public void setMailAdrCntrlCity(String mailAdrCntrlCity) {
		this.mailAdrCntrlCity = mailAdrCntrlCity;
	}
	public String getMobileCntryCd() {
		return mobileCntryCd;
	}
	public void setMobileCntryCd(String mobileCntryCd) {
		this.mobileCntryCd = mobileCntryCd;
	}
	public String getMobileAreaCd() {
		return mobileAreaCd;
	}
	public void setMobileAreaCd(String mobileAreaCd) {
		this.mobileAreaCd = mobileAreaCd;
	}
	
	//Added by Aubrey for KFPROG-1560 starts
	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public boolean isMobileVerified() {
		return mobileVerified;
	}

	public void setMobileVerified(boolean mobileVerified) {
		this.mobileVerified = mobileVerified;
	}

	public String getLastEmailVerified() {
		return lastEmailVerified;
	}

	public void setLastEmailVerified(String lastEmailVerified) {
		this.lastEmailVerified = lastEmailVerified;
	}

	public String getLastMobileVerified() {
		return lastMobileVerified;
	}

	public void setLastMobileVerified(String lastMobileVerified) {
		this.lastMobileVerified = lastMobileVerified;
	}
	//Added by Aubrey for KFPROG-1560 starts
	public String getLastReadDate() {
		return lastReadDate;
	}
	public void setLastReadDate(String lastReadDate) {
		this.lastReadDate = lastReadDate;
	}
	public String getRcreDate() {
		return rcreDate;
	}
	public void setRcreDate(String rcreDate) {
		this.rcreDate = rcreDate;
	}
	
}
