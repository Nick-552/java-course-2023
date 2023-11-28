package edu.project3.statisticsCollector;

import edu.project3.model.NginxLog;
import edu.project3.model.Report;
import edu.project3.model.ReportColumn;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.AllArgsConstructor;
import static java.lang.Math.max;

@AllArgsConstructor
public class RequestsCounter implements StatisticsCollector {

    private final OffsetDateTime from;

    private final OffsetDateTime to;

    @Override
    public Report collect(List<NginxLog> nginxLogs) {
        int size = max(1, nginxLogs.size());
        long sum = 0;
        Set<String> files = new TreeSet<>();
        for (var nginxLog : nginxLogs) {
            sum += nginxLog.bodyBytesSent();
            files.add(nginxLog.file());
        }
        String averageBodyBytesSent = String.valueOf(sum / size);
        StringBuilder filesString = new StringBuilder("'");
        for (String fileName : files) {
            filesString.append(" ").append(fileName);
        }
        filesString.append(" '");
        return new Report(
            "Общая информация",
            List.of(
                new ReportColumn(
                    "Метрика",
                    List.of(
                        "Файл(-ы)",
                        "Начальная дата",
                        "Конечная дата",
                        "Количество запросов",
                        "Средний размер ответа"
                    )
                ),
                new ReportColumn(
                    "Значение",
                    List.of(
                        filesString.toString(),
                        (from == null ? "-" : from.toLocalDate().toString()),
                        (to == null ? "-" : to.toLocalDate().toString()),
                        String.valueOf(nginxLogs.size()),
                        averageBodyBytesSent
                    )
                )
            )
        );
    }
}
