package com.acme.demo

import org.gradle.api.Plugin
import org.gradle.api.Project

open class MySigningPlugin: Plugin<Project> {
    override
    fun apply(project: Project) {
        project.pluginManager.apply("base")
        project.tasks.create("sign", MySign::class.java) {
            group = "Signing"
        }
    }

}