package com.github.computerhuis.portaal.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TicketType {
    REPAIR("Reparatie"),
    ISSUE("Uitgifte");

    private final String label;
}
