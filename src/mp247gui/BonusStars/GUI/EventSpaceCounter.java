package mp247gui.BonusStars.GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import mp247gui.BonusStars.GUI.Utils.HorizontalLayer;


public class EventSpaceCounter extends HorizontalLayer{
	private SimpleCounter[] counters;
	
	public EventSpaceCounter(String[] chars){
		InitComponents(chars);
	
		
	}
	public void Reset(){
		for (SimpleCounter s: counters) s.Reset();
	}
	
	private void InitComponents(String[] chars){
		this.setBorder(BorderFactory.createTitledBorder("Event Counter"));
		InitIndividualCounters(chars);
		this.setVisible(true);
	}
	private void InitIndividualCounters(String[] chars){
		counters = new SimpleCounter[chars.length];
		for (int i = 0; i < chars.length; i++){
			InitCounter(i,chars[i]);	
		}
	}
	private void InitCounter(int index,String charName){
		counters[index] = new SimpleCounter(charName);
		this.addComp(counters[index]);
	}
	
	public static void main(String argv[]){
		JFrame main = new JFrame();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] chars = {"mario","peach","wario","luigi"};
		main.setContentPane(new EventSpaceCounter(chars));
		main.pack();
		main.setVisible(true);
		
		
	}
	
}
