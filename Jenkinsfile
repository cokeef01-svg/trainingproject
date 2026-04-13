pipeline {
    agent any

    tools {
        jdk 'JDK'
        maven 'Maven'
    }

    parameters {
        choice(
            name: 'SUITE',
            choices: ['smoke', 'regression', 'edge-smoke'],
            description: 'Choose which TestNG suite to run'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test -Dsurefire.suiteXmlFiles=testng/${params.SUITE}.xml"
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: 'reports/*.html, screenshots/*.png', allowEmptyArchive: true
        }
    }
}