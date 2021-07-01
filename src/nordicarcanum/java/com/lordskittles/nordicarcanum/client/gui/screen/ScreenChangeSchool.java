package com.lordskittles.nordicarcanum.client.gui.screen;

import com.lordskittles.arcanumapi.client.gui.screen.ScreenBase;
import com.lordskittles.nordicarcanum.client.Keys;
import com.lordskittles.nordicarcanum.core.NordicNames;
import net.minecraft.entity.player.PlayerEntity;

public class ScreenChangeSchool extends ScreenBase {

    public ScreenChangeSchool(PlayerEntity player) {

        super(NordicNames.CHANGE_SCHOOL_GUI, player);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {

        if(keyCode == Keys.change_school.getKey().getKeyCode()) {
            this.closeScreen();

            return true;
        }

        return super.keyReleased(keyCode, scanCode, modifiers);
    }
}
