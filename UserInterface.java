import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;

import ecs100.UI;

public class UserInterface {

	ArrayList<Team> teams = new ArrayList<Team>(); //An list of all teams in the file.

	public void listTeams() {
		UI.clearText(); //Clears all text from the UI.
		for (Team t : teams) { //For each team,
			UI.println(t.getName()); //output the team's name.
		}
	}

	public void listTeamsWithCoaches() {
		UI.clearText();
		for (Team t : teams) { //For each team,
			UI.print(t.getName() + ", "); //Print the team name,
			UI.println(t.getCoachName()); //and the coach name.
		}
	}

	public void listTeamPlayers() {
		boolean teamFound = false; //Variable records whether or not the specified team has been found.
		ArrayList<String> allPlayerStats = new ArrayList<String>(); //Stores Strings that record the player's stats

		UI.clearText();
		String team = UI.askString("Which team?");
		
		for (Team t : teams) { //For each team,
			if(t.getName().equals(team)){ //check if the team name matches the specified name. 
				teamFound = true; //If so, note that a team has been found
				allPlayerStats = t.getAllPlayerStats(); //and store the ArrayList of players' stats.
			}
		}
		
		if(teamFound){ //If the team has been found
			for (String s : allPlayerStats){
				UI.println(s); //Output the strings of player's stats.
			}
		}
		else UI.println("No such team found."); //If no team is found, advise the user.
	}

	public void listPositionPlayersOnTeam() {
		ArrayList<String> playersThatPlay = new ArrayList<String>(); //This stores the names all players who play the specified position.
		boolean teamFound = false; //Records if a team has been found.
		
		UI.clearText();
		String team = UI.askString("Which team?");
		String position = UI.askString("Which position?");
		
		for (Team t : teams) { //For every team,
			if(t.getName().equals(team)){ //check if the team name matches the specified name.
				teamFound = true; //If so, record that a team has been found.
				playersThatPlay = t.getPlayersThatPlay(position); //Get all player names who play the specified position.
			}
		}
		
		if (teamFound) { //If the team was found,
			if(playersThatPlay.size() != 0){ //Check if there are any players that play the specified position
				for(String s : playersThatPlay){
					UI.println(s); //If so, print the names of all players.
				}
			}
			else UI.println("No players found."); //If there are no players who play that position, advise the user.
		}
		else UI.println("No such team found."); //If there is no team with the specified name, advise the user.
	}

	public void listHeights() {
		ArrayList<String> playersWithinHeight = new ArrayList<String>(); //Records the names of all player's within a height range.
		boolean noPlayers = true; //Records if no players have been found, i.e. will be set to false when a player is found.
		
		UI.clearText();
		int min = UI.askInt("Taller than?");
		int max = UI.askInt("Shorter than?");
		
		for(Team t : teams){ //For every team
			playersWithinHeight = t.getPlayersWithinHeight(min, max); //Get all the players in one team who are within the height range. 
			if(playersWithinHeight.size() > 0) //If any players were found,
				noPlayers = false; //Record that a player has been found.
			for(String s : playersWithinHeight){
				UI.println(s); //Prints the name of every player within the height range.
			}
		}
		
		if(noPlayers) //If no players were found.
			UI.println("No players in that range."); //Advise the user
	}

