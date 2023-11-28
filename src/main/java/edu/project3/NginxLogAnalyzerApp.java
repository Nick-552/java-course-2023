package edu.project3;

import edu.project3.argument.ArgumentType;
import edu.project3.dateTimeParser.IsoDateTimeParser;
import edu.project3.filter.Filter;
import edu.project3.filter.LogsTimeFilter;
import edu.project3.format.Format;
import edu.project3.model.NginxLog;
import edu.project3.receiver.LogsReceiver;
import edu.project3.receiver.Receivers;
import edu.project3.statisticsCollector.IpAddresses;
import edu.project3.statisticsCollector.PartOfDayStatistics;
import edu.project3.statisticsCollector.RequestedResources;
import edu.project3.statisticsCollector.RequestsCounter;
import edu.project3.statisticsCollector.ResponseCodes;
import edu.project3.statisticsCollector.StatisticsCollector;
import edu.project3.writer.ReportCliWriter;
import edu.project3.writer.ReportFileWriter;
import edu.project3.writer.ReportWriter;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static edu.project3.argument.ArgumentType.FORMAT;
import static edu.project3.argument.ArgumentType.FROM;
import static edu.project3.argument.ArgumentType.PATH;
import static edu.project3.argument.ArgumentType.TO;

public class NginxLogAnalyzerApp {

    private static final List<ReportWriter> REPORT_WRITERS = List.of(
        new ReportCliWriter(),
        new ReportFileWriter()
    );

    private final List<Filter<NginxLog>> nginxLogsFilters = new ArrayList<>(){};

    private final List<StatisticsCollector> statisticsCollectors = new ArrayList<>();

    private final Map<ArgumentType, String> argsMap;

    public NginxLogAnalyzerApp(Map<ArgumentType, String> argsMap) {
        this.argsMap = argsMap;
        OffsetDateTime from = IsoDateTimeParser.parse(argsMap.get(FROM));
        OffsetDateTime to = IsoDateTimeParser.parse(argsMap.get(TO));
        nginxLogsFilters.add(new LogsTimeFilter(from, to));
        statisticsCollectors.add(new RequestsCounter(from, to));
        statisticsCollectors.add(new RequestedResources());
        statisticsCollectors.add(new ResponseCodes());
        statisticsCollectors.add(new IpAddresses());
        statisticsCollectors.add(new PartOfDayStatistics());
    }

    public void run() {
        LogsReceiver logsReceiver = Receivers.getLogReceiver(argsMap.get(PATH)); // choose receiver

        List<String> logStrings = logsReceiver.receiveLogs(); // receiving logs

        // parse logs strings into logs
        List<NginxLog> logs = logStrings.stream().map(NginxLog::fromString).filter(Objects::nonNull).toList();

        // filter logs
        for (var filter : nginxLogsFilters) {
            logs = filter.filter(logs);
        }

        List<NginxLog> finalLogs = logs;
        var reports = statisticsCollectors.stream()
            .map(statisticsCollector -> statisticsCollector.collect(finalLogs))
            .toList();

        for (var reportWriter : REPORT_WRITERS) {
            for (var report : reports) {
                reportWriter.write(report, Format.parseFormat(argsMap.get(FORMAT)));
            }
        }
    }
}
