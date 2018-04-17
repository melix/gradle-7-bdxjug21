package com.acme.demo

import com.google.common.hash.Hashing
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.TaskAction
import java.io.File

open class MySign : DefaultTask() {
    lateinit
    var sources: FileCollection

    var algorithm = "md5"

    var outputFile: File

    init {
        outputFile = project.file("${project.buildDir}/$name/hash.$algorithm")
    }

    @TaskAction
    fun sign() {
        outputFile.parentFile.mkdirs()
        outputFile.writeText(hashInputs())
    }

    private
    fun hashInputs(): String = hasher().run {
        sources.forEach {
            putBytes(it.readBytes())
        }
        hash().toString()
    }

    @Suppress("deprecation")
    private
    fun hasher() = when (algorithm) {
        "md5" -> Hashing.md5()
        "sha1" -> Hashing.sha1()
        "sha256" -> Hashing.sha256()
        else -> throw IllegalArgumentException("Unsupported hashing: $algorithm")
    }.newHasher()!!
}