package edu.hw8.ex2;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import static org.assertj.core.api.Assertions.assertThat;

class FixedThreadPoolTest {

    @SneakyThrows
    @Test
    void fixedThreadPoolTest() {
        ThreadPool threadPool = FixedThreadPool.create(1);
        threadPool.start();
        List<Long> expected = List.of(0L, 1L, 3L, 8L, 21L, 55L, 144L, 377L, 987L, 2584L);
        final List<Long> actual = new CopyOnWriteArrayList<>();
        for(int i = 0; i < 10; i++) {
            final int cur = i * 2;
            threadPool.execute(() -> actual.add(FibonacciUtils.compute(cur)));
        }
        threadPool.close();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
