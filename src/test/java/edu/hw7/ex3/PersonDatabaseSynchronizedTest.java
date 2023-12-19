package edu.hw7.ex3;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PersonDatabaseSynchronizedTest {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    private static final PersonDatabase PERSON_DATABASE = new PersonDatabaseSynchronized();

    @BeforeEach
    void clearAndFill() {
        PERSON_DATABASE.clear();
        PERSON_DATABASE.add(new Person(0, "N", "SW", "991"));
        PERSON_DATABASE.add(new Person(1, "E", "SW", "123"));
        PERSON_DATABASE.add(new Person(2, "D", "Kah", "552"));
    }

    @Test
    void add() {
        Person person1 = new Person(3, "DS", "Tink", "777");
        Person person2 = new Person(4, "SH", "SPb", "555");
        assertThat(PERSON_DATABASE.findByPhone("777")).containsExactly();
        PERSON_DATABASE.add(person1);
        PERSON_DATABASE.add(person2);
        assertThat(PERSON_DATABASE.findByPhone("777")).containsExactly(person1);
    }

    @Test
    void delete() {
        assertThat(PERSON_DATABASE.findByAddress("SW"))
            .containsExactlyInAnyOrder(
                new Person(0, "N", "SW", "991"),
                new Person(1, "E", "SW", "123")
            );
        PERSON_DATABASE.delete(0);
        assertThat(PERSON_DATABASE.findByAddress("SW"))
            .containsExactlyInAnyOrder(
                new Person(1, "E", "SW", "123")
            );
    }


    @SneakyThrows @Test
    void findBySeveralThreads() {
        Future<List<Person>>[] futures = new Future[3];
        futures[0] = EXECUTOR_SERVICE.submit(() -> PERSON_DATABASE.findByPhone("991"));
        futures[1] = EXECUTOR_SERVICE.submit(() -> PERSON_DATABASE.findByName("N"));
        futures[2] = EXECUTOR_SERVICE.submit(() -> PERSON_DATABASE.findByAddress("SW"));
        EXECUTOR_SERVICE.shutdown();
        EXECUTOR_SERVICE.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        assertThat(futures[0].get()).containsExactly(new Person(0, "N", "SW", "991"));
        assertThat(futures[1].get()).containsExactly(new Person(0, "N", "SW", "991"));
        assertThat(futures[2].get()).containsExactly(
            new Person(0, "N", "SW", "991"),
            new Person(1, "E", "SW", "123")
        );
    }
}
