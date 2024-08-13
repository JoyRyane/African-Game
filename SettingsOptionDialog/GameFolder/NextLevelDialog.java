package GameFolder;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NextLevelDialog extends JDialog {

    private RoundedPanel contentPanel;
    private JPanel buttonPanel, homePanel;
    RoundedButton nextButton;
    private LevelTopBarPanel levelTopBarPanel;
    private ShapeLevelSelectDialog shapeLevelSelectDialog;
    private ShapeLevel  level;
    int levelNumber,i, index, catIndex;
    private Timer shapeNameTimer;
    private ShapeLevel shapeLevel, nextLevel;
    private JLabel label;
    private ShapeMatchListener shapeMatchListener;
    private LandingPageFrame landingPageFrame;
    private GameSelectBoardPanel gameSelectBoardPanel;
    
    Properties properties = new Properties();
    private String[] categoryNames = {"SHAPES", "NUMBERS", "LETTERS", "FRUITS"};
    private boolean[] locked;
    private boolean[] completed;
    
    String[] shapeNames = {"Circle", "Square", "Triangle", "Rhombus", "Star"};
    String[] shapeNames2 = {"Circle", "Square", "Triangle", "Rhombus", "Star", "Rectangle"};
    String[] shapeNames3 = {"Circle", "Square", "Triangle", "Rhombus", "Star", "Rectangle", "Heart"};
    String[] shapeNames4 = {"Circle", "Square", "Triangle", "Rhombus", "Star", "Rectangle", "Heart", "Parallelogram"};
    String[] shapeNames5 = {"Circle", "Square", "Triangle", "Rhombus", "Star", "Rectangle", "Heart", "Parallelogram", "Trapezium"};
    private Polygon[] shapes, shapes2, shapes3, shapes4, shapes5;
    private Color shapeColors[], shapeColors2[], shapeColors3[], shapeColors4[], shapeColors5[];
    private int currentLabelIndex;
    private int centerX, centerY;
    private final int radius = -180;

    public NextLevelDialog(JFrame parent, ShapeLevelSelectDialog shapeLevelSelectDialog,
                           LevelTopBarPanel levelTopBarPanel,ShapeLevel shapeLevel,ShapeLevel nextLevel,
                           ShapeMatchListener shapeMatchListener,
                           int levelNumber, int index, LandingPageFrame landingPageFrame,int catIndex, GameSelectBoardPanel gameSelectBoardPanel) {
        super(parent, "Popup Dialog", true);
        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
        this.levelTopBarPanel = levelTopBarPanel;
        this.levelNumber = levelNumber;
        this.index = index;
        this.shapeLevel = shapeLevel;
        this.nextLevel = nextLevel;
        this.shapeMatchListener = shapeMatchListener;    
        this.landingPageFrame = landingPageFrame;
        this.gameSelectBoardPanel = gameSelectBoardPanel;
        this.catIndex = catIndex;
        this.properties = new Properties();
        
        initializeShapes();
        initUI();
        layoutUI();
        handleEventsUI();
        levelTopBarPanel.removeFromParent();
        initializeAnimation();
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
        
        nextButton = new RoundedButton("Next Level");
        
        label = new JLabel("Next Level", JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(240, 164, 75));
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

                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof NextLevelBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
                loadProperties();

                
                if(levelNumber <=5) {
                    shapeLevelSelectDialog.handleLevelClick(e, nextLevel);
                }
                else {
                	
                	JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                    set.dispose(); 
                    
                	CategoryCompletedBackgroundDialog categoryDialog = new CategoryCompletedBackgroundDialog(landingPageFrame);
                	
                	
                    	Timer timer = new Timer(2000, event -> {
                    		categoryDialog.setVisible(true);
                      });
                      timer.setRepeats(false);
                      timer.start();
                      unlockCategory();
                      
                }
                
                levelNumber++;

                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });

        homePanel.add(homeLabel);

        label.setEnabled(false);
	    buttonPanel.add(label);
        contentPanel.add(homePanel, BorderLayout.NORTH);
        contentPanel.add(nameImageLabel, BorderLayout.EAST);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
        pack();
        centerX = getWidth() / 2;
        centerY = getHeight() /2 + 150;
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
    
    public void unlockCategory() {
        	properties.setProperty(categoryNames[catIndex] + ".completed", "true");
        	completed[catIndex] = true;
        	locked[catIndex + 1] = false;
        	gameSelectBoardPanel.unlockNextCategory(catIndex);
        saveProperties();
    }
    private void initializeShapes() {
        
            switch (levelNumber) {
                case 1:
                	animatedShapes();
                    break;
                case 2:
                	animatedShapes();
                    break;
                case 3:
                	animatedShapes2();
                    break;
                case 4:
                	animatedShapes3();
                    break;
                case 5:
                	animatedShapes4();
                    break;
                case 6:
                	animatedShapes5();
                    break;
                default:
                    break;
            }
    }
    
    public void animatedShapes() {
    	shapes = new Polygon[shapeNames.length];        
        shapeColors = new Color[shapeNames.length]; 

        shapeColors[0] = Color.GREEN; 
        shapeColors[1] = Color.BLUE; 
        shapeColors[2] = Color.MAGENTA; 
        shapeColors[3] = Color.RED; 
        shapeColors[4] = Color.YELLOW; 
        
        for (int i = 0; i < shapeNames.length; i++) {
            switch (shapeNames[i].toLowerCase()) {
                case "circle":
                    shapes[i] = createCircle(centerX + 120, centerY + 40, 40); 
                    break;
                case "square":
                    shapes[i] = createSquare(centerX + 310, centerY + 30, 80); 
                    break;
                case "triangle":
                	shapes[i] = createTriangle(centerX + 390, centerY + 200, 100); 
                    break;
                case "rhombus":
                	shapes[i] = CreateShapes.createRhombus(centerX + 270, centerY + 340, 35, 25); 
                    break;
                case "star":
                	shapes[i] = CreateShapes.createStar(centerX + 80, centerY + 300, 50, 25,5); 
                    break;
                default:
                    break;
            }
        }
        
        
    }
    public void animatedShapes2() {
        shapes2 = new Polygon[shapeNames2.length];
        shapeColors2 = new Color[shapeNames2.length];
        
        shapeColors2[0] = Color.YELLOW;
        shapeColors2[1] = Color.ORANGE;
        shapeColors2[2] = Color.RED;
        shapeColors2[3] = Color.MAGENTA;
        shapeColors2[4] = Color.GREEN;
        shapeColors2[5] = Color.CYAN;
        
        for (int i = 0; i < shapeNames2.length; i++) {
            switch (shapeNames2[i].toLowerCase()) {
                case "circle":
                    shapes2[i] = createCircle(centerX + 130, centerY + 40, 40);
                    break;
                case "square":
                    shapes2[i] = createSquare(centerX + 320, centerY + 40, 70);
                    break;
                case "triangle":
                    shapes2[i] = createTriangle(centerX + 400, centerY + 200, 90);
                    break;
                case "rhombus":
                    shapes2[i] = CreateShapes.createRhombus(centerX + 270, centerY + 350, 30, 25);
                    break;
                case "star":
                    shapes2[i] = CreateShapes.createStar(centerX + 95, centerY + 320, 45, 22,5);
                    break;
                case "rectangle":
                    shapes2[i] = createRectangle(centerX + 40, centerY + 150, 100, 50);
                    break;
                default:
                    break;
            }
        }
    }
    
    public void animatedShapes3() {
        shapes3 = new Polygon[shapeNames3.length];
        shapeColors3 = new Color[shapeNames3.length];
        
        shapeColors3[0] = Color.MAGENTA;
        shapeColors3[1] = Color.GREEN;
        shapeColors3[2] = Color.ORANGE;
        shapeColors3[3] = Color.PINK;
        shapeColors3[4] = Color.BLUE;
        shapeColors3[5] = Color.CYAN;
        shapeColors3[6] = Color.RED;
        
        for (int i = 0; i < shapeNames3.length; i++) {
            switch (shapeNames3[i].toLowerCase()) {
                case "circle":
                    shapes3[i] = createCircle(centerX + 115, centerY + 45, 30);
                    break;
                case "square":
                    shapes3[i] = createSquare(centerX + 280, centerY + 35, 60);
                    break;
                case "triangle":
                    shapes3[i] = createTriangle(centerX + 395, centerY + 150, 80);
                    break;
                case "rhombus":
                    shapes3[i] = CreateShapes.createRhombus(centerX + 350, centerY + 300, 25, 20);
                    break;
                case "star":
                    shapes3[i] = CreateShapes.createStar(centerX + 200, centerY + 380, 40, 20,5);
                    break;
                case "rectangle":
                    shapes3[i] = createRectangle(centerX + 50, centerY + 300, 90, 45);
                    break;
                case "heart":
                    shapes3[i] = CreateShapes.createHeart(centerX + 10, centerY + 55, 70, 65);
                    break;
                default:
                    break;
            }
        }
    }
    
    public void animatedShapes4() {
        shapes4 = new Polygon[shapeNames4.length];
        shapeColors4 = new Color[shapeNames4.length];
        
        shapeColors4[0] = Color.MAGENTA; 
        shapeColors4[1] = Color.GREEN; 
        shapeColors4[2] = Color.ORANGE; 
        shapeColors4[3] = Color.PINK; 
        shapeColors4[4] = Color.RED; 
        shapeColors4[5] = Color.CYAN; 
        shapeColors4[6] = Color.BLUE; 
        shapeColors4[7] = Color.YELLOW; 
        
        for (int i = 0; i < shapeNames4.length; i++) {
            switch (shapeNames4[i].toLowerCase()) {
                case "circle":
                    shapes4[i] = createCircle(centerX + 130, centerY + 60, 20); 
                    break;
                case "square":
                    shapes4[i] = createSquare(centerX + 280, centerY + 30, 40); 
                    break;
                case "triangle":
                    shapes4[i] = createTriangle(centerX + 400, centerY + 120, 50); 
                    break;
                case "rhombus":
                    shapes4[i] = CreateShapes.createRhombus(centerX + 400, centerY + 250, 15, 7); 
                    break;
                case "star":
                    shapes4[i] = CreateShapes.createStar(centerX + 320, centerY + 360, 30, 15,5); 
                    break;
                case "rectangle":
                    shapes4[i] = createRectangle(centerX + 170, centerY + 320, 60, 30); 
                    break;
                case "heart":
                    shapes4[i] = CreateShapes.createHeart(centerX + 40, centerY + 250, 45, 40); 
                    break;
                case "parallelogram":
                    shapes4[i] = CreateShapes.createParallelogram(centerX + 40, centerY + 130, 45, 40); 
                    break;
                default:
                    break;
            }
        }
    }
    public void animatedShapes5() {
        shapes5 = new Polygon[shapeNames5.length];
        shapeColors5 = new Color[shapeNames5.length];
        
        shapeColors5[0] = Color.MAGENTA; 
        shapeColors5[1] = Color.WHITE; 
        shapeColors5[2] = Color.ORANGE; 
        shapeColors5[3] = Color.YELLOW; 
        shapeColors5[4] = Color.BLUE; 
        shapeColors5[5] = Color.CYAN; 
        shapeColors5[6] = Color.GREEN; 
        shapeColors5[7] = Color.PINK; 
        shapeColors5[8] = Color.RED; 
        
        for (int i = 0; i < shapeNames5.length; i++) {
            switch (shapeNames5[i].toLowerCase()) {
                case "circle":
                    shapes5[i] = createCircle(centerX + 143, centerY + 55, 20); 
                    break;
                case "square":
                    shapes5[i] = createSquare(centerX + 270, centerY + 30, 40); 
                    break;
                case "triangle":
                    shapes5[i] = createTriangle(centerX + 380, centerY + 90, 50); 
                    break;
                case "rhombus":
                    shapes5[i] = CreateShapes.createRhombus(centerX + 400, centerY + 200, 15, 7); 
                    break;
                case "star":
                    shapes5[i] = CreateShapes.createStar(centerX + 370, centerY + 320, 30, 15,5); 
                    break;
                case "rectangle":
                    shapes5[i] = createRectangle(centerX + 260, centerY + 390, 60, 30); 
                    break;
                case "heart":
                    shapes5[i] = CreateShapes.createHeart(centerX + 120, centerY + 330, 45, 40); 
                    break;
                case "parallelogram":
                    shapes5[i] = CreateShapes.createParallelogram(centerX + 40, centerY + 250, 45, 40); 
                    break;
                case "trapezium":
                    shapes5[i] = CreateShapes.createTrapezium(centerX + 35, centerY + 120, 45, 40); 
                    break;
                default:
                    break;
            }
        }
    }
    
    private void initializeAnimation() {
        int shapeDelay = 1000; 
        int textDelay = 2000;
        i = 0;

        
        	shapeNameTimer = new Timer(textDelay + shapeDelay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	if(levelNumber == 1 || levelNumber == 2) {
	                    if (i < shapeNames.length) {
	                    	animateShape(shapes[i], shapeColors[i]);

                            Timer textTimer = new Timer(textDelay, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    animateText(shapeNames[i]);
                                    i++;
                                }
                            });
                            textTimer.setRepeats(false);
                            textTimer.start();
	                    } else {
	                        shapeNameTimer.stop(); 
	                        label.setEnabled(true);
	                     }
                	}else if(levelNumber == 3) {
                		if (i < shapeNames2.length) {
                			animateShape(shapes2[i], shapeColors2[i]);

                            Timer textTimer = new Timer(textDelay, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    animateText(shapeNames2[i]);
                                    i++;
                                }
                            });
                            textTimer.setRepeats(false);
                            textTimer.start();
                        } else {
                            shapeNameTimer.stop(); 
                            label.setEnabled(true);
                        }
                	}else if(levelNumber == 4) {
                		if (i < shapeNames3.length) {
                            animateShape(shapes3[i], shapeColors3[i]);

                            Timer textTimer = new Timer(textDelay, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    animateText(shapeNames3[i]);
                                    i++;
                                }
                            });
                            textTimer.setRepeats(false);
                            textTimer.start();
                        } else {
                            shapeNameTimer.stop(); 
                            label.setEnabled(true);
                        }
                	}else if(levelNumber == 5) {
                		if (i < shapeNames4.length) {
                            animateShape(shapes4[i], shapeColors4[i]);

                            Timer textTimer = new Timer(textDelay, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    animateText(shapeNames4[i]);
                                    i++;
                                }
                            });
                            textTimer.setRepeats(false);
                            textTimer.start();
                        } else {
                            shapeNameTimer.stop();   
                            label.setEnabled(true);
                        }
                	}else if(levelNumber == 6) {
                		if (i < shapeNames5.length) {
                            animateShape(shapes5[i], shapeColors5[i]);

                            Timer textTimer = new Timer(textDelay, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    animateText(shapeNames5[i]);
                                    i++;
                                }
                            });
                            textTimer.setRepeats(false);
                            textTimer.start();
                        } else {
                            shapeNameTimer.stop(); 
                            label.setEnabled(true);
                        }
                	}
                	
                }
            });
        	
            shapeNameTimer.start();
            
            
    }
    
    private void addButtonToPanel() {
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
                if(levelNumber <= 6) {
                    shapeLevelSelectDialog.handleLevelClick(e, level);
                }

                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });
        
        Timer timer = new Timer(1000, event -> {
        	buttonPanel.add(label);
        	buttonPanel.revalidate(); 
        	buttonPanel.repaint();
       });
       timer.setRepeats(false);
       timer.start();
    }

    private void animateText(String text) {
        JLabel animatedLabel = new JLabel(text);
        animatedLabel.setFont(new Font("Arial", Font.BOLD, 24));
        animatedLabel.setHorizontalAlignment(JLabel.CENTER);
        animatedLabel.setSize(200, 100); 
        animatedLabel.setVerticalAlignment(JLabel.TOP);

        contentPanel.add(animatedLabel);

        if(levelNumber == 1 || levelNumber == 2) {
        	double angle = Math.toRadians(40) / (shapeNames.length) * i + (i + 1); 
            int x = (int) (centerX + radius * Math.cos(angle)); 
            int y = (int) (centerY + radius * Math.sin(angle)); 
            animatedLabel.setLocation(x - animatedLabel.getWidth()/20, y - 40 ); 
        }else if( levelNumber == 3) {
        	double angle = Math.toRadians(40) / (shapeNames2.length) * i + (i + 1); 
            int x = (int) (centerX + radius * Math.cos(angle)); 
            int y = (int) (centerY + radius * Math.sin(angle)); 
            animatedLabel.setLocation(x - animatedLabel.getWidth()/24, y - 40 ); 
        }else if(levelNumber == 4) {
        	double angle = Math.toRadians(-20) / (shapeNames3.length) * i + (i + 1); 
            int x = (int) (centerX + radius * Math.cos(angle)); 
            int y = (int) (centerY + radius * Math.sin(angle)); 
            animatedLabel.setLocation(x - animatedLabel.getWidth()/24, y - 40 ); 
        }else if(levelNumber == 5) {
        	double angle = Math.toRadians(-90) / (shapeNames4.length) * i + (i + 1); 
            int x = (int) (centerX + radius * Math.cos(angle)); 
            int y = (int) (centerY + radius * Math.sin(angle)); 
            animatedLabel.setLocation(x + animatedLabel.getWidth()/20, y - 40 ); 
        }else if(levelNumber == 6) {
        	double angle = Math.toRadians(-150) / (shapeNames5.length) * i + (i + 1); 
            int x = (int) (centerX + radius * Math.cos(angle)); 
            int y = (int) (centerY + radius * Math.sin(angle)); 
            animatedLabel.setLocation(x + animatedLabel.getWidth()/18, y - 40 ); 
        }
        
        contentPanel.repaint();
    }

    private Polygon createCircle(int centerX, int centerY, int radius) {
        int sides = 50;
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];
        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides;
            xPoints[i] = (int) (centerX + radius * Math.cos(angle));
            yPoints[i] = (int) (centerY + radius * Math.sin(angle));
        }
        return new Polygon(xPoints, yPoints, sides);
    }
    
    private Polygon createRectangle(int centerX, int centerY, int width, int height) {
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int[] xPoints = {centerX - halfWidth, centerX + halfWidth, centerX + halfWidth, centerX - halfWidth};
        int[] yPoints = {centerY - halfHeight, centerY - halfHeight, centerY + halfHeight, centerY + halfHeight};
        return new Polygon(xPoints, yPoints, 4);
    }

    private Polygon createSquare(int centerX, int centerY, int sideLength) {
        int halfSide = sideLength / 2;
        int[] xPoints = {centerX - halfSide, centerX + halfSide, centerX + halfSide, centerX - halfSide};
        int[] yPoints = {centerY - halfSide, centerY - halfSide, centerY + halfSide, centerY + halfSide};
        return new Polygon(xPoints, yPoints, 4);
    }

    private Polygon createTriangle(int centerX, int centerY, int sideLength) {
        int halfSide = sideLength / 2;
        int height = (int) (Math.sqrt(3) * halfSide);
        int[] xPoints = {centerX, centerX - halfSide, centerX + halfSide};
        int[] yPoints = {centerY - height / 2, centerY + height / 2, centerY + height / 2};
        return new Polygon(xPoints, yPoints, 3);
    }

    private void animateShape(Polygon shape, Color color) {
        JPanel shapePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(color); 
                g2d.fillPolygon(shape);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50); 
            }
        };

        shapePanel.setOpaque(false); 
        contentPanel.add(shapePanel, BorderLayout.CENTER); 

        contentPanel.revalidate();
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