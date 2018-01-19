package br.com.mserpa.music.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.mserpa.music.models.MusicModel;

@Component
public class FakeDatabase {
	
	private List<MusicModel> musics;
	
	public FakeDatabase() {
		musics = new ArrayList<MusicModel>();
		musics.add(new MusicModel(1L, "Marilyn Manson", "The beautiful people"));
		musics.add(new MusicModel(2L, "Marilyn Manson", "Tourniquet"));
		musics.add(new MusicModel(3L, "Korn", "Blind"));
		musics.add(new MusicModel(4L, "Korn", "Helmet in the bush"));
		musics.add(new MusicModel(5L, "Coal Chamber", "Loco"));
		musics.add(new MusicModel(6L, "Coal Chamber ", "Sway"));
		musics.add(new MusicModel(7L, "Rammstein", "Zerstoren"));
		musics.add(new MusicModel(8L, "Rammstein", "Zwitter"));	
	}
	
	public Optional<MusicModel> findByID(Long id){
		return musics.stream().filter(m -> m.getId() == id).findFirst();
	}

}
