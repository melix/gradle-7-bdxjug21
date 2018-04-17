plugins {
   `java-gradle-plugin`
}

gradlePlugin {
   (plugins) {
      "code-generator" {
         id = "code-generator"
         implementationClass = "com.acme.gradle.CodeGeneratorPlugin"
      }
   }
}

repositories {
   jcenter()
}

dependencies {
   implementation("org.apache.commons:commons-lang3:3.7")
}