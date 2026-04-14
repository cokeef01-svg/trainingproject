pipeline {
    agent any

    tools {
        // Use the JDK and Maven tools configured in Jenkins
        jdk 'JDK'
        maven 'Maven'
    }

    parameters {
        // Choose which TestNG suite file to run
        choice(
            name: 'SUITE',
            choices: ['smoke', 'regression', 'edge-smoke', 'cucumber'],
            description: 'Choose which TestNG suite to run'
        )

        // Used for Cucumber tag filtering
        string(
            name: 'TAGS',
            defaultValue: '@smoke',
            description: 'Cucumber tag expression'
        )

        // Browser used by Cucumber Hooks
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'edge'],
            description: 'Browser to use for Cucumber runs'
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
                // Run selected suite
                // TAGS applies mainly to cucumber suite
                // BROWSER is used by Cucumber Hooks through system property
                bat "mvn clean test -Dsurefire.suiteXmlFiles=testng/${params.SUITE}.xml -Dcucumber.filter.tags=\"${params.TAGS}\" -Dbrowser=${params.BROWSER}"
            }
        }
    }

    post {
        always {
            // Publish Surefire / TestNG results
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true

            // Archive reports and screenshots if they exist
            archiveArtifacts artifacts: 'reports/*.html, screenshots/*.png', allowEmptyArchive: true
        }
    }
}