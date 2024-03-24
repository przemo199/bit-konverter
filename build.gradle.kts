plugins {
    java
    kotlin("jvm") version "1.9.23"
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.example"
            artifactId = "bit-konverter"
            version = "0.0.1"

            from(components["java"])
        }
    }
}
