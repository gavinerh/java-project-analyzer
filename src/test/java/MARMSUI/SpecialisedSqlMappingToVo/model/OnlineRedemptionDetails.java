package MARMSUI.SpecialisedSqlMappingToVo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class OnlineRedemptionDetails {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String orderNum;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long bsktTotValue;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long redeemedValue;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long bsktTotMiles;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long bsktRedeemedMiles;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String bsktPromoCd;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String bsktDelMode;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<RdpnItems> rdpnItemsList;

    public OnlineRedemptionDetails(String orderNum, long bsktTotValue, long redeemedValue, long bsktTotMiles, long bsktRedeemedMiles, String bsktPromoCd, String bsktDelMode) {
        this.orderNum = orderNum;
        this.bsktTotValue = bsktTotValue;
        this.redeemedValue = redeemedValue;
        this.bsktTotMiles = bsktTotMiles;
        this.bsktRedeemedMiles = bsktRedeemedMiles;
        this.bsktPromoCd = bsktPromoCd;
        this.bsktDelMode = bsktDelMode;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public long getBsktTotValue() {
        return bsktTotValue;
    }

    public void setBsktTotValue(long bsktTotValue) {
        this.bsktTotValue = bsktTotValue;
    }

    public long getRedeemedValue() {
        return redeemedValue;
    }

    public void setRedeemedValue(long redeemedValue) {
        this.redeemedValue = redeemedValue;
    }

    public long getBsktTotMiles() {
        return bsktTotMiles;
    }

    public void setBsktTotMiles(long bsktTotMiles) {
        this.bsktTotMiles = bsktTotMiles;
    }

    public long getBsktRedeemedMiles() {
        return bsktRedeemedMiles;
    }

    public void setBsktRedeemedMiles(long bsktRedeemedMiles) {
        this.bsktRedeemedMiles = bsktRedeemedMiles;
    }

    public String getBsktPromoCd() {
        return bsktPromoCd;
    }

    public void setBsktPromoCd(String bsktPromoCd) {
        this.bsktPromoCd = bsktPromoCd;
    }

    public String getBsktDelMode() {
        return bsktDelMode;
    }

    public void setBsktDelMode(String bsktDelMode) {
        this.bsktDelMode = bsktDelMode;
    }

    public List<RdpnItems> getRdpnItemsList() {
        return rdpnItemsList;
    }

    public void setRdpnItemsList(List<RdpnItems> rdpnItemsList) {
        this.rdpnItemsList = rdpnItemsList;
    }
}
