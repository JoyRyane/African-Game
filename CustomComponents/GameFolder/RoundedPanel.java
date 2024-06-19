package GameFolder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

class RoundedPanel extends JPanel {
    private int cornerRadius;
    private Color backgroundColor;

    public RoundedPanel(int radius, Color bgColor) {
        cornerRadius = radius;
        backgroundColor = bgColor;
        setOpaque(false);
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded panel with specified background color
        graphics.setColor(backgroundColor);
        graphics.fillRoundRect(0, 0, width , height, arcs.width, arcs.height);
        graphics.setColor(getForeground());
//        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }
} 