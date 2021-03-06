pipeline {
    agent any
    environment {
	    PATH = "/usr/local/go/bin:$PATH"
        GITHUB_TOKEN = credentials('github-release')
        CURSEFORGE_KEY = credentials('curseforge-token')
        NEXUS_USERNAME = credentials('nexus-username')
        NEXUS_PASSWORD = credentials('nexus-password')
        BUILD_RESULT_EMAIL = credentials('build-result-email')
    }

    stages {
        stage('Clean') {
            steps{
                sh 'chmod +x gradlew'
                sh 'chmod +x create-release.sh'
                sh './gradlew clean'
            }
        }

        stage('Build, Test, Package') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Deploy') {
            when{
                expression {
                    return env.GIT_BRANCH == '1.16';
                }
            }
            steps {
                sh './create-release.sh'
                sh './gradlew curseforge'
                sh './gradlew publish'
            }
        }
    }
    post {
        success {
            archiveArtifacts artifacts: 'build/libs/*.jar'
            mail to: env.BUILD_RESULT_EMAIL,
            subject: "Build Success: ${currentBuild.fullDisplayName}",
            body: "Build ${env.BUILD_URL} was successful."
        }
        failure {
            mail to: env.BUILD_RESULT_EMAIL,
            subject: "Build Failed: ${currentBuild.fullDisplayName}",
            body: "Build ${env.BUILD_URL} failed."
        }
    }
}
