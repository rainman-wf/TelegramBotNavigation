plugins {
    kotlin("jvm") version "1.9.0"
    id("maven-publish")
    id("java-library")
}

group = "rainmann.telegram-bot"
version = "1.0.0"

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10")
    implementation("com.google.dagger:dagger:2.48")
}

publishing {
    publications {
        create<MavenPublication>("maven_public") {
            groupId = "rainmann.telegram-bot"
            version = "1.0.0"
            artifactId = "TelegramBotNavigation"
            from(components.getByName("kotlin"))
        }
    }
}

kotlin {
    jvmToolchain(17)
}