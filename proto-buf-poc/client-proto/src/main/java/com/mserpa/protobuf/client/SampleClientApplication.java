package com.mserpa.protobuf.client;


import com.mserpa.protobuf.client.compiledproto.Protos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class SampleClientApplication implements CommandLineRunner{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        //Create proto message
        Protos.MessageCard firstMessage = Protos.MessageCard.newBuilder().setText("Lorem Ipsum").setUser("First").build();
        //Post proto message
        restTemplate.postForEntity("http://localhost:9000/books", firstMessage, Protos.MessageCard.class);

        //Create other proto message
        Protos.MessageCard secondMessage = Protos.MessageCard.newBuilder().setText("Test").setUser("Second").build();
        //Post other proto message
        restTemplate.postForEntity("http://localhost:9000/books", secondMessage, Protos.MessageCard.class);

        HttpHeaders headers = new HttpHeaders();
        //Set octet-stream header to request
        headers.add("accept", "application/octet-stream");
        //GET messageList from Api
        ResponseEntity<Protos.MessageList> response = restTemplate.getForEntity("http://localhost:9000/books", Protos.MessageList.class);
        System.out.print("Status Code: ");
        System.out.println(response.getStatusCode());
        Protos.MessageList messageListResponse = response.getBody();

        if(messageListResponse.getMessagesList() != null)
            messageListResponse.getMessagesList().stream().forEach(x -> System.out.println(x.getUser()));

    }

    public static void main(String[] args){
        SpringApplication.run(SampleClientApplication.class, args);
    }

}
