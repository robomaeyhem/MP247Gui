package mp247gui.BonusStars.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import mp247gui.BonusStars.GUI.Utils.HorizontalLayer;

public class MaxMoneyTracker extends HorizontalLayer {
	private MaxMoneySingleTracker[] counters;
	
	
	public MaxMoneyTracker(String[] chars){
		InitComponents(chars);
	
		
	}
	public void Reset(){
		for (MaxMoneySingleTracker s: counters) s.Reset();
	}
	
	private void InitComponents(String[] chars){
		this.setBorder(BorderFactory.createTitledBorder("Max money"));		
		InitIndividualCounters(chars);
		this.setVisible(true);
	}
	private void InitIndividualCounters(String[] chars){
		counters = new MaxMoneySingleTracker[chars.length];
		for (int i = 0; i < chars.length; i++){
			InitCharacter(i,chars[i]);	
		}
	}
	private void InitCharacter(int index,String charName){
		counters[index] = new MaxMoneySingleTracker(charName);
		this.addComp(counters[index]);
	}
	
	
	
	
	public static void main(String argv[]){
		JFrame main = new JFrame();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] chars = {"mario","peach","wario","luigi"};
		main.setContentPane(new MaxMoneyTracker(chars));
		main.pack();
		main.setVisible(true);
		
		
	}
}
