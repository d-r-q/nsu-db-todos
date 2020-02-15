plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(project(":todos-usecases"))
    testImplementation(project(":todos-storage-mem"))
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}