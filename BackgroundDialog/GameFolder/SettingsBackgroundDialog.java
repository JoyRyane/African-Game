package GameFolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsBackgroundDialog extends JDialog {
	
	SettingsDialog dialog;
	private BackgroundMusicController musicController;
    public SettingsBackgroundDialog(JFrame parent, BackgroundMusicController musicController) {
        super(parent, "Fullscreen Popup", false);
        this.musicController = musicController;
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
        
        dialog = new SettingsDialog((JFrame) getParent(), musicController);
//        
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