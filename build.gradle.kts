import org.gradle.kotlin.dsl.test
import org.gradle.kotlin.dsl.testImplementation

plugins {
    java
    kotlin("jvm") version "2.0.0"
    `maven-publish`
}

group = "com.github.przemo199"
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
            groupId = "com.github.przemo199"
            artifactId = "bit-konverter"
            version = "0.0.3"

            from(components["java"])
        }
    }
}
