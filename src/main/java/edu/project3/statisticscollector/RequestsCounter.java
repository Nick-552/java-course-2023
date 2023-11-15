package edu.project3.statisticscollector;

import edu.project3.model.NginxLog;
import edu.project3.model.Report;
import java.util.List;

public class RequestsCounter implements StatisticsCollector {
    @Override
    public Report collect(List<NginxLog> nginxLogs) {
        return null;
    }
}
