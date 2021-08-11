package com.lordskittles.nordicarcanum.data;

import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenEvents {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent _event) {

        DataGenerator generator = _event.getGenerator();

        if(_event.includeClient()) {

            generator.addProvider(new LanguageProvider(generator));
//            generator.addProvider(new BlockStateProvider(generator, _event.getExistingFileHelper()));
        }
    }
}
