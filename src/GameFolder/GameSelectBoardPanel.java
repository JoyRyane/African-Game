package GameFolder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameSelectBoardPanel extends JDialog {
    private LandingPageFrame parent;
    private RoundedPanel backgroundPanel;
    private JPanel innerPanel;
    private CategoryPanel categoryPanel;
    private BackgroundMusicController musicController;
    private Properties properties;
    private Properties flag;

    private String[] categoryNames = {"SHAPES", "NUMBERS", "LETTERS", "FRUITS"};
    private String[] iconPaths = {"icon/shapesIcon.png", "icon/numbersIcon.png", "icon/lettersIcon.png", "icon/fruitsIcon.png"};
    private Color[] colors = {new Color(250, 147, 26), new Color(250, 26, 26), new Color(192, 26, 250), new Color(26, 143, 250)};
    private boolean[] locked;
    private boolean[] completed;

    public GameSelectBoardPanel(LandingPageFrame parent, BackgroundMusicController musicController) {
        super(parent, "Popup Dialog", false);
        this.parent = parent;
        this.musicController = musicController;
        this.properties = new Properties();
        this.flag = new Properties();
        loadProperties();
        initUI();
        layoutUI();
    }

    private void loadProperties() {
        try (InputStream input = new FileInputStream("resources/categoryStates.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        locked = new boolean[categoryNames.length];
        completed = new boolean[categoryNames.length];
        for (int i = 0; i < categoryNames.length; i++) {
            locked[i] = Boolean.parseBoolean(properties.getProperty(categoryNames[i] + ".locked", "true"));
            completed[i] = Boolean.parseBoolean(properties.getProperty(categoryNames[i] + ".completed", "false"));
        }
    }

    private void saveProperties() {
        try (OutputStream output = new FileOutputStream("resources/categoryStates.properties")) {
            for (int i = 0; i < categoryNames.length; i++) {
                properties.setProperty(categoryNames[i] + ".locked", Boolean.toString(locked[i]));
                properties.setProperty(categoryNames[i] + ".completed", Boolean.toString(completed[i]));
            }
            properties.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initUI() {
        backgroundPanel = new RoundedPanel(10, new Color(96, 151, 95));
        backgroundPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        backgroundPanel.setLayout(new BorderLayout());

        innerPanel = new JPanel();
        innerPanel.setBackground(new Color(107, 218, 105));
        innerPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.X_AXIS));

        categoryPanel = new CategoryPanel();

        for (int i = 0; i < categoryNames.length; i++) {
            addCategoryPanel(categoryNames[i], iconPaths[i], colors[i], locked[i], completed[i], i);
        }

        backgroundPanel.add(innerPanel, BorderLayout.CENTER);
        setContentPane(backgroundPanel);
    }

    private void addCategoryPanel(String categoryName, String iconPath, Color color, boolean isLocked, boolean isCompleted, int index) {
        JPanel categoryPanel = this.categoryPanel.createPanel(categoryName, new ImageIcon(iconPath), color, isLocked, isCompleted);
        if (!isLocked) {
            categoryPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!isLocked) {
                        openShapeLevelSelectDialog(categoryName, index, e);
                    }
                }
            });
        }
        innerPanel.add(categoryPanel);
        innerPanel.add(Box.createHorizontalStrut(10));
        
        
    }
    
    private void openShapeLevelSelectDialog(String categoryName, int index, MouseEvent e) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(GameSelectBoardPanel.this);
        JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
        set.dispose();

        ShapeLevelSelectDialog shapeLevelDialog = new ShapeLevelSelectDialog(parentFrame, categoryName, index, musicController, this);
        shapeLevelDialog.setVisible(true);
    }
    


    void unlockNextCategory(int index) {
        if (index < locked.length - 1) {
            locked[index + 1] = false;
            properties.setProperty(categoryNames[index + 1] + ".locked", "false");
        }
        saveProperties();
        refreshCategoryPanels();
    }

    public void refreshCategoryPanels() {
        innerPanel.removeAll();
        for (int i = 0; i < categoryNames.length; i++) {
            addCategoryPanel(categoryNames[i], iconPaths[i], colors[i], locked[i], completed[i], i);
        }
        innerPanel.revalidate();
        innerPanel.repaint();
    }

    private void layoutUI() {
        this.setSize(600, 300);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));

        parent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                updatePosition();
            }

            @Override
            public void componentResized(ComponentEvent e) {
                updatePosition();
            }
        });

        updatePosition();
    }
    
    public void test() {
    	System.out.println("This is GameSelectBoardPanel!");
    }

    protected void updatePosition() {
        int parentX = parent.getX();
        int parentY = parent.getY();
        int parentWidth = parent.getWidth();
        int parentHeight = parent.getHeight();

        int dialogWidth = getWidth();
        int dialogHeight = getHeight();
        int marginBottom = 70;
        int dialogX = parentX + (parentWidth - dialogWidth) / 2;
        int dialogY = parentY + parentHeight - dialogHeight - marginBottom;

        setLocation(dialogX, dialogY);
    }
}
