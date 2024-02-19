package MARMSUI.SpecialisedSqlMappingToVo.model;

public class RdpnPayment {
    private String billtoPrt;
    private String paymntType;
    private String guestPaymnt;
    private long totPts;
    private double totAmt;
    private String currencyCd;
    private String voucherNo;
    private String mcoNo;

    public RdpnPayment(String billtoPrt, String paymntType, String guestPaymnt, long totPts, double totAmt, String currencyCd, String voucherNo, String mcoNo) {
        this.billtoPrt = billtoPrt;
        this.paymntType = paymntType;
        this.guestPaymnt = guestPaymnt;
        this.totPts = totPts;
        this.totAmt = totAmt;
        this.currencyCd = currencyCd;
        this.voucherNo = voucherNo;
        this.mcoNo = mcoNo;
    }

    public String getBilltoPrt() {
        return billtoPrt;
    }

    public void setBilltoPrt(String billtoPrt) {
        this.billtoPrt = billtoPrt;
    }

    public String getPaymntType() {
        return paymntType;
    }

    public void setPaymntType(String paymntType) {
        this.paymntType = paymntType;
    }

    public String getGuestPaymnt() {
        return guestPaymnt;
    }

    public void setGuestPaymnt(String guestPaymnt) {
        this.guestPaymnt = guestPaymnt;
    }

    public long getTotPts() {
        return totPts;
    }

    public void setTotPts(long totPts) {
        this.totPts = totPts;
    }

    public double getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(long totAmt) {
        this.totAmt = totAmt;
    }

    public String getCurrencyCd() {
        return currencyCd;
    }

    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getMcoNo() {
        return mcoNo;
    }

    public void setMcoNo(String mcoNo) {
        this.mcoNo = mcoNo;
    }
}
