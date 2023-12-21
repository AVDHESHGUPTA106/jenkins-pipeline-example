#!/usr/bin/groovy

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
//        stage('Terraform apply') {
//            steps {
//                dir(path: 'tf-tuts') {
//                script {
//                withCredentials([aws(credentialsId: "AWS-Jenkins-Credentials")]) {
//                    sh(
//                            script: """
//                                        terraform apply --auto-approve
//                                    """,
//                            label: "Deploying service layer in AWS"
//                    )
//                def dd_ip = sh(returnStdout: true, script: "terraform output public_ip").trim()
//                def region = sh(returnStdout: true, script: "terraform output aws_region").trim()
//                echo dd_ip
//                echo region
//                variableMap = [publicIp : dd_ip, awsRegion:region]
//                }
//              }
//                }
//            }
//        }
        
//        stage('Terraform destroy') {
//            steps {
//                script {
//                withCredentials([aws(credentialsId: "AWS-Jenkins-Credentials")]) {
//                sh 'terraform destroy --auto-approve'
//                }
//              }
//            }
//        }

        stage('Build') {
            steps {
                script {
                variableMap = [publicIp : "10.10.10.10", awsRegion:"us-east-1"]
                sh 'printenv'
                runMaven('test -Dauth0Secret=$variableMap.publicIp -DawsRegion=$variableMap.awsRegion', 'Running smoke tests')
                //sh script: "mvn --no-transfer-progress -B -e test -Dauth0Secret=${variableMap.publicIp} -DawsRegion=${variableMap.awsRegion}", label: 'Running smoke tests'
            }
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

String runMaven(final String steps, final String label) {
        sh script: "mvn --no-transfer-progress -B -e ${steps}", label: label
}
