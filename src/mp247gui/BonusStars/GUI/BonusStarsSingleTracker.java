package mp247gui.BonusStars.GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

import mp247gui.BonusStars.GUI.Utils.HorizontalLayer;
import mp247gui.BonusStars.GUI.Utils.VerticalLayer;

public class BonusStarsSingleTracker extends HorizontalLayer {
	
	private SimpleCounter ec;
	private SimpleCounter stars;
	private MinigameSingleTracker mt;
	private MaxMoneySingleTracker mmt;
	
	public BonusStarsSingleTracker(String charname){
		this.setBorder(BorderFactory.createTitledBorder(charname));
		
		stars = new SimpleCounter("Stars");
		ec = new SimpleCounter("Event Counter");
		mt = new MinigameSingleTracker("Minigame earns");
		mmt = new MaxMoneySingleTracker("Max Money");
		
		this.addComp(stars);
		this.addComp(ec);
		this.addComp(mt);
		this.addComp(mmt);
	}
	
	public void Reset(){
		ec.Reset();
		stars.Reset();
		mt.Reset();
		mmt.Reset();
	}
	
	public static void main(String argv[]){
		JFrame main = new JFrame();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setContentPane(new BonusStarsSingleTracker("mario"));
		main.pack();
		main.setVisible(true);
		
		
	}
}
