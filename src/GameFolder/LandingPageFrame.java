package GameFolder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.prefs.Preferences;

public class LandingPageFrame extends JFrame implements BackgroundMusicController{
     private BackgroundPanel landingContainer;
     private JLabel imageLabel,rateUsLabel,rateUsIconLabel;
     private GameSelectBoardPanel gameSelect;
     private JPanel topRightPanel,labelPanel,settingsOption, 
     settingsPanel,aboutPanel,dailyChallengePanel, centerPanel, landingPageBigContainer;
     private ImageIcon rateUsIcon, finalRateUsIcon;
     private Image ruIcon, resizedruIcon;
     private RoundedPanel rateUsBigContainer;
     private SettingsIconPanel settingsIconPanel;
     private GameBoardDialog gameBoardDialog;
     private ShapeLevelSelectDialog shapeLevelSelectDialog;
     private SettingsDialog settingsDialog;
     private LevelTopBarPanel levelTopBarPanel;
     private Clip backgroundMusicClip;
     
     
     public LandingPageFrame() {
    	 
         settingsDialog = new SettingsDialog(this,this);
         
        initUI();
        layoutUI(); 
        
        if (settingsDialog.getInitialState()) {
            addBackgroundMusic();
        }else {
        	removeBackgroundMusic();
        }
    }  

    private void initUI() {   
        setTitle("Item Sort");
        setSize(800, 700);   
        setLayout(new BorderLayout());   
        setMinimumSize(new Dimension(800, 700));  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);

        try {
            BufferedImage iconImage = ImageIO.read(new File("icon\\logo.png"));
            setIconImage(iconImage);
        } catch (IOException ex) {
            ex.printStackTrace(); 
        }	

        try {
            BufferedImage backgroundImg = ImageIO.read(new File("icon\\platform.jpeg"));
            landingContainer = new BackgroundPanel(backgroundImg);
        } catch (IOException e) {
            e.printStackTrace();
            landingContainer = new BackgroundPanel(null);
        }
        landingContainer.setLayout(new BorderLayout());
        landingContainer.setBorder(new EmptyBorder(27,20,0,22));
        
        landingPageBigContainer = new JPanel();
        landingPageBigContainer.setBackground(Color.WHITE);        
        
//        levelTopBarPanel = new LevelTopBarPanel("Welcome",this,null,null,0, null,0);
        
        gameSelect = new GameSelectBoardPanel(this,this);
        
        topRightPanel = new JPanel();
        topRightPanel.setOpaque(false);
        topRightPanel.setPreferredSize(new Dimension(110, 100));
        
        settingsOption = new JPanel();
        settingsOption.setOpaque(false); 
        
        rateUsBigContainer = new RoundedPanel(45,new Color(99, 181, 147));
        rateUsBigContainer.setBorder(new EmptyBorder(7,2,7,2));
        rateUsBigContainer.setLayout(new BorderLayout());
        rateUsBigContainer.setPreferredSize(new Dimension(110, 70));
        
        rateUsBigContainer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        rateUsLabel = new JLabel("Rate Us");
        rateUsLabel.setForeground(Color.WHITE);
        rateUsLabel.setFont(new Font(rateUsLabel.getFont().getName(), Font.BOLD | Font.ITALIC, 20));
        
        labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        
        rateUsIcon = new ImageIcon("icon\\rateUsIcon.png");
        ruIcon = rateUsIcon.getImage();
        resizedruIcon = ruIcon.getScaledInstance(80, 20, Image.SCALE_SMOOTH);
        finalRateUsIcon = new ImageIcon(resizedruIcon);
        rateUsIconLabel = new JLabel(finalRateUsIcon); 
        
