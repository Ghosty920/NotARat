@file:Suppress("UnstableApiUsage", "PropertyName")

val baseGroup: String by project
val mcVersion: String by project
val version: String by project
val modid: String by project

plugins {
    idea
    java
    id("gg.essential.loom") version "0.10.0.+"
    id("dev.architectury.architectury-pack200") version "0.1.3"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

loom.forge {
    pack200Provider.set(dev.architectury.pack200.java.Pack200Adapter())
}

repositories {
    mavenCentral()
    maven("https://repo.sk1er.club/repository/maven-public/")
    maven("https://repo.sk1er.club/repository/maven-releases/")
    maven("https://jitpack.io")
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

val shadowImpl: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

val shadowMod: Configuration by configurations.creating {
    configurations.modImplementation.get().extendsFrom(this)
}

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")
    runtimeOnly("me.djtheredstoner:DevAuth-forge-legacy:1.1.2")
}

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
}

tasks.withType(Jar::class) {
    archiveBaseName.set(modid)
    manifest.attributes.run {
        this["ModSide"] = "CLIENT"
        this["FMLCorePluginContainsFMLMod"] = "true"
        this["ForceLoadAsMod"] = "true"
    }
}

val remapJar by tasks.named<net.fabricmc.loom.task.RemapJarTask>("remapJar") {
    archiveClassifier.set("")
    from(tasks.shadowJar)
    input.set(tasks.shadowJar.get().archiveFile)
}

tasks {
    processResources {
        inputs.property("version", project.version)
        inputs.property("mcversion", mcVersion)
        inputs.property("modid", modid)

        filesMatching(listOf("mcmod.info")) {
            expand(inputs.properties)
        }

        rename("(.+_at.cfg)", "META-INF/$1")
    }
    jar {
        archiveClassifier.set("without-deps")
        destinationDirectory.set(layout.buildDirectory.dir("badjars"))
    }
    shadowJar {
        destinationDirectory.set(layout.buildDirectory.dir("badjars"))
        archiveClassifier.set("all-dev")
        configurations = listOf(shadowImpl, shadowMod)
        doLast {
            configurations.forEach {
                println("Copying jars into mod: ${it.files}")
            }
        }
        fun relocate(name: String) = relocate(name, "$baseGroup.deps.$name")
    }
    assemble.get().dependsOn(remapJar)

}