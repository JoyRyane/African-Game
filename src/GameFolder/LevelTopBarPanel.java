package GameFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LevelTopBarPanel extends JPanel{
    private JPanel returnPanel, levelPanel, settingsContainerPanel, settingsPanel, hintPanel,
            coinPanel, replayPanel, middlePanel, scorePanel, imagePanel, star1IconPanel, star2IconPanel,
            star3IconPanel;
    private SettingsIconPanel settingsIconPanel;
    private JLabel level, coinBadge, hintBadge, category, scoreLabel, star1IconLabel, star2IconLabel,
    star3IconLabel;
    private int levelNumber, index, catIndex;
    private String levelTitle;
    private LandingPageFrame landingPageFrame;
    private GameBoardDialog gameBoardDialog;
    private ShapeLevelSelectDialog shapeLevelSelectDialog;
    private String categoryName;
    private GameData gameData;
    private ShapeModel shapeModel;
    private BackgroundMusicController musicController;
    private ShapeLevel shapeLevel, nextLevel;
    RoundedProgressBar progressBar;
    private ProgressData progressData;
    private ImageIcon star1Icon, finalStar1Icon, star2Icon, finalStar2Icon, star3Icon, finalStar3Icon;
    private Image s1Icon, resizedS1Icon, s2Icon, resizedS2Icon, s3Icon, resizedS3Icon;
    private GameSelectBoardPanel gameSelectBoardPanel;
    
    public LevelTopBarPanel(String levelTitle, LandingPageFrame landingPageFrame, GameBoardDialog gameBoardDialog,
                            ShapeLevelSelectDialog shapeLevelSelectDialog, int levelNumber, String categoryName, int index,
                            ShapeLevel shapeLevel,ShapeLevel nextLevel, ShapeModel shapeModel,BackgroundMusicController musicController,
                            int catIndex,GameSelectBoardPanel gameSelectBoardPanel) {
        this.levelTitle = levelTitle;
        this.landingPageFrame = landingPageFrame;
        this.gameBoardDialog = gameBoardDialog;
        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
        this.levelNumber = levelNumber;
        this.categoryName = categoryName;
        this.shapeModel = shapeModel;
        this.nextLevel = nextLevel;
        this.index = index;
        this.progressData = ProgressData.getInstance();
        this.shapeLevel = shapeLevel;
        this.gameData = GameData.getInstance();
        this.musicController = musicController;
        this.gameSelectBoardPanel = gameSelectBoardPanel;
        this.catIndex = catIndex;
        
        initUI();
        layoutUI();
        updateUIValues();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));

        returnPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        returnPanel.setOpaque(false);
        
        scorePanel = new JPanel(new BorderLayout());
        scorePanel.setBorder(new EmptyBorder(0,12,0,0));
        scorePanel.setOpaque(false);

        levelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        levelPanel.setOpaque(false);

        middlePanel = new JPanel(new BorderLayout());
        middlePanel.setOpaque(false);

        settingsContainerPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        settingsContainerPanel.setOpaque(false);
    }

    private void layoutUI() {
        try {
            BufferedImage iconImage = ImageIO.read(new File("icon\\return.png"));
            int scaledWidth = 30;
            int scaledHeight = 30;
            Image scaledIconImage = iconImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledIconImage);
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            returnPanel.add(iconLabel);
            iconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component parent = SwingUtilities.getWindowAncestor((Component) e.getSource());

                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());

                    removeFromParent();
                    shapeModel.stopSound();

                    if (gameBoardDialog != null) {
                        gameBoardDialog.closeDialog();
                    }

                    if (parentFrame instanceof LandingPageFrame) {
                        LandingPageFrame landingPageFrame = (LandingPageFrame) parentFrame;
                        landingPageFrame.addTopRightPanel();
                        landingPageFrame.addIconImage();
                        landingPageFrame.removeGameBoardDialog();
                        landingPageFrame.addGameSelectBoardPanel();
                    }
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        level = new JLabel(levelTitle);
        level.setForeground(Color.WHITE);
        level.setLayout(new FlowLayout(FlowLayout.CENTER));
        level.setFont(new Font(level.getName(), Font.BOLD | Font.ITALIC, 20));

        category = new JLabel(categoryName);
        category.setForeground(Color.WHITE);
        category.setFont(new Font(level.getName(), Font.BOLD | Font.ITALIC, 30));

        middlePanel.add(category, BorderLayout.NORTH);
        middlePanel.add(level, BorderLayout.SOUTH);

        levelPanel.add(middlePanel);

        settingsIconPanel = new SettingsIconPanel();
        hintPanel = settingsIconPanel.createSettingsPanel(
                new ImageIcon("icon\\hint.png"),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Action for hint button
//                    	shapeModel.highlightUnmatchedShapes();
//                    	shapeModel.playSound("audio\\sound.wav");
                    }
                }
        );
        coinPanel = settingsIconPanel.createSettingsPanel(
                new ImageIcon("icon\\coins.png"),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Action for coin button
                    }
                }
        );

        replayPanel = settingsIconPanel.createSettingsPanel(
                new ImageIcon("icon\\replay.png"),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ReplayBackgroundDialog replay = new ReplayBackgroundDialog(landingPageFrame,
                                shapeLevelSelectDialog, gameBoardDialog, LevelTopBarPanel.this, levelNumber, index, shapeLevel,
                                nextLevel,catIndex, gameSelectBoardPanel);
                        replay.setVisible(true);
                    }
                }
        );
        settingsPanel = settingsIconPanel.createSettingsPanel(
                new ImageIcon("icon\\settings.png"),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        SettingsBackgroundDialog dialog = new SettingsBackgroundDialog(landingPageFrame, musicController);
                        dialog.setVisible(true);
                    }
                }
        );
        
        scoreLabel = new JLabel("Score: 20");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), Font.BOLD | Font.ITALIC, 12));
        
        progressBar = new RoundedProgressBar(0, 100, 100, 12);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(50, 205, 50));
        progressBar.setOpaque(false);
        
        returnPanel.add(scorePanel);
        scorePanel.add(scoreLabel, BorderLayout.NORTH);
        scorePanel.add(progressBar, BorderLayout.SOUTH);
        
        imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBorder(new EmptyBorder(0,17,0,0));
        imagePanel.setOpaque(false);
        
        star1IconPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        star1IconPanel.setPreferredSize(new Dimension(20,20));
        star1IconPanel.setOpaque(false);
        
        star1Icon = new ImageIcon("icon\\toggleButton.png");
        s1Icon = star1Icon.getImage();
        resizedS1Icon = s1Icon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        finalStar1Icon = new ImageIcon(resizedS1Icon);
        star1IconLabel = new JLabel(finalStar1Icon); 
        
        
        star2IconPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        star2IconPanel.setPreferredSize(new Dimension(20,20));
        star2IconPanel.setOpaque(false);
        
        star2Icon = new ImageIcon("icon\\star.png");
        s2Icon = star2Icon.getImage();
        resizedS2Icon = s2Icon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        finalStar2Icon = new ImageIcon(resizedS2Icon);
        star2IconLabel = new JLabel(finalStar2Icon); 
        
        star3IconPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        star3IconPanel.setPreferredSize(new Dimension(20,20));
        star3IconPanel.setOpaque(false);
        
        star3Icon = new ImageIcon("icon\\star3.png");
        s3Icon = star3Icon.getImage();
        resizedS3Icon = s3Icon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        finalStar3Icon = new ImageIcon(resizedS3Icon);
        star3IconLabel = new JLabel(finalStar3Icon); 
        
        star1IconPanel.add(star1IconLabel);
        star2IconPanel.add(star2IconLabel);
        star3IconPanel.add(star3IconLabel);
        
        imagePanel.add(star1IconPanel,BorderLayout.WEST);
        imagePanel.add(star2IconPanel, BorderLayout.CENTER);
        imagePanel.add(star3IconPanel, BorderLayout.EAST);
        
        scorePanel.add(imagePanel, BorderLayout.CENTER);
        coinBadge = new JLabel("Coins: " + gameData.getTotalCoins());
        hintBadge = new JLabel("Hints: " + gameData.getTotalHints());

        hintBadge.setForeground(Color.YELLOW);
        hintPanel.add(hintBadge);

        coinBadge.setForeground(Color.YELLOW);
        coinPanel.add(coinBadge);

        settingsContainerPanel.add(hintPanel);
        settingsContainerPanel.add(coinPanel);
        settingsContainerPanel.add(replayPanel);
        settingsContainerPanel.add(settingsPanel);

        add(returnPanel, BorderLayout.WEST);
        add(levelPanel, BorderLayout.CENTER);
        add(settingsContainerPanel, BorderLayout.EAST);
    }

    private void updateUIValues() {
        coinBadge.setText("Coins: " + gameData.getTotalCoins());
        hintBadge.setText("Hints: " + gameData.getTotalHints());
        progressBar.setValue(progressData.getProgress());
        scoreLabel.setText("Score: " + progressData.getScore());
    }

    public void removeFromParent() {
        Container parent = getParent();
        if (parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }
    }

    public void addReward(int hints, int coins) {
        if(!shapeLevel.isCompleted()) {
        	gameData.addHints(hints);
            gameData.addCoins(coins);
            updateUIValues();
    	}
    }
    
    public void test() {
    	System.out.println("This is a test");
    }
    
    public void updateLevelName(String newLevelName) {
        level.setText(newLevelName);
    }

    public void setLevelName(String newLevelName) {
        level.setText(newLevelName);
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
    
    public void increaseProgressBar(int increment) {
    	if(!shapeLevel.isCompleted()) {
    		int newValue = progressBar.getValue() + increment;
    		progressBar.setValue(newValue);
    		progressData.setProgress(newValue);
    	}
    }

    public void increaseScore(int increment) {
        if(!shapeLevel.isCompleted()) {
        	int currentScore = Integer.parseInt(scoreLabel.getText().split(": ")[1]);
            int newScore = currentScore + increment;
            scoreLabel.setText("Score: " + newScore);
           progressData.setScore(newScore);
        }
    }
}
