package mp247gui.BonusStars.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mp247gui.BonusStars.GUI.BonusStarsWindow;
import mp247gui.Model.Game;

public class BonusStarsController {
	private String[] players = {"Mario","Yoshi","Peach","Wario"};
	private Game board;
	private BonusStarsWindow VisualTracker;
	private BonusStarsCommander com;
	
	public BonusStarsController(){
		board = new Game(players);
		InitCommander();
		
		SetElement[] stars = new SetElement[4];
		for (int i = 0; i < 4; i++){
			stars[i] = new SetStars();
		}
		
		SetElement[] events = new SetElement[4];
		for (int i = 0; i < 4; i++){
			events[i] = new SetEvents();
		}
		
		SetElement[] minigames = new SetElement[4];
		for (int i = 0 ; i < 4; i++){
			minigames[i] = new SetMinigames();
		}
		
		SetElement[] max = new SetElement[4];
		for (int i = 0 ; i < 4; i++){
			max[i] = new SetMaxMoney();
		}
		
		
		VisualTracker = new BonusStarsWindow(players,stars,events,minigames,max,new Reset(),new Undo());
		UpdateGuessStars();
	}
	private void InitCommander(){
		com = new BonusStarsCommander(board);
		
		/*for (String s: players){
			com.SetStars(s,0);
			com.SetMaxCoins(s,0);
			com.SetMinigameCoins(s,0);
			com.SetEventCells(s,0);
		}*/
	}
	public void SetStars(String charname,int n){
	
	}
	public void SetStarsRelative(String charname, int n){
		int stars = board.findByName(charname).getStars();
		com.SetStars(charname, stars + n);
		//board.findByName(charname).setStars(stars + n);
		VisualTracker.SetStars(charname,board.findByName(charname).getStars());	
		UpdateGuessStars();
	}
	
	public void SetEvents(String charname,int n){
	
	}
	public void SetEventsRelative(String charname, int n){
		int events = board.findByName(charname).getEventCells();
		com.SetEventCells(charname, events +n);
		//board.findByName(charname).setEventCells(events + n);
		VisualTracker.SetEvents(charname,events +n);
		UpdateGuessStars();
	}
	public void SetMinigames(String charname,int n){
		com.SetMinigameCoins(charname, n);
		//board.findByName(charname).setMinigameCoins(n);
		VisualTracker.SetMinigames(charname, n);
		UpdateGuessStars();
	}
	public void SetMinigamesRelative(String charname,int n){
		int coins = board.findByName(charname).getMinigameCoins();
		com.SetMinigameCoins(charname, n + coins);
		//board.findByName(charname).setMinigameCoins(coins + n);
		VisualTracker.SetMinigames(charname, coins + n);
		UpdateGuessStars();
	}
	public void SetMaxMoney(String charname,int n){
		int max = board.findByName(charname).getMaxCoins();
		
		if (n > max){
			com.SetMaxCoins(charname, n);
			//board.findByName(charname).setMaxCoins(n);
			VisualTracker.SetMaxMoney(charname, n);
			UpdateGuessStars();
		}
	}
	public void SetMaxMoneyRelative(String charname,int n){
		
	}
	
	private void UpdateGuessStars(){
		for (String s: players){
			VisualTracker.SetGuessedStars(s, board.GuessStars(s));
		}
	}
	
	private void UpdateAll(){
		for (String s: players){
			VisualTracker.SetStars(s, board.findByName(s).getStars());
			VisualTracker.SetEvents(s, board.findByName(s).getEventCells());
			VisualTracker.SetMinigames(s, board.findByName(s).getMinigameCoins());
			VisualTracker.SetMaxMoney(s, board.findByName(s).getMaxCoins());
			VisualTracker.SetGuessedStars(s, board.GuessStars(s));
		}
	}
	
	public void Reset(){
		board = new Game(players);
		InitCommander();
		VisualTracker.Reset();
		UpdateGuessStars();
	}
	
	public void Undo(){
		com.Undo();
		UpdateAll();
	}
	
	private class Reset implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Reset();
		}
		
	}
	
	private class Undo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Undo();
		}
		
	}
	
	
	public abstract class SetElement{
		protected String charName;
		protected boolean Relative;
		protected int number;
		
		public void setName(String name){charName = name;}
		public void setRelative(boolean r){Relative = r;}
		public void setNumber(int n){number = n;}

		public void Set(){
			if (Relative){
				SetElementRelative();
			}
			else {
				SetElementValue();
			}
		}
		
		protected abstract void SetElementValue();
		protected abstract void SetElementRelative();
		
	}
	
	public class SetStars extends SetElement{

		@Override
		protected void SetElementValue() {
			SetStars(charName,number);
		}

		@Override
		protected void SetElementRelative() {
			SetStarsRelative(charName,number);	
		}
		
	}
	
	public class SetEvents extends SetElement{

		@Override
		protected void SetElementValue() {
			SetEvents(charName,number);
		}

		@Override
		protected void SetElementRelative() {
			SetEventsRelative(charName,number);	
		}
		
	}
	
	public class SetMinigames extends SetElement{

		@Override
		protected void SetElementValue() {
			SetMinigames(charName,number);
		}

		@Override
		protected void SetElementRelative() {
			SetMinigamesRelative(charName,number);
		}
		
	}
	
	public class SetMaxMoney extends SetElement{

		@Override
		protected void SetElementValue() {
			SetMaxMoney(charName,number);
		}

		@Override
		protected void SetElementRelative() {
			SetMaxMoneyRelative(charName,number);
		}
		
	}
}
