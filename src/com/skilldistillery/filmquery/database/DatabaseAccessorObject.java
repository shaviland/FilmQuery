package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static String user = "student";
	private static String password = "student";
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static Connection conn;
	static {
		try {
			conn = DriverManager.getConnection(URL, user, password);

			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {

		Film film = null;

		String sql = "SELECT film.*, language.name, category.name FROM film \n" + 
				"JOIN language ON language.id = film.language_id \n" + 
				"JOIN film_category ON film.id = film_category.film_id \n" + 
				"JOIN category ON film_category.category_id = category.id  \n" + 
				"WHERE film.id = ?";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();

			while (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageID(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setLanguage(rs.getString("name"));
				film.setCategory(rs.getString("name"));
				film.setActors(findActorsByFilmId(film.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return film;

		}
		return film;

	}
	
		
	

	public Actor findActorById(int actorId) {
	
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		Actor actor = new Actor();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				actor.setId(rs.getInt(1));
				actor.setFirstName(rs.getString(2));
				actor.setLastName(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		try {
			String sql = "SELECT id, first_name, last_name \n" + 
					"FROM actor JOIN film_actor \n" + 
					"ON actor.id = film_actor.actor_id \n" + 
					"WHERE film_id = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			rs = stmt.executeQuery();

			
			while (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));;
				actor.setLastName(rs.getString("last_name"));;

				actors.add(actor);
			}
			return actors;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;

	}

	public void closeConnection() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




	@Override
	public Film findFilmByKeyword(String keyword) {
	Film film = null;
		
		String sql = "SELECT film.*, language.name, category.name FROM film \n" + 
				"JOIN language ON language.id = film.language_id \n" + 
				"JOIN film_category ON film.id = film_category.film_id \n" + 
				"JOIN category ON film_category.category_id = category.id  \n" + 
				"WHERE film.title LIKE ? OR film.description LIKE ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageID(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setLanguage(rs.getString("name"));
				film.setCategory(rs.getString("name"));
				film.setActors(findActorsByFilmId(film.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return film;
			
		}
		return film;
		
	}

}
