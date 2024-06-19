package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GreetingsDialog extends JDialog {

    private JPanel contentPanel;
    private boolean firstTimeVisible = true;

    public GreetingsDialog(JFrame parent) {
        super(parent, "Popup Dialog", false);
        initUI();
        layoutUI();
        handleEventsUI();
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible && firstTimeVisible) {
            firstTimeVisible = false;
            updateDialogSizeAndPosition();
        }
        super.setVisible(visible);
    }

    private void handleEventsUI() {
        getParent().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateDialogSizeAndPosition();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                updateDialogSizeAndPosition();
            }
        });
    }

    private void initUI() {
        setLocationRelativeTo(getParent());
        setUndecorated(true);
        setResizable(true);
        setBackground(new Color(0, 0, 0, 0));

        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);

        ImageIcon imageIcon = new ImageIcon("icon\\greetings.png");

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(imageIcon);

        JLabel hello = new JLabel("<html>Good Morning<br> How are you?<br>I am glad to see you too!</html>");
        hello.setForeground(Color.WHITE);
        Font helloFont = hello.getFont();
        hello.setFont(new Font(helloFont.getName(), Font.BOLD, 17));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(imageLabel, gbc);

        setContentPane(contentPanel);
    }

    private void updateDialogSizeAndPosition() {
        Container parent = getParent();
        int paddingBottom = 20;

        int parentX = parent.getLocation().x;
        int parentY = parent.getLocation().y;
        int parentWidth = parent.getWidth();
        int parentHeight = parent.getHeight();

        int dialogWidth = getWidth();
        int dialogHeight = getHeight();

        int desiredWidth = (int) (parentWidth * 0.8);
        int desiredHeight = (int) (parentHeight * 0.5);

        setSize(desiredWidth, desiredHeight);

        int dialogX = parentX + (parentWidth - desiredWidth) / 2;
        int dialogY = parentY + parentHeight - desiredHeight - paddingBottom;

        setLocation(dialogX, dialogY);
    }

    private void layoutUI() {
        this.setSize(600, 300);
        this.setLocationRelativeTo(getParent());
    }
}

