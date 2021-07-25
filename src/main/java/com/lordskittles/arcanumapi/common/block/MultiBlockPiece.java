package com.lordskittles.arcanumapi.common.block;

import net.minecraft.util.StringRepresentable;

public enum MultiBlockPiece implements StringRepresentable {
    BOTTOM_LEFT("bot_left"),
    BOTTOM_RIGHT("bot_right"),
    TOP_LEFT("top_left"),
    TOP_RIGHT("top_right");

    private String name;

    MultiBlockPiece(String name) {

        this.name = name;
    }

    @Override
    public String getSerializedName() {

        return name;
    }
}
