package Fields;

public class Person {
    private final String name;
    private final boolean isEmployed;
    private final int age;
    private final float salary;

    private Address address;

    public Person(String name, boolean isEmployed, int age, float salary, Address address) {
        this.name = name;
        this.isEmployed = isEmployed;
        this.age = age;
        this.salary = salary;
        this.address = address;
    }
}
