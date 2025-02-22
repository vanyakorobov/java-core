package org.example.service;

import org.example.model.Contact;

import java.util.*;

public class Phone {

    private List<Contact> contactList = new ArrayList<>();
    private Set<Contact> contactSet = new HashSet<>();
    private Map<String, List<Contact>> contactMap = new HashMap<>();

    public void addContact(String name, String phone, String email, String group) {
        Contact newContact = new Contact(name, phone, email, group);

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Имя не может быть пустым.");
            return;
        }

        if (phone == null || phone.trim().isEmpty() || !phone.matches("\\d+")) {
            System.out.println("Номер телефона должен быть представлен в числовом формате и не может быть пустым.");
            return;
        }

        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
            System.out.println("Некорректный адрес электронной почты.");
            return;
        }

        if (group == null || group.trim().isEmpty()) {
            System.out.println("Группа не может быть пустой.");
            return;
        }

        if (contactSet.add(newContact)) {
            contactList.add(newContact);
            contactMap.put(group, contactList);
            System.out.println("Контакт добавлен: " + newContact);
        } else {
            System.out.println("Контакт с таким именем и номером телефона уже существует: " + newContact);
        }
    }

    private boolean isValidEmail(String email) {
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');
        return atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < email.length() - 1;
    }

    public void printContacts() {
        Iterator<Contact> iterator = contactList.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            System.out.println(contact);
        }
    }

    public void printContactsByGroup(String group) {
        List<Contact> contactsInGroup = contactMap.get(group);
        if (contactsInGroup != null && !contactsInGroup.isEmpty()) {
            System.out.println("Контакты в группе '" + group + "':");
            Iterator<Contact> iterator = contactsInGroup.iterator();
            while (iterator.hasNext()) {
                Contact contact = iterator.next();
                System.out.println(contact);
            }
        } else {
            System.out.println("Группа '" + group + "' пуста или не существует.");
        }
    }

    public void removeContact(String name) {
        Contact contactToRemove = null;
        for (Contact contact : contactList) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contactToRemove = contact;
                break;
            }
        }

        if (contactToRemove != null) {
            contactList.remove(contactToRemove);
            contactSet.remove(contactToRemove);
            List<Contact> contactsInGroup = contactMap.get(contactToRemove.getGroup());
            if (contactsInGroup != null) {
                contactsInGroup.remove(contactToRemove);
                if (contactsInGroup.isEmpty()) {
                    contactMap.remove(contactToRemove.getGroup());
                }
            }
            System.out.println("Контакт удален: " + contactToRemove);
        } else {
            System.out.println("Контакт с именем '" + name + "' не найден.");
        }
    }

    public void searchContactsByName(String name) {
        List<Contact> foundContacts = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getName().equalsIgnoreCase(name)) {
                foundContacts.add(contact);
            }
        }

        if (foundContacts.isEmpty()) {
            System.out.println("Контакты с именем '" + name + "' не найдены.");
        } else {
            System.out.println("Найденные контакты с именем '" + name + "':");
            for (Contact contact : foundContacts) {
                System.out.println(contact);
            }
        }
    }
}