	public void graphHeights() {
		UI.clearText();
		UI.clearGraphics();
		String teamOrPos = UI.askString("Graph by team or position?"); //Asks the user how they want to sort players.
		if(teamOrPos.equals("team")){	//if the user chose team
			ArrayList<Player> PlayerHeights = new ArrayList<Player>();	//creates an ArrayList of players
			boolean teamFound = false; //creates a boolean for if team name found.
			String team = UI.askString("Enter a team");	//asks the user to enter a team name.
			for(Team t : teams){	//for every team in the teams ArrayList
				if(t.getName().equals(team)){	//checks to see if the team name is valid.
					PlayerHeights = t.getPlayerHeights();	//if it is, gets the player heights
					teamFound = true; //sets boolean to true
				}
			}
			if(teamFound){	//if the team name matched one in the team ArrayList
				UI.clearText();
				int xcoord = 60;
				int arrayPosition = 1;
				UI.println("Team selected: " + team);	//shows the user which team they selected
				UI.drawLine(50, 50, 50, 300);	//draws the x-axis
				UI.drawString("Height", 5, 200);	//labels the x-axis
				UI.drawString("(cm)",5 ,215);
				UI.drawLine(50, 300, 425, 300);	//draws the y-axis
				UI.drawString("Players", 200, 330);	//labels the y-axis
				for(Player p : PlayerHeights){	//for every player
					int height = p.getHeight();	//gets their height
					String heightString = "" + height;	
					UI.fillRect(xcoord, (300-height), 15, height);	//draws a bar of the player's height
					UI.drawString(heightString, xcoord-5, 100);	//writes the height above the bar
					String arrayPositionString = "" + arrayPosition; 
					UI.drawString(arrayPositionString, xcoord, 315); //writes a number below the bar
					UI.println(arrayPosition + ": " + p.getName()); //shows which player corresponds to that bar.
					xcoord = xcoord + 30; //iterates so the next bar is not drawn on top of the first
					arrayPosition++;	//iterates to the next array position
				}	
			}
			else{
				UI.println("No such team found.");	//if no team name matched, inform the user.
			}
		}
		else if(teamOrPos.equals("position")){	//if position chosen,
			ArrayList<Player> playersThatPlay = new ArrayList<Player>();
			String position = UI.askString("Enter a position?"); //asks the user to enter a position
			int xcoord = 60;
			int arrayPosition = 1;
			int teamcoord = 350;
			UI.clearText();
			UI.println("Position selected: " + position); //displays the position chosen
			for(Team t : teams){	//for each team
				playersThatPlay = t.getPlayerPositions(position);	//gets players that play the chosen position
				if(playersThatPlay.size() != 0){	//if some players play that position
					UI.setColor(Color.getHSBColor((float) (Math.random()), (float)(1.0), (float)(1.0))); //sets a colour for the team
					UI.drawString(t.getName(), 100, teamcoord);	//writes the team name in the corresponding colour
					teamcoord+=10;
					for(Player p : playersThatPlay){
						int height = p.getHeight();
						String heightString = "" + height;
						UI.fillRect(xcoord, (300-height), 10, height);
						String arrayPositionString = "|" + arrayPosition;
						UI.drawString(arrayPositionString, xcoord, 315);
						UI.println(arrayPosition + ": " + p.getName() + ", Height: " + heightString);
						xcoord+=20;
						arrayPosition++;
					}	
				}
				else{
					UI.println("No players found for that position.");
				}
			}
			UI.setColor(Color.black);	//sets UI colour to black.
			UI.drawLine(50, 50, 50, 300);	//draws x-axis
			UI.drawString("Height", 5, 200); //labels x-axis
			UI.drawString("(cm)",5 ,215);
			UI.drawLine(50, 300, 800, 300); //draws y-axis
			UI.drawString("Players", 200, 330); //labels y-axis
		}
		
		else{
			UI.println("Invalid Choice");
		}
	}
	
	public void checkSelection() {
		checkLineUps(false); //Check line ups, but output only the first possible line up found.
	}

	public void possibleLineUps() {
		checkLineUps(true); //Check line ups and output all possible line ups found.
	}

