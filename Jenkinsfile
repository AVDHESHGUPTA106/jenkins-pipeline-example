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
                variableMap = [publicIp : '1.1.1.10.1', awsRegion:'asdfg']
                env.GIT_REPO_NAME = env.GIT_URL.replaceFirst(/^.*?(?::\/\/.*?\/|:)(.*).git$/, '$1')
                env.GIT_ORG_NAME =env.GIT_REPO_NAME.tokenize('/').first()
                env.GIT_SERVICE_NAME =env.GIT_REPO_NAME.tokenize('/').last()
                gitMetaData = gitMetaData(env.GIT_URL)
                env.avdhesh = gitMetaData
                def ex = "test -Dauth0Secret=${variableMap.publicIp} -DawsRegion=${variableMap.awsRegion}"
                sh 'printenv'
                runMaven(ex, 'Running smoke tests')
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
        echo label
        sh script: "mvn --no-transfer-progress -B -e ${steps}", label: label
}

String gitMetaData(final String giturl){

    def gitOrgRepoName = giturl.replaceFirst(/^.*?(?::\/\/.*?\/|:)(.*).git$/, '$1')
    def gitOrgName = gitOrgRepoName.tokenize('/').first()
    def gitRepoName = gitOrgRepoName.tokenize('/').last()
    map = [gitOrg:gitOrgName, gitRepo:gitRepoName]

    return map
}
