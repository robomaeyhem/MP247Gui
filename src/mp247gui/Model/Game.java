package mp247gui.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Game {
	private Player[] players;
	
	public Game(String[] playerNames){
		
		InitPlayers(playerNames);
	}
	
	private void InitPlayers(String[] names){
		players = new Player[names.length];
		for (int i = 0; i < names.length; i++){
			players[i] = new Player(names[i]);
		}
	}
	
	public Set<Player> guessWinner(){
		Map<Player,Integer> stars = new HashMap<Player,Integer>();
		for (Player p: players){
			stars.put(p, p.getStars());
		}
		
		for (Bonus b: Bonus.values()){
			Set<Player> bonusWinners = guessBonusWinner(b);
			for (Player p: bonusWinners){
				stars.put(p, stars.get(p)+1);
			}
		}
		
		int MaxStars = 0;
		for (Player p: stars.keySet()){
			if (stars.get(p) > MaxStars){
				MaxStars = stars.get(p);
			}
		}
		
		Set<Player> ret = new HashSet<>();
		for (Player p: stars.keySet()){
			if (stars.get(p) == MaxStars){
				ret.add(p);
			}
		}
		
		return ret;
		
	}
	
	public int GuessStars(String player){
		int ret = 0;
		Player p = findByName(player);
		ret += p.getStars();
		for (Bonus b: Bonus.values()){
			Set<Player> bonusWinners = guessBonusWinner(b);
			if (bonusWinners.contains(p)) ret ++;
		}
		
		return ret;

	}

	public Player findByName(String playername){
		for (int i = 0 ; i < players.length; i++){
			if (players[i].getName().equals(playername)) return players[i];
		}
		return null;
	}
	
	public Set<Player> guessBonusWinner(Bonus b){
		return b.getWinners(players);
	}
	
	public Player[] getAll(){
		return players;
	}
	
	public String toString() {
		String ret = "";
		for (int i = 0; i < players.length; i ++){
			ret += players[i] + "\n";
		}
		return ret;
	}
	
	
	
	public static void main(String argv[]){
		
		Game g = null;
		
		Scanner in = new Scanner(System.in);
		System.out.print("Select your option: \n");
		System.out.print("1: Create Game (Char names by jump line) \n");
		System.out.print("2: Set to player X Y stars \n");
		System.out.print("3: Set to player X Y Event cells: \n");
		System.out.print("4: Set to player X Y minigame coins \n");
		System.out.print("5: Set to player X Y max coins\n");
		System.out.print("6: Guess player X total stars with bonus\n");
		System.out.print("7: Guess winner \n");
		System.out.print("8: Print game \n");
		System.out.print("-1: Exit \n");
		String line;
		while ((line = in.nextLine()) != "-1"){
			switch (line){
				case "1": {
					System.out.print("Type number of characters: \n");
					int size = Integer.valueOf(in.nextLine());
					String[] chars = new String[size];
					System.out.print("Type the characters: \n");
					for (int i = 0; i < size; i++){
						String charname = in.nextLine();
						chars[i] = charname;
					}
					
					g = new Game(chars);
					System.out.print(g + "\n");
					break;
					
				}
				case "2":{
					System.out.print("Type character name: \n");
					String charname = in.nextLine();
					System.out.print("Type the stars: \n");
					int x = Integer.valueOf(in.nextLine());

					g.findByName(charname).setStars(x);
					break;
					
				}
				case "3":{
					System.out.print("Type character name: \n");
					String charname = in.nextLine();
					System.out.print("Type the number of event cells: \n");
					int x = Integer.valueOf(in.nextLine());

					g.findByName(charname).setEventCells(x);
					break;
				}
				case "4":{
					System.out.print("Type character name: \n");
					String charname = in.nextLine();
					System.out.print("Type the number of minigame coins: \n");
					int x = Integer.valueOf(in.nextLine());

					g.findByName(charname).setMinigameCoins(x);
					break;
				}
				case "5":{
					System.out.print("Type character name: \n");
					String charname = in.nextLine();
					System.out.print("Type the number of max coins: \n");
					int x =Integer.valueOf(in.nextLine());

					g.findByName(charname).setMaxCoins(x);
					break;
				}
				case "6":{
					System.out.print("Type character name: \n");
					String charname = in.nextLine();
					System.out.print("Guessed Stars: " + g.GuessStars(charname) + "\n");
					break;
					
				}
				case "7":{
					System.out.print("Winners: \n");
					for (Player p: g.guessWinner()) System.out.print(p + "\n");
					break;
				}
				case "8":{
					System.out.print(g);
					break;
				}
			}
		}
		
		
		
	}
}
