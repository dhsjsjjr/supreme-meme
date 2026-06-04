package net.mat0u5.lifeseries.geyser;

import org.geysermc.geyser.api.extension.Extension;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineResourcePacksEvent;
import org.geysermc.geyser.api.pack.ResourcePack;
import org.geysermc.event.subscribe.Subscribe;

import java.nio.file.Path;

public class LifeSeriesExtension implements Extension {

    @Subscribe
    public void onResourcePackDefine(GeyserDefineResourcePacksEvent event) {
        // Load the Bedrock resource pack bundled in the extension JAR
        // Place lifeseries_rp.mcpack in the extension's data folder
        Path packPath = dataFolder().resolve("lifeseries_rp.mcpack");

        if (!packPath.toFile().exists()) {
            logger().warning("[LifeSeries] lifeseries_rp.mcpack not found in " + dataFolder()
                    + " — Bedrock players will not see custom entities.");
            logger().warning("[LifeSeries] Export the resource pack from Blockbench and place it there.");
            return;
        }

        event.register(ResourcePack.create(packPath));
        logger().info("[LifeSeries] Registered Bedrock resource pack for Snail, TriviaBot, AngrySnowman.");
    }
}
