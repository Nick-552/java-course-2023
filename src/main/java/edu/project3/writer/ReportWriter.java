package edu.project3.writer;

import edu.project3.format.Format;
import edu.project3.model.Report;

public interface ReportWriter {

    void write(Report report, Format format);
}
