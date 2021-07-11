package com.lordskittles.nordicarcanum.magic.progression;

import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.lordskittles.nordicarcanum.common.events.IServerLifecycleListener;
import com.lordskittles.arcanumapi.core.ArcanumAPI;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class ProgressionIOThread extends TimerTask implements IServerLifecycleListener {

    private static ProgressionIOThread saveTask;
    private static Timer ioThread;

    private boolean inSave = false, skipTick = false;
    private Map<UUID, PlayerProgress> playerSaveQueue = Maps.newHashMap();
    private Map<UUID, PlayerProgress> awaitingSaveQueue = Maps.newHashMap();

    public static ProgressionIOThread startup() {

        if(saveTask == null) {
            saveTask = new ProgressionIOThread();
        }

        return getTask();
    }

    public static ProgressionIOThread getTask() {

        return saveTask;
    }

    @Override
    public void onServerStart() {

        if(ioThread != null) {
            return;
        }

        if(saveTask == null) {
            saveTask = new ProgressionIOThread();
        }

        ioThread = new Timer("ProgressionIOThread", true);
        ioThread.scheduleAtFixedRate(saveTask, 30_000, 30_000);
    }

    @Override
    public void onServerStop() {

        this.flushAndSaveAll();

        saveTask.cancel();
        saveTask = null;
        ioThread.cancel();
        ioThread = null;
    }

    @Override
    public void run() {

        if(skipTick)
            return;

        inSave = true;
        for(Map.Entry<UUID, PlayerProgress> entry : playerSaveQueue.entrySet()) {
            saveNow(entry.getKey(), entry.getValue());
        }
        playerSaveQueue.clear();
        inSave = false;

        playerSaveQueue.putAll(awaitingSaveQueue);
        awaitingSaveQueue.clear();
    }

    private void flushAndSaveAll() {

        skipTick = true;
        playerSaveQueue.putAll(awaitingSaveQueue);
        for(Map.Entry<UUID, PlayerProgress> entry : playerSaveQueue.entrySet()) {
            saveNow(entry.getKey(), entry.getValue());
        }
        playerSaveQueue.clear();
        awaitingSaveQueue.clear();
        skipTick = false;
        inSave = false;
    }

    private void scheduleSave(UUID uuid, PlayerProgress progress) {

        if(inSave) {
            awaitingSaveQueue.put(uuid, progress);
        }
        else {
            playerSaveQueue.put(uuid, progress);
        }
    }

    private void cancelScheduledSave(UUID playerUUID) {

        awaitingSaveQueue.remove(playerUUID);
        playerSaveQueue.remove(playerUUID);
    }

    public static void saveProgress(UUID uuid, PlayerProgress progress) {

        if(saveTask != null) {
            saveTask.scheduleSave(uuid, progress);
        }
    }

    public static void cancelSave(UUID playerUUID) {

        if(saveTask != null) {
            saveTask.cancelScheduledSave(playerUUID);
        }
    }

    static void saveNow(UUID uuid, PlayerProgress progress) {

        File playerFile = ProgressionHelper.getPlayerFile(uuid);
        try {
            Files.copy(playerFile, ProgressionHelper.getPlayerBackupFile(uuid));
        }
        catch(IOException exception) {
            NordicArcanum.LOG.warn("Failed copying progress file contents to backup file!");
            exception.printStackTrace();
        }

        try {
            CompoundNBT nbt = new CompoundNBT();
            progress.store(nbt);
            CompressedStreamTools.write(nbt, playerFile);
        }
        catch(IOException ignored) {}
    }
}
