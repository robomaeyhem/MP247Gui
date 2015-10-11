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

import mp247gui.BonusStars.GUI.Utils.HorizontalLayer;
import mp247gui.BonusStars.GUI.Utils.VerticalLayer;

public class MinigameSingleTracker extends JPanel {
	
	
	private int money;
	
	private JLabel DisplayMoney;
	private JButton add10;
	private JButton addX;
	private JTextField X;
	private JButton undo;
	
	private Stack<Integer> undoStack;
	
	public MinigameSingleTracker(){
		Init();
	}
	public MinigameSingleTracker(String title){
		Init();
		this.setBorder(BorderFactory.createTitledBorder(title));
	}
	
	public void Add(int n){
		undoStack.push(n);
		money += n;
		DisplayCount();
	}
	
	public void Undo(){
		if (!undoStack.empty()){
			int n = undoStack.pop();
			money -= n;
			DisplayCount();
		}
	}
	
	public void AddCustomMoney(){
		Add(getCustomMoney());
		X.setText("");
	}
	
	public void Reset(){
		money = 0;
		DisplayCount();
	}
	private void Init(){
		money = 0;
		InitComponents();
		DisplayCount();
		undoStack = new Stack<>();
	}
	
	private void InitComponents(){
		add10 = new JButton("Add 10");
		add10.addActionListener(new Add10());
		
		addX = new JButton("Add X");
		addX.addActionListener(new AddX());
		
		
		DisplayMoney = new JLabel();
		X = new JTextField();
		X.setPreferredSize(new Dimension(50,20));
		X.addActionListener(new AddX());
		undo = new JButton("undo");
		undo.addActionListener(new UndoAdd());
		
		VerticalLayer h1 = new VerticalLayer();
		h1.addComp(DisplayMoney);
		h1.addComp(X);
		
		
		VerticalLayer h2 = new VerticalLayer();
		h2.addComp(add10);
		//h2.addComp(addX);
		h2.addComp(undo);
		
		HorizontalLayer l = new HorizontalLayer();
		l.addComp(h1);
		l.addComp(h2);
		
		this.setLayout(new GridBagLayout());
		this.add(l);
		
		this.setVisible(true);	
		
	}
	
	public int getCustomMoney(){
		String s = X.getText();
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
	
	
	private class Add10 implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Add(10);
		}	
	}
	private class AddX implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			AddCustomMoney();
		}
		
	}
	
	private class UndoAdd implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Undo();
		}
		
	}
	public static void main(String argv[]){
		JFrame main = new JFrame();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setContentPane(new MinigameSingleTracker());
		main.pack();
		main.setVisible(true);
		
	}
}
