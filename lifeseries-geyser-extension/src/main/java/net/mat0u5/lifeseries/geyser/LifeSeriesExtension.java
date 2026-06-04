package net.mat0u5.lifeseries.geyser;

import org.geysermc.geyser.api.extension.Extension;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomEntitiesEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserPostInitializeEvent;
import org.geysermc.event.subscribe.Subscribe;

public class LifeSeriesExtension implements Extension {

    @Subscribe
    public void onDefineCustomEntities(GeyserDefineCustomEntitiesEvent event) {
        // Register all three LifeSeries custom entities so Bedrock clients can see them
        event.register(SnailEntityDefinition.build());
        event.register(TriviaBotEntityDefinition.build());
        event.register(AngrySnowmanEntityDefinition.build());

        logger().info("[LifeSeries] Registered 3 custom entity definitions for Bedrock clients.");
    }

    @Subscribe
    public void onPostInitialize(GeyserPostInitializeEvent event) {
        logger().info("[LifeSeries Geyser Extension] Loaded successfully. " +
                "Snail, TriviaBot, and AngrySnowman are now visible to Bedrock players.");
    }
}
