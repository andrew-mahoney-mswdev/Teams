
public class Coach implements Person{ //A team coach
	private String name; //The coach's name
	
	public Coach(String coachName) { //The constuctor sets the coach's name
		name = coachName;
	}
	
	public String getName() {
		return name;
	};
}
