package net.mat0u5.lifeseries.geyser;

import org.geysermc.geyser.api.entity.type.decorator.CustomEntityDefinition;

/**
 * Bedrock definition for the LifeSeries AngrySnowman entity (lifeseries:angrysnowman).
 *
 * Used in Nice Life. It extends SnowGolem on the Java side, so it already looks
 * like a snow golem — but it has 8 texture variants (variation_0.png through
 * variation_7.png) chosen deterministically from the entity's UUID on the Java
 * renderer.
 *
 * Because Geyser does not yet have a hook for per-entity texture swapping
 * based on UUID, we handle this two ways:
 *
 *   Option A (simple): Use a single "average" texture (variation_0) for all
 *   Bedrock players. Fast to implement, looks fine.
 *
 *   Option B (full fidelity): Override translateEntity() in a GeyserExtension
 *   EntityTranslator and compute the same UUID hash the Java renderer uses, then
 *   set the Bedrock texture path accordingly. This requires the Geyser Extension
 *   EntityTranslator API which is still experimental; a stub is included in
 *   AngrySnowmanTranslator.java.
 *
 * Hit box: 0.7w x 1.9F, eyeHeight 1.7F — matches Java sized(0.7F, 1.9F).
 */
public class AngrySnowmanEntityDefinition {

    public static final String JAVA_IDENTIFIER = "lifeseries:angrysnowman";

    public static CustomEntityDefinition build() {
        return CustomEntityDefinition.builder()
                .identifier(JAVA_IDENTIFIER)
                .bedrockIdentifier("lifeseries:angrysnowman")
                // Matches Java sized(0.7F, 1.9F)
                .width(0.7f)
                .height(1.9f)
                .summonable(false)
                .build();
    }
}
