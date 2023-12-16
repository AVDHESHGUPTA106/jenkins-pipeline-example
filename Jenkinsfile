def variables
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
                variables['dd_ip'] = dd_ip
                variables['region'] = region
                }
              }
                }
            }
        }
        stage('Terraform destroy') {
            steps {
                dir(path: 'tf-tuts') {
                script {
                withCredentials([aws(credentialsId: "AWS-Jenkins-Credentials")]) {
                sh 'terraform destroy --auto-approve'
                }
              }
                }
            }
        }

        stage('Build') {
            steps {
                sh 'printenv'
                sh script: "mvn --no-transfer-progress -B -e test -Dauth0Secret=${variables}", label: 'Running smoke tests'
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
