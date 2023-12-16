import groovy.json.JsonOutput
def variableMap
pipeline {
    agent any

    tools { 
      maven 'maven' 
      jdk 'Java' 
    }
    stages {
        stage('Terraform init') {
            steps {
                dir(path: 'tf-tuts') {
                sh 'terraform init'
                }
            }
        }
        stage('Terraform apply') {
            steps {
                dir(path: 'tf-tuts') {
                script {
                withCredentials([aws(credentialsId: "AWS-Jenkins-Credentials")]) {
                sh 'terraform apply --auto-approve'
                def dd_ip = sh(returnStdout: true, script: "terraform output public_ip").trim()
                def region = sh(returnStdout: true, script: "terraform output aws_region").trim()
                echo dd_ip
                echo region
                variableMap = '{"publicIp" : '+dd_ip+', "awsRegion": '+region+'}'
                }
              }
                }
            }
        }
        

        stage('Build') {
            steps {
                sh 'printenv'
                sh script: "mvn --no-transfer-progress -B -e test -Dauth0Secret=\"${variableMap}\"", label: 'Running smoke tests'
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
