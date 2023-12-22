package MARMSUI.SpecialisedSqlMappingToVo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

//Added by Chandima for KFPROG-3998
/**
 * 
 * This class is used to set monthly Elite/PPS , Air/Non-Air values
 *  
 */
public class QualDetailsMonthly implements Serializable {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
	private Date month;
	private String type;
	private long airPoints; // elite miles : flight miles
	private long airSectCount;
	private long airValue; // pps value : flight PPSv
	private long nonAirPoints; // elite miles : non-flight miles
	private long nonAirSectCount;
	private long nonAirValue; // pps value : non-flight PPSv
	
	
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getAirPoints() {
		return airPoints;
	}
	public void setAirPoints(long airPoints) {
		this.airPoints = airPoints;
	}
	public long getAirSectCount() {
		return airSectCount;
	}
	public void setAirSectCount(long airSectCount) {
		this.airSectCount = airSectCount;
	}
	public long getAirValue() {
		return airValue;
	}
	public void setAirValue(long airValue) {
		this.airValue = airValue;
	}
	public long getNonAirPoints() {
		return nonAirPoints;
	}
	public void setNonAirPoints(long nonAirPoints) {
		this.nonAirPoints = nonAirPoints;
	}
	public long getNonAirSectCount() {
		return nonAirSectCount;
	}
	public void setNonAirSectCount(long nonAirSectCount) {
		this.nonAirSectCount = nonAirSectCount;
	}
	public long getNonAirValue() {
		return nonAirValue;
	}
	public void setNonAirValue(long nonAirValue) {
		this.nonAirValue = nonAirValue;
	}
	
}
