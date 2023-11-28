package edu.project3.model;

import java.util.List;
import java.util.Objects;
import static java.lang.Math.max;

public record ReportColumn(String columnHead, List<String> values, Integer maxLength) {
    public ReportColumn(String columnHead, List<String> values) {
        this(
            columnHead,
            values,
            max(
            columnHead.length(),
            values.stream().filter(Objects::nonNull).map(String::length)
                .max(Integer::compareTo).orElse(0)
        ));
    }
}
