package edu.hw3.ex7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

class StringNullComparatorTest {

    @Test
    @DisplayName("Basic test")
    void treeMapWithNull_shouldWork_whenAddingEntryWithNullKey() {
        TreeMap<String, String> tree = new TreeMap<>(new StringNullComparator());
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("Sorting order")
    void treeMapWithNull_shouldBeRightOrdered_whenSeveralElements() {
        TreeMap<String, String> tree = new TreeMap<>(new StringNullComparator());
        tree.put("Ab", "gsfdgs");
        tree.put("Ba", "sdfasd");
        tree.put(null, "fgsdfgs");
        tree.put("Aa", "dfadfa");
        String[] expected = new String[]{null, "Aa", "Ab", "Ba"};
        assertThat(tree.keySet().toArray()).isEqualTo(expected);
    }
}
