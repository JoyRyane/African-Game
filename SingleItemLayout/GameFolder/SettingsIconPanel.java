package GameFolder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SettingsIconPanel{
	public JPanel createSettingsPanel(ImageIcon icon, ActionListener actionListener) {
        RoundedPanel panel = new RoundedPanel(20, new Color(99, 181, 147));
        panel.setBorder(new EmptyBorder(0,0,0,0));
        
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.setBorder(new EmptyBorder(0,2,0,2));
        button.setBackground(new Color(99, 181, 147));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(actionListener);
        
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon finalResizedIcon = new ImageIcon(resizedImg);
        JLabel iconLabel = new JLabel(finalResizedIcon); 
        
        button.add(iconLabel);        
        panel.add(button);

        return panel;
    }
} 
