#!/bin/bash

cd ../lambda-processor/

rm function.zip \
    && zip -r function.zip .

echo 'Packaged code!'

awslocal lambda update-function-code --function-name lambda-sqs-processor --zip-file fileb://function.zip
echo 'Lambda Updated!'