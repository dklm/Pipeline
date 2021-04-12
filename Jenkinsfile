pipeline {
    agent any
    stages {
        stage('Hello Pipeline'){
            steps{
                sh 'echo Hello Pipeline!'
            }
        }
        stage('Checkout') {
            steps {
                deleteDir()
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
