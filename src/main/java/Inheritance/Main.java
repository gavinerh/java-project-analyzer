package Inheritance;

public class Main extends RichMan{
    public static void main(String[] args){
        RichMan p = new RichMan();
        p.setName("gavin");
        p.setArms("2");
        p.setLegs("8");
        p.setNetWorth(20000.00);
        Main main = new Main();
        main.testInheritance(p);
    }

    public void testInheritance(Human human){
        setData(human);
        System.out.println(this.getName());
        System.out.println(this.getArms());
        System.out.println(this.getLegs());
        System.out.println(this.getNetWorth());
    }
}
