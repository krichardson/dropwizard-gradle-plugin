# Dropwizard Gradle Plugin

A plugin for simplifying the setup of a Dropwizard Project

The plugin will automaticaly add the dropwizard dependencies and include the ShadowJar plugin
for building an executable jar.

Here's an example of a starter build.gradle file that can get you going:

```
group 'com.example.project'
version '1.0.0-SNAPSHOT'

buildscript {
    repositories {
        jcenter()
        mavenLocal()
    }
    dependencies {
        classpath 'net.krisr.gradle:dropwizard-gradle-plugin:1.0.0-SNAPSHOT'
    }
}

apply plugin: 'java'
apply plugin: 'net.krisr.dropwizard'

mainClassName='com.example.project.Application'
```
