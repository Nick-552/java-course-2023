package edu.project3;


import edu.project3.argument.ArgumentType;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import static edu.project3.argument.ArgumentType.getOptions;

public final class Main {

    private Main() {}

    public static void main(String[] args) {
        new NginxLogAnalyzerApp(parseArgs(args)).run();
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public static Map<ArgumentType, String> parseArgs(String[] args) {
        Options options = getOptions();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            helpFormatter.printHelp("nginx-log-analyzer", options);
            System.exit(1);
        }
        Map<ArgumentType, String> argsMap = new HashMap<>();
        for (var arg: ArgumentType.values()) {
            argsMap.put(arg, cmd.getOptionValue(arg.getOption()));
        }
        return argsMap;
    }
}
