package org.dimdev.aesthetics;

import com.google.common.base.Predicate;
import net.minecraft.scoreboard.Score;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nullable;

@Mod(modid = "aesthetics",
        name = "Aesthetics",
        acceptableRemoteVersions = "*",
        updateJSON = ""
)
public class Aesthetics {

    static Predicate<Score> s = new Predicate<Score>()
    {
        public boolean apply(@Nullable Score p_apply_1_) {
            return p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#");
        }
    };

    public static Predicate<Score> getPredicate() {
        return s;
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        ConfigManager.sync("aesthetics", Config.Type.INSTANCE);
        MinecraftForge.EVENT_BUS.register(ModConfig.class);
    }
}
