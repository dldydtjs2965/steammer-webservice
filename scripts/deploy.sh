#!/usr/bin/env bash

REPOSITORY=/home/ec2-user/app/buildfile
PROJECT_NAME=steammer-webservice

echo "> 프로젝트 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동중인 어플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl steammer-webservice | grep jar | '{print $1}')

echo "현재 구동 중인 어플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID"]; then
  echo ">  현재 구동중인 어플리케이션이 없으므로 종료하지 않겠습니다"
else
  echo "> kill -15 ${CURRENT_PID}"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR_Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
  -Dspring.config.location=classpath:/home/ec2-user/application.properties,/home/ec2-user/app/properties/application-oauth.properties \
  -Dspring.profiles.active=default \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &