package GameFolder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CategoryCompletedDialog extends JDialog {
	
    private RoundedPanel contentPanel;
    private ImageIcon closeIcon, resizedCloseIcon, star1Icon, finalStar1Icon, categoryImgIcon, finalCIIcon, themeIcon, finalThemeIcon;
    private Image closeImg, resizedCloseImg, s1Icon, resizedS1Icon, ciIcon, resizedCIIcon, tIcon, resizedThemeIcon;
    private JLabel label, closeIconLabel,star1Label, star2Label, star3Label, ciLabel,categoryRewardLabel, themeLabel;
    private JPanel categoryContent, closePanel, star1Panel, star2Panel, star3Panel, starPanel, themePanel, bottomPanel;
    private RoundedButton button;
    
    public CategoryCompletedDialog(JFrame parent) {
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
//        contentPanel.setOpaque(false);
        contentPanel.setBackground(Color.BLUE);
//        contentPanel.setB);     
        
        closeIcon = new ImageIcon("icon\\close.png");
        closeImg = closeIcon.getImage();
        resizedCloseImg = closeImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        resizedCloseIcon = new ImageIcon(resizedCloseImg);

        closeIconLabel = new JLabel(resizedCloseIcon);
        closeIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(CategoryCompletedDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof CategoryCompletedBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
                if (parentFrame instanceof LandingPageFrame) {
                    LandingPageFrame landingPageFrame = (LandingPageFrame) parentFrame;
                    landingPageFrame.addTopRightPanel();
                    landingPageFrame.addIconImage();
                    landingPageFrame.addGameSelectBoardPanel();
                    landingPageFrame.increaseProgressBar(25);
                }
                
                unlockNextTheme();
               
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });
        
        closePanel = new JPanel(new BorderLayout());
        closePanel.setOpaque(false);
        
        GridBagConstraints gbcCloseIconLabel = new GridBagConstraints();
        gbcCloseIconLabel.anchor = GridBagConstraints.NORTHEAST;
        gbcCloseIconLabel.insets = new Insets(5, 15, 5, 5);
        
        closePanel.add(closeIconLabel,BorderLayout.EAST);
        
        label = new JLabel("SHAPES CATEGORY COMPLETED", JLabel.CENTER);
        label.setForeground(Color.WHITE);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.BOLD, 30));
        
        categoryContent = new JPanel();
        categoryContent.setPreferredSize(new Dimension(530,500));
        categoryContent.setBorder(new EmptyBorder(10,15,5,15));
        categoryContent.setOpaque(false);
        
        starPanel = new JPanel(new BorderLayout());
        starPanel.setPreferredSize(new Dimension(490,100));
        starPanel.setBorder(new EmptyBorder(10,15,5,15));
        starPanel.setOpaque(false);
        
        star1Panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        star1Panel.setPreferredSize(new Dimension(75,75));
        star1Panel.setOpaque(false);
        
        star1Icon = new ImageIcon("icon\\star.png");
        s1Icon = star1Icon.getImage();
        resizedS1Icon = s1Icon.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        finalStar1Icon = new ImageIcon(resizedS1Icon);
        star1Label = new JLabel(finalStar1Icon);
        
        star2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        star2Panel.setPreferredSize(new Dimension(75,75));
        star2Panel.setOpaque(false);
        
//        star2Icon = new ImageIcon("icon\\star.png");
//        s2Icon = star2Icon.getImage();
//        resizedS2Icon = s2Icon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
//        finalStar2Icon = new ImageIcon(resizedS2Icon);
        star2Label = new JLabel(finalStar1Icon);
        
        star3Panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        star3Panel.setPreferredSize(new Dimension(75,75));
        star3Panel.setOpaque(false);
        
