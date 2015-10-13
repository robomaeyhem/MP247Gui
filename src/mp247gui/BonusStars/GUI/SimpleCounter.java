package mp247gui.BonusStars.GUI;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mp247gui.BonusStars.Controllers.BonusStarsController.SetElement;
import mp247gui.BonusStars.GUI.Utils.HorizontalLayer;
import mp247gui.BonusStars.GUI.Utils.VerticalLayer;

public class SimpleCounter extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int count;
	private JLabel display;
	private JButton plus;
	private JButton minus;
	
	private SetElement listener;
	
	public SimpleCounter(SetElement listener,String title){
		Init();
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.listener = listener;
		listener.setRelative(true);

	}
	
	public void SetCount(int c){
		count = c;
		DisplayCount();
	}
	
	public void Reset(){
		count = 0;
		DisplayCount();
	}
	
	private void Plus(){
		listener.setNumber(1);
		listener.Set();
	}
	private void Minus(){
		listener.setNumber(-1);
		listener.Set();
	}
	
	
	private void Init(){
		count = 0;
		InitComponents();
		DisplayCount();
	}
	private void InitComponents(){
		plus = new JButton("+");
		plus.addActionListener(new Add());
		
		minus = new JButton("-");
		minus.addActionListener(new Substract());
		
		display = new JLabel();
		
		HorizontalLayer h = new HorizontalLayer();
		h.addComp(minus);
		h.addComp(plus);
		
		
		VerticalLayer l = new VerticalLayer();
		l.addComp(display);
		l.addComp(h);
		
		this.setLayout(new GridBagLayout());
		this.add(l);
		
		this.setVisible(true);	
		
	}
	
	private void DisplayCount(){
		display.setText(String.valueOf(count));
	}
	
	
	private class Add implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Plus();
		}	
	}
	private class Substract implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Minus();
		}
		
	}
	

	
}
