package com.github.computerhuis.portaal.web.dataset.person.timesheet;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.computerhuis.portaal.repository.PersonRepository;
import com.github.computerhuis.portaal.repository.TimesheetOverviewRepository;
import com.github.computerhuis.portaal.repository.model.Person;
import com.github.computerhuis.portaal.repository.view.TimesheetOverview;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class TimesheetCrudBean {

    private final TimesheetOverviewRepository timesheetOverviewRepository;
    private final PersonRepository personRepository;

    private List<TimesheetOverview> list;
    private Long personId;
    private Person person;

    public void fetch() {
        person = personRepository.findById(personId).orElse(new Person());
        list = timesheetOverviewRepository.findByPersonId(personId);
    }
}
