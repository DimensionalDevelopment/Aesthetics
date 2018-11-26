package org.dimdev.aesthetics;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class LoadingConfig {

    private Configuration config;

    public boolean scoreboardChanges = true;


    public LoadingConfig(File file) {
        if (!file.exists()) { return; }

        config = new Configuration(file);
        scoreboardChanges = config.get("changes", "scoreboardChanges", true).getBoolean();
    }
}