//        star2Icon = new ImageIcon("icon\\star.png");
//        s2Icon = star2Icon.getImage();
//        resizedS2Icon = s2Icon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
//        finalStar2Icon = new ImageIcon(resizedS2Icon);
        star3Label = new JLabel(finalStar1Icon);
        
        star1Panel.add(star1Label);
        star2Panel.add(star2Label);
        star3Panel.add(star3Label);
        starPanel.add(star1Panel, BorderLayout.WEST);
        starPanel.add(star2Panel, BorderLayout.CENTER);
        starPanel.add(star3Panel, BorderLayout.EAST);
        
        categoryImgIcon = new ImageIcon("icon\\happyKids.png");
        ciIcon = categoryImgIcon.getImage();
        resizedCIIcon = ciIcon.getScaledInstance(350, 200, Image.SCALE_SMOOTH);
        finalCIIcon = new ImageIcon(resizedCIIcon);
        ciLabel = new JLabel(finalCIIcon);
        
        categoryContent.add(starPanel);
        categoryContent.add(ciLabel);
        
        bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        categoryRewardLabel = new JLabel("YOU JUST UNLOCKED A NEW THEME", JLabel.CENTER);
        categoryRewardLabel.setForeground(Color.WHITE);
        Font categoryFont = categoryRewardLabel.getFont();
        categoryRewardLabel.setFont(new Font(categoryFont.getName(), Font.BOLD, 17));
        
        bottomPanel.add(categoryRewardLabel, gbc);
        
        
        themePanel = new JPanel();
        themePanel.setPreferredSize(new Dimension(75,75));
        themePanel.setOpaque(false);
        
        themeIcon = new ImageIcon("icon\\island.jpeg");
        tIcon = themeIcon.getImage();
        resizedThemeIcon = tIcon.getScaledInstance(100, 70, Image.SCALE_SMOOTH);
        finalThemeIcon = new ImageIcon(resizedThemeIcon);
        themeLabel = new JLabel(finalThemeIcon);
        
        gbc.gridy++;
        bottomPanel.add(themeLabel, gbc);
        
        
        button = new RoundedButton("APPLY NOW");
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(240, 164, 75));
        Font buttonFont = button.getFont();
        button.setFont(new Font(buttonFont.getName(), Font.BOLD, 12));
        button.setHorizontalTextPosition(JLabel.LEFT);
        button.setVerticalTextPosition(JLabel.CENTER);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(CategoryCompletedDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof CategoryCompletedBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
                if (parentFrame instanceof LandingPageFrame) {
                    LandingPageFrame landingPageFrame = (LandingPageFrame) parentFrame;
                    landingPageFrame.addTopRightPanel();
                    landingPageFrame.addIconImage();
                    landingPageFrame.addGameSelectBoardPanel();
                    landingPageFrame.increaseProgressBar(25);
                    landingPageFrame.setBackgroundImage("icon/island.jpeg");
                }
                // Update config.properties file
                Properties prop = new Properties();
                try (FileInputStream in = new FileInputStream("resources/config.properties")) {
                    prop.load(in);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Update the backgroundImagePath property
                prop.setProperty("backgroundImagePath", "icon/island.jpeg");
                unlockNextTheme();

                // Save the updated properties back to config.properties
                try (FileOutputStream out = new FileOutputStream("resources/config.properties")) {
                    prop.store(out, null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });
        
        gbc.gridy++;
        bottomPanel.add(button, gbc);
        categoryContent.add(bottomPanel);
        
        contentPanel.add(closePanel,BorderLayout.NORTH);
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.add(categoryContent, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }
    
   
    private void layoutUI() {
        this.setSize(620, 600);
        this.setMinimumSize(new Dimension(600, 500));
        this.setMaximumSize(new Dimension(620, 800));

        this.setLocationRelativeTo(getParent());
    }
    
    private void unlockNextTheme() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("resources/themes.properties")) {
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Failed to load theme configuration file: " + ex.getMessage());
            return;
        }
        
            props.setProperty("theme2.locked", "false");

        try (OutputStream output = new FileOutputStream("resources/themes.properties")) {
            props.store(output, null);
//            System.out.println("Next theme unlocked.");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Failed to save theme configuration file: " + ex.getMessage());
        }
    }

    private void updatePosition() {
        setLocationRelativeTo(getParent()); // Update dialog's position relative to the parent frame
    }
}
