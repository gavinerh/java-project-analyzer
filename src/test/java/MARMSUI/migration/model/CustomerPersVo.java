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
 * Value Object to hold customer personal data details
 *
 */
public class CustomerPersVo {
	private Long intId;
	private String rcreUserId;
	private String title;
	private String lchgUserId;
	private String familyName;
	private String rcreDt;
	private String givenName;		
	private String gender;
	private String lchgDt;
	private String dob;
	private String nationalityCd;
	private String ctryOfBirth;
	private String maritalStatusInd;
	private String enrCreateCtyCd;
	private String passportId;
	private String staffId;
	private String passportExpDt;
	private String prefLangWritten1;
	private String emailAddr;
	private String srcOfEnr;
	private String enrCreateUserId;
	private String cardName;
	private String enrCreateDt;
	private String formOfEnrInd;
	private String enrCreateSubsrc;
	private String dtOfEnrFormReceipt;
	private String srcOfPrgInfoCd;
	private String srcOfPrgInfoTxt;
	private String introducerIntId;
	private String partnerPromoCd;
	
	private String prefLangWritten2;
	private String splCusType;
	private String prefLangSpoken1;
	private String frmPendFlg;
	private String prefLangSpoken2;
	private String introducerPrgCd;
	private String prefLangLocalFlg;
	private String directMailInd;
	private String stopMailInd;	
	private String altEmailAddr;
	private String corresName;
	private String mailingName;
	private String passportIssCtry;
	private String mailAddrInd;
	private String vipFlg;
	private String blkCusFlg;
	private String dedupStatus;
	private String principalIntId;
	private String cobrandActivatePrt;
	private String introducerCustomerId;	
	private String ctryOfResidence;
	private String lastVerified;
	private String euResidnt;
	
