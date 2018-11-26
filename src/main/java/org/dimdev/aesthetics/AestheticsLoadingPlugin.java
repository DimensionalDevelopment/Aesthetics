package org.dimdev.aesthetics;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class AestheticsLoadingPlugin implements IFMLLoadingPlugin {
    private static final Logger log = LogManager.getLogger("Aesthetics");
    public static LoadingConfig config;

    static {
        log.info("Initializing Aesthetics");
        config = new LoadingConfig(new File(Launch.minecraftHome, "config/aesthetics.cfg"));

        initMixin();
    }

    private static void initMixin() {
        MixinBootstrap.init();

        if (config.scoreboardChanges) {
            log.info("Initializing ScoreBoard Mixins");
            Mixins.addConfiguration("mixins.aesthetics.scoreboard.json");
        }
    }

    @Override public String[] getASMTransformerClass() { return new String[0]; }

    @Override public String getModContainerClass() { return null; }

    @Nullable @Override public String getSetupClass() { return null; }

    @Override public void injectData(Map<String, Object> data) {}

    @Override public String getAccessTransformerClass() { return null; }
}
