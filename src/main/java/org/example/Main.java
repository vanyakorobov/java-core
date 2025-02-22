package org.example;

import org.example.service.Phone;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Phone contactManager = new Phone();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:\n" +
                    "1 - Добавить контакт\n" +
                    "2 - Удалить контакт\n" +
                    "3 - Показать все контакты\n" +
                    "4 - Показать контакты по группе\n" +
                    "5 - Поиск контакта по имени\n" +
                    "6 - Поиск контакта по номеру телефона\n" +
                    "7 - Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите имя контакта:");
                    String name = scanner.nextLine();

                    System.out.println("Введите номер телефона:");
                    String phone = scanner.nextLine();

                    System.out.println("Введите адрес электронной почты:");
                    String email = scanner.nextLine();

                    System.out.println("Введите группу:");
                    String group = scanner.nextLine();

                    contactManager.addContact(name, phone, email, group);
                    break;

                case 2:
                    System.out.println("Введите имя контакта для удаления:");
                    String nameToRemove = scanner.nextLine();
                    contactManager.removeContact(nameToRemove);
                    break;

                case 3:
                    System.out.println("\nВсе контакты:");
                    contactManager.printContacts();
                    break;

                case 4:
                    System.out.println("Введите группу для отображения контактов:");
                    String groupToShow = scanner.nextLine();
                    contactManager.printContactsByGroup(groupToShow);
                    break;

                case 5:
                    System.out.println("Введите имя для поиска:");
                    String nameToSearch = scanner.nextLine();
                    contactManager.searchContactsByName(nameToSearch);
                    break;

                case 6:
                    System.out.println("Выход из программы.");
                    return;

                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }
}