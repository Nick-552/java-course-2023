package edu.project3.writer;

import edu.project3.format.Format;
import edu.project3.model.Report;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportFileWriter implements ReportWriter {

    private static final Path STATISTICS_PATH = Paths.get("").resolve("logsStatistics");

    @Override
    public void write(Report report, Format format) {
        if (!STATISTICS_PATH.toFile().isDirectory()) {
            STATISTICS_PATH.toFile().mkdir();
        }
        File reportFile = new File(STATISTICS_PATH.toFile(), report.header() + format.getFileExtension());
        try (FileWriter fileWriter = new FileWriter(reportFile)) {
            String renderedReport = format.getStatisticsRenderer().render(report);
            fileWriter.write(renderedReport);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
