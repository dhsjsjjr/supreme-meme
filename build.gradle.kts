plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "net.mat0u5.lifeseries"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.opencollab.dev/main/")   // Geyser + Floodgate artifacts
}

dependencies {
    // Geyser Extension API — provided at runtime by Geyser itself
    compileOnly("org.geysermc.geyser:api:2.1.0-SNAPSHOT")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.shadowJar {
    archiveClassifier.set("")
    // No extra dependencies to shade — Geyser API is provided
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
