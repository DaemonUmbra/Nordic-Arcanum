package com.lordskittles.nordicarcanum.core;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

public class NordicConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec SPEC;

    static {
        initializeConfig();
    }

    private static void initializeConfig() {

        initGeneral();
        initWorldGen();

        SPEC = BUILDER.build();
    }

    public static void load() {

        final CommentedFileConfig configData = CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve(NordicArcanum.MODID + "-common.toml"))
                .sync().autosave().writingMode(WritingMode.REPLACE).build();

        configData.load();
    }

    private static void startGroup(String groupName) {

        BUILDER.comment(groupName).push(groupName.toLowerCase().replace(' ', '_'));
    }

    private static void endGroup() {

        BUILDER.pop();
    }

    // region General
    public static ForgeConfigSpec.BooleanValue logPackets;

    private static void initGeneral() {

        startGroup("General");
        {
            logPackets = BUILDER.comment("Should packets be logged to the console?").define("logPackets", true);
        }
        endGroup();
    }

    // endregion
    // region World
    public static ForgeConfigSpec.BooleanValue genArcaneDust;
    public static ForgeConfigSpec.IntValue arcaneDustPerChunk;
    public static ForgeConfigSpec.BooleanValue genFeldspar;
    public static ForgeConfigSpec.IntValue feldsparPerChunk;
    public static ForgeConfigSpec.BooleanValue genCarnelian;
    public static ForgeConfigSpec.IntValue carnelianPerChunk;
    public static ForgeConfigSpec.BooleanValue genGarnet;
    public static ForgeConfigSpec.IntValue garnetPerChunk;
    public static ForgeConfigSpec.BooleanValue genThulite;
    public static ForgeConfigSpec.IntValue thulitePerChunk;

    public static ForgeConfigSpec.BooleanValue genSilver;
    public static ForgeConfigSpec.IntValue silverPerChunk;
    public static ForgeConfigSpec.BooleanValue genNickle;
    public static ForgeConfigSpec.IntValue nicklePerChunk;
    public static ForgeConfigSpec.BooleanValue genBismuth;
    public static ForgeConfigSpec.IntValue bismuthPerChunk;

    public static ForgeConfigSpec.BooleanValue genYew;
    public static ForgeConfigSpec.BooleanValue genJuniper;
    public static ForgeConfigSpec.BooleanValue genPine;

    public static ForgeConfigSpec.BooleanValue genPillars;
    public static ForgeConfigSpec.BooleanValue genShrines;
    public static ForgeConfigSpec.BooleanValue genArcanumLakes;
    public static ForgeConfigSpec.IntValue arcanumLakeChance;

    private static void initWorldGen() {

        startGroup("World Generation");
        {
            startGroup("Core");
            {
                startGroup("Arcane Dust");
                {
                    genArcaneDust = BUILDER.comment("Should Arcane Dust Ore generate in the world?").define("genArcaneDust", true);
                    arcaneDustPerChunk = BUILDER.comment("How many Arcane Dust Ore nodes should spawn each chunk? [1-20]").defineInRange("arcaneDustPerChunk", 5, 1, 20);
                }
                endGroup();
                startGroup("Feldspar");
                {
                    genFeldspar = BUILDER.comment("Should Feldspar generate in the world?").define("genFeldspar", true);
                    feldsparPerChunk = BUILDER.comment("How many Feldspar nodes should spawn each chunk? [1-20]").defineInRange("feldsparPerChunk", 1, 1, 20);
                }
                endGroup();
            }
            endGroup();

            startGroup("Gems");
            {
                startGroup("Carnelian");
                {
                    genCarnelian = BUILDER.comment("Should Carnelian Ore generate in the world?").define("genCarnelian", true);
                    carnelianPerChunk = BUILDER.comment("How many Carnelian Ore nodes should spawn each chunk? [1-20]").defineInRange("carnelianPerChunk", 1, 1, 20);
                }
                endGroup();
                startGroup("Garnet");
                {
                    genGarnet = BUILDER.comment("Should Garnet Ore generate in the world?").define("genGarnet", true);
                    garnetPerChunk = BUILDER.comment("How many Garnet Ore nodes should spawn each chunk? [1-20]").defineInRange("garnetPerChunk", 1, 1, 20);
                }
                endGroup();
                startGroup("Thulite");
                {
                    genThulite = BUILDER.comment("Should Thulite Ore generate in the world?").define("genThulite", true);
                    thulitePerChunk = BUILDER.comment("How many Thulite Ore nodes should spawn each chunk? [1-20]").defineInRange("thulitePerChunk", 1, 1, 20);
                }
                endGroup();
            }
            endGroup();

            startGroup("Metals");
            {
                startGroup("Bismuth");
                {
                    genBismuth = BUILDER.comment("Should Bismuth Ore generate in the world?").define("genBismuth", true);
                    bismuthPerChunk = BUILDER.comment("How many Bismuth Ore nodes should spawn each chunk? [1-20]").defineInRange("bismuthPerChunk", 7, 1, 20);
                }
                endGroup();
                startGroup("Silver");
                {
                    genSilver = BUILDER.comment("Should Silver Ore generate in the world?").define("genSilver", true);
                    silverPerChunk = BUILDER.comment("How many Copper Ore nodes should spawn each chunk? [1-20]").defineInRange("silverPerChunk", 4, 1, 20);
                }
                endGroup();
                startGroup("Nickle");
                {
                    genNickle = BUILDER.comment("Should Nickle Ore generate in the world?").define("genNickle", true);
                    nicklePerChunk = BUILDER.comment("How many Nickle Ore nodes should spawn each chunk? [1-20]").defineInRange("nicklePerChunk", 5, 1, 20);
                }
                endGroup();
            }
            endGroup();

            startGroup("Trees");
            {
                startGroup("Yew");
                {
                    genYew = BUILDER.comment("Should Yew trees generate in the world?").define("genYew", true);
                }
                endGroup();
                startGroup("Juniper");
                {
                    genJuniper = BUILDER.comment("Should Juniper trees generate in the world?").define("genJuniper", true);
                }
                endGroup();
                startGroup("Pine");
                {
                    genPine = BUILDER.comment("Should Pine trees generate in the world?").define("genPine", true);
                }
                endGroup();
            }
            endGroup();

            startGroup("Decorations");
            {
                genPillars = BUILDER.comment("Should Ancient Norse Pillars generate in the world?").define("genNorsePillars", true);
                genShrines = BUILDER.comment("Should Shrines generate in the world?").define("genShrines", true);
                genArcanumLakes = BUILDER.comment("Should Liquid Arcanum Lakes generate in the world?").define("genArcanumLakes", true);
                arcanumLakeChance = BUILDER.comment("What is the chance that Liquid Arcanum Lakes generate in the world?").defineInRange("arcanumLakeChance", 15, 0, 100);
            }
            endGroup();
        }
        endGroup();
    }

    // endregion
}
