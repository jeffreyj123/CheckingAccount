/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkingaccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI component to handle checking account display and modification.
 * 
 * @author jeffrey_jacinto
 */
public class COptionsPanel extends JPanel {
    private JLabel prompt;
    private JRadioButton newTrans, transList, checkList, depositList;
    
    /**
     * Constructs a new COptionsPanel object with JRadioButtons representing
     * options to add transaction and display transaction, check withdrawal, or
     * deposit data.
     */
    public COptionsPanel()
    {
        prompt = new JLabel("Select checking account option: ");
        prompt.setFont(new Font("Helvetica", Font.BOLD, 24));
        
        newTrans = new JRadioButton("Enter new Transaction");
        newTrans.setBackground(Color.LIGHT_GRAY);
        
        transList = new JRadioButton("List all Transactions");
        transList.setBackground(Color.LIGHT_GRAY);
        
        checkList = new JRadioButton("List all check withdrawals");
        checkList.setBackground(Color.LIGHT_GRAY);
        
        depositList = new JRadioButton("List all deposits");
        depositList.setBackground(Color.LIGHT_GRAY);
        
        ButtonGroup options = new ButtonGroup();
        options.add(newTrans);
        options.add(transList);
        options.add(checkList);
        options.add(depositList);
        
        COptionListener listener = new COptionListener();
        newTrans.addActionListener(listener);
        transList.addActionListener(listener);
        checkList.addActionListener(listener);
        depositList.addActionListener(listener);
        
        
        add(prompt);
        add(newTrans);
        add(transList);
        add(checkList);
        add(depositList);
        
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(400, 100));
    }
    
    private class COptionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();
            if (source == newTrans)
                Main.addTrans();
            else if (source == transList)
                Main.showList(3);
            else if (source == checkList)
                Main.showList(1);
            else
                Main.showList(2);
        }
    }
}
