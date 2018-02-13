package br.com.mserpa.playlist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.mserpa.playlist.restclient.MusicRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mserpa.playlist.db.FakeDatabase;
import br.com.mserpa.playlist.db.PlaylistModel;
import br.com.mserpa.playlist.discovery.EurekaServiceDiscovery;

@Component
public class PlaylistService {
	
	@Autowired
	private FakeDatabase database;
	
	@Autowired
	private MusicRestClient musicRestClient;

	public List<String> getPlaylist(Long id){
		return database.findByID(id)
			.map(playlist -> musicRestClient.getSongs(playlist))
			.orElse(new ArrayList<>());
	}
	
	
}
