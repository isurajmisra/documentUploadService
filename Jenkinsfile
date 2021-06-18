pipeline {
    environment{
    registry = "vikaspolicedockerhub/accounting-service"
    registryCredential = "Docker_Hub_Pwd"
    dockerImage = ''
  }
    agent any
    tools{
        maven "maven"
    } 
    stages {
        //stage('SCM') {
        //    steps {
        //        git branch: 'cashup', credentialsId: 'Azure-Repo-Cred', url: 'https://RestaurantOneSolution@dev.azure.com/RestaurantOneSolution/RestaurantOneSolution/_git/ROS_ACCOUNTING-SERVICE'
        //    }
        //}
        stage('Build'){                
                steps{
                    sh '''
                    cd Accounting-Service
                    mvn clean install -DskipTests=true
                    '''
            }
        }
        stage('Build image'){
            steps{
                echo "Building docker image"
                    script{
                    sh  'cd Accounting-Service'
                    sh  'dockerImage = docker.build registry + ":$BUILD_NUMBER"' 
                    }

            }
        }
        stage('Push Image'){
            steps{
                echo "Pushing docker image"
                     script{
                        docker.withRegistry('',registryCredential) {
                        dockerImage.push()
                        dockerImage.push('latest')
          }
        }

         }
        }
    }
}