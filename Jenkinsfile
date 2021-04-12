pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                sh 'deleteDir()'
                sh 'checkout scm'
            }
        }
        stage('Test') {
            steps{
                sh 'mvn verify'
            }
        }
    }
}
