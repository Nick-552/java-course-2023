package edu.project3.filter;

import edu.project3.model.NginxLog;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogsTimeFilter extends Filter<NginxLog> {

    private final OffsetDateTime fromTime;

    private final OffsetDateTime toTime;

    @Override
    protected boolean passFiltration(NginxLog log) {
        return (toTime == null || !toTime.isBefore(log.timeStamp()))
            && (fromTime == null || !fromTime.isAfter(log.timeStamp()));
    }
}
