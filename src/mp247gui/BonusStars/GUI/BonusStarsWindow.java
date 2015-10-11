package mp247gui.BonusStars.GUI;

import javax.swing.JFrame;

public class BonusStarsWindow extends JFrame{

	public BonusStarsWindow(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] chars = {"MARIO","YOSHI","PEACH","WARIO"};
		setTitle("CHARACTER TRACK");
		setContentPane(new BonusStarsTracker(chars));
		pack();
		setVisible(true);
	}
	
	public static void main(String[] argv){
		new BonusStarsWindow();
	}
}
