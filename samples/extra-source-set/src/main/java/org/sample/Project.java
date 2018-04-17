package org.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Project {

    private static final Logger LOGGER = LoggerFactory.getLogger(Project.class);

    private final String name;

    public Project(String name) {
        LOGGER.info("Creating a project named '{}'", name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}