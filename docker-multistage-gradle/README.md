# Docker multi-stage POC

This project is using multi-stage of docker to create an image with a minimal size.

State 1 will build a .jar from springboot application

State 2 will copy the .jar built before and run it