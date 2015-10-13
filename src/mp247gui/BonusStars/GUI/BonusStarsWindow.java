package mp247gui.BonusStars.GUI;

import javax.swing.JFrame;

import mp247gui.BonusStars.Controllers.BonusStarsController.SetElement;

public class BonusStarsWindow extends JFrame{

	BonusStarsTracker b;
	public BonusStarsWindow(String[] chars,SetElement[] StarsListeners,SetElement[] EventListeners){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("CHARACTER TRACK");
		b = new BonusStarsTracker(chars,StarsListeners,EventListeners);
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
	

}
