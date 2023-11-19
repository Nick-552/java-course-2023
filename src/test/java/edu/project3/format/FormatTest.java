package edu.project3.format;

import edu.project3.renderer.MarkdownRenderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FormatTest {

    @ParameterizedTest
    @ValueSource(strings = {"md", "markdown", "Markdown", "MARKDOWN"})
    @DisplayName("parse markdown")
    void parseFormat_shouldReturnMarkdown_whenMarkdownStringProvided(String string) {
        Format format = Format.parseFormat(string);
        assertThat(format).isEqualTo(Format.MARKDOWN);
        assertThat(format.getFileExtension()).isEqualTo(".md");
        assertThat(format.getStatisticsRenderer()).isInstanceOf(MarkdownRenderer.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"adoc", "ADOC", "AsciiDoc", "ADoc", "Adoc"})
    @DisplayName("parse adoc")
    void parseFormat_shouldReturnAsciiDoc_whenAsciiDocStringProvided(String string) {
        assertThat(Format.parseFormat(string)).isEqualTo(Format.ASCII_DOC);
    }

    @ParameterizedTest
    @ValueSource(strings = {"gasfgsfd", "4523452", "adc", ""})
    @DisplayName("parse strange")
    void parseFormat_shouldReturnMarkdown_whenStrangeStringProvided(String string) {
        assertThat(Format.parseFormat(string)).isEqualTo(Format.MARKDOWN);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("parse strange")
    void parseFormat_shouldReturnMarkdown_whenNullStringProvided(String string) {
        assertThat(Format.parseFormat(string)).isEqualTo(Format.MARKDOWN);
    }
}
