package mp247gui.BonusStars.GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NumericDisplay extends JPanel{
	
	protected int number;
	protected JLabel display;
	
	public NumericDisplay(String title){
		number = 0;
		this.setBorder(BorderFactory.createTitledBorder(title));
		InitCompontents();
		DisplayCount();
	}
	
	private void InitCompontents(){
		this.setLayout(new FlowLayout());
		display = new JLabel();
		display.setPreferredSize(new Dimension(100,20));
		this.add(display);
		
		
	}
	
	public void SetNumber(int n){
		this.number = n;
		DisplayCount();
	}
	
	protected void DisplayCount(){
		display.setText(String.valueOf(number));
	}
	
	

}
