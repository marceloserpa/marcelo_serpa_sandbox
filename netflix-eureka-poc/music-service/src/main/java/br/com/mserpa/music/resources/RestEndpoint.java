package br.com.mserpa.music.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mserpa.music.discovery.EurekaServiceDiscovery;
import br.com.mserpa.music.models.MusicModel;


@RestController
public class RestEndpoint {
	
	@Autowired
	private EurekaServiceDiscovery eurekaServiceDiscovery;
	
	@Autowired
	private EmbeddedWebApplicationContext webApplicationContext;

	@RequestMapping(method=RequestMethod.GET, path="/music/v1/musics/")
	public List<MusicModel> getAll() {
		return new ArrayList<MusicModel>();
	}
	
}
