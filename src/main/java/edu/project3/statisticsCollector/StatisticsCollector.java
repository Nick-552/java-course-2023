package edu.project3.statisticsCollector;

import edu.project3.model.NginxLog;
import edu.project3.model.Report;
import java.util.List;

public interface StatisticsCollector {
    Report collect(List<NginxLog> nginxLogs);
}
