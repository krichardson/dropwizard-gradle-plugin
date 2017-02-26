package net.krisr.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

class DropwizardExtension {

    public static final String GROUP = "io.dropwizard"
    private final version = getClass().classLoader.getResource("dropwizard/dropwizard-version.txt").text.trim()
    private final DependencyHandler dependencies

    String configPath = 'config.yml'

    DropwizardExtension(Project project) {
        this.dependencies = project.dependencies
    }

    Dependency getCore() {
        dependency("core")
    }

    Dependency getTest() {
        dependency("testing")
    }

    Dependency dependency(String name) {
        dependencies.create("${GROUP}:dropwizard-${name}:${version}")
    }
}
