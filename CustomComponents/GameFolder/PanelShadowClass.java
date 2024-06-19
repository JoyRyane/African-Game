package GameFolder;

import java.awt.*;
import javax.swing.*;

class PanelShadowClass extends JPanel {
    private int cornerRadius;
    private Color backgroundColor;
    private Color shadowColor;
    private int shadowSize;

    public PanelShadowClass(int radius, Color bgColor, Color shadowColor, int shadowSize) {
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        this.shadowColor = shadowColor;
        this.shadowSize = shadowSize;
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

        // Draw the shadow
        for (int i = 0; i < shadowSize; i++) {
//            float alpha = (float) (shadowSize - i) / shadowSize;
//        	(int) (alpha * 255)
            graphics.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), 7));

            // Draw the right edge shadow
            graphics.fillRect(width - shadowSize + i, shadowSize, shadowSize, height - shadowSize);

            // Draw the bottom edge shadow
            graphics.fillRect(shadowSize, height - shadowSize + i, width - shadowSize, shadowSize);
        }

        // Draw the rounded panel with specified background color
        graphics.setColor(backgroundColor);

        // Adjust the dimensions to ensure edges meet
        int adjustedWidth = width - shadowSize;
        int adjustedHeight = height - shadowSize;

        graphics.fillRoundRect(0, 0, adjustedWidth, adjustedHeight, arcs.width, arcs.height);
        graphics.setColor(getForeground());
    }





}