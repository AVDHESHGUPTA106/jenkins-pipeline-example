pipeline {
    agent any

    tools { 
      maven 'maven' 
      jdk 'Java' 
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'printenv'
                sh script: "mvn --no-transfer-progress -B -e test -Dauth0Secret=authsecret", label: 'Running smoke tests'
            }
        }
     }
    post {
       always {
          junit(
        allowEmptyResults: true,
        testResults: '*/test-reports/.xml'
      )
      }
   } 
}
