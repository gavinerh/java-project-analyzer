package MARMSUI.SpecialisedSqlMappingToVo.model;

import java.util.Date;

public class PPSReserveVal{
	private String userId;
	private String auditId;
	private Date expDt;
	private String prgCD;
	private long intId;
	private long reserveVal;
	private String validFlg;
	private long extReserveVal;
	private long actualPPSVal;
	private String isNew;
	private Date qlfyStartDt;
	private Date qlfyEndDt;
	private Date origQlfyEndDt;
	private Date actualQlfyEndDt;
	private long totalReserveVal;
	private Date qlfyDt;
	private long ppsValReqForRequal;
	private int defYrsAllowToExtend;
	private Date newExpDt;
	
	public long getPpsValReqForRequal() {
		return ppsValReqForRequal;
	}

	public void setPpsValReqForRequal(long ppsValReqForRequal) {
		this.ppsValReqForRequal = ppsValReqForRequal;
	}

	public Date getQlfyDt() {
		return qlfyDt;
	}

	public void setQlfyDt(Date qlfyDt) {
		this.qlfyDt = qlfyDt;
	}

	public long getTotalReserveVal() {
		return extReserveVal+reserveVal;
	}

	public void setTotalReserveVal(long totalReserveVal) {
		this.totalReserveVal = totalReserveVal;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public PPSReserveVal() {
		// blank
	}


	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public Date getExpDt() {
		return expDt;
	}

	public void setExpDt(Date expDt) {
		this.expDt = expDt;
	}

	public long getExtReserveVal() {
		return extReserveVal;
	}

	public void setExtReserveVal(long extReserveVal) {
		this.extReserveVal = extReserveVal;
	}

	public String getPrgCD() {
		return prgCD;
	}

	public void setPrgCD(String prgCD) {
		this.prgCD = prgCD;
	}

	public long getReserveVal() {
		return reserveVal;
	}

	public void setReserveVal(long reserveVal) {
		this.reserveVal = reserveVal;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getValidFlg() {
		return validFlg;
	}

	public void setValidFlg(String validFlg) {
		this.validFlg = validFlg;
	}

	public long getIntId() {
		return intId;
	}

	public void setIntId(long intId) {
		this.intId = intId;
	}

	public long getActualPPSVal() {
		return actualPPSVal;
	}

	public void setActualPPSVal(long actualPPSVal) {
		this.actualPPSVal = actualPPSVal;
	}

	public Date getOrigQlfyEndDt() {
		return origQlfyEndDt;
	}

	public void setOrigQlfyEndDt(Date origQlfyEndDt) {
		this.origQlfyEndDt = origQlfyEndDt;
	}

	public Date getQlfyEndDt() {
		return qlfyEndDt;
	}

	public void setQlfyEndDt(Date qlfyEndDt) {
		this.qlfyEndDt = qlfyEndDt;
	}

	public Date getQlfyStartDt() {
		return qlfyStartDt;
	}

	public void setQlfyStartDt(Date qlfyStartDt) {
		this.qlfyStartDt = qlfyStartDt;
	}

	public Date getActualQlfyEndDt() {
		return actualQlfyEndDt;
	}

	public void setActualQlfyEndDt(Date actualQlfyEndDt) {
		this.actualQlfyEndDt = actualQlfyEndDt;
	}

	public int getDefYrsAllowToExtend() {
		return defYrsAllowToExtend;
	}

	public void setDefYrsAllowToExtend(int defYrsAllowToExtend) {
		this.defYrsAllowToExtend = defYrsAllowToExtend;
	}

	public Date getNewExpDt() {
		return newExpDt;
	}

	public void setNewExpDt(Date newExpDt) {
		this.newExpDt = newExpDt;
	}

}
