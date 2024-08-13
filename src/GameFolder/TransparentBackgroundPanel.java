package GameFolder;

import java.awt.*;
import javax.swing.*;

class TransparentBackgroundPanel extends JPanel {
    private float alpha; 

    public TransparentBackgroundPanel(float alpha) {
        this.alpha = alpha;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }
}