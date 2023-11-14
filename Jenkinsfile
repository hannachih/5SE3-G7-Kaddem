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
                         withSonarQubeEnv(credentialsId: 'to') {
                         sh 'mvn clean package sonar:sonar '
                        }
                    }
                }
         }

         stage('Upload file to nexus'){
            steps{
                script{
                    nexusArtifactUploader artifacts: [[artifactId: 'khaddem', classifier: '', file: 'target/khaddem-4.0.jar', type: 'jar']], credentialsId: 'nexus-auth', groupId: 'tn.esprit.spring', nexusUrl: '192.168.33.10:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'Kadem', version: '4.0'
                }
            }
         }

         stage('Docker Image Build'){
            steps{
                script{
                             def imageName = "$JOB_NAME:v1.$BUILD_ID".toLowerCase()
                                        def dockerHubUsername = "hannachih"

                                        // Log in to Docker Hub
                                        withCredentials([string(credentialsId: 'git_creds', variable: 'docker_Hub')]) {
                                            sh "docker login -u $dockerHubUsername -p ${docker_Hub}"
                                        }

                                        // Build the Docker image
                                        sh "docker build -t hannachih/$imageName ."

                                        // Push the Docker image to Docker Hub
                                        sh "docker push hannachih/$imageName"

                                        // Log out from Docker Hub
                                        sh "docker logout"
                }
            }
         }

    }
}