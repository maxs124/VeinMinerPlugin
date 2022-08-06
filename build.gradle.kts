import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    // gradlew shadowRun
}

group = "com.mshmelev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://hub.jeff-media.com/nexus/repository/jeff-media-public/")
}

dependencies {
    //MorePersistentDataTypes
    implementation("com.jeff_media:MorePersistentDataTypes:2.3.1")

    //paper api
    compileOnly("io.papermc.paper:paper-api:1.19.1-R0.1-SNAPSHOT")

    //nbt api
    implementation("de.tr7zw:item-nbt-api-plugin:2.10.0")

    //command api
    implementation("dev.jorel:commandapi-shade:8.5.0")

    // Bukkit only - plugin yml
    bukkitLibrary("com.google.code.gson", "gson", "2.8.7")

    testImplementation(kotlin("test"))
}

bukkit {
    main = "com.mshmelev.veinminer.Main"
    name = "VeinMinerPlugin"
    apiVersion = "1.13"
    version = "1.0"
    description = "Vein Miner Plugin"
    author = "maxs"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

//tasks.withType<ShadowJar> {
//    dependencies {
//        include ("dev.jorel:commandapi-shade:8.4.1")
//    }
//
//    relocate("dev.jorel.commandapi", "com.mshmelev.veinminer")
//}

