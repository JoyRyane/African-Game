package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoadingLanguageDialog extends JDialog {

     RoundedPanel contentPanel, languageOptionPanel, languageOption;
     JButton button;
     JComboBox dropDownNativeLanguage, dropDownSecondaryLanguage;
     JLabel label,languageLabel,pLanguageLabel,sLanguageLabel, nativeLanguageLabel,secondaryLanguageLabel;
     ImageIcon resizedIcon, resizedLanguageIcon,resizedPLanguageIcon;
    String secondaryLanguage[] = {"English","French"};

    String nativeLanguage[] = {"Nufi'i","Munga'ka"};
    
    public LoadingLanguageDialog(JFrame parent) { 
        super(parent, "Popup Dialog", true);
        initUI(); 
        layoutUI();
        handleEventsUI(); 
    }  
 

	private void handleEventsUI() { 
        this.addComponentListener(new ComponentAdapter() {
            @Override 
            public void componentResized(ComponentEvent e) {
                updateLayout();
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
        
        dropDownNativeLanguage = new JComboBox(nativeLanguage);
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
        
        
        
        dropDownSecondaryLanguage = new JComboBox(secondaryLanguage);
        
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
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LoadingLanguageDialog.this);
                
                JDialog parentDialog = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());

                JFrame grandParentFrame = (JFrame) SwingUtilities.getWindowAncestor(parentDialog); 
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof LoadingLanguageBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                if (grandParentFrame instanceof LoadingPageFrame) {
                    LoadingPageFrame loadingPageFrame = (LoadingPageFrame) grandParentFrame;
                    GreetingsDialog greetings = new GreetingsDialog(loadingPageFrame);

                    loadingPageFrame.removeImagePanel();
                    loadingPageFrame.removeProgressBar();
                    greetings.setVisible(true);
                    
                    Timer timer = new Timer(3000, event -> {
                    	loadingPageFrame.removeBackgroundMusic();
                    	loadingPageFrame.dispose();
                    	openLandingPage();
                    });
                    timer.setRepeats(false);
                    timer.start();
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
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.add(languageOption, BorderLayout.SOUTH); 

        setContentPane(contentPanel);
    }

    private void layoutUI() {
        this.setSize(200, 300);
        this.setMinimumSize(new Dimension(100,200));
        this.setMaximumSize(new Dimension(300,400));
        
        this.setLocationRelativeTo(getParent());
        this.updateLayout();
    }

    private void updateLayout() {
        Dimension parentSize = getParent().getSize();
        int width = parentSize.width;

        if (width < 500) {
            layoutUI1();
        } else if (width >= 500 && width <= 800) {
            layoutUI2();
        } else {
            layoutUI3();
        }
    }

    private void layoutUI1() {
        label.setIconTextGap(5);
        label.setIcon(resizeIcon(10, 10));
        label.setFont(new Font(label.getFont().getName(), Font.BOLD | Font.ITALIC, 10)); 
    }

    private void layoutUI2() {
        label.setIconTextGap(5);
        label.setIcon(resizeIcon(30, 30));
        label.setFont(new Font(label.getFont().getName(), Font.BOLD | Font.ITALIC, 20)); 
    }

    private void layoutUI3() {
        label.setIconTextGap(5);
        label.setIcon(resizeIcon(50, 50));
        label.setFont(new Font(label.getFont().getName(), Font.BOLD | Font.ITALIC, 25)); 
    }

    private ImageIcon resizeIcon(int width, int height) {
        Image img = resizedIcon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(resizedImg);
    }

    private void updatePosition() {
        setLocationRelativeTo(getParent()); 
    }
    
    private void openLandingPage() {
    	LandingPageFrame frame = new LandingPageFrame();
        frame.setVisible(true);
    }
}