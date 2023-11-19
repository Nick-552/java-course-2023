package edu.hw6.ex5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class HackerNewsTest {

    @Test
    void hackerNewsTopStories() {
        assertThat(HackerNews.hackerNewsTopStories()).isNotEmpty();
    }

    @Test
    void news() {
        assertThat(HackerNews.news(37570037)).isEqualTo("JDK 21 Release Notes");
    }
}
