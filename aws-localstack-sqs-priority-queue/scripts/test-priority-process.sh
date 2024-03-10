#!/bin/bash

PRIORITY_QUEUE_URL=http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-messages-priority
MAIN_QUEUE_URL=http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-messages-main
MESSAGES_PER_BATCH=10
MESSAGE_BODY="Normal message for Marcelo!"

send_batch() {
  local messages=()
  for ((i=1; i <= $MESSAGES_PER_BATCH; i++)); do
    messages+=("{\"Id\":\"message_$i\",\"MessageBody\":\"main $i - $MESSAGE_BODY\"}")
  done

  echo messages

  awslocal sqs send-message-batch --queue-url $MAIN_QUEUE_URL --entries "${messages[@]}"
}


send_batch

awslocal sqs send-message --queue-url $PRIORITY_QUEUE_URL --message-body "PRIORITY #? = Hello, Marcelo! Priority message"