package edu.vt.cs5044;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * CelsiusConverter class for Homework 6
 *
 * @author gasser18
 * @version Nov 20, 2019
 *
 */
public class CelsiusConverter extends JPanel implements ActionListener
{
    private final JButton convertButton;
    private final JLabel celsiusLabel;
    private final JLabel fahrenheitLabel;
    private final JTextField txtField;
    
    public CelsiusConverter() 
    {
        convertButton = new JButton("Convert");
        celsiusLabel = new JLabel("Celcius");
        fahrenheitLabel = new JLabel("Fahrenheit");
        txtField = new JTextField(8);
        
        convertButton.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 20, 10)); // 2x2 layout, with small gaps
        panel.add(txtField);
        panel.add(celsiusLabel);
        panel.add(convertButton);
        panel.add(fahrenheitLabel);
        
        this.add(panel);
    }
    

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Parse degrees Celsius as a double and convert to Fahrenheit.
        int tempFahr = (int) ((Double.parseDouble(txtField.getText()))
                    * 1.8 + 32);
        fahrenheitLabel.setText(tempFahr + " Fahrenheit");        
    }

    private static void createAndShowGUI() 
    {
        JFrame frame = new JFrame("CelsiusConverter by gasser18");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new CelsiusConverter();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
                
            }
        });
    }
}
