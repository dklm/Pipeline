pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                sh 'deleteDir()'
                checkout scm
            }
        }
        stage('Test') {
            steps{
                sh 'mvn verify'
            }
        }
    }
}
