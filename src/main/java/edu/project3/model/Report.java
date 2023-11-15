package edu.project3.model;

import java.util.List;

public record Report(String header, List<ReportColumn> stats, Integer maxLength) { }
