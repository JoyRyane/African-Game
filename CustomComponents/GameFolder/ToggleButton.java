package GameFolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.prefs.Preferences;

public class ToggleButton extends JToggleButton {
    private static final long serialVersionUID = 1L;

    private static final Color BACKGROUND_COLOR_ON = new Color(154, 218, 123);
    private static final Color BACKGROUND_COLOR_OFF = new Color(189, 195, 199);
    private static final Color LABEL_COLOR = Color.BLACK;

    private static final int PREFERRED_WIDTH = 60;
    private static final int PREFERRED_HEIGHT = 30;

    private String labelOff;
    private String labelOn;
    private Image knobImage;

    private boolean switchedOn = true;
    private BackgroundMusicController musicController;
    private Preferences prefs; // Preferences instance for storing state
    private boolean settingsDialogOpen = false; // Flag to track settings dialog state

    public ToggleButton(String labelOff, String labelOn, BackgroundMusicController musicController) {
        super();
        setContentAreaFilled(false); // Make the button transparent
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        setBorder(null); // Remove the border from the SwitchButton

        this.labelOff = labelOff;
        this.labelOn = labelOn;
        this.musicController = musicController;

        // Load the image for the knob
        try {
            // Make sure the image file "toggleButton.png" is located correctly
            knobImage = new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\toggleButton.png").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize Preferences instance
        prefs = Preferences.userNodeForPackage(ToggleButton.class);

        // Retrieve last stored state, default to true if not found
        switchedOn = prefs.getBoolean("switchedOn", true);

        // Add ActionListener to handle click events
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!settingsDialogOpen) { // Check if settings dialog is not open
                    switchedOn = !switchedOn;
                    prefs.putBoolean("switchedOn", switchedOn); // Store state
                    if (switchedOn) {
                        musicController.addBackgroundMusic(); // Start background music
                    } else {
                        musicController.removeBackgroundMusic(); // Stop background music
                    }
                    repaint(); // Redraw the button
                }
            }
        });
    }

    public boolean getCurrentState() {
        return switchedOn;
    }

    public void setState(boolean state) {
        switchedOn = state;
        prefs.putBoolean("switchedOn", switchedOn); // Store the current state
        if (switchedOn) {
            musicController.addBackgroundMusic(); // Start background music
        } else {
            musicController.removeBackgroundMusic(); // Stop background music
        }
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background
        if (switchedOn) {
            g.setColor(BACKGROUND_COLOR_ON);
        } else {
            g.setColor(BACKGROUND_COLOR_OFF);
        }

        // Draw rounded rectangle as the button
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 11, 53, 15, 25, 25);
        g2d.fill(roundedRectangle);

        // Draw knob image
        if (knobImage != null) {
            int knobSize = 23; // Size of the knob image
            int knobX = switchedOn ? getWidth() - knobSize : -2; // Adjust knobX position
            int knobY = -((getHeight() - knobSize) / 2) + 8; // Center the knob vertically
            g2d.drawImage(knobImage, knobX, knobY, knobSize, knobSize, this);
        }

        g2d.dispose();
    }
}
