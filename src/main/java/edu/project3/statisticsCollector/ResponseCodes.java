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
        List<String> codesList = new ArrayList<>();
        List<String> amountOfRequestsList = new ArrayList<>();
        final int head = 5;
        sortedResponseCodesStatistic.reversed().stream().limit(head).forEachOrdered(stringLongEntry -> {
            codesList.add(String.valueOf(stringLongEntry.getKey()));
            amountOfRequestsList.add(String.valueOf(stringLongEntry.getValue()));
        });
        var reasonPhraseList = codesList.stream()
            .map(e -> HttpStatus
                .valueOf(Integer.parseInt(e))
                .getReasonPhrase())
            .toList();
        return new Report(
            "Коды ответов",
            List.of(
                new ReportColumn("Код ответа", codesList),
                new ReportColumn("Сообщение", reasonPhraseList),
                new ReportColumn("Количество", amountOfRequestsList)
            )
        );
    }
}
