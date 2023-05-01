plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.10"
    id("maven-publish")
    id("java-library")
}

group = "rainmann.telegram-bot"
version = "0.1.3"

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.github.pengrad:java-telegram-bot-api:6.5.0")
    implementation("com.squareup.retrofit2:retrofit:2.7.2")
    implementation("com.squareup.retrofit2:converter-gson:2.7.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
}


publishing {
    publications {
        create<MavenPublication>("maven_public") {
            groupId = "rainmann.telegram-bot"
            version = "0.1.3"
            artifactId = "TelegramBotNavigation"
            from(components.getByName("kotlin"))
        }
    }
}



