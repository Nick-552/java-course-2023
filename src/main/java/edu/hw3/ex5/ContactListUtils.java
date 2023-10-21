package edu.hw3.ex5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class ContactListUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String NAME_AND_SURNAME_REGEX = "^[A-Z][a-z]*$";

    private ContactListUtils() {}

    public static List<Contact> parseContacts(String[] contacts, Order order) {
        if (contacts == null) {
            return new ArrayList<>();
        }
        List<Contact> contactList = new ArrayList<>();
        for (String contactString : contacts) {
            contactList.add(parseContact(contactString));
        }
        sort(contactList, order);
        return contactList;
    }


    private static void sort(List<Contact> contactList, Order order) {
        if (order == Order.ASC) {
            contactList.sort(Comparator.naturalOrder());
        } else {
            contactList.sort(Comparator.reverseOrder());
        }
    }

    private static Contact parseContact(@NotNull String contactString) {
        String[] contactArr = contactString.split(" ");
        if (contactArr.length != 2) {
            throw new IllegalArgumentException("Неверное количество слов для контакта");
        }
        String name = contactArr[0];
        String surname = contactArr[1];
        if (!name.matches(NAME_AND_SURNAME_REGEX) || !surname.matches(NAME_AND_SURNAME_REGEX)) {
            LOGGER.warn("Возможно имя или фамилия введены неправильно");
        }
        return new Contact(name, surname);
    }
}
