package edu.project3.writer;

import edu.project3.format.Format;
import edu.project3.model.Report;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class ReportCliWriter implements ReportWriter {

    @Override
    public void write(Report report, Format format) {
        System.out.println(
            format
                .getStatisticsRenderer()
                .render(report)
        );
    }
}
