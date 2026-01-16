package com.github.computerhuis.portaal.repository;

import com.github.computerhuis.portaal.repository.view.TicketOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketOverviewRepository extends JpaRepository<TicketOverview, Long> {
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    @Query(nativeQuery = true, value = "SELECT * FROM ticket_overview WHERE status != 'CLOSED'")
    List<TicketOverview> getActiveTickets();
}
