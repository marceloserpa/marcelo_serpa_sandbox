#!/bin/bash

CLASSPATH=src/main/resources/

KEYSTORE_PATH=$CLASSPATH/keystore

if [ -d "$KEYSTORE_PATH" ]; then
   echo "'$KEYSTORE_PATH' found "
else
   echo "'$KEYSTORE_PATH' not found. Creating directory"
   mkdir $CLASSPATH/keystore
   echo "'$KEYSTORE_PATH' created."
fi


KEYSTORE_FILE=$KEYSTORE_PATH/keystore.p12

if [ -f "$KEYSTORE_FILE" ]; then
   echo "'$KEYSTORE_FILE' found "
else
    cd $KEYSTORE_PATH
   
    keytool  -genkeypair \
            -alias marceloserpa-tls-poc \
            -keyalg RSA \
            -keysize 4096 \
            -validity 3650 \
            -dname "CN=localhost" \
            -keypass changeit \
            -keystore keystore.p12 \
            -storeType PKCS12 \
            -storepass changeit
    echo "created keypair $KEYSTORE_FILE"

    keytool -export -alias marceloserpa-tls-poc \
            -keystore keystore.p12 \
            -rfc -file X509_certificate.cer

    path=$(pwd)/X509_certificate.cer

    echo "exported x509 certificate. Path $path"

fi






