plugins {
    java
    kotlin("jvm") version "2.0.20"
    `maven-publish`
}

group = "com.github.przemo199"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.przemo199"
            artifactId = "bit-konverter"
            version = "0.0.4"

            from(components["java"])
        }
    }
}
