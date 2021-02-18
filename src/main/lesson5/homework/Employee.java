package main.lesson5.homework;

public class Employee {

    protected String name;
    protected String position;
    protected String email;
    protected String phone;
    protected double salary;
    protected int age;

    public Employee(String name, String position, String email, String phone, double salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    void printData() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", должность: " + position + ", емайл: " + email + ", телефон: " + phone + ", зарплата: " + salary + ", возраст: " + age;
    }
}
