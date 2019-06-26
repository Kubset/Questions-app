#!/bin/bash

echo uploading new version to EC2 instance...

scp -i questions.pem ./target/demo-0.0.1-SNAPSHOT.jar ubuntu@ec2-52-50-211-7.eu-west-1.compute.amazonaws.com:/home/ubuntu

echo Logging on EC2 instance by pem file...

ssh -i "questions.pem" ubuntu@ec2-52-50-211-7.eu-west-1.compute.amazonaws.com 'java -jar /home/ubuntu/demo-0.0.1-SNAPSHOT.jar'


