package GameFolder;

import java.awt.*;
import java.util.Properties;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.util.ArrayList;
//import java.nio.file.*;

public class ShapeLevelSelectDialog extends JDialog implements ShapeMatchListener {

    private RoundedPanel contentPanel, languageOption;
    private JButton button;
    private JLabel titleLabel;
    private ImageIcon resizedSettingsIcon;
    private JPanel themes;
    private ShapeLevelPanel shapeLevelPanel;
    private GameBoardDialog gameBoardDialog;
    private List<ShapeLevel> levels;
    private ShapeLevel currentLevel;
    private ShapeLevel nextLevel;
    private Properties levelProperties;
    private LevelTopBarPanel levelTopBarPanel;
    private int levelNumber, index;
    private String categoryName;
    private ShapeMatchListener shapeMatchListener;
    private BackgroundMusicController musicController;
//    private ShapeModel shapeModel;

    public ShapeLevelSelectDialog(JFrame parent,String categoryName, int index, BackgroundMusicController musicController) {
        super(parent, "Popup Dialog", false);
//        this.levelTopBarPanel = levelTopBarPanel;
        this.categoryName = categoryName;
        this.index = index;
        this.musicController = musicController;
//        this.shapeModel = shapeModel;
        loadLevelProperties();
        initUI();
        layoutUI();
        handleEventsUI();
        updatePosition();
    }

//    private void loadLevelProperties() {
//        levelProperties = new Properties();
//        try (InputStream input = new FileInputStream("resources/levels.properties")) {
//            levelProperties.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        levels = new ArrayList<>();
//        for (int i = 1; i <= 6; i++) {
//            String levelName = "LEVEL " + i;
//            boolean locked = Boolean.parseBoolean(levelProperties.getProperty("level" + i + ".locked"));
//            boolean completed = Boolean.parseBoolean(levelProperties.getProperty("level" + i + ".completed"));
//            levels.add(new ShapeLevel(levelName, locked, completed));
//        }
//    }
    
    private void loadLevelProperties() {
        levelProperties = new Properties();
        File propertiesFile = new File("resources/levels.properties");

        if (!propertiesFile.exists()) {
            try (InputStream defaultInput = getClass().getClassLoader().getResourceAsStream("resources/levels.properties")) {
                if (defaultInput != null) {
                    Files.copy(defaultInput, propertiesFile.toPath());
                } else {
                    System.out.println("Default properties file not found.");
                    return;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        try (InputStream input = new FileInputStream(propertiesFile)) {
            levelProperties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        levels = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            String levelName = "LEVEL " + i;
            boolean locked = Boolean.parseBoolean(levelProperties.getProperty("level" + i + ".locked"));
            boolean completed = Boolean.parseBoolean(levelProperties.getProperty("level" + i + ".completed"));
            levels.add(new ShapeLevel(levelName, locked, completed));
        }
    }

    private void saveLevelProperties() {
        try (OutputStream output = new FileOutputStream("resources/levels.properties")) {
            for (int i = 0; i < levels.size(); i++) {
                ShapeLevel level = levels.get(i);
                levelProperties.setProperty("level" + (i + 1) + ".locked", Boolean.toString(level.isLocked()));
                levelProperties.setProperty("level" + (i + 1) + ".completed", Boolean.toString(level.isCompleted()));
            }
            levelProperties.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

        titleLabel = new JLabel(categoryName, JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        Font titleFont = titleLabel.getFont();
        titleLabel.setFont(new Font(titleFont.getName(), Font.BOLD | Font.ITALIC, 25));

        languageOption = new RoundedPanel(10, new Color(107, 218, 105));
        languageOption.setLayout(new GridBagLayout());
        languageOption.setBorder(new EmptyBorder(5, 5, 5, 5));
        languageOption.setPreferredSize(new Dimension(500, 450));

        themes = new JPanel();
        themes.setLayout(new GridLayout(2, 3, 7, 10));
        themes.setOpaque(false);
        themes.setPreferredSize(new Dimension(520, 400));
        themes.setBorder(new EmptyBorder(5, 5, 5, 5));

        shapeLevelPanel = new ShapeLevelPanel();
        if(index == 0) {
        	for (ShapeLevel level : levels) {
                JPanel shapeLevel = shapeLevelPanel.createShapeLevelPanel(level);
                shapeLevel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleLevelClick(e, level);
                    }
                });
                themes.add(shapeLevel);
            }
        }if(index == 1) {
        	for (ShapeLevel level : levels) {
                JPanel letterLevel = shapeLevelPanel.createShapeLevelPanel(level);
                letterLevel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleLevelClick(e, level);
                    }
                });
                themes.add(letterLevel);
            }
        }
        
