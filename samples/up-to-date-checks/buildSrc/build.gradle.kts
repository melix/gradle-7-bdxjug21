plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.google.guava:guava:24.1-jre")
}

gradlePlugin {
    (plugins) {
        "my-signing-plugin" {
            id = "my-signing-plugin"
            implementationClass = "com.acme.demo.MySigningPlugin"
        }
    }
}