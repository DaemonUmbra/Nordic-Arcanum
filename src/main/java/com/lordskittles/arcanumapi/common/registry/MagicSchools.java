package com.lordskittles.arcanumapi.common.registry;

import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.arcanumapi.magic.schools.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ObjectHolder(ArcanumAPI.MODID)
@Mod.EventBusSubscriber(modid = ArcanumAPI.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MagicSchools
{
    private static Map<Integer, MagicSchool> idSchools = new HashMap<>();
    private static Map<ResourceLocation, MagicSchool> schools = new HashMap<>();

    public static final MagicSchool undiscovered = new MagicSchool(ArcanumAPI.MODID, SchoolType.Undiscovered);
    public static final MagicSchool ur = new MagicSchool(ArcanumAPI.MODID, SchoolType.Ur);
    public static final MagicSchool kaun = new MagicSchool(ArcanumAPI.MODID, SchoolType.Kaun);
    public static final MagicSchool ar = new MagicSchool(ArcanumAPI.MODID, SchoolType.Ar);
    public static final MagicSchool hagal = new MagicSchool(ArcanumAPI.MODID, SchoolType.Hagal);
    public static final MagicSchool yr = new MagicSchool(ArcanumAPI.MODID, SchoolType.Yr);
    public static final MagicSchool fe = new MagicSchool(ArcanumAPI.MODID, SchoolType.Fe);

    public static final void registerSchool(MagicSchool school)
    {
        if (!idSchools.containsKey(school.getSchool().id))
        {
            idSchools.put(school.getSchool().id, school);
        }

        if (!schools.containsKey(school.getRegistryName()))
        {
            schools.put(school.getRegistryName(), school);
        }
    }

    public static final MagicSchool getSchoolFor(int id)
    {
        if (idSchools.containsKey(id))
        {
            return idSchools.get(id);
        }

        return null;
    }

    public static MagicSchool getSchool(ResourceLocation location)
    {
        return schools.get(location);
    }

    public static final Collection<MagicSchool> getIdSchools()
    {
        return idSchools.values();
    }

    @SubscribeEvent
    public static void registerSchools(RegistryEvent.Register<MagicSchool> event)
    {
        for (MagicSchool school : idSchools.values())
        {
            event.getRegistry().register(school);
        }
    }
}