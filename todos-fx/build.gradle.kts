plugins {
    kotlin("jvm")
}

version = "unspecified"

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("no.tornado:tornadofx:1.7.17")

    implementation("io.ktor:ktor-client-cio:1.3.0")
    implementation("io.ktor:ktor-client-json-jvm:1.3.0")
    implementation("io.ktor:ktor-client-jackson:1.3.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}