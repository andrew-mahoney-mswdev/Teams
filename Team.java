import java.util.ArrayList;

public class Team { //A collection of people playing in a team.
	private String name; //The team's name.
	private ArrayList<Person> people = new ArrayList<Person>(); //A collection of all the people in the team.

	public Team (String teamName) { //The constructor sets the team's name.
		name = teamName;
	}

	public void addPerson(Person thePerson) { //Adds a person to the team.
		people.add(thePerson);
	}

	public String getName () {
		return name;
	}

	public String getCoachName () {	//Finds and returns the name of the first Coach object in the people array. Otherwise, returns an empty String.
		for(Person p : people){
			if(p instanceof Coach)
				return p.getName();
		}
		return "";
	}

	public boolean isPlayerOnTeam(String theName) { //Checks if a player is on the team.
		for (Person p : people) { //For each person on the team.
			if (p instanceof Player && p.getName().equals(theName)) return true; //Return true if they are a player with the specified name.
		}
		return false;
	}

	public Player getPlayerObj(String theName) { //Returns a player object.
		for (Person p : people) { //For each person on the team.
			if (p instanceof Player && p.getName().equals(theName)) return (Player) p; //Return the object if the person is a player with the specified name. 
		}
		return null;
	}
	
	public ArrayList<String> getAllPlayerStats(){
	//Returns an ArrayList of strings, which contains formatted info about each player's stats.
		ArrayList<String> allPlayerStats = new ArrayList<String>();
		for(Person p : people){ 
			if(p instanceof Player){ //For every player in the team.
				Player currentPlayer = (Player)p; //Cast the Person to a Player
				String currentPlayerStats = p.getName() +  //Create a string of their stats.
						", height: " + currentPlayer.getHeight() +
						", born: " + currentPlayer.getBirthPlace();
				allPlayerStats.add(currentPlayerStats); //Add this to the ArrayList.
			}
		}
		return allPlayerStats;
	}

	public ArrayList<String> getPlayersThatPlay(String position){
	//Returns an ArrayList with the names of Players that play a specific position.
		ArrayList<String> playersThatPlay = new ArrayList<String>();
		for(Person p : people){
			if(p instanceof Player){ //For every player in the team.
				Player currentPlayer = (Player) p; //Cast the Person as a Player.
				if(currentPlayer.checkPosition(position)) //Check if the player can play a position.
					playersThatPlay.add(currentPlayer.getName()); //If so, add the player's name to the ArrayList.
			}
		}
		return playersThatPlay;
	}
	
	public ArrayList<String> getPlayersWithinHeight(int min, int max){
	//Returns an ArrayList of names of players within a specified height range.
		ArrayList<String> playersWithinHeight = new ArrayList<String>();
		for(Person p : people){
			if(p instanceof Player){ //For every player in the team.
				Player currentPlayer = (Player) p; //Cast the Person as a Player.
				int height = currentPlayer.getHeight(); //Get the player's height.
				if(height >= min && height <= max){ //If the height is within range,
					playersWithinHeight.add(currentPlayer.getName()); //Add the player's name to the ArrayList.
				}
			}
		}
		return playersWithinHeight;
	}

	public ArrayList<Player> getPlayerHeights(){ //this method creates an ArrayList containing all players in the team
		ArrayList<Player> playerHeights = new ArrayList<Player>(); //new ArrayList
		for(Person p : people){ //for each person in the ArrayList
			if(p instanceof Player){ //checks if player
				Player currentPlayer = (Player) p; //casts to player
				playerHeights.add(currentPlayer); //adds player to ArrayList
			}
		}
		return playerHeights;
	}
	
	public ArrayList<Player> getPlayerPositions(String position){ //this method creates an ArrayList containing all players in the team who play a specified position
		ArrayList<Player> playerPositions = new ArrayList<Player>(); //new ArrayList
		for(Person p : people){ //for each person
			if(p instanceof Player){ //checks if player
				Player currentPlayer = (Player) p; //casts to player
				if(currentPlayer.checkPosition(position)){ //if they play specified position
					playerPositions.add(currentPlayer); //add to the ArrayList
				}
			}
		}
		return playerPositions;
	}
}
