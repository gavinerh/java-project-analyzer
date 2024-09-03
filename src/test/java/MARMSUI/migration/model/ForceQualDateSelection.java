package MARMSUI.migration.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ForceQualDateSelection {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date qualStartDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date qualEndDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date extensionStartDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date reinstateStartDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Singapore")
    private Date otherDate;


    public Date getOtherDate() {
        return otherDate;
    }

    public void setOtherDate(Date otherDate) {
        this.otherDate = otherDate;
    }

    public Date getQualStartDt() {
        return qualStartDt;
    }

    public void setQualStartDt(Date qualStartDt) {
        this.qualStartDt = qualStartDt;
    }

    public Date getQualEndDt() {
        return qualEndDt;
    }

    public void setQualEndDt(Date qualEndDt) {
        this.qualEndDt = qualEndDt;
    }

    public Date getExtensionStartDt() {
        return extensionStartDt;
    }

    public void setExtensionStartDt(Date extensionStartDt) {
        this.extensionStartDt = extensionStartDt;
    }

    public Date getReinstateStartDt() {
        return reinstateStartDt;
    }

    public void setReinstateStartDt(Date reinstateStartDt) {
        this.reinstateStartDt = reinstateStartDt;
    }
}
