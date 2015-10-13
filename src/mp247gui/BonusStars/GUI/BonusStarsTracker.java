package mp247gui.BonusStars.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mp247gui.Main;
import mp247gui.BonusStars.Controllers.BonusStarsController.SetElement;
import mp247gui.BonusStars.GUI.Utils.VerticalLayer;

public class BonusStarsTracker extends VerticalLayer{
	private BonusStarsSingleTracker[] trackers;
	
	public BonusStarsTracker(String[] chars,SetElement[] StarsListeners,SetElement[] EventListeners, SetElement[] MinigamesListener,SetElement[] MaxListeners,ActionListener reset){
		InitComponents(chars,StarsListeners,EventListeners,MinigamesListener,MaxListeners,reset);
	}
	
	private void InitComponents(String[] chars,SetElement[] StarsListeners,SetElement[] EventListeners, SetElement[] MinigamesListener,SetElement[] MaxListeners,ActionListener reset){
		trackers = new BonusStarsSingleTracker[chars.length];
		
		for (int i = 0; i < chars.length; i ++){
			trackers[i] = new BonusStarsSingleTracker(chars[i],StarsListeners[i],EventListeners[i],MinigamesListener[i],MaxListeners[i]);
			this.addComp(trackers[i]);
		}
		
		JPanel Preset = new JPanel(new FlowLayout());
		JButton res = new JButton("Reset");
		res.addActionListener(reset);
		Preset.add(res);
		this.addComp(Preset);
		

		this.setVisible(true);
	}
	
	private BonusStarsSingleTracker getByCharName(String charname){
		for (int i = 0; i < trackers.length; i ++){
			if (trackers[i].getCharName().equals(charname)) return trackers[i];
		}
		return null;
	}
	
	public void SetStars(String character,int stars){
		getByCharName(character).SetStars(stars);
	}
	
	public void SetEvents(String character,int events){
		getByCharName(character).SetEvents(events);
	}
	public void SetMinigames(String character,int coins){
		getByCharName(character).SetMinigames(coins);
	}
	public void SetMaxMoney(String character,int coins){
		getByCharName(character).SetMaxMoney(coins);
	}
	public void SetGuessedStars(String character, int stars){
		getByCharName(character).SetGuessedStars(stars);
		
	}
	public void Reset(){
		for (BonusStarsSingleTracker t : trackers) t.Reset();
	}
	

}
