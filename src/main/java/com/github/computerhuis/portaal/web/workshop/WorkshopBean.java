package com.github.computerhuis.portaal.web.workshop;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.computerhuis.portaal.repository.TicketOverviewRepository;
import com.github.computerhuis.portaal.repository.view.TicketOverview;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class WorkshopBean {

    private final TicketOverviewRepository ticketOverviewRepository;

    private List<TicketOverview> list;

    public void fetch() {
        list = ticketOverviewRepository.getActiveTickets();
    }
}
