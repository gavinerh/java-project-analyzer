package MARMSUI.SpecialisedSqlMappingToVo;

import java.io.Serializable;
import java.util.Date;


/**
 * The VO Object to represent the data equivalent of 1 row of
 * information from CUS_TIER_QUAL table.
 */
public class TierQual implements Serializable
{
	private String intID;
	private String tierStatus;
	private String tierType;
	private String mbrSince;
	private int yrsQlfd;
	private Date firstQlfyingDate;
	private Date currQlfyingDate;
	private Date tierExpDate;
	private String prevTierStatus;
	private String prevMbrSince;
	private Date prevFailDate;
	private String highestTierStatus;
	private Date rcreDate;

	public void setIntID(String intID)
	{
		this.intID = intID;
	}
	public void setTierStatus(String tierStatus)
	{
		this.tierStatus = tierStatus;
	}
	public void setMbrSince(String mbrSince)
	{
		this.mbrSince = mbrSince;
	}
	public void setYrsQlfd(int yrsQlfd)
	{
		this.yrsQlfd = yrsQlfd;
	}
	public void setFirstQlfyingDate(Date firstQlfyingDate)
	{
		this.firstQlfyingDate = firstQlfyingDate;
	}
	public void setCurrQlfyingDate(Date currQlfyingDate)
	{
		this.currQlfyingDate = currQlfyingDate;
	}
	public void setTierExpDate(Date tierExpDate)
	{
		this.tierExpDate = tierExpDate;
	}
	public void setPrevTierStatus(String prevTierStatus)
	{
		this.prevTierStatus = prevTierStatus;
	}
	public void setPrevMbrSince(String prevMbrSince)
	{
		this.prevMbrSince = prevMbrSince;
	}
	public void setPrevFailDate(Date prevFailDate)
	{
		this.prevFailDate = prevFailDate;
	}
	public void setRcreDate(Date rcreDate)
	{
		this.rcreDate = rcreDate;
	}
	public void setHighestTierStatus(String highestTierStatus)
	{
		this.highestTierStatus = highestTierStatus;
	}
	public String getIntID()
	{
		return this.intID;
	}
	public String getTierStatus()
	{
		return this.tierStatus;
	}
	public String getMbrSince()
	{
		return this.mbrSince;
	}
	public int getYrsQlfd()
	{
		return this.yrsQlfd;
	}
	public Date getFirstQlfyingDate()
	{
		return this.firstQlfyingDate;
	}
	public Date getCurrQlfyingDate()
	{
		return this.currQlfyingDate;
	}
	public Date getTierExpDate()
	{
		return this.tierExpDate;
	}
	public String getPrevTierStatus()
	{
		return this.prevTierStatus;
	}
	public String getPrevMbrSince()
	{
		return this.prevMbrSince;
	}
	public Date getPrevFailDate()
	{
		return this.prevFailDate;
	}
	public Date getRcreDate()
	{
		return this.rcreDate;
	}
	public String getHighestTierStatus()
	{
		return this.highestTierStatus;
	}
	public String getTierType() {
		return tierType;
	}
	public void setTierType(String tierType) {
		this.tierType = tierType;
	}
	public TierQual()
	{

	}

}