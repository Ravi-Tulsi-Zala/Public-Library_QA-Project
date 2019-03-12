package com.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.library.DAOMapper.IMovieMapper;
import com.library.DAOMapperImpl.MovieMapper;
import com.library.IDAO.IMovieDAO;
import com.library.businessModels.LibraryItem;
import com.library.businessModels.Movie;
import com.library.dbConnection.DatabaseConnection;
import com.library.itemSearch.IMovieSearchRequestDetails;

public class MovieDAO implements IMovieDAO {

	private PreparedStatement preparedStatement;
	String query;
	Connection connection;
	IMovieMapper iMovieMapper = new MovieMapper();

	public MovieDAO() {

		try {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.connection = databaseConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int[] search(IMovieSearchRequestDetails requestDetails) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Movie getMovieById(int itemID) {

		Movie movie = new Movie();
		query = "SELECT * from movie WHERE Item_ID = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				movie = iMovieMapper.mapMovie(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movie;
	}

	@Override
	public Movie getMovieByTitle(String movieTitle) {

		Movie movie = new Movie();
		query = "SELECT * from movie WHERE Title LIKE ?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+movieTitle+"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				movie = iMovieMapper.mapMovie(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movie;
	}

	@Override
	public List<Movie> getMoviesByDirectorName(String directorName) {

		Movie movie = new Movie();
		query = "SELECT * from movie WHERE Director LIKE ?";
		List<Movie> moviesByDirectorName = new ArrayList<Movie>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+directorName+"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				movie = new Movie();
				movie = iMovieMapper.mapMovie(resultSet);
				moviesByDirectorName.add(movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moviesByDirectorName;
	}

	@Override
	public List<Movie> getMoviesByCategory(String category) {

		Movie movie = new Movie();
		query = "SELECT * from movie WHERE Category LIKE ?";
		List<Movie> moviesByCategory = new ArrayList<Movie>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+category+"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				movie = new Movie();
				movie = iMovieMapper.mapMovie(resultSet);
				moviesByCategory.add(movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moviesByCategory;
	}

	@Override
	public List<Movie> getMoviesByDescription(String movieDescription) {

		Movie movie = new Movie();
		query = "SELECT * from movie WHERE Description LIKE ?";
		List<Movie> moviesByDescription = new ArrayList<Movie>();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+movieDescription+"%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				movie = new Movie();
				movie = iMovieMapper.mapMovie(resultSet);
				moviesByDescription.add(movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moviesByDescription;
	}

	@Override
	public Boolean createMovie(Movie movie) {

		try {
			query = "INSERT INTO movie (Category,Title,Director,Description,Availability) VALUES (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
		
			preparedStatement.setString(1, movie.getCategory());
			preparedStatement.setString(2, movie.getTitle());
			preparedStatement.setString(3, movie.getDirector());
			preparedStatement.setString(4, movie.getDescription());
			preparedStatement.setInt(5, movie.getAvailability());
			preparedStatement.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateMovie(Movie movie) {

		try {
			query = "UPDATE movie SET Category=?,Title=?,Director=?,Description=?,Availability=? WHERE Item_ID=? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movie.getCategory());
			preparedStatement.setString(2, movie.getTitle());
			preparedStatement.setString(3, movie.getDirector());
			preparedStatement.setString(4, movie.getDescription());
			preparedStatement.setInt(5, movie.getAvailability());
			preparedStatement.setInt(6, movie.getItemID());
			preparedStatement.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deleteMovie(Movie movie) {

		try {
			query = "DELETE from movie WHERE Item_ID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, movie.getItemID());
			preparedStatement.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
