package com.lordskittles.nordicarcanum.common.item.crafting;

import com.lordskittles.arcanumapi.common.item.ItemMod;
import com.lordskittles.arcanumapi.common.utilities.ItemUtilities;
import com.lordskittles.arcanumapi.common.utilities.NBTUtilities;
import com.lordskittles.arcanumapi.core.NBTConstants;
import com.lordskittles.nordicarcanum.client.itemgroups.NordicItemGroup;
import com.lordskittles.nordicarcanum.common.inventory.crafting.VikingSawRecipe;
import com.lordskittles.nordicarcanum.common.item.magic.ItemRod;
import com.lordskittles.nordicarcanum.common.registry.RecipeType;
import com.lordskittles.nordicarcanum.common.registry.Sounds;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.SoundCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class ItemVikingSaw extends ItemMod {

    private static final BlockPos BLOCKPOS_MAX = new BlockPos(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

    public ItemVikingSaw() {

        super(NordicItemGroup.INSTANCE);
    }

    private VikingSawRecipe getRecipe(ItemStack input, Level world) {

        return RecipeType.viking_saw.findFirst(world, recipe -> recipe.matches(input));
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {

        Level world = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);
        // Honestly if the player is null... we have other issues lol
        ItemStack offHand = Objects.requireNonNull(player).getHeldItemOffhand();
        VikingSawRecipe recipe = getRecipe(new ItemStack(state.getBlock().asItem()), world);

        if(offHand.getItem() instanceof ItemRodBlueprint && recipe != null) {
            ItemStack output = ItemStack.EMPTY;
            int progress = getProgress(stack, pos) + 1;

            if(progress >= recipe.cuts) {
                output = ItemUtilities.deepCopy(recipe.getRecipeOutput());

                if(output.getItem() instanceof ItemRod) {
                    ItemRod rod = (ItemRod) output.getItem();
                    ItemRodBlueprint blueprint = (ItemRodBlueprint) offHand.getItem();

                    rod.setShape(blueprint.getShape());

                    CompoundNBT base = NBTUtilities.getPersistentData(NordicArcanum.MODID, output);
                    base.putInt(NBTConstants.ROD_SHAPE_KEY, blueprint.getShape().getValue());
                    output.write(base);
                }

                resetProgress(stack);
            }
            else {
                Sounds.play(Sounds.viking_saw.get(), world, SoundCategory.BLOCKS, pos, player, 1, 0.1F, 0.9F);
                stack.write(getProgressNbt(stack, progress, pos));
            }

            if(output != ItemStack.EMPTY) {
                world.destroyBlock(pos, false);
                world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), output));
            }

            return ActionResultType.SUCCESS;
        }
        else {
            resetProgress(stack);
        }

        return ActionResultType.PASS;
    }

    private CompoundNBT getProgressNbt(ItemStack stack, int progress, BlockPos pos) {

        CompoundNBT base = NBTUtilities.getPersistentData(NordicArcanum.MODID, stack);
        base.putInt(NBTConstants.LOG_PROGRESS_KEY, progress);
        base.putString(NBTConstants.BLOCK_POS, pos.toString());
        return base;
    }

    private int getProgress(ItemStack stack, BlockPos pos) {

        CompoundNBT nbt = NBTUtilities.getPersistentData(NordicArcanum.MODID, stack);
        if(! nbt.contains(NBTConstants.BLOCK_POS))
            return 0;

        String nbtPos = nbt.getString(NBTConstants.BLOCK_POS);
        return nbtPos.equals(pos.toString()) ? nbt.getInt(NBTConstants.LOG_PROGRESS_KEY) : 0;
    }

    private void resetProgress(ItemStack stack) {

        stack.write(getProgressNbt(stack, 0, BLOCKPOS_MAX));
    }
}