package com.work.test.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ReportType1 implements IReport {

    @Override
    public String getType() {
        return "Type 1";
    }

    public ReportType1(Integer id) {

        System.out.println("Create object Type1 with id: " + id);
    }

    private List<String> bucet = Arrays.asList("one", "two", "three");
    private Integer id;
    private String name;
    private Integer publishingYear;
    private String annotation;
    private List<Integer> authors;
    private String fio;
    private Integer birthYear;
    private List<Integer> books;
    private Integer customerId;
    private Date startedAt;
    private Date stoppedAt;
    private boolean finished;
    private String phone;
}
