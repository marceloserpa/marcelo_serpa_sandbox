package br.com.mserpa.playlist.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mserpa.playlist.service.PlaylistService;


@RestController
public class RestEndpoint {
	
	
	@Autowired
	private PlaylistService service;

	@RequestMapping(method=RequestMethod.GET, path="/playlist/v1/playlists/{id}")
	public List<String> find(@PathVariable("id") Long id) {
		return service.getPlaylist(id);
	}
	
}
