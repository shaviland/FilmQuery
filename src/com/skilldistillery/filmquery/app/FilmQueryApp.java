package com.skilldistillery.filmquery.app;

import java.io.ObjectInputStream.GetField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean quit = false;
		System.out.println("*************************************");
		System.out.println("*    Welcom to the film database.   *");
		System.out.println("*************************************");
		System.out.println();
		do {
			System.out.println("*************************************");
			System.out.println("*                                   *");
			System.out.println("*    What would you like to do?     *");
			System.out.println("*                                   *");
			System.out.println("*    1. Look up film by ID.         *");
			System.out.println("*    2. Look up film by keyword.    *");
			System.out.println("*    3. Exit application.           *");
			System.out.println("*                                   *");
			System.out.println("*************************************");
			System.out.println();
			String selection = input.nextLine();

			try {
				int choice = Integer.parseInt(selection);
				if (choice > 0 && choice <= 3) {
					quit = lookUpFlim(choice, input);
				} else {
					System.out.println("That is not a valid selection");
				}
			} catch (NumberFormatException e) {
				System.out.println("That is not a valid number");
			}
		} while (!quit);
	}

	private boolean lookUpFlim(int choice, Scanner input) {
		switch (choice) {
		case 1:
			filmById(input);
			break;

		case 2:
			filmByKeyword(input);
			break;

		default:
			System.out.println("Goodbye.");
			db.closeConnection();
			return true;

		}
		return false;
	}

	private void filmById(Scanner input) {
		Film film = new Film();
		System.out.print("Input a film ID: ");
		String selection = input.nextLine();

		try {
			int filmId = Integer.parseInt(selection);
			film = db.findFilmById(filmId);
			boolean valid = true;
			if (film != null) {
				System.out.println(film.getTitle());
				System.out.println();
				film = db.findFilmById(filmId);
				do {
					System.out.println("Do you want to:\n1. See film details \n2. Go to main menu.");
					String choice = input.nextLine();
					int filmChoice = Integer.parseInt(choice);
					switch (filmChoice) {
					case 1:
						System.out.println(film);
						valid = false;
						break;
					case 2:
						valid = false;
						break;
					default:
						System.out.println("That is not a valid selection");
						break;
					}
				} while (valid);

			} else {
				System.out.println("There is no film with that ID.");
			}
		} catch (NumberFormatException e) {
			System.out.println("That is not a valid number");
		}

	}

	private void filmById(int input) {
		Film film = new Film();

		film = db.findFilmById(input);
		if (film != null) {
			System.out.println(film);
		} else {
			System.out.println("There is no film with that ID.");
		}

	}

	private void filmByKeyword(Scanner input) {

		List<Film> films = new ArrayList<>();

		System.out.print("Input a keyword: ");
		String keyword = input.nextLine();
		boolean valid = true;

		films = db.findFilmByKeyword(keyword);
		if (films.size() == 0 || films == null) {
			System.out.println("There are no films with that keyword.");

		} else {

			for (Film film : films) {

				System.out.println("Film ID: " + film.getId() + " \nFilm Title: " + film.getTitle());
				System.out.println();
			}
		}

		do {
			System.out.println("Do you want to:\n1. See a film's details \n2. Go to main menu.");
			String choice = input.nextLine();
			int filmChoice = Integer.parseInt(choice);
			switch (filmChoice) {
			case 1:
				System.out.print("Enter Film ID for details: ");
				String filmSelection = input.nextLine();
				try {
					int filmID = Integer.parseInt(filmSelection);
					filmById(filmID);
					System.out.println();
				} catch (NumberFormatException e) {
					System.out.println("That is not a valid number");
				}
				valid = false;
				break;
			case 2:
				valid = false;
				break;
			default:
				System.out.println("That is not a valid selection");
				break;
			}
		} while (valid);

	}

}
