package edu.project3.statisticsCollector;

import edu.project3.model.NginxLog;
import edu.project3.model.Report;
import edu.project3.model.ReportColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IpAddresses implements StatisticsCollector {
    @Override
    public Report collect(List<NginxLog> nginxLogs) {
        List<Map.Entry<String, Long>> list = new ArrayList<>(nginxLogs.stream()
            .collect(Collectors.groupingBy(NginxLog::ipAddress, Collectors.counting()))
            .entrySet()
            .stream()
            .toList()
        );
        list.sort(Map.Entry.comparingByValue());
        List<String> list0 = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        final int head = 5;
        list.reversed().stream().limit(head).forEachOrdered(stringLongEntry -> {
            list0.add(stringLongEntry.getKey());
            list1.add(String.valueOf(stringLongEntry.getValue()));
        });
        return new Report(
            "Наиболее частые IP адреса",
            List.of(
                new ReportColumn("IP адрес", list0),
                new ReportColumn("Количество", list1)
            )
        );
    }
}
