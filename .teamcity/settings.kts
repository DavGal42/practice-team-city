import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.python
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2025.07"

project {

    buildType(MyBuild)
}

object MyBuild : BuildType({
    name = "MyBuild"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "greeting"
            id = "greeting"
            scriptContent = """echo "Hello From TeamCity!""""
        }
        python {
            name = "Run simple python script"
            id = "Run_simple_python_script"
            command = file {
                filename = "main.py"
            }
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})
