package mp247gui.BonusStars.GUI.Utils;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class HorizontalLayer extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout l;
    private JPanel Central;
    public HorizontalLayer(){
        

        this.setLayout(new GridBagLayout());
        
        
        Central = new JPanel();
        l = new GridBagLayout();
        Central.setLayout(l);
        this.add(Central);
       
    }
    
   
    public void addComp(Component c){
        JPanel j = new JPanel(new FlowLayout());
        j.add(c);
       
        Central.add(j);
    }
}
