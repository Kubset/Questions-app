#!/bin/bash

echo building application...

mvn package

echo uploading new version to EC2 instance...

scp -i questions.pem $echo $find target/*.jar ubuntu@52.215.25.97:/home/ubuntu

echo Logging on EC2 instance by pem file...

ssh -i "questions.pem" ubuntu@52.215.25.97 './run.sh'


