##  Film Query

### Week Seven Weekend Homework

### Overview
This is a command-line application that retrieves and displays film data. It us be menu-based, and allows the user to choose actions and submit query data.
It connects to a database of films and actors using Java to connect to the SQL database and execute commands to retrieve the needed data. The application is split into the main app, entities holding the film and actor classes, a database accessor interface and a database accessor object class.

### Main application:
The user is welcomed into the application and then prompted to select from a menu. They have the option to choose to enter a film by id or keyword or they have the choice to exit the app. If they want to find a film by id, they're prompted to input a number. If the input is not a number, "That is not a valid number" is displayed. If an id is entered that is not associated with a film, "There is no film with that ID." When a valid id is entered, the title of the film is displayed along with a menu with the options to see the film details or return to the menu.

When the user selects the keyword option, they are prompted to input a keyword to search for a film with that word in the title or description. The title is displayed along with the menu to see the details or return to the main menu.

The details shown for the film are:
id, title, description, release year, length, rating, specialFeatures, language, category, a list of actors.


### Lessons Learned
Building classes in an Object-Oriented manner with data from an SQL database and having them communicate with one another.
Writing commands that retrieve information from tables in and SQL database.
Joining tables to get complete information in SQL.
Practice writing methods that interact with an SQL database and return data.
