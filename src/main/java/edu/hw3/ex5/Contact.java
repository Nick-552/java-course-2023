package edu.hw3.ex5;

import org.jetbrains.annotations.NotNull;

public record Contact(String name, String surname) implements Comparable<Contact> {
    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public int compareTo(@NotNull Contact contact) {
        return this.surname.toLowerCase().compareTo(contact.surname.toLowerCase());
    }
}
