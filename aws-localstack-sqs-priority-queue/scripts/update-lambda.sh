
#!/bin/bash

rm function.zip \
    && zip function.zip index.mjs

echo 'Packaged code!'

awslocal lambda update-function-code --function-name lambda-sqs-processor --zip-file fileb://function.zip
echo 'Lambda Updated!'