//package GameFolder;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//
//public class GameBoardDialog extends JDialog {
//
//    private RoundedPanel contentPanel;
//    private ShapeModel shapeModel;
//    private LandingPageFrame landingPageFrame;
//    private ShapeLevelSelectDialog shapeLevelSelectDialog;
//    private LevelTopBarPanel levelTopBarPanel;
//    private int levelNumber;
//
//    public GameBoardDialog(JFrame parent, LandingPageFrame landingPageFrame,
//    		ShapeLevelSelectDialog shapeLevelSelectDialog,LevelTopBarPanel levelTopBarPanel,int levelNumber) {
//        super(parent, "Game Board", false);
//        this.landingPageFrame = landingPageFrame;
//        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
//        this.levelTopBarPanel = levelTopBarPanel;
//        this.levelNumber = levelNumber;
//        initUI(levelNumber);
//        layoutUI();
//        handleEventsUI();
//        handleResponsiveness();
//        attachComponentListenerToParent();
//    }
//
//    @Override
//    public void setVisible(boolean visible) {
//        if (visible) {
//            centerDialog();
//        }
//        super.setVisible(visible);
//    }
//
//    private void centerDialog() {
//        if (getParent() != null) {
//            int parentX = getParent().getX();
//            int parentY = getParent().getY();
//            int parentWidth = getParent().getWidth();
//            int parentHeight = getParent().getHeight();
//
//            int dialogWidth = getWidth();
//            int dialogHeight = getHeight();
//            int marginBottom = 20;
//
//            int dialogX = parentX + (parentWidth - dialogWidth) / 2;
//            int dialogY = parentY + (parentHeight - dialogHeight) - marginBottom;
//
//            setLocation(dialogX, dialogY);
//        }
//    }
//
//    private void attachComponentListenerToParent() {
//        if (getParent() != null) {
//            getParent().addComponentListener(new ComponentAdapter() {
//                @Override
//                public void componentResized(ComponentEvent e) {
//                    centerDialog();
//                }
//
//                @Override
//                public void componentMoved(ComponentEvent e) {
//                    centerDialog();
//                }
//            });
//        }
//    }
//
//    private void initUI(int levelNumber) {
//        setUndecorated(true);
//        setBackground(new Color(0, 0, 0, 0)); 
//        contentPanel = new RoundedPanel(10, new Color(0, 0, 0, 100));
//        contentPanel.setLayout(new BorderLayout());
//        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//        shapeModel = new ShapeModel(this, landingPageFrame, shapeLevelSelectDialog,
//        		shapeLevelSelectDialog,levelTopBarPanel, levelNumber);
//        contentPanel.add(shapeModel, BorderLayout.CENTER);
//        
////        levelTopBarPanel.removeMiddlePanel();
//
//        setContentPane(contentPanel);
//    }
//
//    private void handleEventsUI() {
//        getParent().addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentMoved(ComponentEvent e) {
//                handleResponsiveness();
//            }
//        });
//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                handleResponsiveness();
//            }
//        });
//    }
//
//    private void handleResponsiveness() {
//        if (getParent() != null) {
//            int parentWidth = getParent().getWidth();
//            int parentHeight = getParent().getHeight();
//            if (parentWidth < 600) {
//                setSize(770, getSize().height);
//            } else if (parentWidth <= 800 && parentWidth >= 600) {
//                setSize(770, getSize().height);
//            } else {
//                setSize(780, getSize().height);
//            }
//        }
//    }
//
//    public void closeDialog() {
//        this.dispose();
//    }
//
//    private void layoutUI() {
//        setSize(600, 550); 
//        centerDialog();
//    }
//}

