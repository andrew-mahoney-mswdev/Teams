import java.util.ArrayList;

public class Player implements Person { //A player in the team.
	private String name; //The player's name.
	private String birthPlace; //The player's place of birth
	private int height; //The player's height
	private ArrayList<String> positions = new ArrayList<String>(); //A list of positions played.
	
	public Player(String theName, String theBirthPlace, int theHeight) { //The constructor sets the name, birth place and height.
		name = theName;
		birthPlace = theBirthPlace;
		height = theHeight;
	}
	
	public void addPosition(String position) { //This method adds a new play position.
		positions.add(position);
	}
	
	public String getName() {
		return name;
	}
	
	public String getBirthPlace() { 
		return birthPlace;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Boolean checkPosition(String position){ //Checks if the Player can play a particular position.
		for(String s : positions){
			if(s.equals(position)) //Checks if any of the positions match the input.
				return true; //If so, returns true.
		} 
		return false; //If true hasn't been returned, return false.
	}
}
