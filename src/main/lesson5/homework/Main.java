package main.lesson5.homework;

public class Main {

    public static void main(String[] args) {
        Employee[] persArray = new Employee[5];
        persArray[0] = new Employee("Николай", "программист", "nicola@mail.ru", "89211111111",32500);
        persArray[1] = new Employee("Иван", "тимлид", "ivashka@mail.ru", "89212223344",120000);
        persArray[2] = new Employee("Карина", "HR", "kara@mail.ru", "89219876543",20345);
        persArray[3] = new Employee("Равшан", "директор", "ravshanivanovich@mail.ru", "89210001111",545000);
        persArray[4] = new Employee("Оля", "тестировщик", "oleole@mail.ru", "89211111123",15000);

        for (int i = 0; i < persArray.length; i++) {
            if (persArray[i].getAge() > 40) {
                persArray[i].printData();
            }
        }
    }
}
