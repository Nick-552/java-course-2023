package edu.project3.model;

import lombok.Builder;

@Builder
public record Request(String type, String resource, String protocol) { }
