plugins {
    kotlin("multiplatform") version "1.7.0"
    id("maven-publish")
}

group = "rainmann.telegram-bot"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    commonMainImplementation("com.github.pengrad:java-telegram-bot-api:6.5.0")
    commonMainImplementation("com.squareup.retrofit2:retrofit:2.7.2")
    commonMainImplementation("com.squareup.retrofit2:converter-gson:2.7.2")
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
    commonMainImplementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }

}



