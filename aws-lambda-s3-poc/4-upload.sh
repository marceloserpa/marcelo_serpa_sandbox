#!/bin/bash
set -eo pipefail
BUCKET=$(aws cloudformation describe-stack-resource --stack-name aws-lambda-s3-poc --logical-resource-id bucket --query 'StackResourceDetail.PhysicalResourceId' --output text)
aws s3 cp files/POC.1.txt s3://$BUCKET/inbound/
