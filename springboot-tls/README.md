# SpringBoot TLS

First, you need to run the script generate-cert.sh to generate the private and public keys.

```shell
./generate-cert.sh
```

Look at the output of script to see the location of the certificate, you will need it to call the API.

```
mserpa@mserpa:~/Documents/repo/marcelo_serpa_sandbox/springboot-tls$ ./generate-cert.sh
'src/main/resources//keystore' not found. Creating directory
'src/main/resources//keystore' created.
created keypair src/main/resources//keystore/keystore.p12
Enter keystore password:  
Certificate stored in file <X509_certificate.cer>
exported x509 certificate. Path /home/mserpa/Documents/repo/marcelo_serpa_sandbox/springboot-tls/src/main/resources/keystore/X509_certificate.cer

```


Just call the API using your certificate ;)

```shell
curl --cacert << ..resources/keystore/X509_certificate.cer>> https://localhost:8443/hello
```

