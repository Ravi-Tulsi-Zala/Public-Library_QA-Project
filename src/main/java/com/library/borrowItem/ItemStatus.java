package com.library.borrowItem;

import com.library.businessModels.DisplayDetailed;
import com.library.businessModels.UserItem;
import com.library.dao.IBookDAO;
import com.library.dao.IMovieDAO;
import com.library.dao.IMusicDAO;
import com.library.dao.IUserItemDAO;
import com.library.daoFactory.DAOFactory;
import com.library.daoFactory.IDAOFactory;

public class ItemStatus {
	
	static final String borowed= "Borrowed";
	static final String onHold = "Reserved";
	static final String available = "Borrow";
	static final String reserve = "Reserve";
	private IUserItemDAO userItemDAO;
	
	private UserItem userItem;
	private int itemID;
	
	public ItemStatus(DisplayDetailed displayDetailed,String userEmail) {
		userItem = new UserItem();
		userItem.setTitle(displayDetailed.getTitle());
		userItem.setCategory(displayDetailed.getItemType());
		userItem.setEmail(userEmail);
		itemID = displayDetailed.getItemID();
		IDAOFactory factory = new DAOFactory();
		userItemDAO = factory.makeUserItemDAO();
	}
	
	private Boolean isItemAvailable()
	{
		IDAOFactory factory = new DAOFactory();
		Boolean availability = false;
		if(userItem.getCategory().equals("Book"))
		{
			IBookDAO bookDAO = factory.makeBookDAO();
			availability = bookDAO.getAvailability(itemID);
		}
		else if(userItem.getCategory().equals("Movie"))
		{
			IMovieDAO movieDAO = factory.makeMovieDAO();
			availability = movieDAO.getAvailability(itemID);
		}
		else if(userItem.getCategory().equals("Music"))
		{
			IMusicDAO musicDAO = factory.makeMusicDAO();
			availability = musicDAO.getAvailability(itemID);
		}
		return availability;
	}
	
	private Boolean isItemAlreadyBooked()
	{
		return userItemDAO.isItemBorrowed(userItem);
	}
	
	private Boolean isItemAlreadyOnHold()
	{
		return userItemDAO.isItemOnHold(userItem);
	}
	
	public String getItemStatus()
	{
		if(isItemAlreadyBooked())
		{
			return borowed;
		}
		else if(isItemAlreadyOnHold())
		{
			return onHold;
		}
		else if(isItemAvailable())
		{
			return available;
		}
		else
		{
			return reserve;
		}
	}
}
   