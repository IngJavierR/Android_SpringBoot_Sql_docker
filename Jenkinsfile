pipeline {
    agent {
        label 'GrupoRoga1'
    }
    tools {
        maven 'M3'
    }
    stages {
        stage('Build Android') {
            steps {
                echo 'Building Android'
                dir ('android/'){
                    sh 'echo sdk.dir=$ANDROID_HOME > local.properties'
                    sh 'yes | $ANDROID_HOME/tools/bin/sdkmanager --licenses'
                    sh 'cp -R $ANDROID_HOME/licenses licenses/'
                    echo 'Cleanning Android'
                    sh './gradlew build clean'
                    echo 'Unit test'
                    sh './gradlew test'
                    echo 'Generating apk'
                    sh './gradlew assembleDebug'
                }
            }
        }
        stage('Build Spring') {
            steps {
                echo 'Building Webservice Spring boot'
                dir ('webservice/'){
                    sh 'mvn clean compile package'
                }
            }
        }
        stage('Running Backend') {
            steps {
                echo 'Running on Docker'
                    sh 'docker-compose up -d'
            }
        }
        stage('Liquibase') {
            steps {
                echo 'Building Webservice Spring boot'
                dir ('database/liquibase/'){
                    sh '$LIQUIBASE_PATH/liquibase --changeLogFile="changesets/db.changelog-master.xml" update'
                }
            }
        }
        stage('Expresso test') {
            when {
                not {
                    branch 'develop'
                }
            }
            steps {
                sh 'docker run --privileged -d -p 6080:6080 -p 5554:5554 -p 5555:5555 -e DEVICE=\'Samsung Galaxy S6\' --name ${BUILD_TAG} butomo1989/docker-android-x86-7.1.1'
                sh '$ANDROID_HOME/platform-tools/adb kill-server'
                sh '$ANDROID_HOME/platform-tools/adb start-server'
                sh '$ANDROID_HOME/platform-tools/adb wait-for-device shell \'while [[ -z $(getprop sys.boot_completed) ]]; do sleep 1; done\''
                dir ('android/'){
                    sh './gradlew connectedAndroidTest'
                }
                sh 'docker rm -f ${BUILD_TAG}'
            }
        }
        stage('Publish') {
            when {
                branch 'master'
            }
            steps {
                sh 'curl "https://dashboard.applivery.com/api/builds" \
                    -X POST \
                    -H "Authorization:42c00e9014a6d943c1e9fe75ed0050de253a3620" \
                    -F app="5a906745ccc70a0d57513329" \
                    -F versionName="Test version name" \
                    -F notes="Bug fixing" \
                    -F notify="true" \
                    -F os="android" \
                    -F tags="tag1" \
                    -F package=@"$WORKSPACE/android/app/build/outputs/apk/debug/app-debug.apk"'
            }
        }
    }
    post { 
        always { 
            //sh 'docker-compose down'
            deleteDir()
        }
        success {
            echo 'I succeeeded!'
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            echo 'I failed :('
        }
        changed {
            echo 'Things were different before...'
        }
    }
}