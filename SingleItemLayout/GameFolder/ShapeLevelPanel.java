package GameFolder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ShapeLevelPanel {

    public JPanel createShapeLevelPanel(ShapeLevel level) {
        boolean locked = level.isLocked();
        boolean completed = level.isCompleted();

        JPanel currentShapeLevelPanel = new JPanel();
        currentShapeLevelPanel.setPreferredSize(new Dimension(190, 190));
        currentShapeLevelPanel.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(190, 190));
        container.setLayout(new GridBagLayout());
        container.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 80));
        panel.setPreferredSize(new Dimension(190, 30));

        JLabel levelText = new JLabel(level.getName());
        levelText.setForeground(Color.WHITE);
        levelText.setFont(new Font(levelText.getName(), Font.BOLD | Font.ITALIC, 15));

        JPanel image = new JPanel(new FlowLayout(FlowLayout.CENTER));
        image.setBorder(new EmptyBorder(7, 1, 1, 4));
        image.setOpaque(false);

        JLabel shapeLevelLabel = createImageLabel("icon\\shapeLevel.png", 50, 50);
        shapeLevelLabel.setBorder(new EmptyBorder(0, 0, 15, 0));
        image.add(shapeLevelLabel);

        JPanel lock = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lock.setOpaque(false);

        JPanel themeTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        themeTitle.setBorder(new EmptyBorder(1, 4, 1, 4));
        themeTitle.setOpaque(false);

        if (!locked && completed) {
            currentShapeLevelPanel.setBackground(new Color(214, 170, 118));
            
            JLabel levelLabel = createImageLabel("icon\\replay.png", 20, 20);
            
            RoundedPanel button = new RoundedPanel(20, new Color(29, 235, 25));
            button.setBorder(new EmptyBorder(7,22,7,22));
            button.setForeground(Color.WHITE);
            button.setLayout(new BorderLayout());
            button.setFont(new Font(levelText.getName(), Font.BOLD | Font.ITALIC, 15));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.add(levelLabel, BorderLayout.CENTER);

            panel.add(levelText);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;

            currentShapeLevelPanel.add(panel, BorderLayout.NORTH);
            gbc.gridy++;
            container.add(image, gbc);
            gbc.gridy++;
            container.add(button, gbc);
            gbc.gridy++;

            currentShapeLevelPanel.add(container);

        } else if (!locked && !completed) {
            currentShapeLevelPanel.setBackground(new Color(255, 140, 6));
            panel.add(levelText);

            RoundedPanel button = new RoundedPanel(20, new Color(29, 235, 25));
            button.setBorder(new EmptyBorder(7,22,7,22));
            button.setLayout(new BorderLayout());
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            JLabel play = new JLabel("Play");
            play.setForeground(Color.WHITE);
            play.setFont(new Font(levelText.getName(), Font.BOLD | Font.ITALIC, 15));
            button.add(play, BorderLayout.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;

            currentShapeLevelPanel.add(panel, BorderLayout.NORTH);
            gbc.gridy++;
            container.add(image, gbc);
            gbc.gridy++;
            container.add(button, gbc);
            gbc.gridy++;

            currentShapeLevelPanel.add(container);

        } else if (locked && !completed) {
            currentShapeLevelPanel.setBackground(new Color(254, 206, 150));
            panel.add(levelText);

            JLabel themeLockLabel = createImageLabel("icon\\lock.png", 20, 20);
            lock.add(themeLockLabel);
            panel.add(lock);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;

            currentShapeLevelPanel.add(panel, BorderLayout.NORTH);
            gbc.gridy++;
            container.add(image, gbc);
            gbc.gridy++;

            currentShapeLevelPanel.add(container);
        }

        return currentShapeLevelPanel;
    }

    private JLabel createImageLabel(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(resizedImg));
    }
}
