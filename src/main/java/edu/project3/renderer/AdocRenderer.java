package edu.project3.renderer;

import edu.project3.model.Report;
import static java.lang.Math.max;

public class AdocRenderer implements ReportRenderer {

    private static final String TABLE_BORDER = "|===\n";

    @Override
    public String render(Report report) {
        StringBuilder output = new StringBuilder();
        output.append("==== %s\n\n".formatted(report.header())); // Header
        output.append(TABLE_BORDER); // Table border
        for (int i = 0; i < report.stats().size(); i++) { // Column heads
            var stat = report.stats().get(i);
            output.append("| ").append(String.format("%" + stat.maxLength() + "s", stat.columnHead()));
            if (i != report.stats().size() - 1) {
                output.append(" ");
            }
        }
        output.append("\n\n");
        long lines = 0; // Max lines counter
        for (var stat : report.stats()) {
            lines = max(lines, stat.values().size());
        }
        for (int i = 0; i < lines; i++) { // Lines
            output.append("| ");
            for (int j = 0; j < report.stats().size(); j++) {
                var stat = report.stats().get(j);
                output.append(String.format("%" + stat.maxLength() + "s", stat.values().get(i)));
                if (j != report.stats().size() - 1) {
                    output.append(" | ");
                }
            }
            output.append("\n");
        }
        output.append(TABLE_BORDER); // Table border
        return output.toString();
    }
}
