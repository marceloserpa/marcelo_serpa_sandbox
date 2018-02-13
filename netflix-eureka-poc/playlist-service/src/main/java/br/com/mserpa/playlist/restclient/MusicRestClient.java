package br.com.mserpa.playlist.restclient;

import br.com.mserpa.playlist.db.PlaylistModel;
import br.com.mserpa.playlist.discovery.EurekaServiceDiscovery;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MusicRestClient {

    private final static String musicApplicationName = "music-service";

    @Autowired
    private EurekaServiceDiscovery serviceDiscovery;

    @Autowired
    private RestTemplate restTemplate;

    public List<String> getSongs(PlaylistModel playlistModel){
        List<String> hosts = serviceDiscovery.getHosts(musicApplicationName);
        String host = "http://" + hosts.get(0);
        return playlistModel.getMusics().stream().map(song -> {
            String resourceAddress = host + "/music/v1/musics/" + song;
            String jsonString = callRemoteService(resourceAddress);
            return buildSongName(jsonString);
        }).collect(Collectors.toList());

    }

    private String callRemoteService(String resourceAddress) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<?> request = new HttpEntity(headers);
        HttpEntity<String> response = restTemplate.exchange(resourceAddress, HttpMethod.GET, request, String.class);
        return response.getBody();
    }

    private String buildSongName(String jsonString) {
        JSONObject jsonResult = new JSONObject(jsonString);
        String author = jsonResult.getString("author");
        String title = jsonResult.getString("title");
        return author + " - " + title;
    }


}
