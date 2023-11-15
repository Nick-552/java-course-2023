package edu.project3.model;

import java.util.List;
import static java.lang.Math.max;

public record ReportColumn(String columnHead, List<String> values, Integer maxLength) {
    public ReportColumn(String columnHead, List<String> values, Integer maxLength) {
        this.columnHead = columnHead;
        this.values = values;
        this.maxLength = max(
            columnHead.length(),
            values.stream().map(String::length)
                .max(Integer::compareTo).get()
        );
    }
}
