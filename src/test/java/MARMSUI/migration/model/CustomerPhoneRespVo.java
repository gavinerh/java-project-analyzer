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

public class CustomerPhoneRespVo {
	private String phoneType;
	private String phoneTypeStr;
	private String lchgDt;
	private String phoneCtryCd;
	private String phoneAreaCd;
	private String phoneNo;
	private String mobileMsgSvcFlg;
	private String mobileSvcProvider;
	private String mobileSubscNetw;
	private String pagerSvcTypeInd;
	private boolean rcvFltPagingInd;
	private boolean mobileRcvFltPagingInd;
	private String prefPhoneInd;
	private String altPhoneInd;

	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getPhoneCtryCd() {
		return phoneCtryCd;
	}
	public void setPhoneCtryCd(String phoneCtryCd) {
		this.phoneCtryCd = phoneCtryCd;
	}
	public String getLchgDt() {
		return lchgDt;
	}
	public void setLchgDt(String lchgDt) {
		this.lchgDt = lchgDt;
	}
	public String getPhoneAreaCd() {
		return phoneAreaCd;
	}
	public void setPhoneAreaCd(String phoneAreaCd) {
		this.phoneAreaCd = phoneAreaCd;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getMobileMsgSvcFlg() {
		return mobileMsgSvcFlg;
	}
	public void setMobileMsgSvcFlg(String mobileMsgSvcFlg) {
		this.mobileMsgSvcFlg = mobileMsgSvcFlg;
	}
	public String getMobileSvcProvider() {
		return mobileSvcProvider;
	}
	public void setMobileSvcProvider(String mobileSvcProvider) {
		this.mobileSvcProvider = mobileSvcProvider;
	}
	public String getMobileSubscNetw() {
		return mobileSubscNetw;
	}
	public void setMobileSubscNetw(String mobileSubscNetw) {
		this.mobileSubscNetw = mobileSubscNetw;
	}
	public String getPagerSvcTypeInd() {
		return pagerSvcTypeInd;
	}
	public void setPagerSvcTypeInd(String pagerSvcTypeInd) {
		this.pagerSvcTypeInd = pagerSvcTypeInd;
	}
	public String getPrefPhoneInd() {
		return prefPhoneInd;
	}
	public void setPrefPhoneInd(String prefPhoneInd) {
		this.prefPhoneInd = prefPhoneInd;
	}
	public String getAltPhoneInd() {
		return altPhoneInd;
	}
	public void setAltPhoneInd(String altPhoneInd) {
		this.altPhoneInd = altPhoneInd;
	}
	@JsonProperty(value="rcvFltPagingInd")
    public boolean isRcvFltPagingInd() {
                    return rcvFltPagingInd;
    }
    public void setRcvFltPagingInd(boolean rcvFltPagingInd) {
                    this.rcvFltPagingInd = rcvFltPagingInd;
    }
    @JsonProperty(value="mobileRcvFltPagingInd")
    public boolean isMobileRcvFltPagingInd() {
                    return mobileRcvFltPagingInd;
    }
    public void setMobileRcvFltPagingInd(boolean mobileRcvFltPagingInd) {
                    this.mobileRcvFltPagingInd = mobileRcvFltPagingInd;
    }
	public String getPhoneTypeStr() {
		return phoneTypeStr;
	}
	public void setPhoneTypeStr(String phoneTypeStr) {
		this.phoneTypeStr = phoneTypeStr;
	}
}
