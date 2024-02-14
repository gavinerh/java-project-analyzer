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

import java.util.Date;

public class CustomerAircraftTypeVo {

	private String aircraftTypeCode;
    private String aircraftTypeDesc;
    private String seatPClassAlphaRange;
    private int seatPClassNoRangeStart;
    private int seatPClassNoRangeEnd;
    private String seatJClassAlphaRange;
    private int seatJClassNoRangeStart;
    private int seatJClassNoRangeEnd;
    private String seatYClassAlphaRange;
    private int seatYClassNoRangeStart;
    private int seatYClassNoRangeEnd;
    private Date lastChangeDate;
    private String seatSClassAlphaRange;
    private int seatSClassNoRangeStart;
    private int seatSClassNoRangeEnd;
    
    
	public String getAircraftTypeCode() {
		return aircraftTypeCode;
	}
	public void setAircraftTypeCode(String aircraftTypeCode) {
		this.aircraftTypeCode = aircraftTypeCode;
	}
	public String getAircraftTypeDesc() {
		return aircraftTypeDesc;
	}
	public void setAircraftTypeDesc(String aircraftTypeDesc) {
		this.aircraftTypeDesc = aircraftTypeDesc;
	}
	public String getSeatPClassAlphaRange() {
		return seatPClassAlphaRange;
	}
	public void setSeatPClassAlphaRange(String seatPClassAlphaRange) {
		this.seatPClassAlphaRange = seatPClassAlphaRange;
	}
	public int getSeatPClassNoRangeStart() {
		return seatPClassNoRangeStart;
	}
	public void setSeatPClassNoRangeStart(int seatPClassNoRangeStart) {
		this.seatPClassNoRangeStart = seatPClassNoRangeStart;
	}
	public int getSeatPClassNoRangeEnd() {
		return seatPClassNoRangeEnd;
	}
	public void setSeatPClassNoRangeEnd(int seatPClassNoRangeEnd) {
		this.seatPClassNoRangeEnd = seatPClassNoRangeEnd;
	}
	public String getSeatJClassAlphaRange() {
		return seatJClassAlphaRange;
	}
	public void setSeatJClassAlphaRange(String seatJClassAlphaRange) {
		this.seatJClassAlphaRange = seatJClassAlphaRange;
	}
	public int getSeatJClassNoRangeStart() {
		return seatJClassNoRangeStart;
	}
	public void setSeatJClassNoRangeStart(int seatJClassNoRangeStart) {
		this.seatJClassNoRangeStart = seatJClassNoRangeStart;
	}
	public int getSeatJClassNoRangeEnd() {
		return seatJClassNoRangeEnd;
	}
	public void setSeatJClassNoRangeEnd(int seatJClassNoRangeEnd) {
		this.seatJClassNoRangeEnd = seatJClassNoRangeEnd;
	}
	public String getSeatYClassAlphaRange() {
		return seatYClassAlphaRange;
	}
	public void setSeatYClassAlphaRange(String seatYClassAlphaRange) {
		this.seatYClassAlphaRange = seatYClassAlphaRange;
	}
	public int getSeatYClassNoRangeStart() {
		return seatYClassNoRangeStart;
	}
	public void setSeatYClassNoRangeStart(int seatYClassNoRangeStart) {
		this.seatYClassNoRangeStart = seatYClassNoRangeStart;
	}
	public int getSeatYClassNoRangeEnd() {
		return seatYClassNoRangeEnd;
	}
	public void setSeatYClassNoRangeEnd(int seatYClassNoRangeEnd) {
		this.seatYClassNoRangeEnd = seatYClassNoRangeEnd;
	}
	public Date getLastChangeDate() {
		return lastChangeDate;
	}
	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
	public String getSeatSClassAlphaRange() {
		return seatSClassAlphaRange;
	}
	public void setSeatSClassAlphaRange(String seatSClassAlphaRange) {
		this.seatSClassAlphaRange = seatSClassAlphaRange;
	}
	public int getSeatSClassNoRangeStart() {
		return seatSClassNoRangeStart;
	}
	public void setSeatSClassNoRangeStart(int seatSClassNoRangeStart) {
		this.seatSClassNoRangeStart = seatSClassNoRangeStart;
	}
	public int getSeatSClassNoRangeEnd() {
		return seatSClassNoRangeEnd;
	}
	public void setSeatSClassNoRangeEnd(int seatSClassNoRangeEnd) {
		this.seatSClassNoRangeEnd = seatSClassNoRangeEnd;
	}
    
    
}
