package com.work.test.dto;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class Report {
    private String type;
    public Report(@NonNull String type) {
        this.type = type;
    }
}
