package com.library.additem;

import org.springframework.web.multipart.MultipartFile;

import com.library.businessModels.Music;
import com.library.dao.IMusicDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;
import com.library.messages.Messages;
import com.library.routes.ILibraryFactory;
import com.library.routes.LibraryFactorySingleton;

public class AddMusicController implements IAddMusicController {

	IDAOFactory factory;
	int itemIdOfMusic;
	IMusicDAO iMusicDAO;
	ILibraryFactory iLibraryfactory;
	LibraryFactorySingleton factorySingleton;
	IItemCoverSetter coverSetter;
	boolean isMusicCreated, isMusicCoverCreated, isMusicDuplicate;;

	public AddMusicController() {
		factory = new DAOFactory();
		iMusicDAO = factory.makeMusicDAO();
		factorySingleton = LibraryFactorySingleton.instance();
		iLibraryfactory = factorySingleton.getFactory();
	}

	public Messages addMusicRecordInDatabase(Music music, MultipartFile musicCoverImage) {

		isMusicDuplicate = iMusicDAO.checkMusicDuplicacy(music);

		if (isMusicDuplicate) {
			return Messages.ERROR_DUPLICATE_MUSIC;
		}

		itemIdOfMusic = iMusicDAO.createMusic(music);
		if (itemIdOfMusic == 0) {

			return Messages.ERROR_MUSIC_CAN_NOT_BE_CREATED;

		} else {
			coverSetter = iLibraryfactory.makeItemCoverSetter();
			isMusicCoverCreated = coverSetter.isCoverAddedToDatabase(itemIdOfMusic, musicCoverImage);
			if (isMusicCoverCreated) {
				return Messages.SUCCESS_MUSIC;
			} else {
				return Messages.ERROR_MUSIC_CAN_NOT_BE_CREATED;
			}
		}

	}
}
