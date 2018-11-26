package org.dimdev.aesthetics;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = "aesthetics", name = "aesthetics", category = "")
public final class ModConfig {

    public enum ScoreBoardType {
        @LangKey("aesthetics.scoreboard.default") DEFAULT,
        @LangKey("aesthetics.scoreboard.spaced_clear") SPACED_CLEAR,
        @LangKey("aesthetics.scoreboard.edge_clear") EDGE_CLEAR
    }

    public static Changes changes = new Changes();
    public static ScoreBoard sb = new ScoreBoard();

    public static class Changes {
        @Name("scoreboardChanges")
        @LangKey("aesthetics.config.scoreboardChanges")
        @RequiresMcRestart
        public boolean scoreboardChanges = true;
    }

    public static class ScoreBoard {
        @Name("scoreboardLook")
        @LangKey("aesthetics.config.scoreboard.scoreboardLook")
        public ScoreBoardType scoreboardLook = ScoreBoardType.DEFAULT;
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals("aesthetics")) {
            ConfigManager.sync(event.getModID(), Type.INSTANCE);
        }
    }
}
