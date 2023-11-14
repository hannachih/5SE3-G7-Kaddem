pipeline {
    agent any

    stages{
        stage('Git Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/hannachih/5SE3-G7-Kaddem.git'
            }
        }
        stage('JUnit Testing'){
                    steps{
                        sh 'mvn test'
                    }
        }

        stage('Intergration Testing'){
                    steps{
                         sh 'mvn verify'
                    }
        }
         stage('Maven Build'){
                     steps{
                        sh 'mvn clean install'
                     }
                }
         stage('Static code analysis'){
                steps{
                    script{
                         withSonarQubeEnv(credentialsId: 'sonarAuth') {
                         sh 'mvn clean package sonar:sonar '
                        }
                    }
                }
         }

         stage('Upload file to nexus'){
            steps{
                script{
                    nexusArtifactUploader artifacts:
                    [
                        [
                            artifactId: 'khaddem',
                             classifier: '',
                              file: 'target/Kadem.jar',
                             type: 'jar'
                        ]
                   ],
                            credentialsId: 'nexus-auth',
                             groupId: 'tn.esprit.spring',
                            nexusUrl: '192.168.33.10:8081',
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                             repository: '5SE3-G7-Kadem',
                            version: '4.0'
                }
            }
         }

         stage('Docker Image Build'){
            steps{
                script{
                                def imageName = "$JOB_NAME:v1.$BUILD_ID".toLowerCase()

                               // Build the Docker image
                               sh "docker build -t $imageName ."

                               // Tag the Docker image
                               sh "docker tag $imageName hannachih/$JOB_NAME:v1.$BUILD_ID"
                               sh "docker tag $imageName hannachih/$JOB_NAME:latest"
                }
            }
         }
    }
}