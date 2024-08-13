package GameFolder;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ThemeDialog extends JDialog {

    private RoundedPanel contentPanel, languageOption;
    private JButton button;
    private JLabel settingsLabel, starLabel1, starLabel2;
    private ImageIcon resizedSettingsIcon;
    private JPanel theme1, theme2, theme3, theme4, theme5, theme6;
    private ThemePanel themePanel;
    private String selectedThemeImagePath;

    public ThemeDialog(JFrame parent) {
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
        setBackground(new Color(0, 0, 0, 0));

        contentPanel = new RoundedPanel(10, new Color(96, 151, 95));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        contentPanel.setOpaque(false);
        contentPanel.setBackground(null);

        button = new RoundedButton("OK");
        button.setBackground(new Color(240, 164, 75));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applySelectedTheme(e); 
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
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(ThemeDialog.this);

                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof ThemeBackgroundDialog) {
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

        ImageIcon settingsImageIcon = new ImageIcon("icon\\theme.png");
        Image settingsImg = settingsImageIcon.getImage();
        Image resizedSettingsImg = settingsImg.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        resizedSettingsIcon = new ImageIcon(resizedSettingsImg);
        settingsLabel = new JLabel("Theme", resizedSettingsIcon, JLabel.CENTER);
        settingsLabel.setForeground(Color.WHITE);
        Font settingsFont = settingsLabel.getFont();
        settingsLabel.setFont(new Font(settingsFont.getName(), Font.BOLD | Font.ITALIC, 25));

        languageOption = new RoundedPanel(10, new Color(107, 218, 105));
        languageOption.setLayout(new GridBagLayout());
        languageOption.setBorder(new EmptyBorder(5, 5, 5, 5));
        languageOption.setPreferredSize(new Dimension(500, 450));

        JPanel themes = new JPanel();
        themes.setLayout(new GridLayout(2, 3, 7, 10));
        themes.setOpaque(false);
        themes.setPreferredSize(new Dimension(520, 400));
        themes.setBorder(new EmptyBorder(5, 5, 5, 5));

        themePanel = new ThemePanel();
        
        
        
        Properties themeProperties = loadThemeProperties();
        
        ImageIcon starIcon = new ImageIcon("icon\\star.png");
        Image starImg = starIcon.getImage();
        Image resizedStarImg = starImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedStarIcon = new ImageIcon(resizedStarImg);
        starLabel1 = new JLabel(resizedStarIcon);
        starLabel2 = new JLabel(resizedStarIcon);

        
        theme1 = themePanel.createPanel(new Color(192, 26, 250), new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\platform.jpeg"), "FOREST PLATFORM", Boolean.parseBoolean(themeProperties.getProperty("theme1.locked", "true")));
        theme1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!Boolean.parseBoolean(themeProperties.getProperty("theme1.locked", "false"))) {
                    starLabel1.setBounds(e.getX() + 100, e.getY() - 15, 15, 15); // Adjust position relative to mouse click

                    removeStarFromTheme(theme2);
                	selectedThemeImagePath = "icon/platform.jpeg";
                	addStarToPanel(theme1,starLabel1);
                }
            }
        });
        theme2 = themePanel.createPanel(new Color(250, 26, 26), new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\island.jpeg"), "ISLAND", Boolean.parseBoolean(themeProperties.getProperty("theme2.locked", "true")));
        theme2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!Boolean.parseBoolean(themeProperties.getProperty("theme1.locked", "false"))) {
                    removeStarFromTheme(theme1);
                	selectedThemeImagePath = "icon/island.jpeg";
                	addStarToPanel(theme2, starLabel2);
                }
            }
        });
        theme3 = themePanel.createPanel(new Color(26, 143, 250), new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\animals.jpg"), "ANIMALS", Boolean.parseBoolean(themeProperties.getProperty("theme3.locked", "true")));
        theme4 = themePanel.createPanel(new Color(231, 235, 25), new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\dusk.jpg"), "SCENERY", Boolean.parseBoolean(themeProperties.getProperty("theme4.locked", "true")));
        theme5 = themePanel.createPanel(new Color(29, 235, 25), new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\nature.jpg"), "NATURE", Boolean.parseBoolean(themeProperties.getProperty("theme5.locked", "true")));
        theme6 = themePanel.createPanel(new Color(250, 147, 26), new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\lionKing.jpg"), "HAKUNA MATATA", Boolean.parseBoolean(themeProperties.getProperty("theme6.locked", "true")));

        themes.add(theme1);
        themes.add(theme2);
        themes.add(theme3);
        themes.add(theme4);
        themes.add(theme5);
        themes.add(theme6);

        languageOption.add(themes);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(25, 5, 5, 5);

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
    
    private void removeStarFromTheme(JPanel themePanel) {
        // Check which theme panel is passed and remove the star accordingly
        if (themePanel == theme1) {
            theme1.remove(starLabel1);
            theme1.revalidate();
            theme1.repaint();
        } else if (themePanel == theme2) {
            theme2.remove(starLabel2);
            theme2.revalidate();
            theme2.repaint();
        }
        // Add similar blocks for other theme panels (theme3, theme4, etc.)
    }

    private void addStarToPanel(JPanel themePanel, JLabel starLabel) {
    	
    	Properties themeProperties = new Properties();
        try (FileInputStream fis = new FileInputStream("resources/themes.properties")) {
            themeProperties.load(fis);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        
        String selectedKey = null;
        if (selectedThemeImagePath.contains("platform.jpeg")) {
            selectedKey = "theme1.locked";
        } else if (selectedThemeImagePath.contains("island.jpeg")) {
            selectedKey = "theme2.locked";
        } else if (selectedThemeImagePath.contains("animals.jpg")) {
            selectedKey = "theme3.locked";
        } else if (selectedThemeImagePath.contains("dusk.jpg")) {
            selectedKey = "theme4.locked";
        } else if (selectedThemeImagePath.contains("nature.jpg")) {
            selectedKey = "theme5.locked";
        } else if (selectedThemeImagePath.contains("lionKing.jpg")) {
            selectedKey = "theme6.locked";
        }
        
        if(selectedKey != null) {
        	if(selectedKey != null && "false".equals(themeProperties.getProperty(selectedKey, "true"))) {
        		removeStarFromTheme(themePanel);

                // Calculate position for star label at the far right
                int starX = themePanel.getWidth() - starLabel.getWidth() - 5; // Adjust as needed
                int starY = (themePanel.getHeight() - starLabel.getHeight()) / 2; // Center vertically

                // Set bounds for star label and add to theme panel
                starLabel.setBounds(starX, starY, starLabel.getWidth(), starLabel.getHeight());
                themePanel.add(starLabel);
                themePanel.revalidate();
                themePanel.repaint();
        	}
        }
        // Remove existing star if any
        
    }


    private Properties loadThemeProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("resources/themes.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void layoutUI() {
        this.setSize(600, 550);
        this.setLocationRelativeTo(getParent());
    }

    private ImageIcon resizeIcon(int width, int height) {
        Image img = resizedSettingsIcon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(resizedImg);
    }
    
    private void applySelectedTheme(ActionEvent e) {
        if (selectedThemeImagePath != null) {
        	
        	 Properties themeProperties = new Properties();
             try (FileInputStream fis = new FileInputStream("resources/themes.properties")) {
                 themeProperties.load(fis);
             } catch (IOException ex) {
                 ex.printStackTrace();
                 return;
             }
             
             String selectedThemeKey = null;
             if (selectedThemeImagePath.contains("platform.jpeg")) {
                 selectedThemeKey = "theme1.locked";
             } else if (selectedThemeImagePath.contains("island.jpeg")) {
                 selectedThemeKey = "theme2.locked";
             } else if (selectedThemeImagePath.contains("animals.jpg")) {
                 selectedThemeKey = "theme3.locked";
             } else if (selectedThemeImagePath.contains("dusk.jpg")) {
                 selectedThemeKey = "theme4.locked";
             } else if (selectedThemeImagePath.contains("nature.jpg")) {
                 selectedThemeKey = "theme5.locked";
             } else if (selectedThemeImagePath.contains("lionKing.jpg")) {
                 selectedThemeKey = "theme6.locked";
             }
            
            if(selectedThemeKey != null) {
            	if(selectedThemeKey != null && "false".equals(themeProperties.getProperty(selectedThemeKey, "true"))) {
            		JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(ThemeDialog.this);

                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        if (window instanceof JDialog && window.getOwner() == parentFrame) {
                            JDialog dialog = (JDialog) window;
                            if (dialog instanceof ThemeBackgroundDialog) {
                                dialog.dispose();
                            }
                        }
                    }

                    JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                    set.dispose();

                    if (parentFrame instanceof LandingPageFrame) {
                        LandingPageFrame landingPageFrame = (LandingPageFrame) parentFrame;
                        landingPageFrame.setBackgroundImage(selectedThemeImagePath);
                    }
                    
                 // Update config.properties file
                    Properties prop = new Properties();
                    try (FileInputStream in = new FileInputStream("resources/config.properties")) {
                        prop.load(in);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    // Update the backgroundImagePath property
                    prop.setProperty("backgroundImagePath", selectedThemeImagePath);
//                    unlockNextTheme();

                    // Save the updated properties back to config.properties
                    try (FileOutputStream out = new FileOutputStream("resources/config.properties")) {
                        prop.store(out, null);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
            	}
            }
        }
    }
    
    public String getSelectedTheme() {
        return selectedThemeImagePath;
    }
    
//    private void unlockNextTheme() {
//        Properties props = new Properties();
//        try (InputStream input = new FileInputStream("resources/themes.properties")) {
//            props.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            System.err.println("Failed to load theme configuration file: " + ex.getMessage());
//            return;
//        }
//        
//            props.setProperty("theme2.locked", "false");
//
//        try (OutputStream output = new FileOutputStream("resources/themes.properties")) {
//            props.store(output, null);
////            System.out.println("Next theme unlocked.");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            System.err.println("Failed to save theme configuration file: " + ex.getMessage());
//        }
//    }

    private void updatePosition() {
        setLocationRelativeTo(getParent());
    }
}
