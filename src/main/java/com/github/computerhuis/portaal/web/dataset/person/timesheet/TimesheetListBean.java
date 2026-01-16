package com.github.computerhuis.portaal.web.dataset.person.timesheet;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.computerhuis.portaal.repository.TimesheetOverviewRepository;
import com.github.computerhuis.portaal.repository.view.TimesheetOverview;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class TimesheetListBean {

    private final TimesheetOverviewRepository timesheetOverviewRepository;

    private List<TimesheetOverview> list;

    public void fetch() {
        list = timesheetOverviewRepository.findAll();
    }
}
