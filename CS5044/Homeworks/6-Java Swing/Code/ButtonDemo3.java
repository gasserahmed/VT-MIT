package edu.vt.cs5044;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * ButtonDemo3 class for Homework 6
 *
 * @author gasser18
 * @version Nov 20, 2019
 *
 */
public class ButtonDemo3 extends JPanel implements ActionListener
{
    JButton left;
    JButton middle;
    JButton right;
    
    public ButtonDemo3() 
    {
        left = new JButton("Disable >");
        left.setActionCommand("disable");
        
        middle = new JButton("Middle");
        
        right = new JButton("< Enable");
        right.setActionCommand("enable");
        right.setEnabled(false);
        
        left.addActionListener(this);
        right.addActionListener(this);
        
        this.add(left);
        this.add(middle);
        this.add(right);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if ("disable".equals(e.getActionCommand())) 
        {
            left.setEnabled(false);
            middle.setEnabled(false);
            right.setEnabled(true);
        } 
        else 
        {
            left.setEnabled(true);
            middle.setEnabled(true);
            right.setEnabled(false);
        }
    }
    
    private static void createAndShowGUI() 
    {
        JFrame frame = new JFrame("ButtonDemo3 by gasser18");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new ButtonDemo3();
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
