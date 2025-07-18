allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.buildDir = "../build"
subprojects {
    project.buildDir = "${rootProject.buildDir}/${project.name}"
}

subprojects {
    afterEvaluate { project ->
        if (project.hasProperty("android")) {
            project.android {
                if (namespace == null) {
                    namespace project.group
                }
            }
        }
    }
}

subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register("clean", Delete) {
    delete rootProject.buildDir
}
