package edu.hw3.ex8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class BackwardsIteratorTest {
    @Test
    @DisplayName("BackwardsIterator test")
    public void backwardIterator_shouldOutInReverseOrder() {
        Collection<String> strings = List.of("A", "B", "C");
        BackwardsIterator<String> iterator = new BackwardsIterator<>(strings);
        assertThat(iterator.next()).isEqualTo("C");
        assertThat(iterator.next()).isEqualTo("B");
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo("A");
        assertThat(iterator.hasNext()).isFalse();
    }

}
