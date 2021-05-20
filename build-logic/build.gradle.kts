plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.github.jruby-gradle:jruby-gradle-plugin:2.0.2")
    implementation("org.asciidoctor:asciidoctor-gradle-jvm:3.3.2")
    implementation("org.ysb33r.gradle:vfs-gradle-plugin:1.0")
    implementation("commons-httpclient:commons-httpclient:3.1")
    implementation("org.ajoberstar:gradle-git:1.1.0")
    implementation("me.champeau.deck2pdf:deck2pdf:0.3.0")
    compileOnly("org.asciidoctor:asciidoctorj:2.4.3")

    components.all(RemoveGroovyRule::class.java)

    configurations.all {
        resolutionStrategy.force("com.jcraft:jsch:0.1.54")
    }
}

gradlePlugin {
    plugins {
        register("presentation") {
            id = "org.gradle.presentation.asciidoctor"
            implementationClass = "AsciidoctorPresentationPlugin"
        }
    }
}

class RemoveGroovyRule: ComponentMetadataRule {

    override
    fun execute(context: ComponentMetadataContext) = context.details.allVariants {
        withDependencies {
            removeAll {
                it.group == "org.codehaus.groovy"
            }
        }
    }

}