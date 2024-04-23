plugins {
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "net.momirealms"
version = "4.16.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.kyori:adventure-api:4.16.0")
    implementation("net.kyori:adventure-text-minimessage:4.16.0")
    implementation("net.kyori:adventure-platform-bukkit:4.3.2")
    implementation("net.kyori:adventure-platform-bungeecord:4.3.2")
}

tasks.shadowJar {
    archiveFileName.set("Adventure-Bundle-" + project.version + ".jar")
}

tasks.publishToMavenLocal.get().dependsOn(tasks.shadowJar)

tasks {
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                groupId = "net.momirealms"
                artifactId = "adventure-bundle"
                version = rootProject.version.toString()
                artifact(shadowJar)
            }
        }
    }
}