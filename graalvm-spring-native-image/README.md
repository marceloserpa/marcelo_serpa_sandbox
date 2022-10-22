# Spring Native

To create the image, run the following goal:

```
$ ./gradlew bootBuildImage
```

Then, you can run the app like any other container:

```
$ docker run --rm -p 8080:8080 graalvm-spring-native-image:0.0.1-SNAPSHOT
```