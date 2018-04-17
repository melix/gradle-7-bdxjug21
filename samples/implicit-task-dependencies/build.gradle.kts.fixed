import com.acme.gradle.CodeGeneratorTask

plugins {
    `java-library`
    id("code-generator")
}

val generateCode by tasks.getting(CodeGeneratorTask::class) {
    conferences = setOf("Breizhcamp", "Devoxx")
}

project.java.sourceSets["main"].java.srcDir(generateCode)