pipeline {
    agent any
    environment {
        PATH = "/usr/local/go/bin:/home/pi/go/bin:$PATH"
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
            steps {
                sh './create-release.sh'
                sh './gradlew curseforge400012'
            }
        }
    }
    post {
        success {
            archiveArtifacts artifacts: 'build/libs/*.jar'
        }
    }
}