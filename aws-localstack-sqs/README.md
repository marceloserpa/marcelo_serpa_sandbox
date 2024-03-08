# SNS + SQS LocalStack

## Commands


awslocal start

awslocal sqs create-queue --queue-name local-queue --region us-east-1


awslocal sns create-topic --name local-topic --region us-east-1 



awslocal sqs get-queue-attributes --queue-url http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/local-queue --attribute-names All



awslocal sns subscribe --notification-endpoint arn:aws:sqs:us-east-1:000000000000:local-queue --topic-arn arn:aws:sns:us-east-1:000000000000:local-topic --protocol sqs --region us-east-1
