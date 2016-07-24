About this sample
====================

This project is a sample using Feign client and Hystrix circuit breaker.

First of all you need run the projects fake-api and feign-hystrix-client-app.

Now you can test the service using the HTTP request sample: 

**Add new product**
<br />
GET localhost:9000/client/products/

{
    "id": 1,
    "name" : “My book sample",
    "price" : 2
}

**List all product**
<br />
POST localhost:9000/client/products/

Activating circuit breaker
====================

When the fake-api is down the circuit breakers are open.

Monitor
================

Access http://localhost:9000/hystrix/ and put http://localhost:9000/hystrix.stream.

:)
