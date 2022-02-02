plugins {
    java
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.openapi.generator") version "5.4.0"
}

group = "io.thewokecoder"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("io.swagger.core.v3:swagger-annotations:2.1.12")
    implementation("io.swagger:swagger-annotations:1.6.4")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<JavaCompile> {
    dependsOn(tasks.openApiGenerate)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    main {
        java {
            srcDirs("$buildDir/generated/openapi/src/main/java")
        }
    }
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$projectDir/src/main/resources/api-swagger.yaml")
    outputDir.set("$buildDir/generated/openapi")
    apiPackage.set("io.thewokecoder.example.api")
    modelPackage.set("io.thewokecoder.example.model")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "generateApis" to "true",
            "generateApiTests" to "false",
            "generateModels" to "true",
            "generateModelTests" to "false",
            "generateModelDocumentation" to "false",
            "generateSupportingFiles" to "false",
            "hideGenerationTimestamp" to "true",
            "interfaceOnly" to "true",
            "library" to "spring-boot",
            "serializableModel" to "true",
            "useBeanValidation" to "true",
            "useTags" to "true",
            "implicitHeaders" to "true",
            "openApiNullable" to "false",
            "oas3" to "true"
        )
    )
}


