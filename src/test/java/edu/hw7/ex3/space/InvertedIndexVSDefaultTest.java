package edu.hw7.ex3.space;

import edu.hw7.ex3.DefaultPersonDatabase;
import edu.hw7.ex3.Person;
import edu.hw7.ex3.InvertedIndexPersonDatabase;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class InvertedIndexVSDefaultTest {

    private static final List<Person> personList;

    static {
        personList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            String name = "name" + i;
            String address = "address" + i;
            String phone = "phone" + i;
            personList.add(new Person(i, name, address, phone));
        }
    }

    private static void printSpaceStats() {
        log.info("Free memory: " + Runtime.getRuntime().freeMemory());
        log.info("Total memory: " + Runtime.getRuntime().totalMemory());
    }

    @SneakyThrows
    @Test
    @DisplayName("space invertedIndex")
    void invertedIndexSpaceTest() {
        System.gc();
        InvertedIndexPersonDatabase invertedIndexPersonDatabase = new InvertedIndexPersonDatabase();
        for (var person : personList) {
            invertedIndexPersonDatabase.add(person);
        }
        log.info("inverted");
        printSpaceStats();
    }

    @SneakyThrows
    @Test
    @DisplayName("space default")
    void defaultSpaceTest() {
        System.gc();
        DefaultPersonDatabase defaultPersonDatabase = new DefaultPersonDatabase();
        for (var person : personList) {
            defaultPersonDatabase.add(person);
        }
        log.info("default");
        printSpaceStats();
    }
}
