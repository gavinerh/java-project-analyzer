package exercises;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonMain {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
//        printConstructorDetail(Person.class);

        Address address = (Address) createInstanceWithArguments(Address.class, "Bukit Timah", "533855");
        Person person1 = (Person) createInstanceWithArguments(Person.class, "John", address, 20);
        System.out.println(person1);


    }

    public static Object createInstanceWithArguments(Class<?> clazz, Object ...args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for(Constructor<?> constructor : clazz.getDeclaredConstructors()){
            if(constructor.getParameterTypes().length == args.length) {
                return constructor.newInstance(args);
            }
        }
        System.out.println("An appropriate constructor was not found");
        return null;
    }

    public static void printConstructorDetail(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        System.out.println(String.format("class %s has %d declared constructors", clazz.getSimpleName(), constructors.length));

        for (int i = 0; i < constructors.length; i++) {
            Class<?>[] parameterTypes = constructors[i].getParameterTypes();
            List<String> parameterTypeNames = Arrays.stream(parameterTypes)
                    .map(type -> type.getSimpleName())
                    .collect(Collectors.toList());
            System.out.println(parameterTypeNames);

        }
    }

    public static class Person {
        private Address address;
        private String name;
        private int age;

        public Person() {
            this.name = "anonymous";
            this.age = 0;
            this.address = null;
        }

        public Person(String name) {
            this.name = name;
            this.age = 0;
            this.address = null;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            this.address = null;
        }

        public Person(String name, Address address, int age) {
            this.address = address;
            this.age = age;
            this.name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "address=" + address +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static class Address {
        private String name;
        private String postal;

        public Address(){
            this.name = "anonymous name";
            this.postal = "unknown postal";
        }

        public Address(String name, String postal) {
            this.name = name;
            this.postal = postal;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPostal() {
            return postal;
        }

        public void setPostal(String postal) {
            this.postal = postal;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "name='" + name + '\'' +
                    ", postal='" + postal + '\'' +
                    '}';
        }
    }
}
