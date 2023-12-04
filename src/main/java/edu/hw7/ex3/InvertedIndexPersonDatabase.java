package edu.hw7.ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndexPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> peopleById = new HashMap<>();

    private final Map<String, List<Integer>> peopleByName = new HashMap<>();

    private final Map<String, List<Integer>> peopleByAddress = new HashMap<>();

    private final Map<String, List<Integer>> peopleByPhone = new HashMap<>();

    @Override
    public void add(Person person) {
        if (peopleById.containsKey(person.id())) {
            return;
        }
        peopleById.put(person.id(), person);
        peopleByName.computeIfAbsent(person.name(), key -> new ArrayList<>()).add(person.id());
        peopleByAddress.computeIfAbsent(person.address(), key -> new ArrayList<>()).add(person.id());
        peopleByPhone.computeIfAbsent(person.phoneNumber(), key -> new ArrayList<>()).add(person.id());
    }

    @Override
    public void delete(int id) {
        Person person = peopleById.remove(id);
        if (person != null) {
            peopleByName.get(person.name()).remove(id);
            peopleByAddress.get(person.address()).remove(id);
            peopleByPhone.get(person.phoneNumber()).remove(id);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        return peopleByName.getOrDefault(name, List.of())
            .stream().map(peopleById::get).toList();
    }

    @Override
    public List<Person> findByAddress(String address) {
        return peopleByAddress.getOrDefault(address, List.of())
            .stream().map(peopleById::get).toList();
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return peopleByPhone.getOrDefault(phone, List.of())
            .stream().map(peopleById::get).toList();
    }

    @Override
    public void clear() {
        peopleById.clear();
        peopleByName.clear();
        peopleByAddress.clear();
        peopleByPhone.clear();
    }
}
