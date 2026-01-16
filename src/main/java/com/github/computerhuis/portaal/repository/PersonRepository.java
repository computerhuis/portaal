package com.github.computerhuis.portaal.repository;

import com.github.computerhuis.portaal.enumeration.PersonRoleType;
import com.github.computerhuis.portaal.repository.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    List<Person> findByFirstNameAndLastName(String lastName, String firstName);

    List<Person> findByFirstName(String firstName);

    List<Person> findByLastName(String lastName);

    List<Person> findByRoles(PersonRoleType role);

    List<Person> findByRolesAndPasshashIsNotNullOrderByFirstNameAsc(PersonRoleType role);
}
