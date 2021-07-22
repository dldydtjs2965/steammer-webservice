#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/webservice
PROJECT_NAME=steammer-webservice

cd $REPOSITORY/$PROJECT_NAME/

echo "> Git Pull"

git pull

echo "> 프로젝트 빌드 시작"

./gradlew build -x test

echo "> webservice 디렉토리로 이동"

cd $REPOSITORY

echo "> Build 파일 복사"

cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY

echo "> 현재 구동중인 어플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

echo "현재 구동 중인 어플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID"]; then
  echo ">  현재 구동중인 어플리케이션이 없으므로 종료하지 않겠습니다"
else
  echo "> kill -15 ${CURRENT_PID}"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새어플리케이션 배포"

JAR_NANME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR_Name: $JAR_NANME"

nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &