package GameFolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.IOException;

public class ShapeModel extends JComponent {
    
    private List<ShapeInfo> movableShapes = new ArrayList<>();
    private List<ShapeInfo> unmovableShapes = new ArrayList<>();
    private List<Confetti> confettiParticles = new ArrayList<>();
    private Point mousePt;
    private ShapeInfo selectedShape;
    private Point originalPosition;
    private JLabel completionLabel;
    private Timer confettiTimer, blinkingTimer;
    private LevelCompletedBackgroundDialog levelCompletedBackgroundDialog;
    private LandingPageFrame landingPageFrame;
    private GameBoardDialog gameBoardDialog;
    private ShapeLevelSelectDialog shapeLevelSelectDialog;
    private ShapeMatchListener shapeMatchListener;
    private int levelNumber,index, catIndex;
    LevelTopBarPanel levelTopBarPanel;
    private ShapeLevel shapeLevel, nextLevel;
    private NextLevelBackgroundDialog nextLevelBackgroundDialog;
    private GameSelectBoardPanel gameSelectBoardPanel;
    private Clip clip;
    
    public ShapeModel(GameBoardDialog gameBoardDialog, LandingPageFrame landingPageFrame, 
    		ShapeLevelSelectDialog shapeLevelSelectDialog, ShapeMatchListener shapeMatchListener, 
    		LevelTopBarPanel levelTopBarPanel,int catIndex,int levelNumber, int index, ShapeLevel shapeLevel, ShapeLevel nextLevel,
    		GameSelectBoardPanel gameSelectBoardPanel) {
    	
    	this.gameBoardDialog = gameBoardDialog;
    	this.landingPageFrame = landingPageFrame;
    	this.shapeLevelSelectDialog = shapeLevelSelectDialog;
    	this.shapeMatchListener = shapeMatchListener;
    	this.levelTopBarPanel = levelTopBarPanel;
    	this.levelNumber = levelNumber;
    	this.index = index;
    	this.shapeLevel = shapeLevel;
    	this.nextLevel = nextLevel;
    	this.gameSelectBoardPanel = gameSelectBoardPanel;
    	this.catIndex = catIndex;

    	initializeShapesForLevels();
        
        completionLabel = new JLabel("");
        completionLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
        completionLabel.setForeground(Color.BLACK);
        completionLabel.setBounds(250, 100, 300, 100);
        setLayout(null);
        add(completionLabel);
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                selectedShape = getMovableShapeAt(mousePt);
                if (selectedShape != null) {
                    originalPosition = new Point(selectedShape.getX(), selectedShape.getY());
                    repaint();
                }
            }

            public void mouseReleased(MouseEvent e) {
            	if (selectedShape != null) {
                  boolean intersected = false;
                  for (ShapeInfo unmovableShape : unmovableShapes) {
                      if (selectedShape.intersects(unmovableShape)) {
                          if (selectedShape.getType() == unmovableShape.getType() && 
                        		  selectedShape.getColor() == unmovableShape.getColor()) {
                              intersected = true;
                              playSound("audio\\sound.wav");
                              checkAllMatched();
                          } else {
                              selectedShape.setX(originalPosition.x);
                              selectedShape.setY(originalPosition.y);
                          }
                          break;
                      }
                  }
                  if (!intersected) {
                      selectedShape.setX(originalPosition.x);
                      selectedShape.setY(originalPosition.y);
                  }
                  repaint();
              }
                selectedShape = null;
                mousePt = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (selectedShape != null) {
                    int dx = e.getX() - mousePt.x;
                    int dy = e.getY() - mousePt.y;
                    selectedShape.move(dx, dy);
                    mousePt = e.getPoint();
                    checkCollisions(selectedShape);
                    repaint();
                }
            }
        });
        
        confettiTimer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Confetti c : confettiParticles) {
                    c.update();
                }
                confettiParticles.removeIf(c -> c.isOffScreen(getHeight()));
                repaint();
            }
        });
  }
    
    public void setShapeHighlighted(int index, boolean highlighted) {
        if (index >= 0 && index < movableShapes.size()) {
            ShapeInfo shape = movableShapes.get(index);
            shape.setHighlighted(highlighted);
            if (highlighted) {
                shape.startBlinking();
                Timer blinkingDurationTimer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        shape.stopBlinking();
                        repaint();
                    }
                });
                blinkingDurationTimer.setRepeats(false); // Make sure the timer only runs once
                blinkingDurationTimer.start();
            } else {
                shape.stopBlinking();
            }
            repaint(); // Trigger a repaint to update the display
        }
    }
    
    public void highlightUnmatchedShapes() {
	      blinkingTimer = new Timer(500, new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	          for (ShapeInfo shape : movableShapes) {
	          	if (shape.isBlinking()) {
	                  shape.toggleBlinkingVisible();
	                  if (shape.getCounterpart() != null) {
	                      shape.getCounterpart().toggleBlinkingVisible();
	                  }
	              }
	          	Timer timer = new Timer(10000, event -> {
	          		blinkingTimer.stop();
	              });
	              timer.setRepeats(false);
	              timer.start();
	              
	          }
	          repaint();
	      }
	  });
	  blinkingTimer.start();
//    	System.out.println("HighlightUnmatchedShapes called!");
    }
    
    

    private boolean isShapeMatched(ShapeInfo movable) {
        for (ShapeInfo unmovable : unmovableShapes) {
            if (movable.getType() == unmovable.getType() &&
                movable.getX() == unmovable.getX() &&
                movable.getY() == unmovable.getY()) {
                return true;
            }
        }
        return false;
    }

    private void initializeShapesForLevels() {
		// TODO Auto-generated method stub
    	switch(levelNumber) {
    	case 1:
    		LevelInitializer.initializeLevel1(movableShapes, unmovableShapes, getX());
    		break;
    	case 2:
    		LevelInitializer.initializeLevel2(movableShapes, unmovableShapes, getX());
    		break;
    	case 3:
    		LevelInitializer.initializeLevel3(movableShapes, unmovableShapes, getX());
    		break;
    	case 4:
    		LevelInitializer.initializeLevel4(movableShapes, unmovableShapes, getX());
    		break;
    	case 5:
    		LevelInitializer.initializeLevel5(movableShapes, unmovableShapes, getX());
    		break;
    	case 6:
    		LevelInitializer.initializeLevel6(movableShapes, unmovableShapes, getX());
    		break;
    	}
    	
    	for (ShapeInfo movable : movableShapes) {
            for (ShapeInfo unmovable : unmovableShapes) {
                if (movable.getType() == unmovable.getType() && movable.getColor().equals(unmovable.getColor())) {
                    movable.setCounterpart(unmovable);
                    unmovable.setCounterpart(movable);
                    break;
                }
            }
        }
		
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (ShapeInfo shape : movableShapes) {
            g.setColor(shape.getColor());
            shape.draw(g);
        }
        for (ShapeInfo shape : unmovableShapes) {
            g.setColor(shape.getColor());
            shape.draw(g);
        }
        for (Confetti c : confettiParticles) {
            c.draw(g);
        }
    }
	
	@Override
    public void removeNotify() {
        super.removeNotify();
        if (blinkingTimer != null) {
            blinkingTimer.stop();
        }
    }
	
    private void checkCollisions(ShapeInfo movableShape) {
        for (ShapeInfo unmovableShape : unmovableShapes) {
            if (movableShape.getType() == unmovableShape.getType() &&
            		movableShape.getColor() == unmovableShape.getColor()) {
                if (movableShape != unmovableShape && movableShape.intersects(unmovableShape)) {
                    pullShapes(movableShape, unmovableShape);
                }
            }
        }
    }

    private void pullShapes(ShapeInfo movableShape, ShapeInfo unmovableShape) {
        int dx = unmovableShape.getX() - movableShape.getX();
        int dy = unmovableShape.getY() - movableShape.getY();
        movableShape.move(dx, dy);
    }

    private ShapeInfo getMovableShapeAt(Point p) {
        for (ShapeInfo shape : movableShapes) {
            if (shape.contains(p)) {
                return shape;
            }
        }
        return null;
    }

    public void playSound(String soundFile) {
        try {
            File soundPath = new File(soundFile);
            if (soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Sound file not found: " + soundFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void checkAllMatched() {
        boolean allMatched = true;
        for (ShapeInfo movableShape : movableShapes) {
            boolean matched = false;
            for (ShapeInfo unmovableShape : unmovableShapes) {
                if (movableShape.getType() == unmovableShape.getType() &&
                    movableShape.getX() == unmovableShape.getX() &&
                    movableShape.getY() == unmovableShape.getY()) {
                    matched = true;
                    movableShape.stopBlinking();
                    if (movableShape.getCounterpart() != null) {
                        movableShape.getCounterpart().stopBlinking();
                    }
                    break;
                }
            }
            if (!matched) {
                allMatched = false;
                break;
            }
        }
        if (allMatched) {
        	completionLabel.setText("Level Completed");
            playSound("audio\\level_applause.wav");
            startConfetti();
            removeLabelAfterDelay(10000); 
            
        }
    }
    
    
    private void startConfetti() {
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * 50);
            confettiParticles.add(new Confetti(x, y));
        }
        confettiTimer.start();
    }
    
    private void removeLabelAfterDelay(int delay) {
        Timer timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
	                completionLabel.setText("");
	                completionLabel.setVisible(false);
	                ((Timer) e.getSource()).stop();
	
	                if (gameBoardDialog != null) {
	                    gameBoardDialog.closeDialog();
	                }
	                stopSound();
	                
	                if(!shapeLevel.isCompleted()) {
	                	levelCompletedBackgroundDialog = new LevelCompletedBackgroundDialog(landingPageFrame,
	    	                    shapeLevelSelectDialog,levelTopBarPanel,shapeMatchListener,shapeLevel,nextLevel, levelNumber, index,
	    	                    landingPageFrame,catIndex, gameSelectBoardPanel);
	    	                levelCompletedBackgroundDialog.setVisible(true);
	                }else {
	                	nextLevelBackgroundDialog = new NextLevelBackgroundDialog(landingPageFrame, shapeLevelSelectDialog,levelTopBarPanel,
	                			shapeLevel,nextLevel,shapeMatchListener,levelNumber,index, landingPageFrame,catIndex, gameSelectBoardPanel); 
	                	nextLevelBackgroundDialog.setVisible(true);
	                	
	                }
	            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    
    
    private void moveShapesLeft(int steps) {
        for (ShapeInfo shape : movableShapes) {
            shape.setX(shape.getX() - steps);
        }
        for (ShapeInfo shape : unmovableShapes) {
            shape.setX(shape.getX() - steps);
        }
    }

    public static class ShapeInfo {
        private int x, y, width, height;
        private Color color;
        private ShapeType type;
        private boolean filled;
        private boolean highlighted; 
        private boolean blinking; 
        private boolean blinkingVisible; 
        private ShapeInfo counterpart;
        
        public ShapeInfo(int x, int y, int width, int height, Color color, ShapeType type, boolean filled, 
        		boolean highlighted, boolean blinking) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
            this.type = type;
            this.filled = filled;
            this.highlighted = highlighted; 
            this.blinking = blinking;
            this.blinkingVisible = true;
        }
        
        public void setHighlighted(boolean highlighted) {
            this.highlighted = highlighted;
        }
        
        public boolean isBlinking() {
            return blinking;
        }
        
        public void setBlinking(boolean blinking) {
            this.blinking = blinking;
        }
        
        public void setCounterpart(ShapeInfo counterpart) {
            this.counterpart = counterpart;
        }

        public void toggleBlinkingVisible() {
            blinkingVisible = !blinkingVisible;
        }

        public void startBlinking() {
            blinking = true;
            if (counterpart != null) {
                counterpart.blinking = true;
            }
        }
        
        public ShapeInfo getCounterpart() {
            return counterpart;
        }

        public void stopBlinking() {
            blinking = false;
            blinkingVisible = true;
            if (counterpart != null) {
                counterpart.blinking = false;
                counterpart.blinkingVisible = true;
            }
        }


        public boolean contains(Point p) {
            if (type == ShapeType.RECTANGLE) {
                return p.x >= x && p.x <= x + width && p.y >= y && p.y <= y + height;
            } else if (type == ShapeType.ELLIPSE) {
                double dx = x + width / 2.0 - p.x;
                double dy = y + height / 2.0 - p.y;
                return dx * dx / (width * width / 4.0) + dy * dy / (height * height / 4.0) <= 1.0;
            } else if (type == ShapeType.TRIANGLE) {
                Polygon triangle = new Polygon(new int[]{x, x + width / 2, x + width}, new int[]{y + height, y, y + height}, 3);
                return triangle.contains(p);
            } else if (type == ShapeType.STAR) {
                Polygon star = CreateShapes.createStar(x + width / 2, y + height / 2, width / 2, height / 4, 5);
                return star.contains(p);
            } else if (type == ShapeType.RHOMBUS) {
                Polygon rhombus = CreateShapes.createRhombus(x + width / 2, y + height / 2, width / 2, height / 2);
                return rhombus.contains(p);
            }else if (type == ShapeType.HEART) {
                Polygon heart = CreateShapes.createHeart(x, y, width, height);
                return heart.contains(p);
            }else if (type == ShapeType.TRAPEZIUM) {
                Polygon trapezium = CreateShapes.createTrapezium(x, y, width, height);
                return trapezium.contains(p);
            } else if (type == ShapeType.PARALLELOGRAM) {
                Polygon parallelogram = CreateShapes.createParallelogram(x, y, width, height);
                return parallelogram.contains(p);
            }
            return false;
        }

        public boolean intersects(ShapeInfo other) {
            Rectangle r1 = new Rectangle(x, y, width, height);
            Rectangle r2 = new Rectangle(other.x, other.y, other.width, other.height);
            return r1.intersects(r2);
        }

        public void move(int dx, int dy) {
            x += dx;
            y += dy;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public Color getColor() {
            return color;
        }

        public ShapeType getType() {
            return type;
        }
        
        public void draw(Graphics g) {
            if (!blinking || blinkingVisible) {
                g.setColor(color);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3));
                if (highlighted) {
                  ((Graphics2D) g).setStroke(new BasicStroke(5));
              }

                if (type == ShapeType.RECTANGLE) {
                    if (filled) {
                        g.fillRect(x, y, width, height);
                    } else {
                        g.drawRect(x, y, width, height);
                    }
                } else if (type == ShapeType.ELLIPSE) {
                    if (filled) {
                        g.fillOval(x, y, width, height);
                    } else {
                        g.drawOval(x, y, width, height);
                    }
                } else if (type == ShapeType.TRIANGLE) { 
                	Polygon triangle = CreateShapes.createTriangle(x, y, width, height);
                    if (filled) {
                    	g.fillPolygon(triangle);
                    } else {
                    	g.drawPolygon(triangle);
                    }
                } else if (type == ShapeType.STAR) {
                    Polygon star = CreateShapes.createStar(x + width / 2, y + height / 2, width / 2, height / 4, 5);
                    if (filled) {
                        g.fillPolygon(star);
                    } else {
                        g.drawPolygon(star);
                    }
                } else if (type == ShapeType.RHOMBUS) {
                    Polygon rhombus = CreateShapes.createRhombus(x + width / 2, y + height / 2, width / 2, height / 2);
                    if (filled) {
                        g.fillPolygon(rhombus);
                    } else {
                        g.drawPolygon(rhombus);
                    }
                } else if (type == ShapeType.HEART) {
                    Polygon heart = CreateShapes.createHeart(x, y, width, height);
                    if (filled) {
                        g.fillPolygon(heart);
                    } else {
                        g.drawPolygon(heart);
                    }
                } else if (type == ShapeType.TRAPEZIUM) {
                    Polygon trapezium = CreateShapes.createTrapezium(x, y, width, height);
                    if (filled) {
                        g.fillPolygon(trapezium);
                    } else {
                        g.drawPolygon(trapezium);
                    }
                } else if (type == ShapeType.PARALLELOGRAM) {
                    Polygon parallelogram = CreateShapes.createParallelogram(x, y, width, height);
                    if (filled) {
                        g.fillPolygon(parallelogram);
                    } else {
                        g.drawPolygon(parallelogram);
                    }
                }
            }
        }
        
    }
    
    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
    
    public enum ShapeType {
        RECTANGLE, ELLIPSE, TRIANGLE, STAR, RHOMBUS, HEART, PARALLELOGRAM,TRAPEZIUM
    }
}