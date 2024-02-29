#!/bin/bash

rm lambda/function.zip 

zip lambda/function.zip lambda/index.js

awslocal lambda create-function \
    --function-name localstack-lambda-url-example \
    --runtime nodejs18.x \
    --zip-file fileb://lambda/function.zip \
    --handler index.handler \
    --role arn:aws:iam::000000000000:role/lambda-role

awslocal lambda create-function-url-config --function-name localstack-lambda-url-example --auth-type NONE