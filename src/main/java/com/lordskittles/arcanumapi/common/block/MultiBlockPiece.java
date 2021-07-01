package com.lordskittles.arcanumapi.common.block;

import net.minecraft.util.IStringSerializable;

public enum MultiBlockPiece implements IStringSerializable {
    BOTTOM_LEFT("bot_left"),
    BOTTOM_RIGHT("bot_right"),
    TOP_LEFT("top_left"),
    TOP_RIGHT("top_right");

    private String name;

    MultiBlockPiece(String name) {

        this.name = name;
    }

    @Override
    public String getString() {

        return name;
    }
}
