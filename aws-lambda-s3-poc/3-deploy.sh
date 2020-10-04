#!/bin/bash
set -eo pipefail
ARTIFACT_BUCKET=$(cat bucket-name.txt)
aws s3 cp files/POC.txt s3://$ARTIFACT_BUCKET/inbound/POC.txt
TEMPLATE=template.yml

gradle build -i

aws cloudformation package --template-file $TEMPLATE --s3-bucket $ARTIFACT_BUCKET --output-template-file out.yml
aws cloudformation deploy --template-file out.yml --stack-name aws-lambda-s3-poc --capabilities CAPABILITY_NAMED_IAM
