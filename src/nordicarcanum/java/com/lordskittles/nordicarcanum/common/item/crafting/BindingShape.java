package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.nordicarcanum.core.NordicNames;

public enum BindingShape {
    None(NordicNames.NONE, - 1),
    Pedestal(NordicNames.PEDESTAL, 0),
    Brazier(NordicNames.BRAZIER, 1),
    Cane(NordicNames.CANE, 2),
    Cage(NordicNames.CAGE, 3);

    public String title;
    private int value;

    BindingShape(String title, int value) {

        this.title = title;
        this.value = value;
    }

    public int getValue() {

        return this.value;
    }

    public static BindingShape get(int value) {

        switch(value) {
            case 0:
                return Pedestal;
            case 1:
                return Brazier;
            case 2:
                return Cane;
            case 3:
                return Cage;
            default:
                return Pedestal;
        }
    }
}
