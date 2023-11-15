package edu.project3.model;

import java.util.List;
import java.util.Map;

public record LogReport(String header, Map<String, List<String>> stats) {}
