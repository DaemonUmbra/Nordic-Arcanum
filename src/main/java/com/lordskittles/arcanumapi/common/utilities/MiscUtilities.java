package com.lordskittles.arcanumapi.common.utilities;

import com.google.common.collect.Iterables;

import java.util.Collection;
import java.util.Random;

public class MiscUtilities
{
    public static <T> T getRandomEntry(Collection<T> collection, Random rand)
    {
        if (collection == null || collection.isEmpty())
            return null;

        int index = rand.nextInt(collection.size());
        return Iterables.get(collection, index);
    }
}
