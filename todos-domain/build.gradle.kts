plugins {
    kotlin("jvm")
}

dependencies {
    api(kotlin("stdlib-jdk8"))

}

tasks.withType<Test> {
    useJUnitPlatform()
}

