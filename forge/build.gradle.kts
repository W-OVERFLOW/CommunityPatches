plugins {
    id("com.github.johnrengelman.shadow") version "7.+"
    id("com.modrinth.minotaur")
    id("com.matthewprenger.cursegradle")
}

base.archivesName.set("debugify-${project.name}")

architectury {
    platformSetupLoomIde()
    forge()
}

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)

    forge.apply {
        mixinConfig("debugify-common.mixins.json")
        mixinConfig("debugify.mixins.json")
        convertAccessWideners.set(true)
        extraAccessWideners.add(loom.accessWidenerPath.get().asFile.name)
    }
}

val common by configurations.creating {
    configurations.compileClasspath.get().extendsFrom(this)
    configurations.runtimeClasspath.get().extendsFrom(this)
    configurations["developmentForge"].extendsFrom(this)
}
val shadowCommon by configurations.creating

dependencies {
    val minecraftVersion: String by rootProject
    val forgeVersion: String by rootProject
    val clothVersion: String by rootProject

    forge("net.minecraftforge:forge:$minecraftVersion-$forgeVersion")

    common(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    shadowCommon(project(path = ":common", configuration = "transformProductionForge")) { isTransitive = false }


    "com.github.zafarkhaja:java-semver:0.9.+".let {
        implementation(it)
        forgeRuntimeLibrary(it)
        shadowCommon(it)
    }

    "com.github.llamalad7:mixinextras:0.0.+".let {
        forgeRuntimeLibrary(it)
        implementation(it)
        annotationProcessor(it)
        shadowCommon(it)
    }

    modImplementation("me.shedaniel.cloth:cloth-config-forge:$clothVersion")
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching("META-INF/mods.toml") {
            expand(
                "version" to project.version
            )
        }
    }

    shadowJar {
        relocate("com.llamalad7.mixinextras", "cc.woverflow.debugify.lib.mixinextras")
        relocate("com.github.zafarkhaja.semver", "cc.woverflow.debugify.lib.jsemver")

        exclude("fabric.mod.json")
        exclude("architectury.common.json")

        configurations = listOf(shadowCommon)
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        inputFile.set(shadowJar.get().archiveFile)
        dependsOn(shadowJar)
        archiveClassifier.set(null as String?)
    }

    jar {
        archiveClassifier.set("dev")
    }
}

components["java"].withGroovyBuilder {
    "withVariantsFromConfiguration"(configurations["shadowRuntimeElements"]) {
        "skip"()
    }
}

val minecraftVersion: String by rootProject

modrinth {
    token.set(findProperty("modrinth.token")?.toString())
    projectId.set("QwxR6Gcd")
    versionName.set("[${project.name.capitalize()} $minecraftVersion] ${project.version}")
    versionNumber.set("${project.version}-${project.name}")
    versionType.set("release")
    uploadFile.set(tasks.remapJar.get())
    gameVersions.set(listOf(minecraftVersion))
    loaders.set(listOf(project.name))
    changelog.set(extra["changelog"].toString())
}

rootProject.tasks["publishToModrinth"].dependsOn(tasks["modrinth"])

if (hasProperty("curseforge.token")) {
    curseforge {
        apiKey = findProperty("curseforge.token")
        project(closureOf<com.matthewprenger.cursegradle.CurseProject> {
            mainArtifact(tasks.remapJar.get(), closureOf<com.matthewprenger.cursegradle.CurseArtifact> {
                displayName = "[${project.name.capitalize()} $minecraftVersion] ${project.version}"
            })

            id = "596224"
            releaseType = "release"
            addGameVersion(minecraftVersion)
            addGameVersion(project.name.capitalize())
            addGameVersion("Java 17")

            relations(closureOf<com.matthewprenger.cursegradle.CurseRelation> {
                requiredDependency("cloth-config-forge")
            })

            changelog = extra["changelog"]
            changelogType = "markdown"
        })

        options(closureOf<com.matthewprenger.cursegradle.Options> {
            forgeGradleIntegration = false
        })
    }
}

rootProject.tasks["publishToCurseforge"].dependsOn(tasks["curseforge"])
