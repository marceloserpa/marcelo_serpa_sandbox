plugins {
    id("java")
    id("info.solidsoft.pitest") version "1.19.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    // Pitest's bundled ASM cannot read Java 25 (class major version 69) bytecode yet,
    // so pin the whole build (compile/test/pitest) to JDK 21.
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

pitest {
    // Required so Pitest understands and runs JUnit 5 tests
    junit5PluginVersion.set("1.2.2")
    pitestVersion.set("1.19.1")
    useClasspathFile.set(true)

    targetClasses.set(listOf("com.marceloserpa.application.*"))
    targetTests.set(listOf("com.marceloserpa.application.*"))

    // STRONGER turns on the bigger mutator set so every scenario below produces mutants
    mutators.set(listOf("STRONGER"))

    timestampedReports.set(false)
    outputFormats.set(listOf("HTML", "XML"))
}
