package com.library.IDAO;

import java.util.List;

import com.library.businessModels.LibraryItem;
import com.library.businessModels.Music;
import com.library.search.MusicSearch;

public interface IMusicDAO {
	public Music getMusicById(int itemID);
	public List<Music> getMusicByCategory(String category);
	public int createMusic(Music music);
	public Boolean updateMusic(Music music);
	public Boolean deleteMusic(Music music); 
	public boolean checkMusicDuplicacy(Music music);
	public List<LibraryItem> getMusicBySearchTerms(MusicSearch requestDetails, String searchTerms); 
}