	private boolean updateBySAA = false;
	
	
	
	
	public void updateBySAA( boolean boo )
    {
        updateBySAA = boo;
    }
    public boolean updateBySAA()
    {
        return updateBySAA;
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
	public String getRcreDt() {
		return rcreDt;
	}
	public void setRcreDt(String rcreDt) {
		this.rcreDt = rcreDt;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getLchgUserId() {
		return lchgUserId;
	}
	public void setLchgUserId(String lchgUserId) {
		this.lchgUserId = lchgUserId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLchgDt() {
		return lchgDt;
	}
	public void setLchgDt(String lchgDt) {
		this.lchgDt = lchgDt;
	}

	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getNationalityCd() {
		return nationalityCd;
	}
	public void setNationalityCd(String nationalityCd) {
		this.nationalityCd = nationalityCd;
	}
	public String getCtryOfBirth() {
		return ctryOfBirth;
	}
	public void setCtryOfBirth(String ctryOfBirth) {
		this.ctryOfBirth = ctryOfBirth;
	}
	public String getMaritalStatusInd() {
		return maritalStatusInd;
	}
	public void setMaritalStatusInd(String maritalStatusInd) {
		this.maritalStatusInd = maritalStatusInd;
	}
	public String getPassportId() {
		return passportId;
	}
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getFormOfEnrInd() {
		return formOfEnrInd;
	}
	public void setFormOfEnrInd(String formOfEnrInd) {
		this.formOfEnrInd = formOfEnrInd;
	}
	public String getSrcOfEnr() {
		return srcOfEnr;
	}
	public void setSrcOfEnr(String srcOfEnr) {
		this.srcOfEnr = srcOfEnr;
	}
	public String getEnrCreateUserId() {
		return enrCreateUserId;
	}
	public void setEnrCreateUserId(String enrCreateUserId) {
		this.enrCreateUserId = enrCreateUserId;
	}
	public String getPassportExpDt() {
		return passportExpDt;
	}
	public void setPassportExpDt(String passportExpDt) {
		this.passportExpDt = passportExpDt;
	}
	public String getEnrCreateDt() {
		return enrCreateDt;
	}
	public void setEnrCreateDt(String enrCreateDt) {
		this.enrCreateDt = enrCreateDt;
	}
	
	
	public String getDtOfEnrFormReceipt() {
		return dtOfEnrFormReceipt;
	}
	public void setDtOfEnrFormReceipt(String dtOfEnrFormReceipt) {
		this.dtOfEnrFormReceipt = dtOfEnrFormReceipt;
	}
	public String getEnrCreateCtyCd() {
		return enrCreateCtyCd;
	}
	public void setEnrCreateCtyCd(String enrCreateCtyCd) {
		this.enrCreateCtyCd = enrCreateCtyCd;
	}
	public String getPrefLangSpoken2() {
		return prefLangSpoken2;
	}
	public void setPrefLangSpoken2(String prefLangSpoken2) {
		this.prefLangSpoken2 = prefLangSpoken2;
	}
	public String getSrcOfPrgInfoCd() {
		return srcOfPrgInfoCd;
	}
	public void setSrcOfPrgInfoCd(String srcOfPrgInfoCd) {
		this.srcOfPrgInfoCd = srcOfPrgInfoCd;
	}
	public String getPrefLangSpoken1() {
		return prefLangSpoken1;
	}
	public void setPrefLangSpoken1(String prefLangSpoken1) {
		this.prefLangSpoken1 = prefLangSpoken1;
	}
	public String getSrcOfPrgInfoTxt() {
		return srcOfPrgInfoTxt;
	}
	public void setSrcOfPrgInfoTxt(String srcOfPrgInfoTxt) {
		this.srcOfPrgInfoTxt = srcOfPrgInfoTxt;
	}
	public String getPrefLangWritten2() {
		return prefLangWritten2;
	}
	public void setPrefLangWritten2(String prefLangWritten2) {
		this.prefLangWritten2 = prefLangWritten2;
	}
	public String getEnrCreateSubsrc() {
		return enrCreateSubsrc;
	}
	public void setEnrCreateSubsrc(String enrCreateSubsrc) {
		this.enrCreateSubsrc = enrCreateSubsrc;
	}
	public String getIntroducerIntId() {
		return introducerIntId;
	}
	public void setIntroducerIntId(String introducerIntId) {
		this.introducerIntId = introducerIntId;
	}
	public String getPrefLangWritten1() {
		return prefLangWritten1;
	}
	public void setPrefLangWritten1(String prefLangWritten1) {
		this.prefLangWritten1 = prefLangWritten1;
	}
	public String getPartnerPromoCd() {
		return partnerPromoCd;
	}
	public void setPartnerPromoCd(String partnerPromoCd) {
		this.partnerPromoCd = partnerPromoCd;
	}
	


	
	public String getPrefLangLocalFlg() {
		return prefLangLocalFlg;
	}
	public void setPrefLangLocalFlg(String prefLangLocalFlg) {
		this.prefLangLocalFlg = prefLangLocalFlg;
	}
	public String getStopMailInd() {
		return stopMailInd;
	}
	public void setStopMailInd(String stopMailInd) {
		this.stopMailInd = stopMailInd;
	}
	
	public String getAltEmailAddr() {
		return altEmailAddr;
	}
	public void setAltEmailAddr(String altEmailAddr) {
		this.altEmailAddr = altEmailAddr;
	}
	public String getCorresName() {
		return corresName;
	}
	public void setCorresName(String corresName) {
		this.corresName = corresName;
	}
	
	public String getMailingName() {
		return mailingName;
	}
	public void setMailingName(String mailingName) {
		this.mailingName = mailingName;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getMailAddrInd() {
		return mailAddrInd;
	}
	public void setMailAddrInd(String mailAddrInd) {
		this.mailAddrInd = mailAddrInd;
	}
	public String getVipFlg() {
		return vipFlg;
	}
	public void setVipFlg(String vipFlg) {
		this.vipFlg = vipFlg;
	}
	public String getBlkCusFlg() {
		return blkCusFlg;
	}
	public void setBlkCusFlg(String blkCusFlg) {
		this.blkCusFlg = blkCusFlg;
	}
	public String getDedupStatus() {
		return dedupStatus;
	}
	public void setDedupStatus(String dedupStatus) {
		this.dedupStatus = dedupStatus;
	}
	public String getPrincipalIntId() {
		return principalIntId;
	}
	public void setPrincipalIntId(String principalIntId) {
		this.principalIntId = principalIntId;
	}
	public String getCobrandActivatePrt() {
		return cobrandActivatePrt;
	}
	public void setCobrandActivatePrt(String cobrandActivatePrt) {
		this.cobrandActivatePrt = cobrandActivatePrt;
	}
	public String getFrmPendFlg() {
		return frmPendFlg;
	}
	public void setFrmPendFlg(String frmPendFlg) {
		this.frmPendFlg = frmPendFlg;
	}
	public String getSplCusType() {
		return splCusType;
	}
	public void setSplCusType(String splCusType) {
		this.splCusType = splCusType;
	}
	public String getIntroducerPrgCd() {
		return introducerPrgCd;
	}
	public void setIntroducerPrgCd(String introducerPrgCd) {
		this.introducerPrgCd = introducerPrgCd;
	}
	public String getDirectMailInd() {
		return directMailInd;
	}
	public void setDirectMailInd(String directMailInd) {
		this.directMailInd = directMailInd;
	}
	public String getPassportIssCtry() {
		return passportIssCtry;
	}
	public void setPassportIssCtry(String passportIssCtry) {
		this.passportIssCtry = passportIssCtry;
	}
	public String getCtryOfResidence() {
		return ctryOfResidence;
	}
	public void setCtryOfResidence(String ctryOfResidence) {
		this.ctryOfResidence = ctryOfResidence;
	}
	public String getLastVerified() {
		return lastVerified;
	}
	public void setLastVerified(String lastVerified) {
		this.lastVerified = lastVerified;
	}
	public String getEuResidnt() {
		return euResidnt;
	}
	public void setEuResidnt(String euResidnt) {
		this.euResidnt = euResidnt;
	}
	public String getIntroducerCustomerId() {
		return introducerCustomerId;
	}
	public void setIntroducerCustomerId(String introducerCustomerId) {
		this.introducerCustomerId = introducerCustomerId;
	}
}
