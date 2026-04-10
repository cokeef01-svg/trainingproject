pipeline {
    agent any

    tools {
        jdk 'JDK'
        maven 'Maven'
    }

    parameters {
        choice(
            name: 'SUITE',
            choices: ['smoke', 'regression'],
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
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'reports/*.html, screenshots/*.png', allowEmptyArchive: true
        }
    }
}
