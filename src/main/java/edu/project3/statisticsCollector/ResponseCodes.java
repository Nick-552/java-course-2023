package edu.project3.statisticsCollector;

import edu.project3.model.NginxLog;
import edu.project3.model.Report;
import edu.project3.model.ReportColumn;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

public class ResponseCodes implements StatisticsCollector {
    @Override
    public Report collect(List<NginxLog> nginxLogs) {
        var sortedResponseCodesStatistic = nginxLogs.stream()
            .collect(Collectors.groupingBy(NginxLog::status, Collectors.counting()))
            .entrySet().stream().sorted(Comparator.comparingLong(Map.Entry::getValue)).toList();
        List<String> list0 = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        final int head = 5;
        sortedResponseCodesStatistic.reversed().stream().limit(head).forEachOrdered(stringLongEntry -> {
            list0.add(String.valueOf(stringLongEntry.getKey()));
            list1.add(String.valueOf(stringLongEntry.getValue()));
        });
        var list2 = list0.stream().map(e -> HttpStatus.valueOf(Integer.parseInt(e)).getReasonPhrase()).toList();
        return new Report(
            "Коды ответов",
            List.of(
                new ReportColumn("Код ответа", list0),
                new ReportColumn("Сообщение", list2),
                new ReportColumn("Количество", list1)
            )
        );
    }
}
