import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	id("com.jetbrains.exposed.gradle.plugin") version "0.2.1"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	
	implementation("mysql:mysql-connector-java:8.0.23") // 追加
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.jetbrains.exposed:exposed-core:0.29.1")
	implementation("org.jetbrains.exposed:exposed-jdbc:0.29.1")
	implementation("org.jetbrains.exposed:exposed-dao:0.29.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
	testImplementation("org.assertj:assertj-core:3.19.0")
	testImplementation("org.mockito:mockito-core:3.8.0")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

sourceSets.main {
    java.srcDirs("build/tables")
}

tasks.generateExposedCode {
    dependsOn("clean")
}

// 追加
exposedCodeGeneratorConfig {
    val dbProperties = loadProperties("${projectDir}/db.properties")
    configFilename = "exposedConf.yml"
		connectionURL = dbProperties["jdbcUrl"].toString()
    user = dbProperties["dataSource.user"].toString()
    password = dbProperties["dataSource.password"].toString()
    databaseName = dbProperties["dataSource.database"].toString()
    databaseDriver = dbProperties["dataSource.driver"].toString()
}
