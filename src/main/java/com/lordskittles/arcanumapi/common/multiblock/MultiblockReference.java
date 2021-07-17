package com.lordskittles.arcanumapi.common.multiblock;

import net.minecraft.block.Block;

public class MultiblockReference {

    public String id;
    public Block block;

    public MultiblockReference(String id, Block block) {

        this.id = id;
        this.block = block;
    }

    public boolean isValidBlock(Block block) {

        return block == this.block;
    }
}