package GameFolder;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameBoardDialog extends JDialog {

    private RoundedPanel contentPanel;
    private ShapeModel shapeModel;
    private LandingPageFrame landingPageFrame;
    private ShapeLevelSelectDialog shapeLevelSelectDialog;
    private LevelTopBarPanel levelTopBarPanel;
    private int levelNumber, index, catIndex;
    private ShapeLevel shapeLevel, nextLevel;
    private GameSelectBoardPanel gameSelectBoardPanel;
   

    public GameBoardDialog(JFrame parent, LandingPageFrame landingPageFrame,ShapeLevelSelectDialog shapeLevelSelectDialog, 
    		LevelTopBarPanel levelTopBarPanel, int levelNumber, int index, ShapeLevel shapeLevel, ShapeLevel nextLevel,int catIndex, 
    		GameSelectBoardPanel gameSelectBoardPanel) {
        super(parent, "Game Board", false);
        this.landingPageFrame = landingPageFrame;
        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
        this.levelTopBarPanel = levelTopBarPanel;
        this.levelNumber = levelNumber;
        this.index = index;
        this.shapeLevel = shapeLevel;
        this.nextLevel = nextLevel;
        this.gameSelectBoardPanel = gameSelectBoardPanel;
//        this.levels = levels;
        initUI(levelNumber);
        layoutUI();
        handleEventsUI();
        handleResponsiveness();
        attachComponentListenerToParent();
        
//        levelTopBarPanel.removeMiddlePanel();
//        System.out.println("Level +" levels.is);
//        System.out.println(nextLevel.isLocked());
//        System.out.println(nextLevel.isCompleted());
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            centerDialog();
        }
        super.setVisible(visible);
    }

    private void centerDialog() {
        if (getParent() != null) {
            int parentX = getParent().getX();
            int parentY = getParent().getY();
            int parentWidth = getParent().getWidth();
            int parentHeight = getParent().getHeight();

            int dialogWidth = getWidth();
            int dialogHeight = getHeight();
            int marginBottom = 20;

            int dialogX = parentX + (parentWidth - dialogWidth) / 2;
            int dialogY = parentY + (parentHeight - dialogHeight) - marginBottom;

            setLocation(dialogX, dialogY);
        }
    }

    private void attachComponentListenerToParent() {
        if (getParent() != null) {
            getParent().addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    centerDialog();
                }

                @Override
                public void componentMoved(ComponentEvent e) {
                    centerDialog();
                }
            });
        }
    }

    private void initUI(int levelNumber) {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); 
        contentPanel = new RoundedPanel(10, new Color(0, 0, 0, 0));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        addShapeModel();
//        levelTopBarPanel.removeMiddlePanel();
        
//        levelTopBarPanel.removeMiddlePanel();
//        getParentFrame();

        setContentPane(contentPanel);
    }
    private void addShapeModel() {
    	shapeModel = new ShapeModel(this, landingPageFrame, shapeLevelSelectDialog,
        		shapeLevelSelectDialog,levelTopBarPanel, catIndex, levelNumber, index, shapeLevel, nextLevel, gameSelectBoardPanel);
        contentPanel.add(shapeModel, BorderLayout.CENTER);
    }

    private void handleEventsUI() {
        getParent().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                handleResponsiveness();
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                handleResponsiveness();
            }
        });
    }

    private void handleResponsiveness() {
        if (getParent() != null) {
            int parentWidth = getParent().getWidth();
            int parentHeight = getParent().getHeight();
            if (parentWidth < 600) {
                setSize(770, getSize().height);
            } else if (parentWidth <= 800 && parentWidth >= 600) {
                setSize(770, getSize().height);
            } else {
                setSize(780, getSize().height);
            }
        }
    }

    public void closeDialog() {
        this.dispose();
    }

    private void layoutUI() {
        setSize(600, 550); 
        centerDialog();
    }
    
    public void test() {
    	System.out.println("This is a test!");
    }
    
//    public void getParentFrame() {
//        // Get the parent component
//        Component parent = getParent();
//
//        // Check if the parent is a JFrame
//        if (parent instanceof JFrame) {
//            LandingPageFrame parentFrame = (LandingPageFrame) parent;
//            // Now you have access to the parent frame
//            // You can use parentFrame as needed
//            System.out.println("Parent frame title: " + parentFrame.getTitle());
//            parentFrame.removeLevelTopBarPanel();
//        } else {
//            System.out.println("Parent is not a JFrame");
//        }
//    }
//    public void updateLevel(int levelNumber, ShapeLevel level) {
//        this.levelNumber = levelNumber;
//        this.nextLevel = level;
//        // Additional logic to update the game board with the new level information
//    }
}
