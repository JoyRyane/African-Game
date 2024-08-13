package GameFolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoadingLanguageBackgroundDialog extends JDialog {

    private LoadingLanguageDialog dialog;

    public LoadingLanguageBackgroundDialog(JFrame parent) {
        super(parent, "Fullscreen Popup", false);
        parent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                updateDialogSizeAndPosition();
            }

            @Override
            public void componentResized(ComponentEvent e) {
                updateDialogSizeAndPosition();
            }
        });
        initUI();
        layoutUI();
    }

    private void initUI() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 200));
        updateDialogSizeAndPosition();
        
        // Ensure dialog is created but not visible yet
        dialog = new LoadingLanguageDialog((JFrame) getParent());
    }

    private void layoutUI() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (dialog != null && dialog.isVisible()) {
                    int loadingFrameWidth = getWidth();
                    int loadingFrameHeight = getHeight();
                    int newWidth;
                    int newHeight;

                    if (loadingFrameWidth < 500) {
                        newWidth = 100;
                    } else if (loadingFrameWidth >= 500 && loadingFrameWidth <= 800) {
                        newWidth = 200;
                    } else {
                        newWidth = 300;
                    }

                    if (loadingFrameHeight < 500) {
                        newHeight = 200;
                    } else if (loadingFrameHeight >= 500 && loadingFrameHeight <= 800) {
                        newHeight = 300;
                    } else {
                        newHeight = 400;
                    }

                    dialog.setSize(newWidth, newHeight);
                    dialog.setLocationRelativeTo(LoadingLanguageBackgroundDialog.this);
                }
            }
        });
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