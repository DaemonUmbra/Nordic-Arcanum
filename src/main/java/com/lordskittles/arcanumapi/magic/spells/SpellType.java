package com.lordskittles.arcanumapi.magic.spells;

public enum SpellType
{
    Projectile("Projectile", 0),
    Touch("Touch", 1),
    Self("Self", 2),
    Summon("Summon", 3);

    private String name;
    private int id;
    private SpellType(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public int getId()
    {
        return this.id;
    }
}
