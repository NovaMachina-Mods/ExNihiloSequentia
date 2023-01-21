pipeline {
    agent {
      label 'master'
    }
    environment {
        NEXUS_USERNAME = credentials('MavenUser')
        NEXUS_PASSWORD = credentials('MavenPassword')
        CURSEFORGE_KEY = credentials('CurseForgeAPIKey')
        DISCORD_WEBHOOK_URL = credentials('discord-webhook-url')
        DISCORD_PREFIX = "Job: ExNihiloSequentia Branch: ${BRANCH_NAME} Build: #${BUILD_NUMBER}"
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage('Notify Start') {
            when {
                not {
                    changeRequest()
                }
            }
            steps {
                discordSend(
                    title: "${DISCORD_PREFIX} Started",
                    successful: true,
                    result: 'ABORTED',
                    webhookURL: DISCORD_WEBHOOK_URL
                )
            }
        }
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                withGradle {
                    sh './gradlew clean build'
                }
            }
        }
        stage('Should Deploy Artifact') {
            steps {
                script {
                    env.SHOULD_DEPLOY = sh (
                        script: 'git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/main || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen',
                        returnStatus: true
                    ) == 0
                    echo "Should deploy artifact: ${SHOULD_DEPLOY}"
                }
            }
        }
        stage('Deploy Packages') {
            when {
                allOf {
                    branch '1.19';
                    expression {
                        env.SHOULD_DEPLOY == 'true'
                    }
                }
            }
            steps {
                withGradle {
                    sh './gradlew publishCurseForge publishMainPublicationToMavenRepository'
                }
            }
        }
    }
    post {
        always {
            script {
                if(env.CHANGE_ID == null) {
                    discordSend(
                        title: "${DISCORD_PREFIX} Finished ${currentBuild.currentResult}",
                        successful: currentBuild.resultIsBetterOrEqualTo("SUCCESS"),
                        result: currentBuild.currentResult,
                        webhookURL: DISCORD_WEBHOOK_URL
                    )
                }
            }
        }
    }
}
