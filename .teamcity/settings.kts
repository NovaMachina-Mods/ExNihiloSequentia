import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubIssues
import jetbrains.buildServer.configs.kotlin.triggers.VcsTrigger
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

version = "2022.10"

project {

    buildType(BuildPullRequests)
    buildType(Build)
    buildType(BuildSecondaryBranches)

    params {
        text("docker_jdk_version", "17", label = "Docker Java Version", description = "Version of Java to use in docker", display = ParameterDisplay.HIDDEN, allowEmpty = false)
        text("git_main_branch", "1.19", label = "Git Main Branch", description = "The git main or default branch to use in VCS operations.", display = ParameterDisplay.HIDDEN, allowEmpty = false)
        text("docker_gradle_version", "7.6", label = "Docker Gradle Version", description = "Version of gradle to use in docker", display = ParameterDisplay.HIDDEN, allowEmpty = false)
        text("github_repository_name", "ExNihiloSequentia", label = "The github repository name. Used to connect to it in VCS Roots.", description = "This is the repository slug on github. So for example `ExNihiloSequentia` or `ExNihiloMekanism`. It is interpolated into the global VCS Roots.", display = ParameterDisplay.HIDDEN, allowEmpty = false)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_3"
            displayName = "NovaMachina-Mods/ExNihiloSequentia"
            repositoryURL = "https://github.com/NovaMachina-Mods/ExNihiloSequentia"
        }
    }
}

object Build : BuildType({
    templates(AbsoluteId("NovaMachinaMods_DiscordNotify"), AbsoluteId("NovaMachinaMods_BuildMainBranches"), AbsoluteId("NovaMachinaMods_Publish"))
    name = "Build"
    description = "Builds and Publishes the main branches of the project."

    allowExternalStatus = true

    vcs {
        root(DslContext.settingsRoot)
    }

    features {
        dockerSupport {
            id = "DockerSupport"
            loginToRegistry = on {
                dockerRegistryId = "PROJECT_EXT_4"
            }
        }
    }
    
    disableSettings("RUNNER_3")
})

object BuildPullRequests : BuildType({
    templates(AbsoluteId("NovaMachinaMods_BuildPullRequests"), AbsoluteId("NovaMachinaMods_DiscordNotify"), AbsoluteId("NovaMachinaMods_BuildMainBranches"))
    name = "Build Pull Requests"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            name = "Publish"
            id = "RUNNER_3"
            enabled = false
            tasks = "publishCurseForge publishMainPublicationToMavenRepository"
            buildFile = "build.gradle"
            dockerImage = "gradle:7.6-jdk17"
            dockerRunParameters = "-u root -v /home/buildagent/.gradle:/home/gradle/.gradle"
        }
        stepsOrder = arrayListOf("RUNNER_7", "RUNNER_8", "RUNNER_4", "RUNNER_3", "RUNNER_1", "RUNNER_5", "RUNNER_2")
    }

    triggers {
        vcs {
            id = "TRIGGER_2"
            quietPeriodMode = VcsTrigger.QuietPeriodMode.USE_CUSTOM
            quietPeriod = 120
        }
    }

    features {
        pullRequests {
            id = "BUILD_EXT_2"
            vcsRootExtId = "${DslContext.settingsRoot.id}"
            provider = github {
                authType = token {
                    token = "credentialsJSON:4cf444c6-a208-41d9-a86a-0ef855551934"
                }
                filterAuthorRole = PullRequests.GitHubRoleFilter.EVERYBODY
            }
        }
    }
})

object BuildSecondaryBranches : BuildType({
    templates(AbsoluteId("NovaMachinaMods_ExcludeDefaultBranch"), AbsoluteId("NovaMachinaMods_DiscordNotify"), AbsoluteId("NovaMachinaMods_BuildMainBranches"))
    name = "Build Secondary Branches"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            name = "Publish"
            id = "RUNNER_3"
            tasks = "publishCurseForge publishMainPublicationToMavenRepository"
            buildFile = "build.gradle"
            dockerImage = "gradle:7.6-jdk17"
            dockerRunParameters = "-u root -v /home/buildagent/.gradle:/home/gradle/.gradle"
        }
        stepsOrder = arrayListOf("RUNNER_4", "RUNNER_7", "RUNNER_8", "RUNNER_3", "RUNNER_1", "RUNNER_5", "RUNNER_2")
    }
})
