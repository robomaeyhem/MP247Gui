package mp247gui.BonusStars.GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

import mp247gui.BonusStars.Controllers.BonusStarsController.SetElement;
import mp247gui.BonusStars.GUI.Utils.HorizontalLayer;
import mp247gui.BonusStars.GUI.Utils.VerticalLayer;

public class BonusStarsSingleTracker extends HorizontalLayer {
	
	private SimpleCounter ec;
	private SimpleCounter stars;
	private MinigameSingleTracker mt;
	private MaxMoneySingleTracker mmt;
	private NumericDisplay guessedStars;
	
	private String CharName;
	
	public BonusStarsSingleTracker(String charname,SetElement starsListener,SetElement eventListener, SetElement minigamesListener,SetElement MaxListener){
		CharName = charname;
		this.setBorder(BorderFactory.createTitledBorder(charname));
		
		starsListener.setName(charname);
		stars = new SimpleCounter(starsListener,"Stars");
		
		eventListener.setName(charname);
		ec = new SimpleCounter(eventListener,"Event Counter");
		
		minigamesListener.setName(charname);
		mt = new MinigameSingleTracker(minigamesListener,"Minigame earns");
		
		MaxListener.setName(charname);
		mmt = new MaxMoneySingleTracker(MaxListener,"Max $");
		
		guessedStars = new NumericDisplay("Final Stars");
		
		this.addComp(stars);
		this.addComp(ec);
		this.addComp(mt);
		this.addComp(mmt);
		this.addComp(guessedStars);
	}
	
	public void Reset(){
		ec.Reset();
		stars.Reset();
		mt.Reset();
		mmt.Reset();
	}
	
	public String getCharName(){
		return CharName;
	}
	
	public void SetStars(int stars){
		this.stars.SetCount(stars);
	}
	
	public void SetEvents(int events){
		this.ec.SetCount(events);
	}
	public void SetMinigames(int coins){
		this.mt.SetMoney(coins);
	}
	public void SetMaxMoney(int coints){
		this.mmt.SetMoney(coints);
	}
	public void SetGuessedStars(int stars){
		this.guessedStars.SetNumber(stars);
	}
	
	

}
