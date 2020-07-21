package com.work.test.service;

import com.work.test.dto.Report;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    public Report buildReport(@NonNull String type, Integer id) {

        Report report = new Report(type);
        if (type == "") {

            return report;
        }

        if (type == "") {

            return report;
        }

        return null;
    }
}
