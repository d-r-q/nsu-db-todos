plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":todos-usecases"))
    api(project(":todos-storage-mem"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}