package com.lordskittles.nordicarcanum.magic.progression;

import com.google.common.io.Files;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fmllegacy.LogicalSidedProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProgressionHelper {

    private static PlayerProgress clientProgress = new InvalidPlayerProgress();
    private static Map<UUID, PlayerProgress> playerProgressServer = new HashMap<>();

    @Nonnull
    public static PlayerProgress getProgress(@Nullable Player player, LogicalSide side) {

        if(side.isClient()) {
            return clientProgress;
        }
        else if(player instanceof ServerPlayer) {
            return getProgressServer((ServerPlayer) player);
        }
        else {
            return new PlayerProgress();
        }
    }

    @Nonnull
    private static PlayerProgress getProgressServer(ServerPlayer player) {

        return getProgress(player.getUUID());
    }

    @Nonnull
    private static PlayerProgress getProgress(UUID uuid) {

        PlayerProgress progress = playerProgressServer.get(uuid);
        if(progress == null) {
            loadPlayerProgress(uuid);
            progress = playerProgressServer.get(uuid);
        }

        if(progress == null) {
            // THIS SHOULD NEVER HAPPEN WTF
            progress = new PlayerProgress();
        }

        return progress;
    }

    private static void loadPlayerProgress(UUID uuid) {

        File playerFile = getPlayerFile(uuid);
        try {
            loadUnsafe(uuid, playerFile);
        }
        catch(Exception e) {
            NordicArcanum.LOG.warn("Unable to load progress from default progress file. Attempting loading backup.");
            NordicArcanum.LOG.warn("Erroneous file: " + playerFile.getName());
            e.printStackTrace();

            playerFile = getPlayerBackupFile(uuid);
            try {
                loadUnsafe(uuid, playerFile);
                Files.copy(playerFile, getPlayerFile(uuid));
            }
            catch(Exception e1) {
                NordicArcanum.LOG.warn("Unable to load progress from backup progress file. Copying relevant files to error files.");
                NordicArcanum.LOG.warn("Erroneous file: " + playerFile.getName());
                e1.printStackTrace();

                File plOriginal = getPlayerFile(uuid);
                File plBackup = getPlayerBackupFile(uuid);
                try {
                    Files.copy(plOriginal, new File(plOriginal.getParent(), plOriginal.getName() + ".lerror"));
                    Files.copy(plBackup, new File(plBackup.getParent(), plBackup.getName() + ".lerror"));
                    NordicArcanum.LOG.warn("Copied progression files to error files. In case you would like to try me (HellFirePvP) to maybe see what i can do about maybe recovering the files,");
                    NordicArcanum.LOG.warn("send them over to me at the issue tracker https://github.com/HellFirePvP/AstralSorcery/issues - 90% that i won't be able to do anything, but reporting it would still be great.");
                }
                catch(IOException e2) {
                    NordicArcanum.LOG.warn("Unable to copy files to error-files.");
                    NordicArcanum.LOG.warn("I've had enough. I can't even access or open the files apparently. I'm giving up.");
                    e2.printStackTrace();
                }
                plOriginal.delete();
                plBackup.delete();

                loadUnsafeFromNBT(uuid, null);
            }
        }
    }

    private static void loadUnsafe(UUID uuid, File playerFile) throws Exception {

        CompoundTag nbt = NbtIo.read(playerFile);
        loadUnsafeFromNBT(uuid, nbt);
    }

    private static void loadUnsafeFromNBT(UUID uuid, @Nullable CompoundTag nbt) {

        PlayerProgress progress = new PlayerProgress();
        if(nbt != null && ! nbt.isEmpty()) {
            progress.load(nbt);
        }

        playerProgressServer.put(uuid, progress);
    }

    public static void savePlayerProgress(Player player) {

        if(player instanceof ServerPlayer) {
            savePlayerProgress(player.getUUID(), false);
        }
    }

    private static void savePlayerProgress(UUID uuid, boolean force) {

        if(playerProgressServer.get(uuid) == null)
            return;

        PlayerProgress progress = playerProgressServer.get(uuid);
        if(force) {
            ProgressionIOThread.saveNow(uuid, progress);
        }
        else {
            ProgressionIOThread.saveProgress(uuid, progress);
        }
    }

    public static File getPlayerFile(UUID uuid) {

        File file = new File(getPlayerDirectory(), uuid.toString() + ".arcanum");
        if(! file.exists()) {
            try {
                NbtIo.write(new CompoundTag(), file);
            }
            catch(IOException ignored) {}
        }

        return file;
    }

    public static File getPlayerBackupFile(UUID uuid) {

        File file = new File(getPlayerDirectory(), uuid.toString() + ".arcanumbackup");
        if(! file.exists()) {
            try {
                NbtIo.write(new CompoundTag(), file);
            }
            catch(IOException ignored) {} //Will be created later anyway... just as fail-safe.
        }
        return file;
    }

    private static File getPlayerDirectory() {

        MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
        if(server == null)
            return null;

        File dir = new File(server.getWorldPath(new LevelResource(NordicArcanum.MODID)).toUri());
        if(! dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
}