        languageOption.add(themes);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);

        contentPanel.add(titleLabel, BorderLayout.CENTER);
        contentPanel.add(languageOption, BorderLayout.SOUTH);
        setContentPane(contentPanel);
    }

    public void handleLevelClick(MouseEvent e, ShapeLevel level) {
    	
    	if(level.isLocked()) {
    		return;
    	}
    	
//    	System.out.println("Name: " + level.getName());
//    	System.out.println("Locked? " + level.isLocked());
//    	System.out.println("Completed? " + level.isCompleted());
    	
        currentLevel = level;
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(ShapeLevelSelectDialog.this);
        JDialog parentDialog = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
        JFrame grandParentFrame = (JFrame) SwingUtilities.getWindowAncestor(parentDialog);

        if (grandParentFrame instanceof LandingPageFrame) {
            LandingPageFrame landingPageFrame = (LandingPageFrame) grandParentFrame;
            landingPageFrame.removeTopRightPanel();
            landingPageFrame.removeImageLabelPanel();
            
            levelNumber = Integer.parseInt(currentLevel.getName().split(" ")[1]);
            ShapeModel shapeModel = ShapeModelSingleton.getInstance(gameBoardDialog,landingPageFrame,this,
              		 shapeMatchListener,levelTopBarPanel,levelNumber,index,level);
//            landingPageFrame.setGameBoardDialog(gameBoardDialog);
            LevelTopBarPanel levelTopBarPanel = new LevelTopBarPanel(level.getName(), 
            		landingPageFrame, gameBoardDialog, this, levelNumber, categoryName, index, level, shapeModel, musicController);
            gameBoardDialog = new GameBoardDialog(parentFrame, landingPageFrame, this, levelTopBarPanel, levelNumber, index, level); // Pass landingPageFrame
            
             
            landingPageFrame.setGameBoardDialog(gameBoardDialog);
            
            landingPageFrame.add(levelTopBarPanel, BorderLayout.NORTH);
            gameBoardDialog.setVisible(true);
//            if(gameBoardDialog.isVisible()) {
//            	levelTopBarPanel.removeMiddlePanel();
//            }
        }
        
        JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
        set.dispose();
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

    private void updatePosition() {
        int parentX = getParent().getX();
        int parentY = getParent().getY();
        int parentWidth = getParent().getWidth();
        int parentHeight = getParent().getHeight();

        int dialogWidth = getWidth();
        int dialogHeight = getHeight();
        int marginBottom = 20;
        int dialogX = parentX + (parentWidth - dialogWidth) / 3;
        int dialogY = parentY + parentHeight - dialogHeight - marginBottom;

        setLocation(dialogX, dialogY);
    }

    @Override
    public void onAllShapesMatched() {
        if (currentLevel != null && !currentLevel.isLocked() && !currentLevel.isCompleted()) {
            currentLevel.setLocked(false);
            currentLevel.setCompleted(true);
            int currentIndex = levels.indexOf(currentLevel);
            if (currentIndex != -1 && currentIndex < levels.size() - 1) {
                nextLevel = levels.get(currentIndex + 1);
                nextLevel.setLocked(false);
                nextLevel.setCompleted(false);
            }
            saveLevelProperties(); 
            refreshShapeLevelPanel();
        }
    }

    public void refreshShapeLevelPanel() {
        themes.removeAll();

        for (ShapeLevel level : levels) {
            JPanel levelPanel = shapeLevelPanel.createShapeLevelPanel(level);
            levelPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleLevelClick(e, level);
                }
            });
            themes.add(levelPanel);
        }

        themes.revalidate();
        themes.repaint();
    }
}
