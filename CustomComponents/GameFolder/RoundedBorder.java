package GameFolder;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

class RoundedBorder implements Border {

    private int radius;


    RoundedBorder(int radius) {
        this.radius = radius;
    } 


    public Insets getBorderInsets(Component c) {
        return new Insets(2, 20, 2, 20);
    } 


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}