package edu.hw7.ex1;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CounterTest {

    @SneakyThrows @Test
    @DisplayName("counting in several threads")
    void counter() {
        Counter counter = new Counter();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }

        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(counter.getValue()).isEqualTo(200000);
    }
}
