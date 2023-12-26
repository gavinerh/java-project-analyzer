package MARMSUI.SpecialisedSqlMappingToVo.model;

import java.sql.Date;

public class PPSYearQual {

	private String intId;
	private int qualPrdSeq;
	private String prgCd;
	private Date qlfyStartDt;
	private Date origQlfyEndDt;
	private Date actualQlfyEndDt;
	private long yrVal;
	private long mergeVal;
	private String validFlg;
	private boolean isCurrentYrFlg; // MKP 91044 addendum
	
	public PPSYearQual() {
		// blank
	}


	public String getIntId() {
		return intId;
	}

	public void setIntId(String intId) {
		this.intId = intId;
	}

	public int getQualPrdSeq() {
		return qualPrdSeq;
	}

	public void setQualPrdSeq(int qualPrdSeq) {
		this.qualPrdSeq = qualPrdSeq;
	}

	public String getPrgCd() {
		return prgCd;
	}

	public void setPrgCd(String prgCd) {
		this.prgCd = prgCd;
	}

	public Date getQlfyStartDt() {
		return qlfyStartDt;
	}

	public void setQlfyStartDt(Date qlfyStartDt) {
		this.qlfyStartDt = qlfyStartDt;
	}

	public long getYrVal() {
		return yrVal;
	}

	public void setYrVal(long yrVal) {
		this.yrVal = yrVal;
	}

	public String getValidFlg() {
		return validFlg;
	}

	public void setValidFlg(String validFlg) {
		this.validFlg = validFlg;
	}

	public Date getActualQlfyEndDt() {
		return actualQlfyEndDt;
	}

	public void setActualQlfyEndDt(Date actualQlfyEndDt) {
		this.actualQlfyEndDt = actualQlfyEndDt;
	}

	public Date getOrigQlfyEndDt() {
		return origQlfyEndDt;
	}

	public void setOrigQlfyEndDt(Date origQlfyEndDt) {
		this.origQlfyEndDt = origQlfyEndDt;
	}

	public long getMergeVal() {
		return mergeVal;
	}

	public void setMergeVal(long mergeVal) {
		this.mergeVal = mergeVal;
	}
	
	public boolean getCurrentYrFlg() {
		return isCurrentYrFlg;
	}

	public void setCurrentYrFlg(boolean isCurrentYrFlg) {
		this.isCurrentYrFlg = isCurrentYrFlg;
	}
}