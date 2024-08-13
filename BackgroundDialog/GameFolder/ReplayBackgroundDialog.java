package GameFolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ReplayBackgroundDialog extends JDialog {
	
	ReplayDialog dialog;
	ShapeLevelSelectDialog shapeLevelSelectDialog;
	GameBoardDialog gameBoardDialog;
	private LevelTopBarPanel levelTopBarPanel;
	private int levelNumber, index, catIndex;
//	private List<ShapeLevel> levels;
	private ShapeLevel shapeLevel, nextLevel;
	private GameSelectBoardPanel gameSelectBoardPanel;
	
    public ReplayBackgroundDialog(JFrame parent, ShapeLevelSelectDialog shapeLevelSelectDialog, 
    		GameBoardDialog gameBoardDialog, LevelTopBarPanel levelTopBarPanel,int levelNumber, 
    		int index, ShapeLevel shapeLevel, ShapeLevel nextLevel,int catIndex, GameSelectBoardPanel gameSelectBoardPanel) {
        super(parent, "Fullscreen Popup", false);
        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
        this.gameBoardDialog  = gameBoardDialog;
        this.levelTopBarPanel = levelTopBarPanel;
        this.index = index;
        this.levelNumber = levelNumber;
//        this.levels = levels;
        this.shapeLevel = shapeLevel;
        this.nextLevel = nextLevel;
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
        
        dialog = new ReplayDialog((JFrame) getParent(), shapeLevelSelectDialog, 
        		gameBoardDialog,levelTopBarPanel,levelNumber, index, shapeLevel, nextLevel,catIndex, gameSelectBoardPanel);
        
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