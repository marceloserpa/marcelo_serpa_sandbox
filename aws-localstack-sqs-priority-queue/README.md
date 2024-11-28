


# Create Queues

awslocal sqs create-queue --queue-name my-messages --region us-east-1

awslocal sqs create-queue --queue-name my-messages-priority --region us-east-1


zip function.zip index.mjs

awslocal lambda create-function --function-name lambda-sqs-processor --runtime nodejs20.x  --role arn:aws:iam::000000000000:role/lambda-role  --handler index.handler  --zip-file fileb://function.zip --timeout 120



awslocal sqs create-queue --queue-name my-messages


http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-messages


http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-messages

awslocal sqs get-queue-attributes --queue-url http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-messages --attribute-names QueueArn

{
    "Attributes": {
        "QueueArn": "arn:aws:sqs:us-east-1:000000000000:my-messages"
    }
}





awslocal lambda create-event-source-mapping --function-name lambda-sqs-processor --batch-size 2 --event-source-arn arn:aws:sqs:us-east-1:000000000000:my-messages



awslocal sqs send-message --queue-url http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-messages --message-body 'Hello, Marcelo!'


aws logs create-log-group --log-group-name /aws/lambda/lambda-sqs-processor

awslocal --region=eu-west-1 logs tail '/aws/lambda/lambda-sqs-processor'




watch -n 1 ''