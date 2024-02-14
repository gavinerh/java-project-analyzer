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
 * 
 * Value Object to manipulate the information on
 *  member's guardian/parent, if the member is a YEC.
 *
 */
public class CustomerGuardianInfoVo {
	
	private Long intId;
	private String coyCtyCd;
	private String rcreUserId;
	private short seqNo;
	private String coyAddrLine4;
	private String rcreDt;
	private String homeAddrLine4;
	private String givenName;
	private String lchgUserId;
	private String homeAddrLine2;
	private String familyName;
	private String title;
	private String lchgDt;
	private String homeStateProv;
	private String gender;
	private String homeAddrLine3;
	private String relnToMbr;
	private String coyCtryCd;
	private String relnToMbrTxt;
	private String homeAddrLine1;
	private String coyRgnCd;
	private String homeCtyCd;
	private String coyStateProv;
	private String homeCtryCd;
	private String coyAddrLine2;
	private String homeZipCd;
	private String coyAddrLine3;
	private String homeRgnCd;
	private String coyPhoneAreaCd;
	private String homePhoneCtryCd;
	private String guardianPrgCd;
	private String homePhoneAreaCd;
	private String coyPhoneCtryCd;
	private String homePhone;
	private String coyAddrLine1;
	private String coyPhone;
	private String guardianIntId;
	private String coyZipCd;
	/*Extra Fields*/
	private String guardianCustomerId;
	
	private String guardianConsent;
	private String guardianLink;
	private String linkDt;
	
	public String getGuardianConsent() {
		return guardianConsent;
	}
	public void setGuardianConsent(String guardianConsent) {
		this.guardianConsent = guardianConsent;
	}
	public String getGuardianLink() {
		return guardianLink;
	}
	public void setGuardianLink(String guardianLink) {
		this.guardianLink = guardianLink;
	}
	public String getLinkDt() {
		return linkDt;
	}
	public void setLinkDt(String linkDt) {
		this.linkDt = linkDt;
	}
	
