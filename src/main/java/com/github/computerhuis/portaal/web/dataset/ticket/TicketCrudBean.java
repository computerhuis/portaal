package com.github.computerhuis.portaal.web.dataset.ticket;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import com.github.computerhuis.portaal.enumeration.TicketType;
import com.github.computerhuis.portaal.repository.EquipmentRepository;
import com.github.computerhuis.portaal.repository.TicketLogOverviewRepository;
import com.github.computerhuis.portaal.repository.TicketLogRepository;
import com.github.computerhuis.portaal.repository.TicketRepository;
import com.github.computerhuis.portaal.repository.model.Equipment;
import com.github.computerhuis.portaal.repository.model.Person;
import com.github.computerhuis.portaal.repository.model.Ticket;
import com.github.computerhuis.portaal.repository.model.TicketLog;
import com.github.computerhuis.portaal.repository.view.TicketLogOverview;
import org.primefaces.event.RowEditEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class TicketCrudBean {

    private static final List<TicketType> TICKET_TYPES = Arrays.asList(TicketType.values());

    private final TicketRepository ticketRepository;
    private final TicketLogOverviewRepository ticketLogOverviewRepository;
    private final EquipmentRepository equipmentRepository;
    private final TicketLogRepository ticketLogRepository;

    private Person owner;
    private Equipment equipment;
    private Ticket ticket;
    private Long id;
    private List<TicketLogOverview> ticketLogs;
    private String newLog;

    @PostConstruct
    public void init() {
        ticket = new Ticket();
        ticket.setDetails(new HashMap<>());
        ticketLogs = new ArrayList<>();
    }

    public List<TicketType> getTicketTypes() {
        return TICKET_TYPES;
    }

    public void fetch() {
        if (id != null) {
            ticket = ticketRepository.findById(id).orElse(new Ticket());
            ticketLogs = ticketLogOverviewRepository.findByTicketId(id);
            if (ticket.getEquipmentId() != null) {
                equipment = equipmentRepository.findById(ticket.getEquipmentId()).orElse(null);
            }
            if (ticket.getDetails() == null) {
                ticket.setDetails(new HashMap<>());
            }
        }
    }

    public String save() {
        ticketRepository.saveAndFlush(ticket);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save", "Wijzigingen opgeslagen"));
        log.info("Saved ticket {}", ticket.getId());

        val viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (viewId.contains("/ticket/crud")) {
            val params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            if (!params.containsKey("id")) {
                return String.format("/ticket/crud?faces-redirect=true&id=%d", ticket.getId());
            }
        }
        return null;
    }

    public String saveNewLog() {
        val username = Long.valueOf(getContext().getAuthentication().getName());
        val ticketLog = new TicketLog();
        ticketLog.setDate(LocalDateTime.now());
        ticketLog.setVolunteerId(username);
        ticketLog.setTicketId(ticket.getId());
        ticketLog.setLog(newLog);

        ticketLogRepository.saveAndFlush(ticketLog);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save", "Wijzigingen opgeslagen"));
        log.info("Saved ticket {}", ticket.getId());

        newLog = null;
        val viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (viewId.contains("/ticket/crud")) {
            val params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            if (!params.containsKey("id")) {
                return String.format("/ticket/crud?faces-redirect=true&id=%d", ticket.getId());
            }
        }
        return null;
    }

    public void setDeleteDetail(final String key) {
        ticket.getDetails().remove(key);
        ticketRepository.saveAndFlush(ticket);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Verwijdering uitgevoerd"));
        log.info("Deleted ticket specification {}", key);
    }

    public void onRowEdit(RowEditEvent<Map.Entry<String, String>> event) {
        Map.Entry<String, String> entry = event.getObject();
        ticket.getDetails().put(entry.getKey(), entry.getValue());
        ticketRepository.saveAndFlush(ticket);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save", "Veld %s aangepast".formatted(entry.getKey())));
        log.info("Chanced ticket specification {}", entry.getKey());
    }
}
