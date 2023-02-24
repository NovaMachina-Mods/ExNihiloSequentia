import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubIssues

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
        text("git_main_branch", "1.19", label = "Git Main Branch", description = "The git main or default branch to use in VCS operations.", display = ParameterDisplay.HIDDEN, allowEmpty = false)
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

    vcs {
        root(DslContext.settingsRoot)

        branchFilter = "+:%git_main_branch%"
    }
    
    disableSettings("RUNNER_3")
})

object BuildPullRequests : BuildType({
    templates(AbsoluteId("NovaMachinaMods_DiscordNotify"), AbsoluteId("NovaMachinaMods_BuildMainBranches"), AbsoluteId("NovaMachinaMods_Publish"))
    name = "Build Pull Requests"

    vcs {
        root(DslContext.settingsRoot)
    }
    
    disableSettings("RUNNER_3")
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