	public void checkLineUps (boolean all) {
	/** This method finds all possible line ups for a given set of players.
	 * @param all - This variable indicates whether all line ups should be found, if it is set to false, the method will only find one line up.
	 */
		String playerName = ""; //Stores the name of a player entered by the user.
		int playerIndex = 0; //An index in the players array.
		ArrayList<Player> players = new ArrayList<Player>(); //The players array stores seven player objects.
		int possibility = 0; //Counts the number of possible line ups found.
		boolean lineupFound = false; //Records whether a possible line up has been found.
		
		UI.clearText();
		do {
			boolean playerOnTeam = false; //Records whether a given player is on any team.
			playerName = UI.askString("Enter player " + (playerIndex + 1) + ":"); //Prompts the user for a player name.
			for (Team t : teams) { //For each team
				if (t.isPlayerOnTeam(playerName)) { //Check if the player is on the team.
					playerOnTeam = true;
					players.add(t.getPlayerObj(playerName)); //Get the player object.
					playerIndex++; //Increment the index of the players array.
				}
			}
			if (playerOnTeam == false) UI.println("No such player found."); //Advise user if player is not found.
		} while (playerIndex < 7);

		//This code works by rearranging the seven players is every possible sequence, and checking each player against a different position
		//until a sequence of players is found that can play each position.  Rearranging the players in all possible positions requires that
		//the numbers 0-6 are rearranged so that they can be used as array indexes.  For example, after checking the positions of players with
		//the indexes 0, 1, 2, 3, 4, 5, 6; we then check 0, 1, 2, 3, 4, 6, 5; and we then check 0, 1, 2, 3, 5, 6, 4, and so on. 
		
		//There are 5040 possible combinations in which the numbers 0 - 6 can be arranged.  This code cycles through all of these combinations,
		//placing each combination in an array called lineup.
		ArrayList<Integer> positionNumber = new ArrayList<Integer>(); //This is used to record the values 0-6 in order.
		int positionIndex; //This is used to record an index in the positionNumber array.
		int[] lineup = new int[7]; //This is used to record a possible sequence of the values 0 - 6.
		
		for (int combination = 0; combination < 5040; combination++) { //We cycle through all possible combinations of 0 - 6.
			for (positionIndex = 0; positionIndex < 7; positionIndex++) positionNumber.add(positionIndex); //We create a sequential array of these values.

			int combinationQuotient = combination; //A quotient of division with the combination number is required for this math. 
			for (int lineupIndex = 7; lineupIndex > 0; lineupIndex--) { //lineupIndex will serve as the divisor.
				positionIndex = combinationQuotient % lineupIndex; //This modulus indicates the value to source from the sequential positionNumber array.
				lineup[lineupIndex - 1] = positionNumber.get(positionIndex); //This number is placed in the next available index in the lineup array. 
				positionNumber.remove(positionIndex); //We remove the number placed in the lineup array
				combinationQuotient /= lineupIndex; //We recalculate the quotient
			}
			
			//At this point in the loop, we have a unique sequence of the numbers 0-6.
			//We check if the players can play each position, based on this sequence.
			if (players.get(lineup[0]).checkPosition("C") &&
				players.get(lineup[1]).checkPosition("GA") &&
				players.get(lineup[2]).checkPosition("GD") &&
				players.get(lineup[3]).checkPosition("GK") &&
				players.get(lineup[4]).checkPosition("GS") &&
				players.get(lineup[5]).checkPosition("WA") &&
				players.get(lineup[6]).checkPosition("WD")
			) {
				lineupFound = true; 
				possibility++; //If they can, we display the players and their positions.
				UI.println("Possible line up #" + possibility + ":");
				UI.println("C - " + players.get(lineup[0]).getName());
				UI.println("GA - " + players.get(lineup[1]).getName());
				UI.println("GD - " + players.get(lineup[2]).getName());
				UI.println("GK - " + players.get(lineup[3]).getName());
				UI.println("GS - " + players.get(lineup[4]).getName());
				UI.println("WA - " + players.get(lineup[5]).getName());
				UI.println("WD - " + players.get(lineup[6]).getName());
				UI.println();
				if (all == false) return; //Return if the method call required that only one line up be found.
			} //End of if statement.
		} //End of outer for loop.
		if (lineupFound == false) UI.println("There is no valid line up."); //If no line up was found, advise the user.
	} //End of method.
	
	public UserInterface() {
		UI.initialise();
		UI.addButton("List teams", this::listTeams);
		UI.addButton("List coaches", this::listTeamsWithCoaches);
		UI.addButton("List team players", this::listTeamPlayers);
		UI.addButton("List players by position", this::listPositionPlayersOnTeam);
		UI.addButton("Search by height", this::listHeights);
		UI.addButton("Check team lineup", this::checkSelection);
		UI.addButton("Graph player heights", this::graphHeights);
		UI.addButton("All possible line ups", this::possibleLineUps);
		// You may want to use this, or move it somewhere else,
		// or write your own version.
		try {
			Scanner scanner = new Scanner(new File("teams.txt"));
			while (scanner.hasNext()) {
				String teamName = scanner.nextLine(); //Read the team name from the file
				Team currentTeam = new Team(teamName); //Creates a Team object with this name
				teams.add(currentTeam); //Adds the Team to the ArrayList of Teams.

				if(scanner.hasNextInt() == false){	//Confirms that the next line does not contain the number of players.
					String coachName = scanner.nextLine(); //Read the coach name from the file
					Coach currentCoach = new Coach(coachName); //Creates a Coach object with this name
					currentTeam.addPerson(currentCoach); //Adds the coach to the Team
				}

				int numPlayers = scanner.nextInt(); //Gets the number of players in the team.
				scanner.nextLine(); //Jumps to the next line.
				for (int index = 0; index < numPlayers; index++) { //Loops on the number of players.
					String playerInfo = scanner.nextLine(); //Reads the line containing positions and the player's name.
					int spaceLocation = playerInfo.indexOf(' '); //Finds the first space character which separates the positions and the name.
					String posString = playerInfo.substring(0, spaceLocation); //Creates a substring of the positions.
					String playerName = playerInfo.substring(spaceLocation + 1); //Creates a substring of the player's name.
					String[] positions = posString.split(","); //Creates an array of positions which were separated by commas/

					int playerHeight = scanner.nextInt(); //Reads the player's height.
					scanner.nextLine();
					String birthPlace = scanner.nextLine(); //Reads the player's birth place.

					Player currentPlayer = new Player(playerName, birthPlace, playerHeight); //Creates a new player object.
					for (String p : positions) currentPlayer.addPosition(p); //Add the player's positions to the player object.
					currentTeam.addPerson(currentPlayer); //Add the player to the team.
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			UI.printf("Error loading file: %s%n", e);
		}
	}

public static void main(String[] args) {
		new UserInterface();
	}
}
