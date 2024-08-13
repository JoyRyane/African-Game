package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SettingsDialog extends JDialog {

    private RoundedPanel contentPanel, languageOptionPanel, languageOption;
    private JButton button;
    private JLabel settingsLabel, languageLabel, soundLabel, themeLabel, limitLabel;
    private ImageIcon resizedSettingsIcon, resizedLanguageIcon, resizedSoundIcon, resizedThemeIcon, resizedLimitIcon;
    private JPanel soundPanel;
    private BackgroundMusicController musicController;
    private ToggleButton toggleButton;
    private LandingPageFrame landingPageFrame;
    private boolean initialState;

    public SettingsDialog(JFrame parent, BackgroundMusicController musicController) { 
        super(parent, "Popup Dialog", true);
        this.musicController = musicController; // Initialize the musicController
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
        setBackground(new Color(0, 0, 0, 0));
 
        contentPanel = new RoundedPanel(10, new Color(96, 151, 95));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        contentPanel.setOpaque(false);
        contentPanel.setBackground(null);
        
        toggleButton = new ToggleButton("OFF", "ON", musicController); // Pass the musicController here
        toggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        initialState = toggleButton.getCurrentState(); // Store initial state
        
        button = new RoundedButton("OK");
        button.setBackground(new Color(240, 164, 75));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(SettingsDialog.this);
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof SettingsBackgroundDialog) {
                            dialog.dispose();
                        } 
                    }
                }
                dispose(); // Close the dialog
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
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(SettingsDialog.this);
                
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();

                if (parentFrame instanceof LandingPageFrame) {
                    LandingPageFrame landingPageFrame = (LandingPageFrame) parentFrame;
                    landingPageFrame.removeBackgroundMusic();
                }
                
                toggleButton.setState(initialState);
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof SettingsBackgroundDialog) {
                            dialog.dispose();
                        } 
                    }
                }
                
                JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                dialog.dispose();
            }
        });
        
        JPanel closePanel = new JPanel(new BorderLayout());
        closePanel.setOpaque(false);
        
        soundPanel = new JPanel();
        soundPanel.setLayout(new FlowLayout());
        soundPanel.setOpaque(false);
 

        ImageIcon settingsImageIcon = new ImageIcon("icon\\settings.png");
        Image settingsImg = settingsImageIcon.getImage();
        Image resizedSettingsImg = settingsImg.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        resizedSettingsIcon = new ImageIcon(resizedSettingsImg);
        settingsLabel = new JLabel("Settings", resizedSettingsIcon, JLabel.CENTER);
        settingsLabel.setForeground(Color.WHITE);
        Font settingsFont = settingsLabel.getFont();
        settingsLabel.setFont(new Font(settingsFont.getName(), Font.BOLD | Font.ITALIC, 25));

        languageOption = new RoundedPanel(10, new Color(107, 218, 105));
        languageOption.setLayout(new GridBagLayout());
        languageOption.setBorder(new EmptyBorder(5, 5, 5, 5));
        languageOption.setPreferredSize(new Dimension(200, 300));
        
        languageOptionPanel = new RoundedPanel(10, new Color(0, 0, 0));
        languageOptionPanel.setOpaque(false);
       

        ImageIcon soundImageIcon = new ImageIcon("icon\\sound.png");
        Image soundImg = soundImageIcon.getImage();
        Image resizedSoundImg = soundImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        resizedSoundIcon = new ImageIcon(resizedSoundImg);
        soundLabel = new JLabel("Music", resizedSoundIcon, JLabel.CENTER);
        soundLabel.setForeground(Color.WHITE);
        Font soundFont = settingsLabel.getFont();
        soundLabel.setFont(new Font(soundLabel.getName(), Font.BOLD, 22));
        
        ImageIcon languageImageIcon = new ImageIcon("icon\\language.png");
        Image languageImg = languageImageIcon.getImage();
        Image resizedLanguageImg = languageImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        resizedLanguageIcon = new ImageIcon(resizedLanguageImg);
        languageLabel = new JLabel("Language", resizedLanguageIcon, JLabel.CENTER);
        languageLabel.setForeground(Color.WHITE);
        Font languageFont = settingsLabel.getFont();
        languageLabel.setFont(new Font(languageLabel.getName(), Font.BOLD, 22));
        languageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        languageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(SettingsDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof SettingsBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
                
                LanguageBackgroundDialog languageDialog = new LanguageBackgroundDialog(parentFrame);
                languageDialog.setVisible(true);
            }
        });
        
        ImageIcon themeImageIcon = new ImageIcon("icon\\theme.png");
        Image themeImg = themeImageIcon.getImage();
        Image resizedThemeImg = themeImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        resizedThemeIcon = new ImageIcon(resizedThemeImg);
        themeLabel = new JLabel("Theme", resizedThemeIcon, JLabel.CENTER);
        themeLabel.setForeground(Color.WHITE);
        Font themeFont = themeLabel.getFont();
        themeLabel.setFont(new Font(themeLabel.getName(), Font.BOLD, 22));
        themeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        themeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(SettingsDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof SettingsBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
                
                ThemeBackgroundDialog themeDialog = new ThemeBackgroundDialog(parentFrame);
                themeDialog.setVisible(true);
            }
        });
        
        ImageIcon limitImageIcon = new ImageIcon("icon\\limit.png");
        Image limitImg = limitImageIcon.getImage();
        Image resizedLimitImg = limitImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        resizedLimitIcon = new ImageIcon(resizedLimitImg);
        limitLabel = new JLabel("Daily Limit", resizedLimitIcon, JLabel.CENTER);
        limitLabel.setForeground(Color.WHITE);
        Font limitFont = themeLabel.getFont();
        limitLabel.setFont(new Font(limitLabel.getName(), Font.BOLD, 22));
        limitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        limitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(SettingsDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof SettingsBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose(); 
                
                DailyLimitBackgroundDialog languageDialog = new DailyLimitBackgroundDialog(parentFrame);
                languageDialog.setVisible(true);
            }
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(25, 5, 5, 5);
        
        soundPanel.add(soundLabel);
        soundPanel.add(toggleButton);
        gbc.gridy++;
        languageOption.add(soundPanel, gbc);
        gbc.gridy++;
        languageOption.add(languageLabel, gbc);
        gbc.gridy++;
        languageOption.add(themeLabel, gbc);
        gbc.gridy++;
        languageOption.add(limitLabel, gbc);
        gbc.gridy++;
        
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        languageOption.add(button, gbc);
        
        GridBagConstraints gbcCloseIconLabel = new GridBagConstraints();
        gbcCloseIconLabel.anchor = GridBagConstraints.NORTHEAST;
        gbcCloseIconLabel.insets = new Insets(5, 20, 17, 5);
        
        closePanel.add(closeIconLabel, BorderLayout.EAST);
        contentPanel.add(closePanel, BorderLayout.NORTH);
        contentPanel.add(settingsLabel, BorderLayout.CENTER);
        contentPanel.add(languageOption, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }
    
    public boolean getInitialState() {
        return initialState;
    }

    private void layoutUI() {
        this.setSize(300, 400);
        this.setLocationRelativeTo(getParent());
    }

    private ImageIcon resizeIcon(int width, int height) {
        Image img = resizedSettingsIcon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(resizedImg);
    }

    private void updatePosition() {
        setLocationRelativeTo(getParent());
    }
}
