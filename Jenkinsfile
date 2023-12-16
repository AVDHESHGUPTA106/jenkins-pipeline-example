pipeline {
    agent any

    tools { 
      maven 'MAVEN_HOME' 
      jdk 'JAVA_HOME' 
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
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
