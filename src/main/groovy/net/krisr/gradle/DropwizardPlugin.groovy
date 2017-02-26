package net.krisr.gradle

import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import nebula.plugin.publishing.maven.MavenNebulaPublishPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.plugins.ide.eclipse.EclipsePlugin
import org.gradle.plugins.ide.idea.IdeaPlugin

class DropwizardPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        DropwizardExtension dropwizardExtension = project.extensions.create("dropwizard", DropwizardExtension, project)

        project.plugins.apply(ApplicationPlugin)
        project.plugins.apply(IdeaPlugin)
        project.plugins.apply(EclipsePlugin)
        //TODO: How to prevent needing to add jcenter repo in buildscript of the utilizing project
        project.plugins.apply(ShadowPlugin)
        project.plugins.apply(MavenNebulaPublishPlugin)


        project.jar {
            manifest {
                attributes 'Built-Date': new Date()
                attributes 'Built-By': System.getProperty('user.name')
                attributes 'Build-Jdk': System.getProperty('java.version')
                attributes 'Implementation-Title': project.name
                attributes 'Implementation-Version': project.version
                attributes 'Implementation-Vendor-Id': project.group
            }
        }

        project.shadowJar {
            mergeServiceFiles() {
                include 'META-INF/services/*'
            }
            archiveName = "${baseName}-${classifier}.${extension}"
        }

        project.run {
            args 'server', dropwizardExtension.configPath
        }

        project.runShadow {
            args 'server', dropwizardExtension.configPath
        }

        project.dependencies {
            compile dropwizardExtension.core
            testCompile dropwizardExtension.test
        }

    }
}
