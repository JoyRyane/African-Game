package GameFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.plaf.metal.MetalScrollBarUI;

public class AboutDialog extends JDialog {

    JPanel contentPanel;
    JScrollPane scrollPane;

    public AboutDialog(JFrame parent) {
        super(parent, "Fullscreen Popup", true);
        parent.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                updateDialogSizeAndPosition();
            }
            public void componentResized(ComponentEvent evt) {
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
    }

    public void layoutUI() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setForeground(Color.WHITE);

        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setOpaque(false);

        logoPanel.setPreferredSize(new Dimension(250, 100));
        logoPanel.setMaximumSize(new Dimension(250, 100));

        JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        returnPanel.setOpaque(false);

        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        contentPanel.add(labelPanel, BorderLayout.CENTER);
        contentPanel.add(returnPanel, BorderLayout.PAGE_START);
        labelPanel.add(logoPanel);

        try {
            BufferedImage iconImage = ImageIO.read(new File("icon\\return.png"));
            int scaledWidth = 30;
            int scaledHeight = 30;
            Image scaledIconImage = iconImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

            ImageIcon icon = new ImageIcon(scaledIconImage);
            JLabel iconLabel = new JLabel(icon);

            returnPanel.add(iconLabel);

            iconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                    set.dispose();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            BufferedImage iconImage = ImageIO.read(new File("icon\\logo.png"));
            int scaledWidth = 150;
            int scaledHeight = 100;
            Image scaledIconImage = iconImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

            ImageIcon icon = new ImageIcon(scaledIconImage);
            JLabel iconLabel = new JLabel(icon);
            logoPanel.add(iconLabel, BorderLayout.CENTER);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JPanel label1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        label1Panel.setOpaque(false);
        label1Panel.setPreferredSize(new Dimension(250, 50));
        label1Panel.setMaximumSize(new Dimension(250, 50));

        JLabel label1 = new JLabel("How to Play Item Sort");
        label1.setForeground(Color.WHITE);
        Font label1Font = label1.getFont();
        label1.setFont(new Font(label1Font.getName(), Font.BOLD | Font.ITALIC, 20));

        label1Panel.add(label1);
        labelPanel.add(label1Panel);

        JPanel introHeadingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        introHeadingPanel.setOpaque(false);
        introHeadingPanel.setPreferredSize(new Dimension(400, 120));
        introHeadingPanel.setMaximumSize(new Dimension(700, 40));

        JLabel introHeading = new JLabel("Introduction");
        introHeading.setForeground(Color.WHITE);
        Font introHeadingFont = introHeading.getFont();
        introHeading.setFont(new Font(introHeadingFont.getName(), Font.BOLD, 18));
        introHeading.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel introTextPanel = new JPanel();
        introTextPanel.setOpaque(false);
        introTextPanel.setPreferredSize(new Dimension(400, 120));
        introTextPanel.setMaximumSize(new Dimension(620, 90));

        JLabel introText = new JLabel("<html>ItemSort is a game for African babies. It involves putting together"
                + " different items from shapes to numbers,<br>letters, fruits, animals and various other"
                + " categories to be added. The child learns about different shapes<br> at the end of each level. "
                + "There is also support for language to introduce the child to his native language.</html>");
        introText.setForeground(Color.WHITE);
        Font introTextFont = introText.getFont();
        introText.setFont(new Font(introTextFont.getName(), Font.PLAIN, 12));

        introHeadingPanel.add(introHeading);
        labelPanel.add(introHeadingPanel);
        labelPanel.add(introTextPanel);
        introTextPanel.add(introText);

        JPanel gamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gamePanel.setOpaque(false);
        gamePanel.setPreferredSize(new Dimension(400, 120));
        gamePanel.setMaximumSize(new Dimension(700, 40));

        JLabel gameHeading = new JLabel("GamePlay");
        gameHeading.setForeground(Color.WHITE);
        Font gameHeadingFont = introHeading.getFont();
        gameHeading.setFont(new Font(gameHeadingFont.getName(), Font.BOLD, 18));
        gameHeading.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel gameTextPanel = new JPanel();
        gameTextPanel.setOpaque(false);
        gameTextPanel.setPreferredSize(new Dimension(400, 190));
        gameTextPanel.setMaximumSize(new Dimension(620, 190));

        JLabel gameText = new JLabel("<html>Items are matched together in pairs. To do this, filled shapes"
                + " are dragged and dropped to their corresponding<br> hollow shapes</html>");
        gameText.setForeground(Color.WHITE);
        Font gameTextFont = gameText.getFont();
        gameText.setFont(new Font(gameTextFont.getName(), Font.PLAIN, 12));

        gamePanel.add(gameHeading);
        labelPanel.add(gamePanel);
        labelPanel.add(gameTextPanel);
        gameTextPanel.add(gameText);
        
        JPanel bigShapePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bigShapePanel.setOpaque(false);
        bigShapePanel.setPreferredSize(new Dimension(650, 150));
        
        JPanel smallShapePanel1 = new JPanel(new BorderLayout());
        smallShapePanel1.setOpaque(false);
        
        JPanel smallShapePanel2 = new JPanel(new BorderLayout());
        smallShapePanel2.setOpaque(false);
        
        JPanel smallShapePanel3 = new JPanel(new BorderLayout());
        smallShapePanel3.setOpaque(false);
        
        JPanel rhombusPanel1 = new JPanel(new FlowLayout());
        rhombusPanel1.setOpaque(false);
        JPanel circlePanel1 = new JPanel(new FlowLayout());
        circlePanel1.setOpaque(false);
        
        JPanel rhombusPanel2 = new JPanel(new FlowLayout());
        rhombusPanel2.setOpaque(false);
        JPanel circlePanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        circlePanel2.setOpaque(false);
        
        JPanel rhombusPanel3 = new JPanel(new FlowLayout());
        rhombusPanel3.setOpaque(false);
        JPanel circlePanel3 = new JPanel(new FlowLayout());
        circlePanel3.setOpaque(false);

        RhombusComponent filledRhombus1 = new RhombusComponent(true);
        CircleComponent hollowCircle1 = new CircleComponent(false);
        RhombusComponent hollowRhombus1 = new RhombusComponent(false);
        CircleComponent filledCircle1 = new CircleComponent(true);
        
        RhombusComponent filledRhombus2 = new RhombusComponent(true);
        RhombusComponent hollowRhombus2 = new RhombusComponent(false);
        CircleComponent filledCircle2 = new CircleComponent(true);
        
        RhombusComponent filledRhombus3 = new RhombusComponent(true);
        CircleComponent filledCircle3 = new CircleComponent(true);
        
        JLabel returnLabel1 = createImageLabel("icon\\next_level.png", 40, 40);
        JLabel returnLabel2 = createImageLabel("icon\\next_level.png", 40, 40);
        
        gameTextPanel.add(bigShapePanel);
        bigShapePanel.add(smallShapePanel1);
        bigShapePanel.add(returnLabel1);
        bigShapePanel.add(smallShapePanel2);
        bigShapePanel.add(returnLabel2);
        bigShapePanel.add(smallShapePanel3);
        
        smallShapePanel1.add(rhombusPanel1, BorderLayout.NORTH);
        smallShapePanel1.add(circlePanel1, BorderLayout.SOUTH);
        
        smallShapePanel2.add(rhombusPanel2, BorderLayout.NORTH);
        smallShapePanel2.add(circlePanel2, BorderLayout.SOUTH);
        
        smallShapePanel3.add(circlePanel3, BorderLayout.NORTH);
        smallShapePanel3.add(rhombusPanel3, BorderLayout.SOUTH);
        
        rhombusPanel1.add(filledRhombus1);
        rhombusPanel1.add(hollowCircle1);
        circlePanel1.add(filledCircle1);
        circlePanel1.add(hollowRhombus1);
        
        rhombusPanel2.add(filledRhombus2);
        rhombusPanel2.add(filledCircle2);
        circlePanel2.add(hollowRhombus2);
        
        circlePanel3.add(filledCircle3);
        rhombusPanel3.add(filledRhombus3);
        
        JPanel hintResetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hintResetPanel.setOpaque(false);
        hintResetPanel.setPreferredSize(new Dimension(400, 40));
        hintResetPanel.setMaximumSize(new Dimension(700, 40));
        
        JLabel hintResetHeading = new JLabel("Hint, Reset");
        hintResetHeading.setForeground(Color.WHITE);
        Font hintResetHeadingFont = hintResetHeading.getFont();
        hintResetHeading.setFont(new Font(hintResetHeadingFont.getName(), Font.BOLD, 18));
        hintResetHeading.setHorizontalAlignment(SwingConstants.RIGHT);
        
        hintResetPanel.add(hintResetHeading);
        labelPanel.add(hintResetPanel);
        
        JPanel hintResetTextPanel = new JPanel();
        hintResetTextPanel.setOpaque(false);
        hintResetTextPanel.setPreferredSize(new Dimension(400, 40));
        hintResetTextPanel.setMaximumSize(new Dimension(620, 40));

        JLabel hintResetText = new JLabel("<html>Hints are made available in order to help the child"
        		+ " in case of difficulty. The child can also reset the game.</html>");
        hintResetText.setForeground(Color.WHITE);
        Font hintResetTextFont = hintResetText.getFont();
        hintResetText.setFont(new Font(hintResetTextFont.getName(), Font.PLAIN, 12));
        
        labelPanel.add(hintResetTextPanel);
        hintResetTextPanel.add(hintResetText);
        
        JPanel themePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        themePanel.setOpaque(false);
        themePanel.setPreferredSize(new Dimension(400, 40));
        themePanel.setMaximumSize(new Dimension(700, 40));
        
        JLabel themeHeading = new JLabel("Theme Selection");
        themeHeading.setForeground(Color.WHITE);
        Font themeHeadingFont = themeHeading.getFont();
        themeHeading.setFont(new Font(themeHeadingFont.getName(), Font.BOLD, 18));
        themeHeading.setHorizontalAlignment(SwingConstants.RIGHT);
        
        themePanel.add(themeHeading);
        labelPanel.add(themePanel);
        
        
        JPanel themeTextPanel = new JPanel();
        themeTextPanel.setOpaque(false);
        themeTextPanel.setPreferredSize(new Dimension(400, 40));
        themeTextPanel.setMaximumSize(new Dimension(620, 40));

        JLabel themeText = new JLabel("<html>Hints are made available in order to help the child"
        		+ " in case of difficulty. The child can also reset the game.</html>");
        themeText.setForeground(Color.WHITE);
        Font themeTextFont = hintResetText.getFont();
        themeText.setFont(new Font(themeTextFont.getName(), Font.PLAIN, 12));
        
        labelPanel.add(themeTextPanel);
        themeTextPanel.add(themeText);
        
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setOpaque(false);

        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setOpaque(false);
        getContentPane().add(scrollPane);
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

    class RhombusComponent extends JComponent {
    	
    	private boolean filled;
    	
    	public RhombusComponent(boolean filled) {
    		this.filled = filled;
    	}
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int halfWidth = getWidth()/8;
            int halfHeight = getHeight()/11;

            if(filled == true) {
            	Polygon rhombus = CreateShapes.createRhombus(centerX, centerY, halfWidth, halfHeight);
                g2d.setColor(Color.RED);
                g2d.fillPolygon(rhombus);
            }else {
            	Polygon rhombus = CreateShapes.createRhombus(centerX, centerY, halfWidth, halfHeight);
                g2d.setColor(Color.RED);
                g2d.drawPolygon(rhombus);
            }
            
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50); // Set your desired size for the rhombus
        }
    }
    
class CircleComponent extends JComponent {
    	
    	private boolean filled;
    	
    	public CircleComponent(boolean filled) {
    		this.filled = filled;
    	}
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int centerX = getWidth()/2;
            int centerY = getHeight()/4;
            int halfWidth = getWidth()/10; // Adjust size as needed
            int halfHeight = getHeight()/10; // Adjust size as needed

            if(filled == true) {
//            	Polygon circle = CreateShapes.createRhombus(centerX, centerY, halfWidth, halfHeight);
                g2d.setColor(Color.GREEN);
                g2d.fillOval(centerX - 15 , centerY - 7, 40, 40);
            }else {
//            	Polygon rhombus = CreateShapes.createRhombus(centerX, centerY, halfWidth, halfHeight);
                g2d.setColor(Color.GREEN);
                g2d.drawOval(centerX - 25, centerY - 8, 40, 40);
            }
            
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50); // Set your desired size for the rhombus
        }
    }
private JLabel createImageLabel(String path, int width, int height) {
    ImageIcon icon = new ImageIcon(path);
    Image img = icon.getImage();
    Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new JLabel(new ImageIcon(resizedImg));
}
}
