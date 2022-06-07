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
        stage('Get Artifacts to Build') {
            steps {
                script {
                    env.DEPLOY_AE = sh (
                        script: 'git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/ae || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen/ae || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen/generated/exnihiloae',
                        returnStatus: true
                    ) == 0
                    env.DEPLOY_API = sh (
                        script: 'git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/api',
                        returnStatus: true
                    ) == 0
                    env.DEPLOY_MAIN = sh (
                        script: 'git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/main || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen/main || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen/generated/exnihilosequentia',
                        returnStatus: true
                    ) == 0
                    env.DEPLOY_MEKANISM = sh (
                        script: 'git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/mekanism || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen/mekanism || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen/generated/exnihilomekanism',
                        returnStatus: true
                    ) == 0
                    env.DEPLOY_THERMAL = sh (
                        script: 'git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/thermal || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen/thermal || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen/generated/exnihilothermal',
                        returnStatus: true
                    ) == 0
                    env.DEPLOY_TINKERS = sh (
                        script: 'git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/tinkers || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen/tinkers || \
                                 git diff --stat --name-only ${GIT_PREVIOUS_SUCCESSFUL_COMMIT} ${GIT_COMMIT} | grep -q src/datagen/generated/exnihilotinkers',
                        returnStatus: true
                    ) == 0
                    echo "Should deploy AE: ${DEPLOY_AE}"
                    echo "Should deploy API: ${DEPLOY_API}"
                    echo "Should deploy MAIN: ${DEPLOY_MAIN}"
                    echo "Should deploy MEKANSIM: ${DEPLOY_MEKANISM}"
                    echo "Should deploy THERMAL: ${DEPLOY_THERMAL}"
                    echo "Should deploy TINKERS: ${DEPLOY_TINKERS}"
                }
            }
        }
        stage('Deploy Packages') {
            when {
                branch '1.18'
            }
            parallel {
                stage('AE') {
//                     when {
//                         expression {
//                             env.DEPLOY_AE == 'true'
//                         }
//                     }
                    steps {
                        withGradle {
                            sh './gradlew curseforge428204 publishAePublicationToMavenRepository'
                        }
                    }
                }
                stage('API') {
                    when {
                        expression {
                            env.DEPLOY_API == 'true'
                        }
                    }
                    steps {
                        withGradle {
                            sh './gradlew publishApiPublicationToMavenRepository'
                        }
                    }
                }
                stage('Main') {
                    when {
                        expression {
                            env.DEPLOY_MAIN == 'true'
                        }
                    }
                    steps {
                        withGradle {
                            sh './gradlew curseforge400012 publishMainPublicationToMavenRepository'
                        }
                    }
                }
//                 stage('Mekanism') {
//                     when {
//                         expression {
//                             env.DEPLOY_MEKANISM == 'true'
//                         }
//                     }
//                     steps {
//                         withGradle {
//                             sh './gradlew curseforge430787 publishMekanismPublicationToMavenRepository'
//                         }
//                     }
//                 }
//                 stage('Thermal') {
//                     when {
//                         expression {
//                             env.DEPLOY_THERMAL == 'true'
//                         }
//                     }
//                     steps {
//                         withGradle {
//                             sh './gradlew curseforge445226 publishThermalPublicationToMavenRepository'
//                         }
//                     }
//                 }
//                 stage('Tinkers') {
//                     when {
//                         expression {
//                             env.DEPLOY_TINKERS == 'true'
//                         }
//                     }
//                     steps {
//                         withGradle {
//                             sh './gradlew curseforge480856 publishTinkersPublicationToMavenRepository'
//                         }
//                     }
//                 }
            }
        }
    }
}