	public String getHomeAddrLine4() {
		return homeAddrLine4;
	}
	public void setHomeAddrLine4(String homeAddrLine4) {
		this.homeAddrLine4 = homeAddrLine4;
	}
	public short getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(short seqNo) {
		this.seqNo = seqNo;
	}
	public String getRcreUserId() {
		return rcreUserId;
	}
	public void setRcreUserId(String rcreUserId) {
		this.rcreUserId = rcreUserId;
	}
	

	
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCoyZipCd() {
		return coyZipCd;
	}
	public void setCoyZipCd(String coyZipCd) {
		this.coyZipCd = coyZipCd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRelnToMbr() {
		return relnToMbr;
	}
	public void setRelnToMbr(String relnToMbr) {
		this.relnToMbr = relnToMbr;
	}
	public Long getIntId() {
		return intId;
	}
	public void setIntId(Long intId) {
		this.intId = intId;
	}
	public String getRelnToMbrTxt() {
		return relnToMbrTxt;
	}
	public void setRelnToMbrTxt(String relnToMbrTxt) {
		this.relnToMbrTxt = relnToMbrTxt;
	}
	public String getHomeAddrLine1() {
		return homeAddrLine1;
	}
	public void setHomeAddrLine1(String homeAddrLine1) {
		this.homeAddrLine1 = homeAddrLine1;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getHomeAddrLine2() {
		return homeAddrLine2;
	}
	public void setHomeAddrLine2(String homeAddrLine2) {
		this.homeAddrLine2 = homeAddrLine2;
	}
	public String getRcreDt() {
		return rcreDt;
	}
	public void setRcreDt(String rcreDt) {
		this.rcreDt = rcreDt;
	}
	public String getHomeAddrLine3() {
		return homeAddrLine3;
	}
	public void setHomeAddrLine3(String homeAddrLine3) {
		this.homeAddrLine3 = homeAddrLine3;
	}
	public String getLchgUserId() {
		return lchgUserId;
	}
	public void setLchgUserId(String lchgUserId) {
		this.lchgUserId = lchgUserId;
	}
	public String getHomeCtyCd() {
		return homeCtyCd;
	}
	public void setHomeCtyCd(String homeCtyCd) {
		this.homeCtyCd = homeCtyCd;
	}
	public String getGuardianCustomerId() {
		return guardianCustomerId;
	}
	public void setGuardianCustomerId(String guardianCustomerId) {
		this.guardianCustomerId = guardianCustomerId;
	}
	public String getHomeStateProv() {
		return homeStateProv;
	}
	public void setHomeStateProv(String homeStateProv) {
		this.homeStateProv = homeStateProv;
	}
	public String getHomeCtryCd() {
		return homeCtryCd;
	}
	public void setHomeCtryCd(String homeCtryCd) {
		this.homeCtryCd = homeCtryCd;
	}
	public String getLchgDt() {
		return lchgDt;
	}
	public void setLchgDt(String lchgDt) {
		this.lchgDt = lchgDt;
	}
	public String getHomeZipCd() {
		return homeZipCd;
	}
	public void setHomeZipCd(String homeZipCd) {
		this.homeZipCd = homeZipCd;
	}

	public String getCoyAddrLine3() {
		return coyAddrLine3;
	}
	public void setCoyAddrLine3(String coyAddrLine3) {
		this.coyAddrLine3 = coyAddrLine3;
	}

	public String getHomePhoneAreaCd() {
		return homePhoneAreaCd;
	}
	public void setHomePhoneAreaCd(String homePhoneAreaCd) {
		this.homePhoneAreaCd = homePhoneAreaCd;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getCoyAddrLine1() {
		return coyAddrLine1;
	}
	public void setCoyAddrLine1(String coyAddrLine1) {
		this.coyAddrLine1 = coyAddrLine1;
	}

	public String getHomePhoneCtryCd() {
		return homePhoneCtryCd;
	}
	public void setHomePhoneCtryCd(String homePhoneCtryCd) {
		this.homePhoneCtryCd = homePhoneCtryCd;
	}
	public String getCoyAddrLine4() {
		return coyAddrLine4;
	}
	public void setCoyAddrLine4(String coyAddrLine4) {
		this.coyAddrLine4 = coyAddrLine4;
	}
	public String getCoyCtyCd() {
		return coyCtyCd;
	}
	public void setCoyCtyCd(String coyCtyCd) {
		this.coyCtyCd = coyCtyCd;
	}
	public String getGuardianPrgCd() {
		return guardianPrgCd;
	}
	public void setGuardianPrgCd(String guardianPrgCd) {
		this.guardianPrgCd = guardianPrgCd;
	}
	public String getCoyStateProv() {
		return coyStateProv;
	}
	public void setCoyStateProv(String coyStateProv) {
		this.coyStateProv = coyStateProv;
	}
	public String getCoyCtryCd() {
		return coyCtryCd;
	}
	public void setCoyCtryCd(String coyCtryCd) {
		this.coyCtryCd = coyCtryCd;
	}
	
	public String getCoyRgnCd() {
		return coyRgnCd;
	}
	public void setCoyRgnCd(String coyRgnCd) {
		this.coyRgnCd = coyRgnCd;
	}
	public String getCoyPhoneCtryCd() {
		return coyPhoneCtryCd;
	}
	public void setCoyPhoneCtryCd(String coyPhoneCtryCd) {
		this.coyPhoneCtryCd = coyPhoneCtryCd;
	}
	public String getCoyPhoneAreaCd() {
		return coyPhoneAreaCd;
	}
	public void setCoyPhoneAreaCd(String coyPhoneAreaCd) {
		this.coyPhoneAreaCd = coyPhoneAreaCd;
	}
	public String getCoyPhone() {
		return coyPhone;
	}
	public void setCoyPhone(String coyPhone) {
		this.coyPhone = coyPhone;
	}
	public String getGuardianIntId() {
		return guardianIntId;
	}
	public void setGuardianIntId(String guardianIntId) {
		this.guardianIntId = guardianIntId;
	}
	public String getHomeRgnCd() {
		return homeRgnCd;
	}
	public void setHomeRgnCd(String homeRgnCd) {
		this.homeRgnCd = homeRgnCd;
	}
	public String getCoyAddrLine2() {
		return coyAddrLine2;
	}
	public void setCoyAddrLine2(String coyAddrLine2) {
		this.coyAddrLine2 = coyAddrLine2;
	}
	
}
