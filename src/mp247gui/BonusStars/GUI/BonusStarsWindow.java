package mp247gui.BonusStars.GUI;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import mp247gui.BonusStars.Controllers.BonusStarsController.SetElement;

public class BonusStarsWindow extends JFrame{

	BonusStarsTracker b;
	public BonusStarsWindow(String[] chars,SetElement[] StarsListeners,SetElement[] EventListeners, SetElement[] minigames,SetElement[] MaxListeners,ActionListener reset,ActionListener undo){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("CHARACTER TRACK");
		b = new BonusStarsTracker(chars,StarsListeners,EventListeners,minigames,MaxListeners,reset,undo);
		setContentPane(b);
		pack();
        setResizable(false);
		setVisible(true);
	}
	
	public void SetStars(String charname,int stars){
		b.SetStars(charname, stars);
	}
	
	public void SetEvents(String charname,int events){
		b.SetEvents(charname,events);
	}
	public void SetMinigames(String charname,int coins){
		b.SetMinigames(charname, coins);
	}
	public void SetMaxMoney(String charname,int max){
		b.SetMaxMoney(charname, max);
	}
	public void SetGuessedStars(String charname, int stars){
		b.SetGuessedStars(charname, stars);
	}
	
	public void Reset(){
		b.Reset();
	}
	

}
