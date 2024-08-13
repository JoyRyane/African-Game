package GameFolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NextLevelBackgroundDialog extends JDialog {
	
	NextLevelDialog dialog;
	ShapeLevelSelectDialog shapeLevelSelectDialog;
	LevelTopBarPanel levelTopBarPanel;
	private int levelNumber, index, catIndex;
	private ShapeLevel shapeLevel, nextLevel;
	private ShapeMatchListener shapeMatchListener;
	private LandingPageFrame landingPageFrame;
	private GameSelectBoardPanel gameSelectBoardPanel;
    public NextLevelBackgroundDialog(JFrame parent,ShapeLevelSelectDialog shapeLevelSelectDialog,
    		LevelTopBarPanel levelTopBarPanel,ShapeLevel shapeLevel,ShapeLevel nextLevel,
    		ShapeMatchListener shapeMatchListener, int levelNumber, int index,LandingPageFrame landingPageFrame,
    		int catIndex,GameSelectBoardPanel gameSelectBoardPanel ) {
        super(parent, "Fullscreen Popup", false);
        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
        this.levelTopBarPanel = levelTopBarPanel;
        this.shapeLevel = shapeLevel;
        this.nextLevel = nextLevel;
        this.shapeMatchListener = shapeMatchListener;
        this.levelNumber = levelNumber;
        this.index = index;
        this.landingPageFrame = landingPageFrame;
        this.gameSelectBoardPanel = gameSelectBoardPanel;
        this.catIndex = catIndex;
        parent.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                updateDialogSizeAndPosition();
            }
            public void componentResized(ComponentEvent evt) {
                updateDialogSizeAndPosition();
            }
        });
        initUI();
    }
    
	private void initUI() {
        setUndecorated(true);
        setBackground(new Color(0,0,0,200));
        updateDialogSizeAndPosition();
        
        dialog = new NextLevelDialog((JFrame) getParent(),shapeLevelSelectDialog,levelTopBarPanel , shapeLevel,
        		nextLevel,shapeMatchListener, levelNumber, index, landingPageFrame,catIndex, gameSelectBoardPanel);
        
//        dialog.setVisible(true);
//        
//        dialog.setAlwaysOnTop(true);
    }
	
    private void updateDialogSizeAndPosition() {
        JFrame parent = (JFrame) getParent();
        Insets parentInsets = parent.getInsets();

        int dialogWidth = parent.getWidth() - parentInsets.left - parentInsets.right;
        int dialogHeight = parent.getHeight() - parentInsets.top - parentInsets.bottom;

        setSize(dialogWidth, dialogHeight);

        int parentX = parent.getLocation().x;
        int parentY = parent.getLocation().y;

        setLocation(parentX + parentInsets.left, parentY + parentInsets.top);
    }
    
    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            // Show the background dialog first
            super.setVisible(true);

            // Now, show the modal dialog on top of the background dialog
            dialog.setModal(true);
            dialog.setVisible(true);
        } else {
            super.setVisible(false);
        }
    }
}