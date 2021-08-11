package com.lordskittles.nordicarcanum.data;

import com.lordskittles.nordicarcanum.common.registry.Blocks;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.Objects;

public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(DataGenerator _gen, ExistingFileHelper _exFileHelper) {

        super(_gen, NordicArcanum.MODID, _exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        Blocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(_block ->
        {
            if(_block instanceof IBlockDataContainer) {

                IBlockDataContainer dataContainer = (IBlockDataContainer) _block;
                dataContainer.generateData(this);
            }
            else {

                this.simpleBlock(_block);
            }

            this.simpleBlockItem(_block,
                    new ModelFile.ExistingModelFile(
                            new ResourceLocation(NordicArcanum.MODID, "block/" + Objects.requireNonNull(_block.getRegistryName()).getPath()),
                            itemModels().existingFileHelper));
        });
    }
}