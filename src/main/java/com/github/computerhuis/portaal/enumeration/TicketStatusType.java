package com.github.computerhuis.portaal.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TicketStatusType {
    OPEN("Open"),
    IN_PROGRESS("Wordt aan gewerkt"),
    READY("Klaar"),
    CUSTOMER_INFORMED("Klant geïnformeerd"),
    CLOSED("Gesloten");

    private final String label;
}
