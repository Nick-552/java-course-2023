package edu.project3.statisticsCollector;

import edu.project3.model.NginxLog;
import edu.project3.model.Report;
import edu.project3.model.ReportColumn;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

public class PartOfDayStatistics implements StatisticsCollector {

    @Override
    public Report collect(List<NginxLog> nginxLogs) {
        var map = nginxLogs.stream()
            .collect(
                Collectors.groupingBy(
                    nginxLog -> PartOfDay.getPartOfDay(nginxLog.timeStamp().toOffsetTime()),
                    Collectors.counting()
                )
            );

        return new Report(
            "Количество запросов по части дня",
            List.of(
                new ReportColumn(
                    "Часть дня", map.keySet().stream().map(Enum::toString).toList()
                ),
                new ReportColumn(
                    "Количество", map.values().stream().map(Objects::toString).toList()
                )
            )
        );

    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public enum PartOfDay {
        NIGHT(
            OffsetTime.of(0, 0, 0, 0, ZoneOffset.UTC),
            OffsetTime.of(5, 59, 59, 999999999, ZoneOffset.UTC)
        ),
        MORNING(
            OffsetTime.of(6, 0, 0, 0, ZoneOffset.UTC),
            OffsetTime.of(11, 59, 59, 999999999, ZoneOffset.UTC)
        ),
        DAY(
            OffsetTime.of(12, 0, 0, 0, ZoneOffset.UTC),
            OffsetTime.of(17, 59, 59, 999999999, ZoneOffset.UTC)
        ),
        EVENING(
            OffsetTime.of(18, 0, 0, 0, ZoneOffset.UTC),
            OffsetTime.of(23, 59, 59, 999999999, ZoneOffset.UTC)
        ),
        ERROR(OffsetTime.MIN, OffsetTime.MAX);

        private final OffsetTime from;

        private final OffsetTime to;

        public static PartOfDay getPartOfDay(OffsetTime offsetTime) {
            for (PartOfDay partOfDay : PartOfDay.values()) {
                if (!offsetTime.isBefore(partOfDay.from)
                    && !offsetTime.isAfter(partOfDay.to)) {
                    return partOfDay;
                }
            }
            return ERROR;
        }
    }
}




