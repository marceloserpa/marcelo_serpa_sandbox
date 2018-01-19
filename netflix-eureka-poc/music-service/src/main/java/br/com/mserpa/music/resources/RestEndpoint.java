package br.com.mserpa.music.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mserpa.music.db.FakeDatabase;
import br.com.mserpa.music.models.MusicModel;


@RestController
public class RestEndpoint {
	
	@Autowired
	private FakeDatabase database;
	
	@RequestMapping(method=RequestMethod.GET, path="/music/v1/musics/{id}")
	public MusicModel getAll(@PathVariable("id") Long id) {
		return database.findByID(id).orElseThrow(() -> new RuntimeException("Not found"));
	}
	
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RuntimeException.class)
    public void handleMusicNotFound() {
       
    }
	
}
