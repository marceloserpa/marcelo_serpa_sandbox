Simple example with Hystrix
==========================

##Operation

This operation receive a seconds number and it sleep during this time.
````json
http://localhost:8083/testfallback/{seconds} 
```
Hystrix fallback method was setted for activate circuite breaker when response's time exceed 5 seconds.

##Monitor

Acess http://localhost:8083/hystrix/ and put http://localhost:8083/hystrix.stream.
