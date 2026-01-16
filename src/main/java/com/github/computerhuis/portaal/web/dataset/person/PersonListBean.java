package com.github.computerhuis.portaal.web.dataset.person;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.computerhuis.portaal.repository.PersonRepository;
import com.github.computerhuis.portaal.repository.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class PersonListBean {

    private final PersonRepository personRepository;

    private List<Person> list;

    public void fetch() {
        list = personRepository.findAll();
    }
}
