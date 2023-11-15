package edu.project3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public enum ArgumentType {
    PATH("p", "path", true, "path of NGINX log file(s) in the form of a local template or URL"),
    FROM("f", "from", false, "time parameter from in ISO8601"),
    TO("t", "to", false, "time parameter to in ISO8601"),
    FORMAT("F", "format", false, "output format of the result: markdown/adoc");

    private final String option;

    private final String longOption;

    private final boolean required;

    private final String description;

    @NotNull public static Options getOptions() {
        Options options = new Options();
        for (var v : ArgumentType.values()) {
            var option = new Option(v.getOption(), v.getLongOption(), true, v.getDescription());
            option.setRequired(v.isRequired());
            options.addOption(option);
        }
        return options;
    }
}
