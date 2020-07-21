package com.work.test.mvcController;

import com.work.test.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value="/mvc")
@Controller
public class MvcReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = { "/report" })
    public String mvcReportGet(Model model) {

        return "report";
    }

    @PostMapping(value = { "/report" })
    public String mvcReportTypedGet(
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        model.addAttribute("report", reportService.buildReport(type, id));
        return "report-show";
    }
}
