package com.wuzei.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloPlugin implements Plugin<Project> {
    @Override
    void apply(Project target) {
        def extension = target.extensions.create("helloextension", HelloPluginExtension)
        target.afterEvaluate {
            println "test ${extension.name}"
        }
    }
}