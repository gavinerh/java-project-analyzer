package Inheritance;

public class RichMan extends Person{
    private double netWorth;

    public double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }

    public void init(ValueObject vo) {
        super.init(vo);
        RichMan richMan = (RichMan) vo;
        setNetWorth(richMan.getNetWorth());
    }

}
