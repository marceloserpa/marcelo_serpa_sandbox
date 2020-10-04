#!/bin/bash
set -eo pipefail
gradle -q packageLibs
mv build/distributions/aws-lambda-s3-poc.zip build/aws-lambda-s3-poc.zip