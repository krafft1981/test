package com.work.test.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import lombok.Data;

@Data
public class ReportType2 implements IReport {

    @Override
    public String getType() {
        return "Type 2";
    }

    public ReportType2(Integer id) {

        Random r = new Random();
        this.id = r.nextInt();
        name = r.toString();
        customerId = r.nextInt();

        System.out.println("Create object Type2 with id: " + id);
    }

    private Integer id;
    private String name;
    private Integer customerId;
    private List<Integer> books = new ArrayList<>();
    private Date startedAt;
    private Date stoppedAt;
    private boolean finished;
    private String test = "test";
}
