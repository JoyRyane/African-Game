package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LimitDialog extends JDialog {

    private RoundedPanel contentPanel, limitOption;
    private JButton button;
    private JLabel label,secondaryLanguageLabel;
    private ImageIcon resizedIcon;
    public LimitDialog(JFrame parent) { 
        super(parent, "Popup Dialog", true);
        initUI(); 
        layoutUI();
        handleEventsUI(); 
    }  
 

	private void handleEventsUI() { 
        this.addComponentListener(new ComponentAdapter() {
            @Override 
            public void componentResized(ComponentEvent e) {
            }
        });
 
        getParent().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                updatePosition();
            }
        });
    }

    private void initUI() {
        setLocationRelativeTo(getParent());
        setUndecorated(true);
        setResizable(true);
        setBackground(new Color(0,0,0,0));
 
        contentPanel = new RoundedPanel(10, new Color(96, 151, 95));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(12,12,12,12));
        contentPanel.setOpaque(false);
        contentPanel.setBackground(null);
        
        button = new RoundedButton("OK");
        button.setBackground(new Color(240, 164, 75));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LimitDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof DailyLimitBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });
        
        ImageIcon closeIcon = new ImageIcon("icon\\close.png");
        Image closeImg = closeIcon.getImage();
        Image resizedCloseImg = closeImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedCloseIcon = new ImageIcon(resizedCloseImg);

        JLabel closeIconLabel = new JLabel(resizedCloseIcon);
        closeIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LimitDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof DailyLimitBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });
        
        JPanel closePanel = new JPanel(new BorderLayout());
        closePanel.setOpaque(false);

        ImageIcon imageIcon = new ImageIcon("icon\\limit.png");
        Image img = imageIcon.getImage();
        Image resizedImg = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        resizedIcon = new ImageIcon(resizedImg);
        label = new JLabel("Daily Limit", resizedIcon, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.BOLD | Font.ITALIC, 20));

        limitOption = new RoundedPanel(10, new Color(107, 218, 105));
        limitOption.setLayout(new GridBagLayout());
        limitOption.setPreferredSize(new Dimension(200,210));
        limitOption.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel text1 = new JLabel("<html>Enter the number of times <br> your child can play</html>");
        text1.setForeground(Color.WHITE);
        Font textFont = text1.getFont();
        text1.setFont(new Font(textFont.getName(), Font.BOLD, 15));
        
        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(170,20));
        input.setBackground(new Color(154,218,123));
        input.setForeground(Color.WHITE);
        Font inputFont = input.getFont();
        input.setFont(new Font(inputFont.getName(), Font.BOLD, 15));
        input.setBorder(null);
        
        JLabel hint = new JLabel("<html>e.g 5,10... </html>");
        hint.setForeground(Color.WHITE);
        Font hintFont = hint.getFont();
        hint.setFont(new Font(hintFont.getName(), Font.BOLD, 17));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0); // Adding top padding
        gbc.gridy++;
        limitOption.add(text1,gbc);
        gbc.gridy++;
        limitOption.add(input,gbc);
        gbc.gridy++;
        limitOption.add(hint,gbc);
        gbc.gridy++;
        limitOption.add(button, gbc);
        
        
        GridBagConstraints gbcCloseIconLabel = new GridBagConstraints();
        gbcCloseIconLabel.anchor = GridBagConstraints.NORTHEAST; 
        gbcCloseIconLabel.insets = new Insets(5, 15, 5, 5); // Add some padding
        
        closePanel.add(closeIconLabel,BorderLayout.EAST);
        contentPanel.add(closePanel,BorderLayout.NORTH);
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.add(limitOption, BorderLayout.SOUTH); 

        setContentPane(contentPanel);
    }

    private void layoutUI() {
        this.setSize(300, 300);
        this.setMinimumSize(new Dimension(100,200));
        this.setMaximumSize(new Dimension(300,400));
        
        this.setLocationRelativeTo(getParent());
    }

    private ImageIcon resizeIcon(int width, int height) {
        Image img = resizedIcon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(resizedImg);
    }

    private void updatePosition() {
        setLocationRelativeTo(getParent()); // Update dialog's position relative to the parent frame
    }
}