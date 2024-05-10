plugins {
    id("java")
    id("application")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

allprojects {
    apply<JavaPlugin>()
    apply(plugin = "java")
    apply(plugin = "application")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "org.gradle.maven-publish")
    application { mainClass.set("") }
}

subprojects {

    group = "net.momirealms"
    version = "4.17.0"

    tasks.shadowJar {
        destinationDirectory.set(file("$rootDir/target"))
        archiveClassifier.set("")
        archiveFileName.set("Adventure-Bundle-" + project.version + ".jar")
    }

    if (project.name == "bundle") {
        publishing {
            publications {
                create<MavenPublication>("mavenJava") {
                    groupId = "net.momirealms"
                    artifactId = "adventure-bundle"
                    version = rootProject.version.toString()
                    artifact(tasks.shadowJar)
                }
            }
        }
    }
}