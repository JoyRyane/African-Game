package GameFolder;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ThemePanel {

    public JPanel createPanel(Color backgroundColor,ImageIcon icon,String labelText , Boolean locked) {
    	
    	RoundedPanel themePanel = new RoundedPanel(10,backgroundColor);
    	themePanel.setPreferredSize(new Dimension(200,190));
    	themePanel.setLayout(new BorderLayout());
    	
        Image themeImg = icon.getImage();
        Image resizedThemeImg = themeImg.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedThemeIcon = new ImageIcon(resizedThemeImg);
        JLabel themeLabel = new JLabel(resizedThemeIcon);
        
        JPanel lock = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lock.setBorder(new EmptyBorder(4,1,1,4));
        lock.setOpaque(false);
        
        if(locked) {
        	ImageIcon themeLockIcon = new ImageIcon("icon\\lock.png");
            Image themeLockImg = themeLockIcon.getImage();
            Image resizedThemeLockImg = themeLockImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon resizedThemeLockIcon = new ImageIcon(resizedThemeLockImg);
            JLabel themeLockLabel = new JLabel(resizedThemeLockIcon);
            lock.add(themeLockLabel);
        }
        
        JPanel themeTitle = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        themeTitle.setBorder(new EmptyBorder(1,4,1,4));
        themeTitle.setOpaque(false);
        
        JLabel themeText = new JLabel(labelText);
        themeText.setForeground(Color.WHITE);
        themeText.setFont(new Font(themeText.getName(), Font.BOLD | Font.ITALIC, 15));
        
        themePanel.add(themeLabel,BorderLayout.NORTH);
        
        themePanel.add(lock,BorderLayout.CENTER);
        themeTitle.add(themeText);
        themePanel.add(themeTitle,BorderLayout.SOUTH);
        themePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return themePanel;
    }
}
