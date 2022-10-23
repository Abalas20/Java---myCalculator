import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

public class GUI extends JFrame{

	private JMenu menu;
	
	private Dimension panelDim = new Dimension(391, 438);
	
	private JPanel cardPanel;
	
    private final static String calculatorPanel = "Calculator";
    private final static String datesPanel = "Dates";
    private final static String volumePanel = "Volume";
    private final static String lengthPanel = "Length";
    private final static String weightAndMassPanel = "WeightAndMass";
    private final static String temperaturePanel = "Temperature";
    private final static String energyPanel = "Energy";
    private final static String powerPanel = "Power";
    private final static String surfacePanel = "Surface";
    private final static String speedPanel = "Speed";
    
    private final static String[] itemNames = {"Calculator","Dates","Volume","Length","WeightAndMass",
    										   "Temperature","Energy","Power","Surface","Speed",};
    private void cardInitialization() {
        cardPanel = new JPanel(new CardLayout());
        cardPanel.add(new Calculator(), calculatorPanel);
       // cardPanel.add(new Dates2(), datesPanel);
       // cardPanel.add(new Volume(), volumePanel);
       // cardPanel.add(new Length(), lengthPanel);
       // cardPanel.add(new WeightAndMass(), weightAndMassPanel);
       // cardPanel.add(new Temperature(), temperaturePanel);
       // cardPanel.add(new Energy(), energyPanel);
       // cardPanel.add(new Power(), powerPanel);
       // cardPanel.add(new Surface(), surfacePanel);
       // cardPanel.add(new Speed(), speedPanel);
    }
    
    class MenuActionListener implements ActionListener{
 
        // An listens for the choice made in the menu bar and puts the correct card on top
         
        @Override
        public void actionPerformed( ActionEvent e) {
            String command = e.getActionCommand();
            validate();
            repaint();
            pack();
            CardLayout c1 = (CardLayout) (cardPanel.getLayout());
            c1.show(cardPanel, command);
            menu.setText("Menu - " + command);
        }
    }
    
    private void createMenuBar(){
        
    	// Creates the menu bar on top of the program
         
        MenuActionListener mal = new MenuActionListener();
        menu = new JMenu("Menu");

        for(int i = 0; i < itemNames.length; i++){
            JMenuItem menuItem = new JMenuItem(itemNames[i]);
            menuItem.addActionListener(mal);
            menu.add(menuItem);
        }
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    
    private void createAndShowUI(){
       // This method sets up the GUI 
        cardInitialization();
        createMenuBar();
        cardPanel.setPreferredSize(panelDim);
        getContentPane().add(cardPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        ImageIcon image = new ImageIcon("icon.png"); 
		setIconImage(image.getImage());
		setTitle("Calculator App");
        setVisible(true);
        setResizable(false);
        getContentPane().setBackground(ColorUIResource.getHSBColor(0.31f,0.09f,0.97f));
    }
	
	public static void main(String[] args) {
        /**
         * Swing elements aren't thread safe, therefore we need to run the below function to make it safe for threads
         */
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                GUI gui = new GUI();
				gui.createAndShowUI();
            }
        });
    }

	}

}
