package edu.hw7.ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> peopleById = new HashMap<>();

    private final Map<String, List<Person>> peopleByName = new HashMap<>();

    private final Map<String, List<Person>> peopleByAddress = new HashMap<>();

    private final Map<String, List<Person>> peopleByPhone = new HashMap<>();

    @Override
    public void add(Person person) {
        if (peopleById.containsKey(person.id())) {
            return;
        }
        peopleById.put(person.id(), person);
        peopleByName.computeIfAbsent(person.name(), key -> new ArrayList<>()).add(person);
        peopleByAddress.computeIfAbsent(person.address(), key -> new ArrayList<>()).add(person);
        peopleByPhone.computeIfAbsent(person.phoneNumber(), key -> new ArrayList<>()).add(person);
    }

    @Override
    public void delete(int id) {
        Person person = peopleById.remove(id);
        if (person != null) {
            peopleByName.get(person.name()).remove(person);
            peopleByAddress.get(person.address()).remove(person);
            peopleByPhone.get(person.phoneNumber()).remove(person);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        return peopleByName.getOrDefault(name, List.of());
    }

    @Override
    public List<Person> findByAddress(String address) {
        return peopleByAddress.getOrDefault(address, List.of());
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return peopleByPhone.getOrDefault(phone, List.of());
    }

    @Override
    public void clear() {
        peopleById.clear();
        peopleByName.clear();
        peopleByAddress.clear();
        peopleByPhone.clear();
    }
}
