import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.jakemarsden.git-hooks").version("0.0.2")
    id("io.gitlab.arturbosch.detekt").version("1.19.0")
    id("com.github.johnrengelman.shadow").version("7.1.2")
    application
}

group = "me.samfa"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven {
        name = "Sonatype Snapshots"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }

    maven {
        name = "Kotlin Discord"
        url = uri("https://maven.kotlindiscord.com/repository/maven-public/")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("dev.kord:kord-core:0.8.0-M9")
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:1.5.2-RC1")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.slf4j:slf4j-nop:1.7.35")
    implementation("ch.qos.logback:logback-core:1.2.10")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

gitHooks {
    setHooks(
        mapOf("pre-commit" to "detekt")
    )
}

detekt {
    buildUponDefaultConfig = true
    config = rootProject.files("detekt.yml")
}
