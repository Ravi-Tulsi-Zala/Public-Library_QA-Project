package com.library.additem;

import java.sql.Blob;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.ICoverDAO;

public class ItemCoverSetter implements IItemCoverSetter {

	IDAOFactory iDAOfactory;
	private boolean isCoverCreated;
	ICoverDAO coverDAO;
	private static final Logger logger = LogManager.getLogger(ItemCoverSetter.class);
	
	public ItemCoverSetter() {
		iDAOfactory = new DAOFactory();
		coverDAO = iDAOfactory.makeCoverDAO();
	}
	
	public boolean isCoverAddedToDatabase(int itemId,MultipartFile coverImage)
	{
		String originalFileName = coverImage.getOriginalFilename();
		byte[] bytes = originalFileName.getBytes();

		try {
			Blob coverBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
			String[] fileNameTokens = coverImage.getOriginalFilename().split("\\.");
			String fileExtension = fileNameTokens[fileNameTokens.length - 1];
			isCoverCreated = coverDAO.createCoverByID(itemId, coverBlob, fileExtension);
			

		} catch (SQLException e) {
			logger.log(Level.ALL,"Error in creating cover",e);
		}
		return isCoverCreated;
	}
	
}
