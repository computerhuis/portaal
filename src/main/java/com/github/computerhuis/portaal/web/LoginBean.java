package com.github.computerhuis.portaal.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.computerhuis.portaal.enumeration.PersonRoleType;
import com.github.computerhuis.portaal.repository.PersonRepository;
import com.github.computerhuis.portaal.repository.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class LoginBean {

    private final PersonRepository personRepository;
    private List<Person> volunteers;

    private String username;
    private String password;

    public void fetch() {
        volunteers = personRepository.findByRolesAndPasshashIsNotNullOrderByFirstNameAsc(PersonRoleType.ROLE_VOLUNTEER);
    }
}
