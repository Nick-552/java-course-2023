package edu.project3.statisticsCollector;

import edu.project3.model.NginxLog;
import edu.project3.model.Report;
import edu.project3.model.ReportColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RequestedResources implements StatisticsCollector {
    @Override
    public Report collect(List<NginxLog> nginxLogs) {
        List<Entry<String, Long>> list = new ArrayList<>(nginxLogs.stream()
            .collect(Collectors.groupingBy(nginxLog -> nginxLog.request().resource(), Collectors.counting()))
            .entrySet()
            .stream()
            .toList()
        );
        list.sort(Entry.comparingByValue());
        List<String> resourceList = new ArrayList<>();
        List<String> amountOfRequestsList = new ArrayList<>();
        final int head = 5;
        list.reversed().stream().limit(head).forEachOrdered(stringLongEntry -> {
            resourceList.add(stringLongEntry.getKey());
            amountOfRequestsList.add(String.valueOf(stringLongEntry.getValue()));
        });
        return new Report(
            "Запрашиваемые ресурсы",
            List.of(
                new ReportColumn("Ресурс", resourceList),
                new ReportColumn("Количество", amountOfRequestsList)
            )
        );
    }
}
