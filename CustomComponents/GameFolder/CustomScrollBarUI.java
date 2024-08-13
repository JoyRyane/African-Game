package GameFolder;

import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.*;

public class CustomScrollBarUI extends MetalScrollBarUI {

    private Color thumbColor;

    public CustomScrollBarUI() {
        // Default thumb color (cyan in this case)
        thumbColor = new Color(255,255,255);
    }

    @Override
    protected void configureScrollBarColors() {
        super.configureScrollBarColors();
        // Set thumb color using UIManager (this is the correct approach)
        UIManager.put("ScrollBar.thumb", thumbColor);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = super.createDecreaseButton(orientation);
        button.setBackground(new Color(255, 255, 255, 100)); // Transparent background for buttons
        return button;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = super.createIncreaseButton(orientation);
        button.setBackground(new Color(255, 255, 255, 100)); // Transparent background for buttons
        return button;
    }
}

