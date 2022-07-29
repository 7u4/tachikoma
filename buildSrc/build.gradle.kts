import org.gradle.plugins.ide.idea.model.IdeaModel

plugins {
    idea
    `kotlin-dsl`
    `embedded-kotlin`
    id("com.github.ben-manes.versions") version "0.42.0"
}

val kotlinVersion = "1.6.10"
dependencies {
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.8.18")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:10.2.1")
    implementation("io.ebean:ebean-gradle-plugin:12.15.0")
    implementation("org.eclipse.jgit:org.eclipse.jgit:5.6.0.201912101111-r")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("org.junit.platform:junit-platform-gradle-plugin:1.2.0")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.42.0")
    implementation("org.apache.commons:commons-compress:1.21")
    api("com.github.breadmoirai:github-release:2.4.1")
}

configurations.all {
    resolutionStrategy {
        failOnVersionConflict()
        force(
            "com.fasterxml.jackson.core:jackson-databind:2.11.1",
            "com.fasterxml.jackson.core:jackson-core:2.11.1",
            "com.fasterxml.jackson.module:jackson-module-jaxb-annotations:2.11.1",
            "com.google.guava:guava:28.2-jre",
            "org.slf4j:slf4j-api:1.7.30",
            "commons-logging:commons-logging:1.2",
            "commons-lang:commons-lang:2.6",
            "com.sun.jersey:jersey-client:1.18",
            "org.apache.maven:maven-artifact:3.6.3",
            "org.apache.maven:maven-model:3.6.3",
            "org.codehaus.plexus:plexus-utils:3.3.0",
            "com.google.gradle:osdetector-gradle-plugin:1.6.2",
            "org.codehaus.groovy.modules.http-builder:http-builder:0.7.2",
            "org.apache.httpcomponents:httpclient:4.5.11",
            "org.jetbrains.kotlin:kotlin-stdlib:$embeddedKotlinVersion",
            "org.jetbrains.kotlin:kotlin-stdlib-common:$embeddedKotlinVersion",
        )
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}


group = "com.tachikoma"

configure<IdeaModel> {
    module {
        outputDir = file("build/idea-out")
        testOutputDir = file("build/idea-testout")
    }
}

kotlinDslPluginOptions {
    jvmTarget.set("11")
}
