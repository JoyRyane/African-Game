package GameFolder;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedProgressBar extends JProgressBar {

    public RoundedProgressBar(int min, int max) {
        super(min, max);
        setUI(new RoundedProgressBarUI());
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0,10));
//        setBackground(new Color(0,0,0,0));
    }

    private static class RoundedProgressBarUI extends BasicProgressBarUI {
        @Override
        protected void paintDeterminate(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = progressBar.getWidth();
            int height = progressBar.getHeight();
            int arc = 20;

            double percentage = progressBar.getPercentComplete();
            int fillWidth = (int) (width * percentage);

            // Draw the background
//            g2d.setColor(progressBar.getBackground());
            g2d.setColor(new Color(0,0,0,100));
            g2d.fillRoundRect(0, 0, width, height, arc, arc);

            // Draw the progress
            g2d.setColor(progressBar.getForeground());
            g2d.fillRoundRect(0, 0, fillWidth, height, arc, arc);

            // Draw the border
            g2d.setColor(new Color(0, 0, 0, 0));
            g2d.drawRoundRect(0, 0, width - 1, height - 1, arc, arc);

            // Draw the text
            if (progressBar.isStringPainted()) {
                g2d.setColor(Color.WHITE);
                String progressString = progressBar.getString();
                FontMetrics fm = g2d.getFontMetrics();
                int stringWidth = fm.stringWidth(progressString);
                int stringHeight = fm.getAscent();
                int x = (width - stringWidth) / 2;
                int y = (height + stringHeight) / 2 - fm.getDescent();
                g2d.drawString(progressString, x, y);
            }
        }

        @Override
        protected void paintIndeterminate(Graphics g, JComponent c) {
            // Handle indeterminate case if needed
        }
    }
}

