# SpringBoot + KeyCloak

Configure the keycloack: 

1. create a healm
2. create a client
3. edit the properties: AccessType=confidential, Service Account: Enabled, Authorization Enabled, Valid Redirect
4. access credentials
5. get the token
```shell
curl -X POST \
http://localhost:9010/auth/realms/security-api/protocol/openid-connect/token \
-H 'Content-Type: application/x-www-form-urlencoded' \
-d 'grant_type=client_credentials&client_id=marcelo-corp&client_secret=<<your client secret>>'
```

Access the API

```shell
curl -X GET 'localhost:8080/books/hello' \
-H 'Authorization: Bearer <<YOUR ACCESS TOKEN>>' 
```


Adding Roles:

Create client roles Admin & Member:
Clients >> marcelo-corp >>  Roles >> Add Role


Create Healm roles app-admin & app-member


click "Composite Roles"
select your client, in my situation 'marcelo-corp', choose Admin or Member.


Create User
- name = user1
- click email verified
- switch to tab credentials ... set password '123456'
- switch to tab 'role mappings', choose admin
- remove all information in "Required User Actions"

Create other user with member role