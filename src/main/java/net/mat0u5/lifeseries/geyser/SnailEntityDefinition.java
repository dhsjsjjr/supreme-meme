package net.mat0u5.lifeseries.geyser;

import org.geysermc.geyser.api.entity.type.decorator.CustomEntityDefinition;
import org.geysermc.geyser.api.entity.type.decorator.NonPlayerEntity;

/**
 * Bedrock definition for the LifeSeries Snail entity (lifeseries:snail).
 *
 * The Snail is used in Wild Life. It has a custom bbmodel with bones:
 *   main > head, trivia, body > shell, back, mid > midfront, midback,
 *          propeller > top, parachute, strings
 *
 * On Bedrock we represent it as a silverfish-sized custom entity using
 * Geyser's CustomEntityDefinition API. The geometry and texture come from
 * the Bedrock resource pack you bundle with the extension (see resources/).
 *
 * Synced data the Java server sends:
 *   - attacking (bool)  → we play the attack animation
 *   - flying (bool)     → we show the propeller/fly geometry
 *   - gliding (bool)    → parachute visible
 *   - landing (bool)    → landing transition
 *   - mining (bool)     → mining animation
 *   - fromTrivia (bool) → swap to trivia skin
 *   - skinName (string) → custom player skin name (dynamic textures)
 *   - playerDead (bool) → swap to zombie skin
 *
 * Note: dynamic skinName textures are Java-only (they download player-uploaded
 * PNGs at runtime). On Bedrock we fall back to the default snail texture for
 * custom skins, but trivia and zombie variants are supported via Molang queries.
 */
public class SnailEntityDefinition {

    /** The Java registry id this extension intercepts */
    public static final String JAVA_IDENTIFIER = "lifeseries:snail";

    public static CustomEntityDefinition build() {
        return CustomEntityDefinition.builder()
                .identifier(JAVA_IDENTIFIER)
                // The Bedrock identifier we register in the resource pack
                .bedrockIdentifier("lifeseries:snail")
                // Hit box matches Java: 0.5w x 0.6h
                .width(0.5f)
                .height(0.6f)
                // Summonable=false; the server controls spawning entirely
                .summonable(false)
                .build();
    }
}