        settingsIconPanel = new SettingsIconPanel(); 
        settingsPanel = settingsIconPanel.createSettingsPanel(
                new ImageIcon("icon\\settings.png"),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	SettingsBackgroundDialog dialog = new SettingsBackgroundDialog(LandingPageFrame.this, LandingPageFrame.this);
                        dialog.setVisible(true);
                    	
                    } 
                }
            );
        
        aboutPanel = settingsIconPanel.createSettingsPanel(
                new ImageIcon("icon\\about.png"),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	AboutDialog dialog = new AboutDialog(LandingPageFrame.this);
                        dialog.setVisible(true);
                    	
                    }
                } 
            );
        
        dailyChallengePanel = settingsIconPanel.createSettingsPanel(
                new ImageIcon("icon\\dailyChallenge.png"),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	DailyChallengeBackgroundDialog dialog = new DailyChallengeBackgroundDialog(LandingPageFrame.this);
                        dialog.setVisible(true);
                    }
                }
            );

    }

    private void layoutUI() { 
        try {
            BufferedImage iconImage = ImageIO.read(new File("icon\\logo.png"));

            Image resizedIconImage1 = iconImage.getScaledInstance(90, 50, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon1 = new ImageIcon(resizedIconImage1);

            Image resizedIconImage2 = iconImage.getScaledInstance(120, 70, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon2 = new ImageIcon(resizedIconImage2);

            Image resizedIconImage3 = iconImage.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon3 = new ImageIcon(resizedIconImage3);

            imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            centerPanel = new JPanel(new BorderLayout());
            centerPanel.setOpaque(false);
            centerPanel.add(imageLabel, BorderLayout.NORTH);
            
//            landingContainer.add(centerPanel,BorderLayout.CENTER);
            addIconImage();

            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    int frameWidth = getWidth();
                    if (frameWidth <= 500) {
                        imageLabel.setIcon(resizedIcon1);
                    } else if  ( 500 <= frameWidth && frameWidth <= 800) {
                        imageLabel.setIcon(resizedIcon2);
                    } else {
                        imageLabel.setIcon(resizedIcon3);
                    }

                    updateDialog();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        labelPanel.add(rateUsLabel);
        rateUsBigContainer.add(labelPanel, BorderLayout.NORTH);
        rateUsBigContainer.add(rateUsIconLabel, BorderLayout.SOUTH);
        
        settingsOption.add(settingsPanel);
        settingsOption.add(aboutPanel);
        settingsOption.add(dailyChallengePanel);
        
        topRightPanel.add(rateUsBigContainer);
        topRightPanel.add(settingsOption);
        
//        landingContainer.add(topRightPanel,BorderLayout.EAST);
        addTopRightPanel();
//        gameSelect.setVisible(true);
        addGameSelectBoardPanel();
        setContentPane(landingContainer);
        
    }
    private void updateDialog() {
        if (gameSelect != null && gameSelect.isVisible()) {
            int frameX = getLocation().x;
            int frameY = getLocation().y;
            int frameWidth = getWidth();
            int frameHeight = getHeight();

            int newWidth = Math.min(frameWidth - 100, 800); 
            int newHeight = Math.min(frameHeight / 2, 500); 

            int marginBottom = 70;
            int dialogX = frameX + (frameWidth - newWidth) / 2;
            int dialogY = frameY + frameHeight - newHeight - marginBottom;

            gameSelect.setSize(newWidth, newHeight);
            gameSelect.setLocation(dialogX, dialogY);
        }
    }

    private void playBackgroundMusic(String filePath) {
//      try {
//          File audioFile = new File(filePath);
//          AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
//          Clip audioClip = AudioSystem.getClip();
//          audioClip.open(audioStream);
//          audioClip.start();
//      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//          e.printStackTrace();
//      }
  	
  	try {
          File audioFile = new File(filePath);
          AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
          backgroundMusicClip = AudioSystem.getClip();
          backgroundMusicClip.open(audioStream);
          backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
          backgroundMusicClip.start();
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
          e.printStackTrace();
      }
  }

    class BackgroundPanel extends JPanel {
        private BufferedImage image;

        public BackgroundPanel(BufferedImage img) {
            this.image = img;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    
    public void addTopRightPanel() {
        if (topRightPanel != null) {
            landingContainer.add(topRightPanel, BorderLayout.EAST);
            landingContainer.revalidate();
            landingContainer.repaint();
        }
    }
    
    public void removeTopRightPanel() {
        if (topRightPanel != null) {
            landingContainer.remove(topRightPanel);
            landingContainer.revalidate();
            landingContainer.repaint();
        }
    }
    
    public void addIconImage() {
        if (topRightPanel != null) {
        	landingContainer.add(centerPanel,BorderLayout.CENTER);
            landingContainer.revalidate();
            landingContainer.repaint();
        }
    }
    
    public void addGameSelectBoardPanel() {
        if (gameSelect != null) {
        	gameSelect.setVisible(true);
        }
    }
    
    public void setGameBoardDialog(GameBoardDialog gameBoardDialog) {
        this.gameBoardDialog = gameBoardDialog;
    }

    public void removeGameBoardDialog() {
        if (gameBoardDialog != null) {
            gameBoardDialog.dispose();
            gameBoardDialog = null;
        }
    }
    
    public void removeImageLabelPanel() {
        if (imageLabel != null) {
            landingContainer.remove(centerPanel);
            landingContainer.revalidate();
            landingContainer.repaint();
        }
    }
    
    public void removeLevelTopBarPanel() {
        if (levelTopBarPanel != null) {
            landingContainer.remove(levelTopBarPanel);
            landingContainer.revalidate();
            landingContainer.repaint();
        }
    }
    
    public LevelTopBarPanel getLevelTopBarPanel() {
        return levelTopBarPanel;
    }
    
    @Override
    public void addBackgroundMusic() {
    	playBackgroundMusic("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\fun_soundFinal2.wav");
    }
    
    @Override
    public void removeBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LandingPageFrame().setVisible(true));
    }
}
