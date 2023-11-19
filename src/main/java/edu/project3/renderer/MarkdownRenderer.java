package edu.project3.renderer;

import edu.project3.model.Report;
import static java.lang.Math.max;

public class MarkdownRenderer implements ReportRenderer {

    @Override
    public String render(Report report) {
        StringBuilder output = new StringBuilder();
        output.append("#### %s\n\n".formatted(report.header()));
        output.append("|");
        long lines = 0;
        for (var stat : report.stats()) {
            output.append(String.format(" %" + stat.maxLength() + "s |", stat.columnHead()));
            lines = max(lines, stat.values().size());
        }
        output.append("\n|");
        for (var stat : report.stats()) {
            output.append(":").append("-".repeat(stat.maxLength())).append(":|");
        }
        output.append("\n");
        for (int i = 0; i < lines; i++) {
            for (var stat : report.stats()) {
                output.append("| ").append(String.format("%" + stat.maxLength() + "s ", stat.values().get(i)));
            }
            output.append("|\n");
        }
        return output.toString();
    }
}
