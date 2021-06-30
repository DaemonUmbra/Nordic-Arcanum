package com.lordskittles.arcanumapi.magic.progression;

public class InvalidPlayerProgress extends PlayerProgress
{
    @Override
    public boolean isValid()
    {
        return false;
    }
}
