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
                sh 'mvn -B clean package'
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
