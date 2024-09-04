import ru.vyarus.gradle.plugin.python.PythonExtension
import ru.vyarus.gradle.plugin.python.task.PythonTask

plugins {
    java

    id("fabric-loom") version "1.7.+"

    id("me.modmuss50.mod-publish-plugin") version "0.5.+"
    `maven-publish`

    id("org.ajoberstar.grgit") version "5.0.0"

    id("ru.vyarus.use-python") version "3.0.0"
}

group = "dev.isxander"
version = "1.21.1+1.0"

loom {
    splitEnvironmentSourceSets()
    mods {
        register("debugify") {
            sourceSet(sourceSets["main"])
            sourceSet(sourceSets["client"])
        }
    }
}

val gametest by sourceSets.registering {
    compileClasspath += sourceSets.main.get().compileClasspath
    runtimeClasspath += sourceSets.main.get().runtimeClasspath
    compileClasspath += sourceSets["client"].compileClasspath
    runtimeClasspath += sourceSets["client"].runtimeClasspath
}

loom {
    runs {
        register("gametest") {
            client()
            ideConfigGenerated(true)
            name("Game Test")
            source(gametest.get())
        }

        listOf(named("client"), named("server")).forEach {
            it {
                vmArg("-Ddebugify.forceMacFixes=true")
                vmArg("-Ddebugify.forceLinuxFixes=true")
                vmArg("-Ddebugify.forceWindowsFixes=true")
            }
        }
    }
    createRemapConfigurations(gametest.get())

    accessWidenerPath.set(file("src/main/resources/debugify.accesswidener"))
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://maven.isxander.dev/releases")
    maven("https://maven.isxander.dev/snapshots")
    maven("https://maven.terraformersmc.com")
    maven("https://maven.quiltmc.org/repository/release")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

val minecraftVersion: String by project
val fabricLoaderVersion: String by project
val qmBuild: String by project
val fabricApiVersion: String by project
val yaclVersion: String by project
val mixinExtrasVersion: String by project
val modMenuVersion: String by project

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings(loom.layered {
        if (qmBuild != "0")
            mappings("org.quiltmc:quilt-mappings:$minecraftVersion+build.$qmBuild:intermediary-v2")
        officialMojangMappings()
    })

    modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")

    modImplementation(fabricApi.module("fabric-resource-loader-v0", fabricApiVersion))
    modRuntimeOnly("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")

    "modClientImplementation"("dev.isxander:yet-another-config-lib:$yaclVersion") { exclude(module = "fabric-loader") }
    "modClientImplementation"("com.terraformersmc:modmenu:$modMenuVersion") { exclude(module = "fabric-loader") }

    "gametestImplementation"(sourceSets.main.get().output)
    "gametestImplementation"(sourceSets["client"].output)
    "modGametestImplementation"(fabricApi.module("fabric-gametest-api-v1", fabricApiVersion))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(21)
}

java {
    withSourcesJar()
}

tasks.processResources {
    val modDescription = """
        Fixes Minecraft bugs found on the bug tracker

        License stuff:
        j-Tai's TieFix - Code used licensed under LGPLv3
        FlashyReese's Sodium Extra - Code used licensed under LGPLv3
        Ampflower's 2x2 Surrounded Saplings Fix - Code used licensed under Zlib
        """.trimIndent()
    inputs.property("version", project.version)
    inputs.property("description", modDescription)
    filesMatching(listOf("fabric.mod.json", "quilt.mod.json")) {
        expand(
            "version" to project.version,
            "description" to modDescription,
        )
    }
}

python {
    pip("requests:2.28.1")
    pip("packaging:21.3")

    scope = PythonExtension.Scope.USER
}

tasks.register<PythonTask>("checkBugStatuses") {
    group = "debugify"

    command = project.file("scripts/check_bug_fixes.py").toString()
}

publishMods {
    displayName.set("Debugify ${project.version}")

    file.set(tasks.remapJar.get().archiveFile)

    changelog.set(
        run {
            var changelogText = file("changelogs/$minecraftVersion/${project.version}.md").takeIf { it.exists() }?.readText()
                ?: "No changelog is provided"
            file("changelogs/header.md").takeIf { it.exists() }?.readText()?.let { changelogText = it + "\n\n" + changelogText }
            changelogText
        }
    )
    type.set(STABLE)
    modLoaders.add("fabric")

    val modrinthId: String by project
    if (modrinthId.isNotBlank() && hasProperty("modrinth.token")) {
        modrinth {
            projectId.set(modrinthId)
            accessToken.set(findProperty("modrinth.token")?.toString())
            minecraftVersions.addAll(minecraftVersion)

            requires { slug.set("yacl") }
            requires { slug.set("fabric-api") }
            optional { slug.set("modmenu") }
        }
    }

    val curseforgeId: String by project
    if (curseforgeId.isNotBlank() && hasProperty("curseforge.token")) {
        curseforge {
            projectId.set(curseforgeId)
            accessToken.set(findProperty("curseforge.token")?.toString())
            minecraftVersions.addAll(minecraftVersion)

            requires { slug.set("yacl") }
            requires { slug.set("fabric-api") }
            optional { slug.set("modmenu") }
        }
    }

    val githubProject: String by project
    if (githubProject.isNotBlank() && hasProperty("github.token")) {
        github {
            repository.set(githubProject)
            accessToken.set(findProperty("github.token")?.toString())
            commitish.set(grgit.branch.current().name)
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("debugify") {
            groupId = "dev.isxander"
            artifactId = "debugify"

            from(components["java"])
        }
    }

    repositories {
        if (hasProperty("XANDER_MAVEN_USER") && hasProperty("XANDER_MAVEN_PASS")) {
            maven(url = "https://maven.isxander.dev/releases") {
                credentials {
                    username = property("XANDER_MAVEN_USER")?.toString()
                    password = property("XANDER_MAVEN_PASS")?.toString()
                }
            }
        } else {
            println("Xander Maven credentials not satisfied")
        }
    }
}

tasks.register("publishDebugify") {
    group = "debugify"

    dependsOn("checkBugStatuses")

    dependsOn("clean")

    dependsOn("publishMods")

    dependsOn("publish")
}

