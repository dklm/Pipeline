pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                sh 'deleteDir()'
                sh 'checkout scm'
            }
        }
        state('Test') {
            steps{
                sh 'mvn verify'
            }
        }
    }
}
