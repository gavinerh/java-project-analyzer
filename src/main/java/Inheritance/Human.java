package Inheritance;

public class Human extends ValueObject {
    private String legs;
    private String arms;

    public String getLegs() {
        return legs;
    }

    public void setLegs(String legs) {
        this.legs = legs;
    }

    public String getArms() {
        return arms;
    }

    public void setArms(String arms) {
        this.arms = arms;
    }

    protected void init(ValueObject obj){
        Human human = (Human) obj;
        setArms(human.getArms());
        setLegs(human.getLegs());
    }

    public void setData(ValueObject vo){
        init(vo);
    }
}
