package com.library.IBussinessModelSetter;

import java.sql.ResultSet;
import java.util.List;

import com.library.businessModels.Music;

public interface IMusicSetter {
	public List<Music> mapMusic(ResultSet resultSet);
}
