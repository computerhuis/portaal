package com.github.computerhuis.portaal.repository;

import com.github.computerhuis.portaal.repository.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
}
