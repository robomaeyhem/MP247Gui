package mp247gui.BonusStars.Controllers;

import java.util.Stack;

import mp247gui.Model.Game;

public class BonusStarsCommander {
	private Stack<Command> actions;
	private Game board;
	
	public BonusStarsCommander(Game board){
		actions = new Stack<>();
		this.board = board;
	}
	
	public void SetStars(String Charname,int stars){
		PushAction(new CommandStars(Charname,board.findByName(Charname).getStars()));
		PersistStars(Charname,stars);	
	}
	public void SetEventCells(String Charname,int events){
		PushAction(new CommandEvent(Charname,board.findByName(Charname).getEventCells()));
		PersistEventCells(Charname,events);	
	}
	public void SetMinigameCoins(String Charname,int coins){
		PushAction(new CommandMinigame(Charname,board.findByName(Charname).getMinigameCoins()));
		PersistMinigameCoins(Charname,coins);	
	}
	public void SetMaxCoins(String Charname,int coins){
		
		
		PushAction(new CommandMax(Charname,board.findByName(Charname).getMaxCoins()));
		PersistMaxCoins(Charname,coins);
	}
	public void Undo(){
		if (!actions.empty()){
			actions.pop().Do();
		}
	}
	
	private void PushAction(Command action){
		actions.push(action);
	}
	
	
	
	private void PersistStars(String Charname,int stars){
		board.findByName(Charname).setStars(stars);
	}
	private void PersistEventCells(String Charname,int events){
		board.findByName(Charname).setEventCells(events);
	}
	private void PersistMinigameCoins(String Charname,int coins){
		board.findByName(Charname).setMinigameCoins(coins);
	}
	private void PersistMaxCoins(String Charname,int coins){
		board.findByName(Charname).setMaxCoins(coins);
	}
	
	private abstract class Command {
		protected String charname;
		protected int n;
		public Command(String charname,int n){
			this.charname = charname;
			this.n = n;
		}
		public abstract void Do();
	}
	
	private class CommandStars extends Command{

		public CommandStars(String charname, int n) {
			super(charname, n);
		}

		@Override
		public void Do() {
			PersistStars(charname,n);
			
		}
		
	}
	private class CommandEvent extends Command{

		public CommandEvent(String charname, int n) {
			super(charname, n);
		}

		@Override
		public void Do() {
			PersistEventCells(charname,n);
		}
		
	}
	private class CommandMinigame extends Command{

		public CommandMinigame(String charname, int n) {
			super(charname, n);
		}

		@Override
		public void Do() {
			PersistMinigameCoins(charname,n);
			
		}

	}
	
	private class CommandMax extends Command{

		public CommandMax(String charname, int n) {
			super(charname, n);
		}

		@Override
		public void Do() {
			PersistMaxCoins(charname,n);
			
		}
		
	}
}
