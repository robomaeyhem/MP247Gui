package mp247gui.Model;

public class Player {
	private String name;
	private int stars;
	private int EventCells;
	private int maxCoins;
	private int MinigameCoins;
	
	public Player(String name){this.name = name;}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public int getEventCells() {
		return EventCells;
	}
	public void setEventCells(int eventCells) {
		EventCells = eventCells;
	}
	public int getMaxCoins() {
		return maxCoins;
	}
	public void setMaxCoins(int maxCoins) {
		this.maxCoins = maxCoins;
	}
	public int getMinigameCoins() {
		return MinigameCoins;
	}

	public void setMinigameCoins(int minigameCoins) {
		MinigameCoins = minigameCoins;
	}
	
	
	public void TestMaxCoins(int coins){
		if (coins > maxCoins) maxCoins = coins;
	}
	
	public void StepEvent(){
		EventCells ++;
	}
	
	public void getStar(){
		stars++;
	}


	@Override
	public String toString() {
		return name + ", Stars: " + stars + ", Events: " + EventCells + ", MaxCoins: " + maxCoins + ", Minigames: " + MinigameCoins;
	}

	@Override
	public boolean equals(Object arg0) {
		return name.equals(((Player)arg0).name);
	}
	
	

	

}
