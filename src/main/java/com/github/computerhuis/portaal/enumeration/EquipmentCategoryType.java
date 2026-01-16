package com.github.computerhuis.portaal.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EquipmentCategoryType {
    DESKTOP("Desktop"),
    LAPTOP("Laptop"),
    TABLET("Tablet"),
    MOBILE("Mobiel"),
    SIM("Simkaart"),
    USB_STICK("USB Stick");

    private final String label;
}
