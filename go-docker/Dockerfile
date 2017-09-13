
FROM golang:alpine

RUN mkdir /app_folder

WORKDIR /app_folder

COPY . /app_folder

EXPOSE 8080:8080

RUN go build server.go

CMD ["./server" ]