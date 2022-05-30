pipeline {
    agent any
    environment {
        NEXUS_USERNAME = credentials('MavenUser')
        NEXUS_PASSWORD = credentials('MavenPassword')
        CURSEFORGE_KEY = credentials('CurseForgeAPIKey')
    }
    options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                withGradle {
                    sh './gradlew clean build'
                }
            }
        }
        stage('SonarQube Analysis') {
          steps {
            withSonarQubeEnv(installationName: "SonarQube Integration", credentialsId: 'SonarQube Admin Token') {
              sh "./gradlew sonarqube"
            }
          }
        }
        stage("Quality Gate") {
          steps {
            timeout(time: 1, unit: 'HOURS') {
              // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
              // true = set pipeline to UNSTABLE, false = don't
              waitForQualityGate(webhookSecretId: 'SonarQubeWebhookSecret', abortPipeline: true)
            }
          }
        }
        stage('Main') {
            when {
                branch '1.16'
            }
            steps {
                withGradle {
                    sh './gradlew curseforge400012 publishMainPublicationToMavenRepository'
                }
            }
        }
    }
}