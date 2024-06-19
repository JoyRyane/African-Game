package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LanguageDialog extends JDialog {

    private RoundedPanel contentPanel, languageOptionPanel, languageOption;
    private JButton button;
    private JComboBox dropDownNativeLanguage, dropDownSecondaryLanguage;
    private JLabel label,nativeLanguageLabel,secondaryLanguageLabel;
    private ImageIcon resizedIcon;
    String secondaryLanguageArray[] = {"English","French"};

    String nativeLanguageArray[] = {"Nufi'i","Munga'ka"};
    
    public LanguageDialog(JFrame parent) { 
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
        
        
        
        ImageIcon closeIcon = new ImageIcon("icon\\close.png");
        Image closeImg = closeIcon.getImage();
        Image resizedCloseImg = closeImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedCloseIcon = new ImageIcon(resizedCloseImg);

        JLabel closeIconLabel = new JLabel(resizedCloseIcon);
        closeIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LanguageDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof LanguageBackgroundDialog) {
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

        ImageIcon imageIcon = new ImageIcon("icon\\language.png");
        Image img = imageIcon.getImage();
        Image resizedImg = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        resizedIcon = new ImageIcon(resizedImg);
        label = new JLabel("Language", resizedIcon, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.BOLD | Font.ITALIC, 20));

        languageOption = new RoundedPanel(10, new Color(107, 218, 105));
        languageOption.setLayout(new GridBagLayout());
        languageOption.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        languageOptionPanel = new RoundedPanel(10, new Color(0, 0, 0));
        languageOptionPanel.setOpaque(false);

        nativeLanguageLabel = new JLabel("Native Language");
        nativeLanguageLabel.setForeground(Color.WHITE);
        nativeLanguageLabel.setFont(new Font(nativeLanguageLabel.getName(), Font.BOLD, 15));
        
        ImageIcon optionIcon = new ImageIcon("icon\\asterisk.png");

        Image optionImage = optionIcon.getImage();

        Image resizedImage = optionImage.getScaledInstance(10, 10, Image.SCALE_SMOOTH);

        ImageIcon resizedOptionIcon = new ImageIcon(resizedImage);
        dropDownNativeLanguage = new JComboBox(nativeLanguageArray);
        
        dropDownNativeLanguage.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(value.toString()); // Set the text of the item

                if (isSelected) {
                    setBackground(Color.GREEN); // Change background color when selected
                    setForeground(Color.WHITE); // Change text color when selected
                    setIcon(resizedOptionIcon);
                } else {
                    setBackground(new Color(154, 218, 123)); // Normal background color
                    setForeground(Color.WHITE); // Normal text color
                }
                
                return this;
            }
        });
        
        secondaryLanguageLabel = new JLabel("Secondary Language");
        secondaryLanguageLabel.setForeground(Color.WHITE);
        secondaryLanguageLabel.setFont(new Font(secondaryLanguageLabel.getName(), Font.BOLD, 15));
        
        dropDownSecondaryLanguage = new JComboBox(secondaryLanguageArray);
        
        dropDownSecondaryLanguage.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(value.toString()); // Set the text of the item

                if (isSelected) {
                    setBackground(Color.GREEN); // Change background color when selected
                    setForeground(Color.WHITE); // Change text color when selected
                    setIcon(resizedOptionIcon);
                } else {
                    setBackground(new Color(154, 218, 123)); // Normal background color
                    setForeground(Color.WHITE); // Normal text color
                }
                
                return this;
            }
        });
        
        button = new RoundedButton("OK");
        button.setBackground(new Color(240, 164, 75));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LanguageDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof LanguageBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        languageOption.add(nativeLanguageLabel, gbc);

        gbc.gridy++;
        languageOption.add(dropDownNativeLanguage,gbc);
        gbc.gridy++;
        languageOption.add(secondaryLanguageLabel, gbc);
        gbc.gridy++;
        languageOption.add(dropDownSecondaryLanguage,gbc);
        gbc.gridy++;
        
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0);
        languageOption.add(button, gbc);
        
        
        GridBagConstraints gbcCloseIconLabel = new GridBagConstraints();
        gbcCloseIconLabel.anchor = GridBagConstraints.NORTHEAST;
        gbcCloseIconLabel.insets = new Insets(5, 15, 5, 5);
        
        closePanel.add(closeIconLabel,BorderLayout.EAST);
        contentPanel.add(closePanel,BorderLayout.NORTH);
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.add(languageOption, BorderLayout.SOUTH); 

        setContentPane(contentPanel);
    }

    private void layoutUI() {
        this.setSize(200, 300);
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
        setLocationRelativeTo(getParent());
    }
}