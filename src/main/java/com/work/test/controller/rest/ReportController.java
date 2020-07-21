package com.work.test.controller.rest;

import com.work.test.dto.Report;
import com.work.test.service.ReportService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public Report doGetReportRequest(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "type", required = true) String type,
            HttpServletResponse response,
            HttpServletRequest request) {
        return reportService.buildReport(type, id);
    }
}
