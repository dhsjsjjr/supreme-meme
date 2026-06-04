package net.mat0u5.lifeseries.geyser;

import org.geysermc.geyser.api.entity.type.decorator.CustomEntityDefinition;

/**
 * Bedrock definition for the LifeSeries TriviaBot entity (lifeseries:triviabot).
 *
 * Used in Wild Life and Nice Life (as SantaBot). It is a humanoid-sized flying
 * entity (0.65w x 1.8h). The Java server sets `santaBot` to switch to the
 * Christmas variant texture.
 *
 * Animations the Java server drives via synced data:
 *   - gliding (bool)
 *   - analyzing (int, 0=off)
 *   - santaBot (bool)        → triggers santabot texture + santa animations
 *   - waving (int, 0=off)
 *   - leaving (bool)
 *   - submittedAnswer (bool)
 *   - ranOutOfTime (bool)
 *   - answeredRight (bool)
 *   - interactedWith (bool)
 *
 * The Bedrock resource pack (see resources/lifeseries_rp/) contains:
 *   - geometry: geometry.lifeseries.triviabot
 *   - textures: textures/entity/triviabot/triviabot.png
 *               textures/entity/triviabot/santabot.png
 *   - animations: driven by the entity.json render controller with Molang
 *     queries against the synced entity properties transmitted by Geyser.
 */
public class TriviaBotEntityDefinition {

    public static final String JAVA_IDENTIFIER = "lifeseries:triviabot";

    public static CustomEntityDefinition build() {
        return CustomEntityDefinition.builder()
                .identifier(JAVA_IDENTIFIER)
                .bedrockIdentifier("lifeseries:triviabot")
                // Matches Java sized(0.65f, 1.8f)
                .width(0.65f)
                .height(1.8f)
                .summonable(false)
                .build();
    }
}
