//package GameFolder;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//
//public class GameSelectBoardPanel extends JDialog {
//    private LandingPageFrame parent;
//    private RoundedPanel backgroundPanel;
//    private JPanel innerPanel;
//    private CategoryPanel categoryPanel;
//    private LevelTopBarPanel levelTopBarPanel;
//    private String shapeCategory, numberCategory, letterCategory, fruitCategory;
//
//    public GameSelectBoardPanel(LandingPageFrame parent, LevelTopBarPanel levelTopBarPanel) {
//        super(parent, "Popup Dialog", false);
//        this.parent = parent;
//        this.levelTopBarPanel = levelTopBarPanel;
//        initUI();
//        layoutUI();
//    } 
//  
//    private void initUI() { 
//    	  
//    	backgroundPanel = new RoundedPanel(10, new Color(96, 151, 95));
//    	backgroundPanel.setBorder(new EmptyBorder(15, 15, 15, 15)); 
//     	backgroundPanel.setLayout(new BorderLayout()); 
//    	
//    	innerPanel = new JPanel();
//    	innerPanel.setBackground(new Color(107, 218, 105));
//    	innerPanel.setBorder(new EmptyBorder(20,10,20,10));
//    	innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.X_AXIS));
//    	
//    	categoryPanel = new CategoryPanel();
//    	
//    	shapeCategory  = "SHAPES";
//    	numberCategory = "NUMBERS";
//    	letterCategory = "LETTERS";
//    	fruitCategory  = "FRUITS";
//
//        JPanel shapesPanel = categoryPanel.createPanel(shapeCategory, new ImageIcon("icon\\shapesIcon.png"), new Color(250, 147, 26),false);
//        JPanel numbersPanel = categoryPanel.createPanel(numberCategory, new ImageIcon("icon\\numbersIcon.png"), new Color(250,26,26),true);
//        JPanel lettersPanel = categoryPanel.createPanel(letterCategory, new ImageIcon("icon\\lettersIcon.png"), new Color(192,26,250),true);
//        JPanel fruitsPanel = categoryPanel.createPanel(fruitCategory, new ImageIcon("icon\\fruitsIcon.png"), new Color(26,143,250),true);
//        
//        shapesPanel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {   
//            	
//            	JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(GameSelectBoardPanel.this);
//            	
//                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
//                set.dispose();
//                
//                ShapeLevelSelectDialog shapeLevelDialog = new ShapeLevelSelectDialog(parentFrame, levelTopBarPanel, categoryName);
//                shapeLevelDialog.setVisible(true);
//            }
//        });
//        
//    	innerPanel.add(shapesPanel);
//    	innerPanel.add(Box.createHorizontalStrut(10)); 
//    	innerPanel.add(numbersPanel);
//    	innerPanel.add(Box.createHorizontalStrut(10));
//    	innerPanel.add(lettersPanel);
//    	innerPanel.add(Box.createHorizontalStrut(10));
//    	innerPanel.add(fruitsPanel);
//        
//        backgroundPanel.add(innerPanel, BorderLayout.CENTER);
//        setContentPane(backgroundPanel);
//    }
//    
//    private void layoutUI() {
//        this.setSize(600, 300);
//        this.setUndecorated(true);
//        this.setBackground(new Color(0,0,0,0));
//
//        parent.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentMoved(ComponentEvent e) {
//                updatePosition();
//            }
//
//            @Override
//            public void componentResized(ComponentEvent e) {
//                updatePosition();
//           }
//        }); 
//
//        updatePosition();
//    }
//    
//    	protected void updatePosition() {
//        int parentX = parent.getX();
//        int parentY = parent.getY();
//        int parentWidth = parent.getWidth();
//        int parentHeight = parent.getHeight();
//
//        int dialogWidth = getWidth();
//        int dialogHeight = getHeight();
//        int marginBottom = 70;
//        int dialogX = parentX + (parentWidth - dialogWidth) / 2;
//        int dialogY = parentY + parentHeight - dialogHeight - marginBottom;
//
//        setLocation(dialogX, dialogY);
//    }
//    
//    private JPanel createItemPanel(String text, String imagePath, int width, int height) {
//        JPanel itemPanel = new JPanel();
//        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
//        itemPanel.setBackground(new Color(250, 147, 26));
//
//        JLabel textLabel = new JLabel(text);
//        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        itemPanel.add(textLabel);
//
//        JLabel imageLabel = new JLabel();
//        ImageIcon imageIcon = resizeImage(imagePath, width, height);
//        imageLabel.setIcon(imageIcon);
//        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        itemPanel.add(imageLabel);
//
//        return itemPanel;
//    }
//    
//    private ImageIcon resizeImage(String imagePath, int width, int height) {
//        ImageIcon imageIcon = new ImageIcon(imagePath);
//        Image image = imageIcon.getImage();
//        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        return new ImageIcon(scaledImage);
//    }
//}

package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameSelectBoardPanel extends JDialog {
    private LandingPageFrame parent;
    private RoundedPanel backgroundPanel;
    private JPanel innerPanel;
    private CategoryPanel categoryPanel;
    private BackgroundMusicController musicController;
//    private LevelTopBarPanel levelTopBarPanel;

    private String[] categoryNames = {"SHAPES", "NUMBERS", "LETTERS", "FRUITS"};
    private String[] iconPaths = {"icon\\shapesIcon.png", "icon\\numbersIcon.png", "icon\\lettersIcon.png", "icon\\fruitsIcon.png"};
    private Color[] colors = {new Color(250, 147, 26), new Color(250, 26, 26), new Color(192, 26, 250), new Color(26, 143, 250)};

    public GameSelectBoardPanel(LandingPageFrame parent, BackgroundMusicController musicController) {
        super(parent, "Popup Dialog", false);
        this.parent = parent;
        this.musicController = musicController;
//        this.levelTopBarPanel = levelTopBarPanel;
        initUI();
        layoutUI();
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
            addCategoryPanel(categoryNames[i], iconPaths[i], colors[i], i);
        }

        backgroundPanel.add(innerPanel, BorderLayout.CENTER);
        setContentPane(backgroundPanel);
    }

    private void addCategoryPanel(String categoryName, String iconPath, Color color, int index) {
        JPanel categoryPanel = this.categoryPanel.createPanel(categoryName, new ImageIcon(iconPath), color, false);
        categoryPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openShapeLevelSelectDialog(categoryName, index, e);
            }
        });
        innerPanel.add(categoryPanel);
        innerPanel.add(Box.createHorizontalStrut(10));
    }

    private void openShapeLevelSelectDialog(String categoryName, int index, MouseEvent e) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(GameSelectBoardPanel.this);
        JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
        set.dispose();

        ShapeLevelSelectDialog shapeLevelDialog = new ShapeLevelSelectDialog(parentFrame,
        		categoryName, index, musicController);
        shapeLevelDialog.setVisible(true);
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
