# LifeSeries Geyser Extension

Makes the three LifeSeries custom Java entities visible to Bedrock players via Geyser.

| Entity | Used in | Java ID |
|---|---|---|
| Snail | Wild Life | `lifeseries:snail` |
| TriviaBot / SantaBot | Wild Life + Nice Life | `lifeseries:triviabot` |
| AngrySnowman | Nice Life | `lifeseries:angrysnowman` |

---

## Prerequisites

- **Geyser-Fabric** or **Geyser-Spigot** installed on your server
- Geyser version that supports the Extension API (2.1.0+)
- The LifeSeries mod installed server-side (Java players auto-download it client-side)

---

## Building

```bash
./gradlew build
# Output: build/libs/lifeseries-geyser-extension-1.0.0.jar
```

---

## Install — 3 Steps

### Step 1: Install the extension JAR

Drop the built JAR into:
```
Geyser/extensions/lifeseries-geyser-extension-1.0.0.jar
```

### Step 2: Copy textures from the LifeSeries mod

The extension ships geometry and entity definitions but **not** the texture PNGs
(they live in the LifeSeries mod's resource pack). You need to copy them into the
Bedrock resource pack folder.

From the LifeSeries resourcepack folder:
```
LifeSeries/src/main/resources/resourcepacks/lifeseries/assets/lifeseries/textures/entity/
```

Copy these into the extension's resource pack (included in the JAR under
`lifeseries_rp/`, but Geyser auto-extracts it — you can also place it alongside):

```
textures/entity/snail/default.png
textures/entity/snail/trivia.png
textures/entity/snail/zombie.png
textures/entity/triviabot/triviabot.png
textures/entity/triviabot/santabot.png
textures/entity/angrysnowman/variation_0.png  (through variation_7.png)
```

Target location (Geyser extracts the resource pack here):
```
Geyser/custom_entities/lifeseries_rp/textures/entity/...
```

Or bundle the textures into the JAR yourself by placing them in
`src/main/resources/lifeseries_rp/textures/entity/` before building.

### Step 3: Restart Geyser

Geyser will log:
```
[LifeSeries] Registered 3 custom entity definitions for Bedrock clients.
[LifeSeries Geyser Extension] Loaded successfully.
```

---

## What Bedrock players will see

### Snail
- Custom snail geometry with shell, head antennae, body
- **Default** skin normally, **trivia** skin when bound to a trivia snail, **zombie** skin when the bound player is dead
- **Propeller + top blade** visible only while flying
- **Parachute + strings** visible only while gliding
- Walk and idle animations

### TriviaBot
- Humanoid robot with antenna and screen face
- **Normal** texture in Wild Life, **SantaBot** texture in Nice Life
- Idle float, walk, glide, and wave animations

### AngrySnowman
- Snow golem body without the flower, 8 texture variants
- Bedrock uses `q.variant` to pick the texture — Geyser maps the entity's
  Java variant data to this automatically
- Walk and idle animations

---

## Morph System (Wild Life player morphs)

The morph system (players disguised as animals) is **Java client-side only**. The
server sends a custom packet to Java clients with the entity type, and Java clients
render a dummy entity over the player. Bedrock clients will simply see the player's
skin as normal — this is expected behavior and doesn't affect gameplay.

If you want morphs on Bedrock, that would require a separate Floodgate/Geyser
EntityTranslator that intercepts the morph sync packet and remaps it to a Bedrock
entity — a significantly more complex project.

---

## Texture variant note (AngrySnowman)

Java picks the texture variation using `new Random(uuid.hashCode()).nextInt(8)`.
On Bedrock, `q.variant` is set by Geyser from the entity's variant synced data.
The AngrySnowman doesn't set a variant on the Java side, so all Bedrock players
will see `variation_0` until you add this to the Java `AngrySnowman` class:

```java
// In AngrySnowman constructor, after calmDuration is set:
int variation = new Random(getUUID().hashCode()).nextInt(SKIN_VARIATIONS);
setVariant(variation); // uses vanilla SynchedEntityData VARIANT if SnowGolem has it
```

Or just live with variation_0 — they all look like snowmen.
