package main.lesson6.homework;

public abstract class Animal {
    private String name;
    private String gender;
    private String feminine = "";


    public Animal (String gender, String name) {
        this.gender = gender;
        this.name = name;
    }

    public Animal() {
        name = "Зверь";
        gender = "male";
    }

    public void run (int meters) {
        System.out.println(name + " " + "пробежал" + this.setFeminine(this.gender) + " " + meters + "м.");
    }

    public void swim (int meters) {
        System.out.println(name + " " + "проплыл" + this.setFeminine(this.gender) + " " + meters + "м.");
    }

    public String setFeminine(String gender) {
        if (gender.equals("female")) {
            feminine = "а";
            return feminine;
        }
        return feminine;
    }
}
