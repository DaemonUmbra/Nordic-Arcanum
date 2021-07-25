package com.lordskittles.nordicarcanum.client;

import com.lordskittles.nordicarcanum.client.gui.utilities.GUIHandler;
import com.lordskittles.nordicarcanum.client.gui.utilities.GUIType;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = NordicArcanum.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class Keys {

    public static KeyMapping change_school;

    public static void registerKeys() {

        change_school = new KeyMapping(NordicNames.CHANGE_SCHOOL_KEY, GLFW.GLFW_KEY_G, NordicNames.KEY_CATEGORY);

        ClientRegistry.registerKeyBinding(change_school);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {

        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if(Minecraft.getInstance().world != null) {
            if(change_school.isKeyDown()) {
                GUIHandler.open(GUIType.ChangeSpell, player);
            }
        }
    }
}
