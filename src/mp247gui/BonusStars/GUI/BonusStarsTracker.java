package mp247gui.BonusStars.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mp247gui.Main;
import mp247gui.BonusStars.GUI.Utils.VerticalLayer;

public class BonusStarsTracker extends VerticalLayer{
	private BonusStarsSingleTracker[] trackers;
	
	public BonusStarsTracker(String[] chars){
		InitComponents(chars);
	}
	
	private void InitComponents(String[] chars){
		trackers = new BonusStarsSingleTracker[chars.length];
		
		for (int i = 0; i < chars.length; i ++){
			trackers[i] = new BonusStarsSingleTracker(chars[i]);
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
	
	public static void main(String argv[]){
		JFrame main = new JFrame();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] chars = {"MARIO","PEACH","WARIO","LUIGI"};
		main.setContentPane(new BonusStarsTracker(chars));
		main.pack();
		main.setVisible(true);
		
		
	}
}
