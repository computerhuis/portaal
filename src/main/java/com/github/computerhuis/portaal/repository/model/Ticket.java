package com.github.computerhuis.portaal.repository.model;

import jakarta.persistence.*;
import lombok.*;
import com.github.computerhuis.portaal.enumeration.TicketType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Map;

@DynamicUpdate
@DynamicInsert
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    private Long equipmentId;
    private String subject;
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "ticket_details",
        joinColumns = @JoinColumn(name = "ticket_id")
    )
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @OrderBy("name ASC")
    private Map<String, String> details;

    private LocalDateTime registered;
    private LocalDateTime unregistered;

    public String getTicketTypeName() {
        if (ticketType != null) {
            return ticketType.getLabel();
        }
        return null;
    }
}
