package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.nordicarcanum.core.NordicNames;

public enum RodShape {
    None(NordicNames.NONE, - 1),
    Straight(NordicNames.STRAIGHT, 0),
    Twist(NordicNames.TWIST, 1),
    Bend(NordicNames.BEND, 2),
    Pillar(NordicNames.PILLAR, 3);

    public String title;
    private int value;

    RodShape(String title, int value) {

        this.title = title;
        this.value = value;
    }

    public int getValue() {

        return this.value;
    }

    public static RodShape get(int value) {

        switch(value) {
            case 0:
                return Straight;
            case 1:
                return Twist;
            case 2:
                return Bend;
            case 3:
                return Pillar;
            default:
                return Straight;
        }
    }
}
