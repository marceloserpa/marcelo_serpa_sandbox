package br.com.mserpa.playlist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	private EurekaServiceDiscovery eurekaServiceDiscovery;

	public List<String> getPlaylist(Long id){
		System.out.println(">>>>>>>>>>>>>>>>");
		Optional<PlaylistModel> playlist = database.findByID(id);
		
		List<String> hosts = eurekaServiceDiscovery.getHosts("music-service");

		return new ArrayList<>();
	}
	
	
}
