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
	private static Connection conn;
	static {
		try {
			conn= DriverManager.getConnection(URL, user, password);
		
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {


	 

		Film film = null;
		
		
		String sql = "SELECT * FROM film WHERE film.id LIKE ?;";
		PreparedStatement stmt;
		ResultSet filmResult;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			filmResult = stmt.executeQuery();
		
			while (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt(1));
				film.setTitle(filmResult.getString(2));

			}
			stmt.close();
			filmResult.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return film;

		}
		return film;

	}

	public Actor findActorById(int actorId) {
		Actor actor = null;
		PreparedStatement stmt;
		ResultSet actorResult;
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLasstName(actorResult.getString(3));
			}
			stmt.close();
			actorResult.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
				
		return actor;
		}
//  TODO 	public Actor findActorById(int actorId);
//	TODO	public List<Actor> findActorsByFilmId(int filmId);

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		PreparedStatement stmt;
		ResultSet actorResult;
		try {
			String sql = "SELECT id, first_name, last_name ";
			sql += " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id " + " WHERE film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int actorId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Actor actor = new Actor(actorId, firstName, lastName);

				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;

	}

}
