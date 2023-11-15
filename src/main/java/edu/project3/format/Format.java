package edu.project3.format;

import edu.project3.renderer.AdocRenderer;
import edu.project3.renderer.MarkdownRenderer;
import edu.project3.renderer.ReportRenderer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Format {
    MARKDOWN(new MarkdownRenderer()),
    ADOC(new AdocRenderer());

    private final ReportRenderer statisticsRenderer;

    public static Format parseFormat(String format) {
        return switch (format) {
            case "md", "markdown", "Markdown", "MARKDOWN" -> MARKDOWN;
            case "ADOC", "AsciiDoc", "adoc", "ADoc", "Adoc" -> ADOC;
            default -> MARKDOWN;
        };
    }
}
