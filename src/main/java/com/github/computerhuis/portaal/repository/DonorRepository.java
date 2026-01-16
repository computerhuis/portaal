package com.github.computerhuis.portaal.repository;

import com.github.computerhuis.portaal.repository.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
}
