#!/bin/bash
BUILD_JAR=$(ls /home/ubuntu/project/deploy/seb39_main_003-0.0.1-SNAPSHOT.jar)
JAR_NAME=$(basename $BUILD_JAR)

rm -rf /home/ubuntu/log
mkdir /home/ubuntu/log

echo "> 현재 시간 : $(date)" >> /home/ubuntu/log/deploy.log

echo "> build 파일 명 : $JAR_NAME" >> /home/ubuntu/log/deploy.log

echo "> 현재 실행중인 애플리케이션 PID 확인 : " >> /home/ubuntu/log/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
	echo "> 현재 구동중인 애플리에키션이 없으므로 종료하지 않습니다." >> /home/ubuntu/log/deploy.log
else
	echo "> kill -15 $CURRENT_PID" >> /home/ubuntu/log/deploy.log
	sudo kill -15 $CURRENT_PID
	sleep 5
fi

echo "> JAR 파일 배포 " >> /home/ubuntu/log/deploy.log
sudo nohup java -jar $BUILD_JAR --spring.config.location=/home/ubuntu/config/application.properties >> /home/ubuntu/log/build.log 2>/home/ubuntu/log/deploy_err.log &
