package br.com.mserpa.playlist.db;

import java.util.List;

public class PlaylistModel {
	
	private Long id;
	private List<Long> musics;
	
	public PlaylistModel() {
		super();
	}
	public PlaylistModel(Long id, List<Long> musics) {
		super();
		this.id = id;
		this.musics = musics;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getMusics() {
		return musics;
	}
	public void setMusics(List<Long> musics) {
		this.musics = musics;
	}
	
	

}
