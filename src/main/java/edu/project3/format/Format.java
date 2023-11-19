package edu.project3.format;

import edu.project3.renderer.AdocRenderer;
import edu.project3.renderer.MarkdownRenderer;
import edu.project3.renderer.ReportRenderer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Format {
    MARKDOWN(new MarkdownRenderer(), ".md"),
    ASCII_DOC(new AdocRenderer(), ".adoc");

    private final ReportRenderer statisticsRenderer;

    private final String fileExtension;

    public static Format parseFormat(String format) {
        return switch (format) {
            case "md", "markdown", "Markdown", "MARKDOWN" -> MARKDOWN;
            case "adoc", "ADOC", "AsciiDoc", "ADoc", "Adoc" -> ASCII_DOC;
            case null -> MARKDOWN;
            default -> MARKDOWN;
        };
    }
}
