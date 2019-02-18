package com.library.buisness;

import java.util.Date;

public interface IUserItem {
	
	public String getItemId();
	public Date getTimeStamp();
	public boolean isBorrowed();
	public void setIsBorowed();
	public boolean isOnHold();
	
}
