package GameFolder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

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
    private int levelNumber, catIndex;
    private String categoryName;
    private ShapeMatchListener shapeMatchListener;
    private BackgroundMusicController musicController;
    private GameSelectBoardPanel gameSelectBoardPanel;

    public ShapeLevelSelectDialog(JFrame parent, String categoryName, int catIndex, BackgroundMusicController musicController,
    		GameSelectBoardPanel gameSelectBoardPanel) {
        super(parent, "Popup Dialog", false);
        this.categoryName = categoryName;
        this.catIndex = catIndex;
        this.musicController = musicController;
        this.gameSelectBoardPanel = gameSelectBoardPanel;
        loadLevelProperties();
        initUI();
        layoutUI();
        handleEventsUI();
        updatePosition();
    }

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
            int levelIndex = Integer.parseInt(levelProperties.getProperty("level" + i + ".index"));
            levels.add(new ShapeLevel(levelName, locked, completed, levelIndex));
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
        if (catIndex == 0) {
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
        }
        if (catIndex == 1) {
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
    	

        if (level.isLocked()) {
            return;
        }
        
        currentLevel = level;
        int currentIndex = currentLevel.getIndex();
	    
	    if(currentIndex < 5) {
	    	nextLevel = levels.get(currentIndex + 1);
	    }else {
	    	nextLevel = null;
	    }
//	    System.out.println(currentIndex);
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(ShapeLevelSelectDialog.this);
        JDialog parentDialog = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
        JFrame grandParentFrame = (JFrame) SwingUtilities.getWindowAncestor(parentDialog);

        if (grandParentFrame instanceof LandingPageFrame) {
            LandingPageFrame landingPageFrame = (LandingPageFrame) grandParentFrame;
            landingPageFrame.removeTopRightPanel();
            landingPageFrame.removeImageLabelPanel();
            
            levelNumber = Integer.parseInt(currentLevel.getName().split(" ")[1]);
            ShapeModel shapeModel = ShapeModelSingleton.getInstance(gameBoardDialog, landingPageFrame, this,
                    shapeMatchListener, levelTopBarPanel, levelNumber, currentIndex, level, nextLevel,catIndex, gameSelectBoardPanel);

            LevelTopBarPanel levelTopBarPanel = new LevelTopBarPanel(level.getName(),
                    landingPageFrame, gameBoardDialog, this, levelNumber, categoryName, currentIndex, level,nextLevel,shapeModel, musicController, 
                    catIndex,gameSelectBoardPanel);
            gameBoardDialog = new GameBoardDialog(parentFrame, landingPageFrame, this, levelTopBarPanel, levelNumber, currentIndex, level, 
            		nextLevel,catIndex, gameSelectBoardPanel);

            landingPageFrame.setGameBoardDialog(gameBoardDialog);

            landingPageFrame.add(levelTopBarPanel, BorderLayout.NORTH);
            gameBoardDialog.setVisible(true);
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
        int dialogWidth = this.getWidth();
        int dialogHeight = this.getHeight();

        int dialogX = parentX + (parentWidth - dialogWidth) / 2;
        int dialogY = parentY + (parentHeight - dialogHeight) / 2;

        setLocation(dialogX, dialogY);
    }

    private void refreshShapeLevelPanel() {
        themes.removeAll();
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
        themes.revalidate();
        themes.repaint();
    }

    @Override
    public void onAllShapesMatched() {
    	int currentIndex = currentLevel.getIndex();
    	if(currentIndex < 5) {
	    	nextLevel = levels.get(currentIndex + 1);
	    }else {
	    	nextLevel = null;
	    }
    	
        if (currentLevel != null && !currentLevel.isLocked() && !currentLevel.isCompleted()) {
            currentLevel.setLocked(false);
            currentLevel.setCompleted(true);
            if (currentIndex != -1 && currentIndex < levels.size() - 1) {
                
                nextLevel.setLocked(false);
                nextLevel.setCompleted(false);
                
            } 
            else {
                nextLevel = null; // No next level available
            }
            saveLevelProperties();
            refreshShapeLevelPanel();
        }
    }
}
