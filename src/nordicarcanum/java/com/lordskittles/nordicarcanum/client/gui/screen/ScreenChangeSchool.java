package com.lordskittles.nordicarcanum.client.gui.screen;

import com.lordskittles.arcanumapi.client.gui.screen.ScreenBase;
import com.lordskittles.nordicarcanum.client.Keys;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.world.entity.player.Player;

public class ScreenChangeSchool extends ScreenBase {

    public ScreenChangeSchool(Player player) {

        super(NordicNames.CHANGE_SCHOOL_GUI, player);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {

        if(keyCode == Keys.change_school.getKey().getValue()) {
            this.onClose();

            return true;
        }

        return super.keyReleased(keyCode, scanCode, modifiers);
    }
}
