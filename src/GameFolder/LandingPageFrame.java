package GameFolder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.Properties;
import java.util.prefs.Preferences;

public class LandingPageFrame extends JFrame implements BackgroundMusicController{
     private BackgroundPanel landingContainer;
     private JLabel imageLabel,rateUsLabel,rateUsIconLabel, starLabel, starIconLabel, numberIconLabel,
     letterIconLabel, fruitIconLabel;
     private GameSelectBoardPanel gameSelect;
     private JPanel topRightPanel,labelPanel,settingsOption, 
     settingsPanel,aboutPanel,dailyChallengePanel, centerPanel, landingPageBigContainer,iconsPanel,
     starIconPanel, numberIconPanel, lettersIconPanel;
     private ImageIcon rateUsIcon, finalRateUsIcon, finalStarIcon, starIcon, numberIcon, finalNumberIcon,
     letterIcon, finalLetterIcon, fruitIcon,finalFruitIcon;
     private Image ruIcon, resizedruIcon, resizedSIcon, sIcon, nIcon, resizedNIcon, lIcon, resizedLIcon, 
     fIcon, resizedFIcon;
     private RoundedPanel rateUsBigContainer;
     private SettingsIconPanel settingsIconPanel;
     private GameBoardDialog gameBoardDialog;
     private ShapeLevelSelectDialog shapeLevelSelectDialog;
     private SettingsDialog settingsDialog;
     private LevelTopBarPanel levelTopBarPanel;
     private Clip backgroundMusicClip;
     private RoundedProgressBar progressBar;
     private CategoryProgressData categoryProgressData;
     private Properties props;
     
     
     public LandingPageFrame() {
    	 
         settingsDialog = new SettingsDialog(this,this);
         categoryProgressData = CategoryProgressData.getInstance();
         
        initProperties();
        initUI();
        layoutUI(); 
        
        if (settingsDialog.getInitialState()) {
            addBackgroundMusic();
        }else {
        	removeBackgroundMusic();
        }
    } 
     
     private void initProperties() {
         props = new Properties();
         try {
             FileInputStream in = new FileInputStream("resources/config.properties");
             props.load(in);
             in.close();
         } catch (IOException e) {
             e.printStackTrace();
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
        String imagePath = props.getProperty("backgroundImagePath");
        BufferedImage backgroundImg = loadImage(imagePath);
        landingContainer = new BackgroundPanel(backgroundImg);
        landingContainer.setLayout(new BorderLayout());
        landingContainer.setBorder(new EmptyBorder(27,20,0,22));
        
        landingPageBigContainer = new JPanel();
        landingPageBigContainer.setBackground(Color.WHITE);        
                
        gameSelect = new GameSelectBoardPanel(this,this);
        
        topRightPanel = new JPanel();
        topRightPanel.setOpaque(false);
        topRightPanel.setPreferredSize(new Dimension(130, 100));
        
        settingsOption = new JPanel();
        settingsOption.setOpaque(false); 
        
        rateUsBigContainer = new RoundedPanel(45,new Color(99, 181, 147));
        rateUsBigContainer.setBorder(new EmptyBorder(7,7,7,7));
        rateUsBigContainer.setLayout(new BorderLayout());
        rateUsBigContainer.setPreferredSize(new Dimension(130, 90));
        
        rateUsBigContainer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        rateUsLabel = new JLabel("Progress");
        rateUsLabel.setForeground(Color.WHITE);
        rateUsLabel.setFont(new Font(rateUsLabel.getFont().getName(), Font.BOLD | Font.ITALIC, 20));
        
        progressBar = new RoundedProgressBar(0, 100, 130, 12);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(50, 205, 50));
        progressBar.setOpaque(false);
        
        labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        
        starIcon = new ImageIcon("icon\\star.png");
        sIcon = starIcon.getImage();
        resizedSIcon = sIcon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        finalStarIcon = new ImageIcon(resizedSIcon);
        starIconLabel = new JLabel(finalStarIcon); 
        
        numberIcon = new ImageIcon("icon\\numbers.png");
        nIcon = numberIcon.getImage();
        resizedNIcon = nIcon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        finalNumberIcon = new ImageIcon(resizedNIcon);
        numberIconLabel = new JLabel(finalNumberIcon); 
        
        letterIcon = new ImageIcon("icon\\letters.png");
        lIcon = letterIcon.getImage();
        resizedLIcon = lIcon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        finalLetterIcon = new ImageIcon(resizedLIcon);
        letterIconLabel = new JLabel(finalLetterIcon); 
        
        fruitIcon = new ImageIcon("icon\\fruits.png");
        fIcon = fruitIcon.getImage();
        resizedFIcon = fIcon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        finalFruitIcon = new ImageIcon(resizedFIcon);
        fruitIconLabel = new JLabel(finalFruitIcon); 
        
        iconsPanel = new JPanel(new BorderLayout());
        iconsPanel.setBorder(new EmptyBorder(0,12,0,0));
        iconsPanel.setOpaque(false);
        
        starIconPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        starIconPanel.setPreferredSize(new Dimension(20,20));
        starIconPanel.setOpaque(false);
        
        numberIconPanel = new JPanel( new FlowLayout(FlowLayout.CENTER));
        numberIconPanel.setPreferredSize(new Dimension(20,20));
        numberIconPanel.setOpaque(false);
        
        lettersIconPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        lettersIconPanel.setPreferredSize(new Dimension(50,20));
        lettersIconPanel.setOpaque(false);
        
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
        iconsPanel.add(starIconPanel, BorderLayout.WEST);
        iconsPanel.add(numberIconPanel, BorderLayout.CENTER);
        iconsPanel.add(lettersIconPanel, BorderLayout.EAST);
        numberIconPanel.add(numberIconLabel);
        starIconPanel.add(starIconLabel);
        lettersIconPanel.add(letterIconLabel);
        lettersIconPanel.add(fruitIconLabel);
        rateUsBigContainer.add(labelPanel, BorderLayout.NORTH);
        rateUsBigContainer.add(iconsPanel, BorderLayout.CENTER);
        rateUsBigContainer.add(progressBar, BorderLayout.SOUTH);
        
        settingsOption.add(settingsPanel);
        settingsOption.add(aboutPanel);
        settingsOption.add(dailyChallengePanel);
        
        topRightPanel.add(rateUsBigContainer);
        topRightPanel.add(settingsOption);
        
        addTopRightPanel();
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
        
        public void setImage(BufferedImage img) {
            this.image = img;
            repaint();
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
    
    public void removeGameSelectBoardPanel() {
        if (gameSelect != null) {
        	gameSelect.setVisible(false);
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
    	playBackgroundMusic("audio\\fun_soundFinal2.wav");
    }
    
    @Override
    public void removeBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
        }
    }
    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void setBackgroundImage(String imagePath) {
        BufferedImage newBackground = loadImage(imagePath);
        if (newBackground != null) {
            landingContainer.setImage(newBackground);
            landingContainer.repaint();
        }
    }
    public void increaseProgressBar(int increment) {
    	int newValue = progressBar.getValue() + increment;
    	progressBar.setValue(newValue);
    	categoryProgressData.setProgress(newValue);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LandingPageFrame().setVisible(true));
    }
}
