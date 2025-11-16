pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo "Obteniendo código del repositorio..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Compilando el proyecto..."
                sh 'mvn clean package -DskipTests=false'
            }
        }

        stage('Test') {
            steps {
                echo "Ejecutando tests..."
                sh 'mvn test'
            }

            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    if (isUnix()) {
                        echo "Detectado sistema Mac/Linux — ejecutando deploy_mac.sh"
                        sh 'chmod +x scripts/deploy_mac.sh'
                        sh './scripts/deploy_mac.sh'
                    } else {
                        echo "Detectado sistema Windows — ejecutando deploy_windows.ps1"
                        bat 'powershell -ExecutionPolicy Bypass -File scripts/deploy_windows.ps1'
                    }
                }
            }
        }
    }

    post {
        success { echo "Pipeline completado exitosamente ✔" }
        failure { echo "El pipeline falló " }
    }
}
