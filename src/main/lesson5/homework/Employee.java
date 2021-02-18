package main.lesson5.homework;

import java.util.Random;

public class Employee {
    static final Random random = new Random();
    private String name;
    private String position;
    private String email;
    private String phone;
    private double salary;
    private int age;

    public Employee(String name, String position, String email, String phone, double salary) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        age = random.nextInt(65);
    }

    public void printData() {
        System.out.println(this);
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", должность: " + position + ", емайл: " + email + ", телефон: " + phone + ", зарплата: " + salary + ", возраст: " + age;
    }
}
