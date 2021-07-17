package com.lordskittles.arcanumapi.common.multiblock;

public interface ITieredMultiblock<T extends Enum> extends IMultiblock {

    T[] getTierValues();

}
