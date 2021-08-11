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
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

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

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = level.getBlockState(pos);
        // Honestly if the player is null... we have other issues lol
        ItemStack offHand = Objects.requireNonNull(player).getOffhandItem();
        VikingSawRecipe recipe = getRecipe(new ItemStack(state.getBlock().asItem()), level);

        if(offHand.getItem() instanceof ItemRodBlueprint && recipe != null) {
            ItemStack output = ItemStack.EMPTY;
            int progress = getProgress(stack, pos) + 1;

            if(progress >= recipe.cuts) {
                output = ItemUtilities.deepCopy(recipe.getResultItem());

                if(output.getItem() instanceof ItemRod) {
                    ItemRod rod = (ItemRod) output.getItem();
                    ItemRodBlueprint blueprint = (ItemRodBlueprint) offHand.getItem();

                    rod.setShape(blueprint.getShape());

                    CompoundTag base = NBTUtilities.getPersistentData(NordicArcanum.MODID, output);
                    base.putInt(NBTConstants.ROD_SHAPE_KEY, blueprint.getShape().getValue());
                    output.save(base);
                }

                resetProgress(stack);
            }
            else {
                Sounds.play(Sounds.viking_saw.get(), level, SoundSource.BLOCKS, pos, player, 1, 0.1F, 0.9F);
                stack.save(getProgressNbt(stack, progress, pos));
            }

            if(output != ItemStack.EMPTY) {
                level.destroyBlock(pos, false);
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), output));
            }

            return InteractionResult.SUCCESS;
        }
        else {
            resetProgress(stack);
        }

        return InteractionResult.PASS;
    }

    private CompoundTag getProgressNbt(ItemStack stack, int progress, BlockPos pos) {

        CompoundTag base = NBTUtilities.getPersistentData(NordicArcanum.MODID, stack);
        base.putInt(NBTConstants.LOG_PROGRESS_KEY, progress);
        base.putString(NBTConstants.BLOCK_POS, pos.toString());
        return base;
    }

    private int getProgress(ItemStack stack, BlockPos pos) {

        CompoundTag nbt = NBTUtilities.getPersistentData(NordicArcanum.MODID, stack);
        if(! nbt.contains(NBTConstants.BLOCK_POS))
            return 0;

        String nbtPos = nbt.getString(NBTConstants.BLOCK_POS);
        return nbtPos.equals(pos.toString()) ? nbt.getInt(NBTConstants.LOG_PROGRESS_KEY) : 0;
    }

    private void resetProgress(ItemStack stack) {

        stack.save(getProgressNbt(stack, 0, BLOCKPOS_MAX));
    }
}