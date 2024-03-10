
#!/bin/bash

MAIN_QUEUE_NAME=my-messages-main
PRIORITY_QUEUE_NAME=my-messages-priority

cd ../lambda-processor/

rm function.zip \
    && zip -r function.zip .

echo 'Packaged code!'

awslocal lambda create-function --function-name lambda-sqs-processor --runtime nodejs20.x --role arn:aws:iam::000000000000:role/lambda-role --handler index.handler --zip-file fileb://function.zip      

echo 'Lambda Created!'

awslocal sqs create-queue --queue-name $MAIN_QUEUE_NAME --region us-east-1

echo 'Main queue created!'

awslocal lambda create-event-source-mapping --function-name lambda-sqs-processor --batch-size 2 --event-source-arn arn:aws:sqs:us-east-1:000000000000:$MAIN_QUEUE_NAME

echo 'Event Source Mapping created!'

awslocal sqs create-queue --queue-name $PRIORITY_QUEUE_NAME --region us-east-1

echo 'Priority queue created!'

awslocal lambda create-event-source-mapping --function-name lambda-sqs-processor --batch-size 2 --event-source-arn arn:aws:sqs:us-east-1:000000000000:$PRIORITY_QUEUE_NAME

echo 'Event Source Mapping created!'

