# Hello GraalVM


Install native-image dependencies

```
gu install native-image
sudo apt-get install build-essential libz-dev zlib1g-dev
```

Compile and run using JVM

```
javac HelloWorld.java
java HelloWorld
```

Compile and run using Graalvm native image

```
javac HelloWorld.java
native-image HelloWorld
./helloworld
```
