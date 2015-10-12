package mp247gui.BonusStars.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mp247gui.BonusStars.GUI.Utils.HorizontalLayer;
import mp247gui.BonusStars.GUI.Utils.VerticalLayer;

public class MinigameTracker extends VerticalLayer {
	
	private MinigameSingleTracker[] counters;
	
	private JButton AddCustomAll;
	private JButton UndoAll;
	
	private HorizontalLayer CharactersSpace;
	
	public MinigameTracker(String[] chars){
		InitComponents(chars);
	
		
	}
	public void Reset(){
		for (MinigameSingleTracker s: counters) s.Reset();
	}
	
	public void AddAll(){
		for (MinigameSingleTracker mc : counters) mc.AddCustomMoney();
	}
	
	public void UndoAll(){
		for (MinigameSingleTracker mc : counters) mc.Undo();
	}
	
	private void InitComponents(String[] chars){
		this.setBorder(BorderFactory.createTitledBorder("Minigame money"));
		
		CharactersSpace = new HorizontalLayer();
		this.addComp(CharactersSpace);
		
		
		AddCustomAll = new JButton("Add all");
		AddCustomAll.addActionListener(new AddAll());
		
		UndoAll = new JButton("Undo all");
		UndoAll.addActionListener(new UndoAll());
		
		HorizontalLayer global = new HorizontalLayer();
		global.addComp(AddCustomAll);
		global.addComp(UndoAll);
		
		this.addComp(global);
		
		
		InitIndividualCounters(chars);
		this.setVisible(true);
	}
	private void InitIndividualCounters(String[] chars){
		counters = new MinigameSingleTracker[chars.length];
		for (int i = 0; i < chars.length; i++){
			InitCharacter(i,chars[i]);	
		}
	}
	private void InitCharacter(int index,String charName){
		counters[index] = new MinigameSingleTracker(charName);
		CharactersSpace.addComp(counters[index]);
	}
	
	private class AddAll implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			AddAll();
		}
	}
	
	private class UndoAll implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			UndoAll();
		}
	}
	
	
	
	public static void main(String argv[]){
		JFrame main = new JFrame();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] chars = {"mario","peach","wario","luigi"};
		main.setContentPane(new MinigameTracker(chars));
		main.pack();
		main.setVisible(true);
		
		
	}
	
	

	
}
