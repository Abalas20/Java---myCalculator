import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;

public class CalculationButtons {
  // A class that provides  the buttons for the calculator based on a 'caller'  type.
	private Font charFont = new Font("Monospaced", Font.PLAIN, 30);
	
    public JButton[] buttonArrayBuilder(String caller) {
        switch (caller) {
            case "Calculator":
                // creates an array of buttons for the calculator panel
                // names for the buttons
                String[] bNamesCalculator = {"%", "√", "X²", "1/X", "CE", "C", "⌫", "÷", "7", "8", "9", "x",
                        "4", "5", "6", "-", "1", "2", "3", "+", "±", "0", ",", "="};
                // key codes for the keystrokes
                int[] keyEvents = {1000, 1000, 1000, 1000, 1000, KeyEvent.VK_DELETE, 8, 111,
                        KeyEvent.VK_NUMPAD7, KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD9, 106, KeyEvent.VK_NUMPAD4,
                        KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6, 109, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2,
                        KeyEvent.VK_NUMPAD3, 107, 1000, KeyEvent.VK_NUMPAD0, 110, 10
                };

                JButton[] calculatorButtons = new JButton[24];
                for (int i = 0; i < calculatorButtons.length; i++) {
                    calculatorButtons[i] = buttonMaker(bNamesCalculator[i], keyEvents[i]);
                }
                return calculatorButtons;

            case "Standard":
                // creates an array of buttons for all types of panels
                String[] bNamesStandard = {"empty", "CE", "⌫", "7", "8", "9",
                        "4", "5", "6", "1", "2", "3", "empty", "0", ","}; // names for the buttons
                int[] keyEvents2 = {1000, KeyEvent.VK_DELETE, 8,
                        KeyEvent.VK_NUMPAD7, KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD9, KeyEvent.VK_NUMPAD4,
                        KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2,
                        KeyEvent.VK_NUMPAD3, 1000, KeyEvent.VK_NUMPAD0, 110}; // key codes for the keystrokes

                JButton[] standardButtons = new JButton[15];
                for (int i = 0; i < standardButtons.length; i++) {
                    standardButtons[i] = buttonMaker(bNamesStandard[i], keyEvents2[i]);
                    if ( standardButtons[i].getName().equals("empty") ) {
                        standardButtons[i].setVisible(false);
                    }
                }
                return standardButtons;

            default:
                return null;
        }
    }
    public JButton buttonMaker (String name, int virtualKey ){
        /**
         * Creates and returns a Button based on the provided name and key.             *
         */
        JButton button = new JButton(name);
        button.setName(name);
        button.setActionCommand(name);
        button.setBackground( Color.GRAY);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFont(charFont);
        button.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if ( button.getModel().isRollover() ) {
                    button.setBackground(Color.LIGHT_GRAY);
                }
                else {
                    button.setBackground(Color.GRAY);
                }
            }
        });
        // When virtualKey == 1000, a created button is invisible and has not actions attached
        if ( virtualKey != 1000 ) {
            InputMap im = button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = button.getActionMap();
            im.put(KeyStroke.getKeyStroke(virtualKey, 0), "clickMe");
            am.put("clickMe", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    btn.doClick();
                }
            });
            return button;
        }
        else {
            return button;
        }
    }
}

