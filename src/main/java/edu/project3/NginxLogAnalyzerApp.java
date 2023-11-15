package edu.project3;

import edu.project3.datetimeparser.IsoDateTimeParser;
import edu.project3.filter.Filter;
import edu.project3.filter.LogsTimeFilter;
import edu.project3.model.ArgumentType;
import edu.project3.model.NginxLog;
import edu.project3.receiver.LocalLogsReceiver;
import edu.project3.receiver.LogsReceiver;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static edu.project3.model.ArgumentType.FROM;
import static edu.project3.model.ArgumentType.PATH;
import static edu.project3.model.ArgumentType.TO;


public class NginxLogAnalyzerApp {

    private final List<Filter<NginxLog>> nginxLogsFilters = new ArrayList<>();

    private final Map<ArgumentType, String> argsMap;

    public NginxLogAnalyzerApp(Map<ArgumentType, String> argsMap) {
        this.argsMap = argsMap;
        OffsetDateTime from = IsoDateTimeParser.parse(argsMap.get(FROM));
        OffsetDateTime to = IsoDateTimeParser.parse(argsMap.get(TO));
        nginxLogsFilters.add(new LogsTimeFilter(from, to));
        for (var arg : argsMap.entrySet()) {
            System.out.println(arg);
        }
    }

    public void run() {
        LogsReceiver logsReceiver = new LocalLogsReceiver(); // choose receiver TODO add several receivers

        List<String> logStrings = logsReceiver.receiveLogs(argsMap.get(PATH)); // receiving logs
        System.out.println(logStrings);

        // parse logs strings into logs
        List<NginxLog> logs = logStrings.stream().map(NginxLog::parseStringToLog).toList();

        // filter logs
        for (var filter : nginxLogsFilters) {
            logs = filter.filter(logs);
        }
    }
}
