package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NextLevelDialog extends JDialog {

    private RoundedPanel contentPanel;
    private JPanel buttonPanel, homePanel;
    RoundedButton nextButton;
    private GameBoardDialog gameBoardDialog;
    private LevelTopBarPanel levelTopBarPanel;
    private ShapeLevelSelectDialog shapeLevelSelectDialog;
    ShapeLevel currentLevel, level;
    int levelNumber, index;
    private JLabel shapeNameLabel;
    private Timer shapeNameTimer;
    int i;
    
    String[] shapeNames = {"Circle", "Square", "Triangle"}; // Example shape names
    private int currentLabelIndex;
    private int centerX, centerY; // Center coordinates of the semicircle
    private final int radius = 150; // Radius of the semicircle

    public NextLevelDialog(JFrame parent, ShapeLevelSelectDialog shapeLevelSelectDialog,
    		LevelTopBarPanel levelTopBarPanel, int levelNumber, int index) {
        super(parent, "Popup Dialog", true);
        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
        this.levelTopBarPanel = levelTopBarPanel;
        this.levelNumber = levelNumber;
        this.index = index;
        // Initialize the currentLevel and level here or through a method
        this.currentLevel = new ShapeLevel("Level " + levelNumber,false,false); // Example initialization
        this.level = new ShapeLevel("Level " + (levelNumber + 1),false,false); // Example initialization for next level
        initUI();
        layoutUI();
        handleEventsUI();
        levelTopBarPanel.removeFromParent();
//        System.out.println(currentLevel);
        initializeAnimation();
//        System.out.println(levelNumber);
    }

    private void handleEventsUI() {
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
        
//        System.out.println(levelNumber);

        homePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        homePanel.setOpaque(false);

        ImageIcon homeImageIcon = new ImageIcon("icon\\home.png");
        Image homeImg = homeImageIcon.getImage();
        Image resizedHomeImg = homeImg.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon resizedHomeImageIcon = new ImageIcon(resizedHomeImg);

        JLabel homeLabel = new JLabel();
        homeLabel.setIcon(resizedHomeImageIcon);
        homeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NextLevelDialog.this);

                removeLevelTopBarPanel(parentFrame);

                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof NextLevelBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }

                if (parentFrame instanceof LandingPageFrame) {
                    LandingPageFrame landingPageFrame = (LandingPageFrame) parentFrame;
                    landingPageFrame.addTopRightPanel();
                    landingPageFrame.addIconImage();
                    landingPageFrame.addGameSelectBoardPanel();
                }

                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });

        ImageIcon nameImageIcon = new ImageIcon("icon\\boy.png");
        Image nameImg = nameImageIcon.getImage();
        Image resizedNameImg = nameImg.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon resizedNameImageIcon = new ImageIcon(resizedNameImg);

        JLabel nameImageLabel = new JLabel();
        nameImageLabel.setIcon(resizedNameImageIcon);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        ImageIcon imageIcon = new ImageIcon("icon\\next_Level.png");
        Image img = imageIcon.getImage();
        Image resizedImg = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        JLabel label = new JLabel("Next Level", resizedIcon, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, 12));
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NextLevelDialog.this);
                JDialog parentDialog = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                JFrame grandParentFrame = (JFrame) SwingUtilities.getWindowAncestor(parentDialog);
                
//                if (parentFrame != null) {
//                    Container contentPane = parentFrame.getContentPane();
//                    Component[] components = contentPane.getComponents();
//                    for (Component component : components) {
//                        if (component instanceof LevelTopBarPanel) {
//                            contentPane.remove(component);
//                        }
//                    }
//                    parentFrame.revalidate();
//                    parentFrame.repaint();
//                }

                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof NextLevelBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }

                levelNumber++;

//                if (grandParentFrame instanceof LandingPageFrame) {
//                    LandingPageFrame landingPageFrame = (LandingPageFrame) grandParentFrame;
//                    landingPageFrame.removeTopRightPanel();
//                    landingPageFrame.removeImageLabelPanel();

//                    gameBoardDialog = new GameBoardDialog(parentFrame, landingPageFrame, shapeLevelSelectDialog, levelTopBarPanel, levelNumber);
//                    landingPageFrame.setGameBoardDialog(gameBoardDialog);
//                    LevelTopBarPanel newLevelTopBarPanel = new LevelTopBarPanel(level.getName(), landingPageFrame, gameBoardDialog, shapeLevelSelectDialog, levelNumber);
//                    landingPageFrame.add(newLevelTopBarPanel, BorderLayout.NORTH);
                    if(levelNumber <= 6) {
                    	shapeLevelSelectDialog.handleLevelClick(e, level);
                    }else {
//                    	System.out.println("Done!!!!");
                    }
//                    	gameBoardDialog.setVisible(true);
//                }

                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });

        homePanel.add(homeLabel);
        buttonPanel.add(label);
        contentPanel.add(homePanel, BorderLayout.NORTH);
        contentPanel.add(nameImageLabel, BorderLayout.EAST);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
        pack();
        centerX = getWidth() / 2;
        centerY = getHeight() / 2 + 50; // Add 50 for better visibility
    }
    
//    private void initializeAnimation() {
//        String[] shapeNames = {"Circle", "Square", "Triangle"}; // Example shape names
////        int i = 0;
//
//        shapeNameLabel = new JLabel();
//        shapeNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        shapeNameLabel.setHorizontalAlignment(JLabel.CENTER);
//        shapeNameLabel.setPreferredSize(new Dimension(200, 50));
//        contentPanel.add(shapeNameLabel, BorderLayout.CENTER);
//
//        shapeNameTimer = new Timer(2000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	int i = 0;
//                if (i < shapeNames.length) {
//                    shapeNameLabel.setText(shapeNames[i]);
//                    i++;
//                } else {
//                    shapeNameTimer.stop();
//                    dispose(); // Close dialog after animation completes
//                }
////            	for(int i = 0; i < shapeNames.length; i++) {
////            		shapeNameLabel.setText(shapeNames[i]);
////            		i++;
////            	}
//            }
//        });
//
//        shapeNameTimer.start();
//    }
    
    private void initializeAnimation() {
        

        int delay = 2000; // Delay between shape transitions in milliseconds
        i = 0;
        

        shapeNameTimer = new Timer(delay, new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                if (i < shapeNames.length) {
                    animateText(shapeNames[i]);
                    i++;
                } else {
                    shapeNameTimer.stop(); // Stop the timer after all shapes are displayed
//                    dispose(); // Close dialog after animation completes
                }
            }
        });

        shapeNameTimer.start();
    }

//    private void animateText(String text) {
//        JLabel animatedLabel = new JLabel(text);
//        animatedLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        animatedLabel.setHorizontalAlignment(JLabel.CENTER);
////        animatedLabel.setPreferredSize(new Dimension(200, 50));
//        animatedLabel.setVerticalAlignment(JLabel.TOP);
//
//        contentPanel.add(animatedLabel, BorderLayout.CENTER);
////        pack();
//        
//
//        Timer slideTimer = new Timer(50, new ActionListener() {
//            int startY = 0;
//            int targetY = 700 - 500;
//            int incrementY = 5;
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                animatedLabel.setBounds(0, startY, 750, 500);
//                startY += incrementY;
////                double angle = Math.toRadians(90) / (shapeNames.length - 1) * i; // Calculate angle
////                int x = (int) (centerX + radius * Math.cos(angle)); // X position
////                int y = (int) (centerY - radius * Math.sin(angle)); // Y position
////
////                animatedLabel.setLocation(x - animatedLabel.getWidth() / 2, y); // Adjust for label width
//                contentPanel.repaint();
//
//                if (startY >= targetY) {
//                    ((Timer) e.getSource()).stop();
//                }
//            }
//        });
//
//        slideTimer.start();
//    }
    
//    private void animateText(String text) {
//        JLabel animatedLabel = new JLabel(text);
//        animatedLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        animatedLabel.setHorizontalAlignment(JLabel.CENTER);
//        animatedLabel.setSize(200, 50); // Set size for layout manager
//        animatedLabel.setVerticalAlignment(JLabel.TOP);
//
//        contentPanel.add(animatedLabel);
//        
//        double angle = Math.toRadians(90) / (shapeNames.length - 1) * i; // Calculate angle
//        int x = (int) (centerX + radius * Math.cos(angle)); // X position
//        int y = (int) (centerY - radius * Math.sin(angle)); // Y position
//
//        animatedLabel.setLocation(x - animatedLabel.getWidth() / 2, y); // Adjust for label width
//
//        contentPanel.repaint();
//        
////        double angle = Math.toRadians(90) / (shapeNameLabels.length - 1) * currentLabelIndex; // Calculate angle
////        int x = (int) (centerX + radius * Math.cos(angle)); // X position
////        int y = (int) (centerY + radius * Math.sin(angle)); // Y position
////
////        animatedLabel.setLocation(x - animatedLabel.getWidth() / 2, y); // Adjust for label width
//
//        // Calculate the position along the semicircle
//        
//    }
    
    private void animateText(String text) {
        JLabel animatedLabel = new JLabel(text);
        animatedLabel.setFont(new Font("Arial", Font.BOLD, 24));
        animatedLabel.setHorizontalAlignment(JLabel.CENTER);
        animatedLabel.setSize(200, 50); // Set size for layout manager
        animatedLabel.setVerticalAlignment(JLabel.TOP);

        contentPanel.add(animatedLabel);

        // Calculate the position along the semicircle
        double angle = Math.toRadians(270) / (shapeNames.length - 1) * i; // Calculate angle
        int x = (int) (centerX + radius * Math.cos(angle)); // X position
        int y = (int) (centerY + radius * Math.sin(angle)); // Y position

        animatedLabel.setLocation(x - animatedLabel.getWidth() / 2, y); // Adjust for label width

        contentPanel.repaint();
    }


    private void removeLevelTopBarPanel(JFrame parentFrame) {
        if (parentFrame != null) {
            for (Component component : parentFrame.getContentPane().getComponents()) {
                if (component instanceof LevelTopBarPanel) {
                    parentFrame.getContentPane().remove(component);
                    parentFrame.revalidate();
                    parentFrame.repaint();
                    break;
                }
            }
        }
    }

    private void layoutUI() {
        this.setSize(750, 500);
        this.setMinimumSize(new Dimension(100, 200));
        this.setMaximumSize(new Dimension(750, 500));

        this.setLocationRelativeTo(getParent());
    }

    private void updatePosition() {
        setLocationRelativeTo(getParent());
    }
}
