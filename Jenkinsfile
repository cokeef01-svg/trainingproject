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
