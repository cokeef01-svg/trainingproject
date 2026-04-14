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

        // Used only for Cucumber runs
        // Examples: @smoke   @regression   @smoke and not @ignore
        string(
            name: 'TAGS',
            defaultValue: '@smoke',
            description: 'Cucumber tag expression'
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
                // Run the selected suite
                // TAGS is mainly used when SUITE = cucumber
                bat "mvn clean test -Dsurefire.suiteXmlFiles=testng/${params.SUITE}.xml -Dcucumber.filter.tags=\"${params.TAGS}\""
            }
        }
    }

    post {
        always {
            // Publish TestNG / Surefire results
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true

            // Archive HTML reports and screenshots if they exist
            archiveArtifacts artifacts: 'reports/*.html, screenshots/*.png', allowEmptyArchive: true
        }
    }
}