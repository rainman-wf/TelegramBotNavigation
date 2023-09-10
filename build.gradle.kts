plugins {
    kotlin("jvm") version "1.9.0"
}

group = "rainmann.telegram-bot"
version = "0.1.7"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.retrofit2:retrofit:2.7.0")
    implementation("com.squareup.retrofit2:converter-gson:2.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.google.dagger:dagger:2.48")
}