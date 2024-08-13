//package GameFolder;
//
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//
//public class CategoryPanel {
//	 //Remember to add boolean completed very important!
//    public JPanel createPanel(String labelText, ImageIcon icon, Color backgroundColor, Boolean locked) {
//        PanelShadowClass panel = new PanelShadowClass(10, backgroundColor,Color.BLACK,7);
//        panel.setBorder(new EmptyBorder(30, 10, 30, 10));
//        panel.setPreferredSize(new Dimension(70,100));
//        
//        JLabel label = new JLabel(labelText);
//        label.setForeground(Color.WHITE);
//        Font font = label.getFont();
//        label.setFont(new Font(label.getName(), Font.BOLD | Font.ITALIC, 20));
//        panel.add(label);  
//        
//        if(locked) {
//        	ImageIcon lockIcon = new ImageIcon("icon\\lock.png");
//            Image lock = lockIcon.getImage();
//            Image resizedLock = lock.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
//            ImageIcon finalResizedLock = new ImageIcon(resizedLock);
//            JLabel lockLabel = new JLabel(finalResizedLock); 
//            lockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//            panel.add(lockLabel);
//        } 
//        
//        Image img = icon.getImage();
//        Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//        ImageIcon finalResizedIcon = new ImageIcon(resizedImg);
//        
//        JLabel iconLabel = new JLabel(finalResizedIcon); 
//        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        iconLabel.setBorder(new EmptyBorder(50,0,5,0));
//        panel.add(iconLabel);
//        
//        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//        return panel;
//    }
//}

package GameFolder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CategoryPanel {
    public JPanel createPanel(String labelText, ImageIcon icon, Color backgroundColor, boolean locked, boolean completed) {
        PanelShadowClass panel = new PanelShadowClass(10, backgroundColor, Color.BLACK, 7);
        panel.setBorder(new EmptyBorder(30, 10, 30, 10));
        panel.setPreferredSize(new Dimension(70, 100));

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        Font font = label.getFont();
        label.setFont(new Font(label.getName(), Font.BOLD | Font.ITALIC, 20));
        panel.add(label);

        if (locked) {
            ImageIcon lockIcon = new ImageIcon("icon/lock.png");
            Image lock = lockIcon.getImage();
            Image resizedLock = lock.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon finalResizedLock = new ImageIcon(resizedLock);
            JLabel lockLabel = new JLabel(finalResizedLock);
            lockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(lockLabel);
        }

        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon finalResizedIcon = new ImageIcon(resizedImg);

        JLabel iconLabel = new JLabel(finalResizedIcon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(new EmptyBorder(50, 0, 5, 0));
        panel.add(iconLabel);

        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return panel;
    }
}
