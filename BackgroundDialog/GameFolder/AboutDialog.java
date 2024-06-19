package GameFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AboutDialog extends JDialog {
	
	JPanel contentPanel;
	JScrollPane scrollPane;
	
	public AboutDialog(JFrame parent) {
        super(parent, "Fullscreen Popup", true);
        parent.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                updateDialogSizeAndPosition();
            }
            public void componentResized(ComponentEvent evt) {
                updateDialogSizeAndPosition();
            }
        });
        initUI(); 
        layoutUI();
    } 
 
	private void initUI() {
        setUndecorated(true);
        setBackground(new Color(0,0,0,200)); 
        updateDialogSizeAndPosition();
    }
	
	public void layoutUI() {
		contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setForeground(Color.WHITE);
         
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setOpaque(false);

        logoPanel.setPreferredSize(new Dimension(250, 100));
        logoPanel.setMaximumSize(new Dimension(250, 100));
        
        JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        returnPanel.setOpaque(false);

        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        
        contentPanel.add(labelPanel,BorderLayout.CENTER);
        contentPanel.add(returnPanel, BorderLayout.PAGE_START);
        labelPanel.add(logoPanel);
        
        try {
            BufferedImage iconImage = ImageIO.read(new File("icon\\return.png"));
            int scaledWidth = 20; 
            int scaledHeight = 20; 
            Image scaledIconImage = iconImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            
            ImageIcon icon = new ImageIcon(scaledIconImage);            
            JLabel iconLabel = new JLabel(icon);
            
            returnPanel.add(iconLabel);
            
            iconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                    set.dispose();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace(); 
        }	
        
        try {
            BufferedImage iconImage = ImageIO.read(new File("icon\\logo.png"));
            int scaledWidth = 150; 
            int scaledHeight = 100; 
            Image scaledIconImage = iconImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            
            ImageIcon icon = new ImageIcon(scaledIconImage);
            JLabel iconLabel = new JLabel(icon);
            logoPanel.add(iconLabel,BorderLayout.CENTER);
        } catch (IOException ex) {
            ex.printStackTrace(); 
        }	
        
        JPanel label1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        label1Panel.setOpaque(false);
        label1Panel.setPreferredSize(new Dimension(250, 50));
        label1Panel.setMaximumSize(new Dimension(250, 50));
        
        JLabel label1 = new JLabel("How to Play Item Sort");
        label1.setForeground(Color.WHITE);
        Font label1Font = label1.getFont();
        label1.setFont(new Font(label1Font.getName(), Font.BOLD | Font.ITALIC, 20));
        
        label1Panel.add(label1);
        labelPanel.add(label1Panel);
        
        JPanel label2Panel = new JPanel();
        label2Panel.setOpaque(false);
        label2Panel.setPreferredSize(new Dimension(400, 120));
        label2Panel.setMaximumSize(new Dimension(700, 120));
        
        labelPanel.add(label2Panel);
        
        JLabel label2 = new JLabel("<html>Item Sort is a classic African game that seeks to and infact "
        		+ "accompanies babies during their development,<br>molding cognitive abilities, language "
        		+ "(both native and secondary) proficiency, and many more</html>");
        label2.setForeground(Color.WHITE);
        Font label2Font = label2.getFont();
        label2.setFont(new Font(label2Font.getName(), Font.PLAIN, 12));
        
        label2Panel.add(label2);
        
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 

        getContentPane().add(scrollPane);
	}

    private void updateDialogSizeAndPosition() {
        JFrame parent = (JFrame) getParent();
        Insets parentInsets = parent.getInsets();

        int dialogWidth = parent.getWidth() - parentInsets.left - parentInsets.right;
        int dialogHeight = parent.getHeight() - parentInsets.top - parentInsets.bottom;

        setSize(dialogWidth, dialogHeight);

        int parentX = parent.getLocation().x;
        int parentY = parent.getLocation().y;

        setLocation(parentX + parentInsets.left, parentY + parentInsets.top);
    }
}

