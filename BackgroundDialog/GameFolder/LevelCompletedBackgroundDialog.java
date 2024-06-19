package GameFolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ComponentAdapter;

public class LevelCompletedBackgroundDialog extends JDialog {
	
	private LevelCompletedDialog dialog;
	private LevelTopBarPanel levelTopBarPanel;
	private ShapeLevelSelectDialog shapeLevelSelectDialog;
	int levelNumber, index;
    public LevelCompletedBackgroundDialog(JFrame parent,ShapeLevelSelectDialog shapeLevelSelectDialog,
    		LevelTopBarPanel levelTopBarPanel, int levelNumber, int index) {
        super(parent, "Fullscreen Popup", false);
        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
        this.levelTopBarPanel = levelTopBarPanel;
        this.levelNumber = levelNumber;
        this.index = index;
        parent.addComponentListener(new ComponentAdapter() {
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
        
        dialog = new LevelCompletedDialog((JFrame) getParent(),shapeLevelSelectDialog,levelTopBarPanel, levelNumber, index);
        
//        levelTopBarPanel.removeFromParent();
        
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