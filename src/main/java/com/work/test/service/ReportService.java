package com.work.test.service;

import com.work.test.dao.AuthorRepository;
import com.work.test.dao.BookRepository;
import com.work.test.dao.CustomerRepository;
import com.work.test.dao.OrderRepository;
import com.work.test.dto.IReport;
import com.work.test.dto.ReportType1;
import com.work.test.dto.ReportType2;
import com.work.test.utils.exceptions.ReportTypeNotFoundException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public IReport buildReport(@NonNull String type, Integer id) {

        if (type.equals("type1")) {
            return new ReportType1(id);
        }

        if (type.equals("type2")) {
            return new ReportType2(id);
        }

        throw new ReportTypeNotFoundException(type);
    }
}
