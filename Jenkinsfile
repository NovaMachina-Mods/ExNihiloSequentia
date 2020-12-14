pipeline {
    agent any
    environment {
        PATH = "/usr/local/go/bin:$PATH"
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
                    return env.GIT_BRANCH == 'origin/1.16';
                }
            }
            steps {
                sh './create-release.sh'
                sh './gradlew curseforge400012'
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