import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val springBootVersion = "2.7.3"
    val kotlinVersion = "1.7.10"

    kotlin("jvm") version kotlinVersion apply true
    kotlin("plugin.spring") version kotlinVersion apply true

    id("org.springframework.boot") version springBootVersion apply true
    id("io.spring.dependency-management") version "1.1.0" apply true
}

group = "com.p31r"
version = "0.0.1-SNAPSHOT"
//java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    //maven { setUrl("https://repo.spring.io/milestone") }
}

apply {
    plugin("org.jetbrains.kotlin.jvm")
    plugin("org.jetbrains.kotlin.plugin.spring")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.vintage:junit-vintage-engine")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
