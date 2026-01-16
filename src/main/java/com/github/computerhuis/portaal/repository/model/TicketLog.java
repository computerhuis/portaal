package com.github.computerhuis.portaal.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@DynamicUpdate
@DynamicInsert
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket_log")
@IdClass(TicketLogPrimaryKey.class)
public class TicketLog {

    @Id
    private Long ticketId;
    @Id
    private LocalDateTime date;

    private Long volunteerId;
    private String log;

}
