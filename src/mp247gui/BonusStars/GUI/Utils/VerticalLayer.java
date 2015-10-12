package mp247gui.BonusStars.GUI.Utils;

import static javax.swing.BoxLayout.Y_AXIS;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class VerticalLayer extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BoxLayout l;
    private JPanel Central;
    public VerticalLayer(){
        

        this.setLayout(new GridBagLayout());
        
        
        Central = new JPanel();
        l = new BoxLayout(Central, Y_AXIS);
        Central.setLayout(l);
        this.add(Central);
       
    }
    
   
    public void addComp(Component c){
        JPanel j = new JPanel(new GridBagLayout());
        Central.add(Box.createVerticalGlue());
        j.add(c);
        Central.add(j);
    }
}
