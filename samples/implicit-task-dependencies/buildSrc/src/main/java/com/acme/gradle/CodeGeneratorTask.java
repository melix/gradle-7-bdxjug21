/*
 * Copyright 2003-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.gradle;

import org.apache.commons.lang3.StringUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CodeGeneratorTask extends DefaultTask {
    private String packageName;
    private Set<String> conferences;
    private File outputDirectory;

    public CodeGeneratorTask() {
        conferences = new HashSet<>();
        outputDirectory = getProject().file(getProject().getBuildDir() + "/" + "generated-sources");
        packageName = "com.acme.conferences";
    }

    @Input
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    @Input
    public Set<String> getConferences() {
        return conferences;
    }

    public void setConferences(final Set<String> conferences) {
        this.conferences = conferences;
    }

    @OutputDirectory
    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(final File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @TaskAction
    public void generateCode() {
        File targetDir = new File(outputDirectory, packageName.replace(".", "/"));
        targetDir.mkdirs();
        for (String conference : conferences) {
            generateSource(targetDir, conference);
        }
    }

    private void generateSource(final File targetDir, final String conference) {
        StringBuilder sb = new StringBuilder();
        String className = StringUtils.capitalize(conference);
        sb.append("package ").append(packageName).append(";\n\n")
                .append("public class ").append(className).append(" {\n")
                .append("   public static String getName() { return \"").append(conference).append("\"; }\n\n")
                .append("}");
        ;
        try (FileWriter writer = new FileWriter(new File(targetDir, className + ".java"), false)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
