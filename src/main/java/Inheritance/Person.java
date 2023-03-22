package Inheritance;

public class Person extends Human {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(ValueObject vo){
        super.init(vo);
        Person p = (Person) vo;
        setName(p.getName());
    }

}
