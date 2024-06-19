package GameFolder;

import java.awt.*;
import javax.swing.*;

class TransparentBackgroundPanel extends JPanel {
    private float alpha; // Opacity value (0.0f to 1.0f)

    public TransparentBackgroundPanel(float alpha) {
        this.alpha = alpha;
        setOpaque(false); // Make the panel transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the opacity
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // Fill the entire panel with a semi-transparent color
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }
}