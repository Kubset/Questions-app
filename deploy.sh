#!/bin/bash

echo building application...

mvn package

echo uploading new version to EC2 instance...

scp -i questions.pem $echo $find target/*.jar ubuntu@ec2-52-50-211-7.eu-west-1.compute.amazonaws.com:/home/ubuntu

echo Logging on EC2 instance by pem file...

ssh -i "questions.pem" ubuntu@ec2-52-50-211-7.eu-west-1.compute.amazonaws.com './run.sh'


