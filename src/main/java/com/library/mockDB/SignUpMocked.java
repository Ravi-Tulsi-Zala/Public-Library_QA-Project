package com.library.mockDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.signUp.IUserBasicInfo;
import com.library.signUp.IUserExtendedInfo;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;

public class SignUpMocked {
	public String fullName;
	public String phoneNumber;
	public String email;
	public String password;
	public String cpassword;
	List arrItems;
	private IUserBasicInfo userBasicInfo;
	private IUserExtendedInfo userExtendedInfo;
	Map map;
	
	public SignUpMocked() {
		userBasicInfo = new UserBasicInfo();
		userExtendedInfo = new UserExtendedInfo();
	}

	public Map getCorruptMockData() {
		arrItems = new ArrayList<String>();
		map = new HashMap<String, ArrayList>();
		userBasicInfo.setEmail("devanshu0101@gmail.com");
		userBasicInfo.setPwd("1qaz!QAZ");
		userExtendedInfo.setCPassword("1qazZAQ!");
		userExtendedInfo.setFullname("devanshu sriv");
		userExtendedInfo.setPhone("902");
		arrItems.add(userBasicInfo);
		arrItems.add(userExtendedInfo);
		map.put("corrupt-data", arrItems);
		return map;
	}

	public Map getMockData() {
		arrItems = new ArrayList<String>();
		map = new HashMap<String, ArrayList>();
		userBasicInfo.setEmail("devanshu1@gmail.com");
		userBasicInfo.setPwd("123456789");
		userExtendedInfo.setCPassword("123456789");
		userExtendedInfo.setFullname("deva sriv");
		userExtendedInfo.setPhone("9024031714");
		arrItems.add(userBasicInfo);
		arrItems.add(userExtendedInfo);
		map.put("clean-data", arrItems);
		return map;
	}

}