pipeline {
    agent any
    triggers {
        pollSCM('H/5 * * * *')
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub') 
        IMAGE_NAME_SERVER = 'ines_chaouch/gestion-server'  
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/InesChaouch/gestion.git' 
            }
        }
        stage('Build Server Image') {
            steps {
                script {
                    dockerImageServer = docker.build("${IMAGE_NAME_SERVER}")
                }
            }
        }
        stage('Scan Server Image') {
            steps {
                script {
                    sh """
                    docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
                    aquasec/trivy:latest image --timeout 10m --exit-code 0 --severity LOW,MEDIUM,HIGH,CRITICAL \
                    ${IMAGE_NAME_SERVER}
                    """
                }
            }
        }
        stage('Push Server Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKERHUB_CREDENTIALS}") {
                        dockerImageServer.push()
                    }
                }
            }
        }
    }
}
