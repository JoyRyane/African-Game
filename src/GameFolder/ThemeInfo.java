package GameFolder;
import javax.swing.*;
import java.awt.*;

public class ThemeInfo {
    private Color color;
    private ImageIcon icon;
    private String name;
    private boolean isSelected;

    public ThemeInfo(Color color, ImageIcon icon, String name, boolean isSelected) {
        this.color = color;
        this.icon = icon;
        this.name = name;
        this.isSelected = isSelected;
    }

    public Color getColor() {
        return color;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
