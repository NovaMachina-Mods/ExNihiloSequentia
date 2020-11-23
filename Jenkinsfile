pipeline {
    agent any

    stages {
        stage('Clean') {
            steps{
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Upload') {
            when {
                branch '1.16'
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
        }
    }
}