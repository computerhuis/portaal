package com.github.computerhuis.portaal.web.setting.volunteer;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.computerhuis.portaal.repository.PersonRepository;
import com.github.computerhuis.portaal.repository.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.github.computerhuis.portaal.enumeration.PersonRoleType.ROLE_VOLUNTEER;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class VolunteerListBean {

    private final PersonRepository personRepository;

    private List<Person> list;

    public void fetch() {
        list = personRepository.findByRoles(ROLE_VOLUNTEER);
    }
}
