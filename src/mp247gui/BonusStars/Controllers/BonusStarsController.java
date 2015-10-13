package mp247gui.BonusStars.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mp247gui.BonusStars.GUI.BonusStarsWindow;
import mp247gui.Model.Game;

public class BonusStarsController {
	private String[] players = {"Mario","Peach","Luigi","Bowser"};
	private Game board;
	private BonusStarsWindow VisualTracker;
	
	public BonusStarsController(){
		board = new Game(players);
		SetElement[] stars = new SetElement[4];
		for (int i = 0; i < 4; i++){
			stars[i] = new SetStars();
		}
		
		SetElement[] events = new SetElement[4];
		for (int i = 0; i < 4; i++){
			events[i] = new SetEvents();
		}
		
		VisualTracker = new BonusStarsWindow(players,stars,events);
	}
	public void SetStars(String charname,int n){
	
	}
	public void SetStarsRelative(String charname, int n){
		int stars = board.findByName(charname).getStars();
		board.findByName(charname).setStars(stars + n);
		VisualTracker.SetStars(charname,board.findByName(charname).getStars());	
	}
	
	public void SetEvents(String charname,int n){
	
	}
	public void SetEventsRelative(String charname, int n){
		int events = board.findByName(charname).getEventCells();
		board.findByName(charname).setEventCells(events + n);
		VisualTracker.SetEvents(charname,board.findByName(charname).getEventCells());
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
}
