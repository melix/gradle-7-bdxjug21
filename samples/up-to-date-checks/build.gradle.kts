import com.acme.demo.MySign

plugins {
    id("com.gradle.build-scan") version ("1.13.1")
    id("my-signing-plugin")
    `java-library`
}

val sign by tasks.getting(MySign::class) {
    sources = project.java.sourceSets["main"].allSource
    algorithm = "sha256"
}

defaultTasks("sign")