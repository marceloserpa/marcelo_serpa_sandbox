package br.com.mserpa.playlist.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class FakeDatabase {

	private List<PlaylistModel> playlists;

	public FakeDatabase() {
		super();
		this.playlists = new ArrayList<>();

		List<Long> newMetal = Arrays.asList(3L, 4L, 5L, 6L);
		List<Long> industrial = Arrays.asList(1L, 2L, 7L, 8L);
		List<Long> german  = Arrays.asList(7L, 8L);
		
		playlists.add(new PlaylistModel(1L, newMetal));
		playlists.add(new PlaylistModel(2L, industrial));
		playlists.add(new PlaylistModel(3L, german));
	}

	public Optional<PlaylistModel> findByID(Long id){
		return playlists.stream().filter(p -> p.getId() == id).findFirst();
	}
	
}
