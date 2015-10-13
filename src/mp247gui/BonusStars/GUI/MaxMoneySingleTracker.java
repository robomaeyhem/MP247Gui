package mp247gui.BonusStars.GUI;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mp247gui.BonusStars.Controllers.BonusStarsController.SetElement;
import mp247gui.BonusStars.GUI.Utils.HorizontalLayer;
import mp247gui.BonusStars.GUI.Utils.VerticalLayer;

public class MaxMoneySingleTracker extends JPanel {

	private int money;
	
	private JLabel DisplayMoney;
	private JTextField next;
	private JButton apply;
	private JButton undo;
	
	private Stack<Integer> undoStack;
	
	private SetElement listener;
	
	public  MaxMoneySingleTracker(){
		Init();
	}
	public  MaxMoneySingleTracker(SetElement listener,String title){
		this.listener = listener;
		Init();
		this.setBorder(BorderFactory.createTitledBorder(title));
	}
	
	public void SetMoney(int money){
		this.money = money;
		DisplayCount();
	}
	
	public void Apply(int n){
		if (n > money){
			listener.setRelative(false);
			listener.setNumber(n);
			listener.Set();
		}	
		next.setText("");
	}
	
	public void Reset(){
		money = 0;
		undoStack = new Stack<>();
		undoStack.push(money);
		DisplayCount();
	}
	
	public void Undo(){
		if (undoStack.size() > 1){
			undoStack.pop();
			int n = undoStack.peek();
			money = n;
			DisplayCount();
		}
	}
	
	public void Next(){
		Apply(getCustomMoney());
	}
	
	
	private void Init(){
		undoStack = new Stack<>();
		money = 0;
		undoStack.push(money);
		InitComponents();
		DisplayCount();
		
	}
	
	
	
	private void InitComponents(){
		
		
		DisplayMoney = new JLabel();
		next = new JTextField();
		next.setPreferredSize(new Dimension(50,20));
		next.addActionListener(new Apply());
		
		undo = new JButton("undo");
		undo.addActionListener(new Undo());
		
		apply = new JButton("apply");
		apply.addActionListener(new Apply());
		
		VerticalLayer h = new VerticalLayer();
		h.addComp(DisplayMoney);
		h.addComp(next);
		//h.addComp(apply);
		//h.addComp(undo);
		
		
		this.setLayout(new GridBagLayout());
		this.add(h);
		
		this.setVisible(true);	
		
	}
	
	public int getCustomMoney(){
		String s = next.getText();
		try{
			int i = Integer.valueOf(s);
			return i;
		}
		catch (Exception e){
			return 0;
		}
	}
	
	private void DisplayCount(){
		DisplayMoney.setText(String.valueOf(money));
	}
	

	private class Apply implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Next();
		}
		
	}
	private class Undo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Undo();
		}
		
	}
	public static void main(String argv[]){
		JFrame main = new JFrame();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setContentPane(new MaxMoneySingleTracker());
		main.pack();
		main.setVisible(true);
		
	}
}
