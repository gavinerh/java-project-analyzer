package MARMSUI.SpecialisedSqlMappingToVo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class TravelDetails {
    private String carrierCd;
    private long fltNo;
    private String subClass;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date fltDepDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date fltArrDt;
    private String destinationCd;
    private String originCd;
    private String transitInd;
    private String itinXrefId;

    public TravelDetails() {
        // empty constructor
    }

    public TravelDetails(String carrierCd, long fltNo, String subClass, Date fltDepDt, Date fltArrDt, String destinationCd, String originCd, String transitInd, String itinXrefId) {
        this.carrierCd = carrierCd;
        this.fltNo = fltNo;
        this.subClass = subClass;
        this.fltDepDt = fltDepDt;
        this.fltArrDt = fltArrDt;
        this.destinationCd = destinationCd;
        this.originCd = originCd;
        this.transitInd = transitInd;
        this.itinXrefId = itinXrefId;
    }
    @JsonIgnore
    public boolean isTravelDetailsIrrelevant() {
        return carrierCd == null && fltNo == 0 && subClass == null && fltDepDt == null && fltArrDt == null && destinationCd == null && originCd == null;
    }

    public String getCarrierCd() {
        return carrierCd;
    }

    public void setCarrierCd(String carrierCd) {
        this.carrierCd = carrierCd;
    }

    public long getFltNo() {
        return fltNo;
    }

    public void setFltNo(long fltNo) {
        this.fltNo = fltNo;
    }

    public String getSubClass() {
        return subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public Date getFltDepDt() {
        return fltDepDt;
    }

    public void setFltDepDt(Date fltDepDt) {
        this.fltDepDt = fltDepDt;
    }

    public Date getFltArrDt() {
        return fltArrDt;
    }

    public void setFltArrDt(Date fltArrDt) {
        this.fltArrDt = fltArrDt;
    }

    public String getDestinationCd() {
        return destinationCd;
    }

    public void setDestinationCd(String destinationCd) {
        this.destinationCd = destinationCd;
    }

    public String getOriginCd() {
        return originCd;
    }

    public void setOriginCd(String originCd) {
        this.originCd = originCd;
    }

    public String getTransitInd() {
        return transitInd;
    }

    public void setTransitInd(String transitInd) {
        this.transitInd = transitInd;
    }

    public String getItinXrefId() {
        return itinXrefId;
    }

    public void setItinXrefId(String itinXrefId) {
        this.itinXrefId = itinXrefId;
    }

    public String generateUniqueString() {
        return String.format("%s%d%s%s%s%s%s%s", carrierCd,fltNo,subClass,fltDepDt == null ? null : fltDepDt.toString(),
                fltArrDt == null ? null : fltArrDt.toString(), destinationCd,originCd,itinXrefId, transitInd);
    }
}
