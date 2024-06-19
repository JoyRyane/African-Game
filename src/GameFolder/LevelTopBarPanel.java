//package GameFolder;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class LevelTopBarPanel extends JPanel implements GameBoardListener {
//    private JPanel returnPanel, levelPanel, settingsContainerPanel, settingsPanel, hintPanel,
//    coinPanel, replayPanel, middlePanel;
//    private SettingsIconPanel settingsIconPanel;
//    private JLabel level, coinBadge, hintBadge,category;
//    private int totalCoins, totalHints, levelNumber, index;
//    private String levelTitle;
//    private LandingPageFrame landingPageFrame;
//    private GameBoardDialog gameBoardDialog;
//    private ShapeLevelSelectDialog shapeLevelSelectDialog;
//    private String categoryName;
////    private JLabel levelNameLabel;
//
//    public LevelTopBarPanel(String levelTitle, LandingPageFrame landingPageFrame, GameBoardDialog gameBoardDialog, 
//                            ShapeLevelSelectDialog shapeLevelSelectDialog, int levelNumber, String categoryName, int index) {
//        this.levelTitle = levelTitle;
//        this.landingPageFrame = landingPageFrame;
//        this.gameBoardDialog = gameBoardDialog;
//        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
//        this.levelNumber = levelNumber;
//        this.categoryName = categoryName;
//        this.index = index;
//        
//        initUI();
//        layoutUI();
//        
//        
//    }
//
//    private void initUI() {
//        setLayout(new BorderLayout());
//        setBackground(new Color(0, 0, 0, 0));
//
//        returnPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
//        returnPanel.setOpaque(false);
//
//        levelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        levelPanel.setOpaque(false);
//        
//        middlePanel = new JPanel(new BorderLayout());
//        middlePanel.setOpaque(false);
//
//        settingsContainerPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
//        settingsContainerPanel.setOpaque(false);
//    }
//
//    private void layoutUI() {
//        try {
//            BufferedImage iconImage = ImageIO.read(new File("icon\\return.png"));
//            int scaledWidth = 30;
//            int scaledHeight = 30;
//            Image scaledIconImage = iconImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
//            ImageIcon icon = new ImageIcon(scaledIconImage);
//            JLabel iconLabel = new JLabel(icon);
//            returnPanel.add(iconLabel);
//            iconLabel.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    Component parent = SwingUtilities.getWindowAncestor((Component) e.getSource());
//
//                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
//
//                    removeFromParent();
//
//                    if(gameBoardDialog != null) {
//                	   gameBoardDialog.closeDialog();
//                   }
//
//                    if (parentFrame instanceof LandingPageFrame) {
//                        LandingPageFrame landingPageFrame = (LandingPageFrame) parentFrame;
//                        landingPageFrame.addTopRightPanel();
//                        landingPageFrame.addIconImage();
//                        landingPageFrame.removeGameBoardDialog();
//                        landingPageFrame.addGameSelectBoardDialog();
//                    }
//                }
//            });
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        level = new JLabel(levelTitle);
//        level.setForeground(Color.WHITE);
//        level.setLayout(new FlowLayout(FlowLayout.CENTER));
//        level.setFont(new Font(level.getName(), Font.BOLD | Font.ITALIC, 20));
//        
//        category = new JLabel(categoryName);
//        category.setForeground(Color.WHITE);
//        category.setFont(new Font(level.getName(), Font.BOLD | Font.ITALIC, 30));
//
//        middlePanel.add(category, BorderLayout.NORTH);
//        middlePanel.add(level, BorderLayout.SOUTH);
//
//        levelPanel.add(middlePanel);
//
//        settingsIconPanel = new SettingsIconPanel();
//        hintPanel = settingsIconPanel.createSettingsPanel(
//                new ImageIcon("icon\\hint.png"),
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        // Action for hint button
//                    }
//                }
//        );
//        coinPanel = settingsIconPanel.createSettingsPanel(
//                new ImageIcon("icon\\coins.png"),
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        // Action for coin button
//                    }
//                }
//        );
//
//        replayPanel = settingsIconPanel.createSettingsPanel(
//                new ImageIcon("icon\\replay.png"),
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        ReplayBackgroundDialog replay = new ReplayBackgroundDialog(landingPageFrame,
//                                shapeLevelSelectDialog, gameBoardDialog, LevelTopBarPanel.this, levelNumber, index);
//                        replay.setVisible(true);
//                    }
//                }
//        );
//        settingsPanel = settingsIconPanel.createSettingsPanel(
//                new ImageIcon("icon\\settings.png"),
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        SettingsBackgroundDialog dialog = new SettingsBackgroundDialog(landingPageFrame);
//                        dialog.setVisible(true);
//                    }
//                }
//        );
//
//        coinBadge = new JLabel("Coins: " + totalCoins);
//        hintBadge = new JLabel("Hints: " + totalHints);
//        
//        hintBadge.setForeground(Color.YELLOW);
//        hintPanel.add(hintBadge);
//
//        coinBadge.setForeground(Color.YELLOW);
//        coinPanel.add(coinBadge);
//
//        settingsContainerPanel.add(hintPanel);
//        settingsContainerPanel.add(coinPanel);
//        settingsContainerPanel.add(replayPanel);
//        settingsContainerPanel.add(settingsPanel);
//
//        add(returnPanel, BorderLayout.WEST);
//        add(levelPanel, BorderLayout.CENTER);
//        add(settingsContainerPanel, BorderLayout.EAST);
//    }
//
//    public void removeFromParent() {
//        Container parent = getParent();
//        if (parent != null) {
//            parent.remove(this);
//            parent.revalidate();
//            parent.repaint();
//        }
//    }
//    
//    public void addCoins(int coins) {
//        totalCoins += coins;
//        coinBadge.setText("Coins: "+ totalCoins);
////        SwingUtilities.invokeLater(() -> {
////            coinBadge.setText("Coins: " + totalCoins);
////            this.revalidate();
////            this.repaint();
////        });
//    }
//
//    public void addReward(int hints, int coins) {
//        totalHints += hints;
//        hintBadge.setText("Hints: " + totalHints);
//        totalCoins += coins;
//        coinBadge.setText("Coins: "+ totalCoins);
////        SwingUtilities.invokeLater(() -> {
////            if (middlePanel != null && middlePanel.getParent() != null) {
////                middlePanel.getParent().remove(middlePanel);
////            }
////            revalidate();
////            repaint();
////        });
//    }
//    
//    public void removeMiddlePanel() {
////        if (middlePanel != null && middlePanel.getParent() != null) {
////            middlePanel.getParent().remove(middlePanel);
////            revalidate();
////            repaint();
////        }
//    	
////    	if (levelPanel != null) {
////            levelPanel.remove(middlePanel);
////            levelPanel.revalidate();
////            levelPanel.repaint();
////        }
//    	
//    	System.out.println("Heyyy");
//    }
//    public void updateLevelName(String newLevelName) {
//        level.setText(newLevelName);
//    }
//
//	@Override
//	public void onGameBoardReady(GameBoardDialog gameBoardDialog) {
//		// TODO Auto-generated method stub
//		this.gameBoardDialog = gameBoardDialog;
//	}
//	public void setLevelName(String newLevelName) {
//        level.setText(newLevelName);
//    }
//	public void setLevelNumber(int levelNumber) {
//        this.levelNumber = levelNumber;
//	}
//}


package GameFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LevelTopBarPanel extends JPanel{
    private JPanel returnPanel, levelPanel, settingsContainerPanel, settingsPanel, hintPanel,
            coinPanel, replayPanel, middlePanel;
    private SettingsIconPanel settingsIconPanel;
    private JLabel level, coinBadge, hintBadge, category;
    private int levelNumber, index;
    private String levelTitle;
    private LandingPageFrame landingPageFrame;
    private GameBoardDialog gameBoardDialog;
    private ShapeLevelSelectDialog shapeLevelSelectDialog;
    private String categoryName;
    private GameData gameData;
    private ShapeModel shapeModel;
    private BackgroundMusicController musicController;
//    private List<ShapeLevel> levels;
    private ShapeLevel shapeLevel;

    public LevelTopBarPanel(String levelTitle, LandingPageFrame landingPageFrame, GameBoardDialog gameBoardDialog,
                            ShapeLevelSelectDialog shapeLevelSelectDialog, int levelNumber, String categoryName, int index,
                            ShapeLevel shapeLevel, ShapeModel shapeModel,BackgroundMusicController musicController) {
        this.levelTitle = levelTitle;
        this.landingPageFrame = landingPageFrame;
        this.gameBoardDialog = gameBoardDialog;
        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
        this.levelNumber = levelNumber;
        this.categoryName = categoryName;
        this.shapeModel = shapeModel;
        this.index = index;
//        this.levels = levels;
        this.shapeLevel = shapeLevel;
        this.gameData = GameData.getInstance();
        this.musicController = musicController;
        
        initUI();
        layoutUI();
        updateUIValues();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));

        returnPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        returnPanel.setOpaque(false);

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
            returnPanel.add(iconLabel);
            iconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component parent = SwingUtilities.getWindowAncestor((Component) e.getSource());

                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());

                    removeFromParent();

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
                    	shapeModel.highlightUnmatchedShapes();
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
                                shapeLevelSelectDialog, gameBoardDialog, LevelTopBarPanel.this, levelNumber, index, shapeLevel);
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
    }

    public void removeFromParent() {
        Container parent = getParent();
        if (parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }
    }

//    public void addCoins(int coins) {
//        gameData.addCoins(coins);
//        updateUIValues();
//    }

    public void addReward(int hints, int coins) {
        gameData.addHints(hints);
        gameData.addCoins(coins);
        updateUIValues();
    }
//
//    public void removeMiddlePanel() {
//        System.out.println("Heyyy");
//    }

    public void updateLevelName(String newLevelName) {
        level.setText(newLevelName);
    }

//    @Override
//    public void onGameBoardReady(GameBoardDialog gameBoardDialog) {
//        this.gameBoardDialog = gameBoardDialog;
//    }

    public void setLevelName(String newLevelName) {
        level.setText(newLevelName);
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}
