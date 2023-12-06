package MARMSUI.SpecialisedSqlMappingToVo.model;


import java.util.Date;

public class TransReserveVal {

	private String userId;
	private java.util.Date rcreDt;
	private long intId;
	private long reserveVal;
	private java.util.Date reserveValDt;
	private String transCd;
	private java.util.Date orgExpDt;
	private java.util.Date newExpDt;
	private java.util.Date origReserveValDt;


	public java.util.Date getOrgExpDt() {
		return orgExpDt;
	}

	public void setOrgExpDt(java.util.Date orgExpDt) {
		this.orgExpDt = orgExpDt;
	}

	public TransReserveVal() {
		// blank
	}


	public long getIntId() {
		return intId;
	}
	public void setIntId(long intId) {
		this.intId = intId;
	}
	public long getReserveVal() {
		return reserveVal;
	}
	public void setReserveVal(long reserveVal) {
		this.reserveVal = reserveVal;
	}
	public java.util.Date getReserveValDt() {
		return reserveValDt;
	}
	public void setReserveValDt(java.util.Date reserveValDt) {
		this.reserveValDt = reserveValDt;
	}
	public String getTransCd() {
		return transCd;
	}
	public void setTransCd(String transCd) {
		this.transCd = transCd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public java.util.Date getRcreDt() {
		return rcreDt;
	}

	public void setRcreDt(java.util.Date rcreDt) {
		this.rcreDt = rcreDt;
	}

	public java.util.Date getOrigReserveValDt() {
		return origReserveValDt;
	}

	public void setOrigReserveValDt(java.util.Date origReserveValDt) {
		this.origReserveValDt = origReserveValDt;
	}

	public java.util.Date getNewExpDt() {
		return newExpDt;
	}

	public void setNewExpDt(Date newExpDt) {
		this.newExpDt = newExpDt;
	}


}
