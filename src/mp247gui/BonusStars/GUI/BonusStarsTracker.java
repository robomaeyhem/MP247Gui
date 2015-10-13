package mp247gui.BonusStars.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mp247gui.Main;
import mp247gui.BonusStars.Controllers.BonusStarsController.SetElement;
import mp247gui.BonusStars.GUI.Utils.VerticalLayer;

public class BonusStarsTracker extends VerticalLayer{
	private BonusStarsSingleTracker[] trackers;
	
	public BonusStarsTracker(String[] chars,SetElement[] StarsListeners,SetElement[] EventListeners){
		InitComponents(chars,StarsListeners,EventListeners);
	}
	
	private void InitComponents(String[] chars,SetElement[] StarsListeners,SetElement[] EventListeners){
		trackers = new BonusStarsSingleTracker[chars.length];
		
		for (int i = 0; i < chars.length; i ++){
			trackers[i] = new BonusStarsSingleTracker(chars[i],StarsListeners[i],EventListeners[i]);
			this.addComp(trackers[i]);
		}
		
		JPanel reset = new JPanel(new FlowLayout());
		JButton res = new JButton("Reset");
		res.addActionListener((ActionEvent ee) -> {
            for (BonusStarsSingleTracker tr: trackers) tr.Reset();
        });
		reset.add(res);
		this.addComp(reset);
		

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
	

}
