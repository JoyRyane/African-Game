package GameFolder;

import java.awt.*;
import javax.swing.*;

public class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground()); 
        }
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        super.paintComponent(g); 
    }
 
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
//        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }
//
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(120, 20);
//    }
}

