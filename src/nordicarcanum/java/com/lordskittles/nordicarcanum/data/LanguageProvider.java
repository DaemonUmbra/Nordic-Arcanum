package com.lordskittles.nordicarcanum.data;

import com.lordskittles.nordicarcanum.client.itemgroups.NordicDecorationItemGroup;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicResourcesItemGroup;
import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.Objects;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {

    private final String[] ignoredSuffixes =
            {
                    "_block",
                    "_item"
            };

    public LanguageProvider(DataGenerator gen) {

        super(gen, NordicArcanum.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

        Blocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(_block ->
        {
            add(_block, generateName(Objects.requireNonNull(_block.getRegistryName()).getPath()));
        });

        addCreativeTab(NordicItemGroup.INSTANCE);
        addCreativeTab(NordicResourcesItemGroup.INSTANCE);
        addCreativeTab(NordicDecorationItemGroup.INSTANCE);
    }

    private String generateName(String _registryName) {

        String name = _registryName;
        for(String toReplace : ignoredSuffixes) {
            name = name.replaceAll("", toReplace);
        }

        String[] splitName = _registryName.split("_");
        for(int i = 0; i < splitName.length; i++) {

            char[] characters = splitName[i].toCharArray();
            characters[0] = Character.toUpperCase(characters[0]);
            splitName[i] = new String(characters);
        }

        return String.join(" ", splitName);
    }

    private void addCreativeTab(ICreativeTabDataProvider creativeTabProvider) {

        add("itemGroup." + creativeTabProvider.getUnlocalizedName(), creativeTabProvider.getPrettyName());
    }
}