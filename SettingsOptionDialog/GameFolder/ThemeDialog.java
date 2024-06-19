package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ThemeDialog extends JDialog{

    private RoundedPanel contentPanel,languageOption;
    private JButton button;
    private JLabel settingsLabel;
    private ImageIcon resizedSettingsIcon;
    private JPanel theme1,theme2,theme3,theme4,theme5,theme6;
    private ThemePanel themePanel;
    
    public ThemeDialog(JFrame parent) { 
        super(parent, "Popup Dialog", true);
        initUI(); 
        layoutUI();
        handleEventsUI(); 
    } 
 
    private void handleEventsUI() {  
        this.addComponentListener(new ComponentAdapter() { 
            @Override
            public void componentResized(ComponentEvent e) { 
            }
        });

        getParent().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                updatePosition();
            }
        });
    }

    private void initUI() {
        setLocationRelativeTo(getParent());
        setUndecorated(true);
        setResizable(true);
        setBackground(new Color(0,0,0,0));
 
        contentPanel = new RoundedPanel(10, new Color(96, 151, 95));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(12,12,12,12));
        contentPanel.setOpaque(false);
        contentPanel.setBackground(null);
        
        button = new RoundedButton("OK");
        button.setBackground(new Color(240, 164, 75));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        ImageIcon closeIcon = new ImageIcon("icon\\close.png");
        Image closeImg = closeIcon.getImage();
        Image resizedCloseImg = closeImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedCloseIcon = new ImageIcon(resizedCloseImg);

        JLabel closeIconLabel = new JLabel(resizedCloseIcon);
        closeIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(ThemeDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof ThemeBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });
        
        JPanel closePanel = new JPanel(new BorderLayout());
        closePanel.setOpaque(false);
        
        ImageIcon settingsImageIcon = new ImageIcon("icon\\theme.png");
        Image settingsImg = settingsImageIcon.getImage();
        Image resizedSettingsImg = settingsImg.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        resizedSettingsIcon = new ImageIcon(resizedSettingsImg);
        settingsLabel = new JLabel("Theme", resizedSettingsIcon, JLabel.CENTER);
        settingsLabel.setForeground(Color.WHITE);
        Font settingsFont = settingsLabel.getFont();
        settingsLabel.setFont(new Font(settingsFont.getName(), Font.BOLD | Font.ITALIC, 25));

        languageOption = new RoundedPanel(10, new Color(107, 218, 105));
        languageOption.setLayout(new GridBagLayout());
        languageOption.setBorder(new EmptyBorder(5, 5, 5, 5));
        languageOption.setPreferredSize(new Dimension(500,450));
        
        JPanel themes = new JPanel();
        themes.setLayout(new GridLayout(2,3,7,10));
        themes.setOpaque(false);
        themes.setPreferredSize(new Dimension(520,400));
        themes.setBorder(new EmptyBorder(5,5,5,5));
        
        themePanel = new ThemePanel();
        
        theme1 = themePanel.createPanel(new Color(192,26,250),new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\platform.jpeg"),"FOREST PLATFORM",false);
        theme2 = themePanel.createPanel(new Color(250,26,26),new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\island.jpeg"),"ISLAND",true);
        theme3 = themePanel.createPanel(new Color(26,143,250),new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\animals.jpg"),"ANIMALS",true);
        theme4 = themePanel.createPanel(new Color(231,235,25),new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\dusk.jpg"),"SCENERY",true);
        theme5 = themePanel.createPanel(new Color(29,235,25),new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\nature.jpg"),"NATURE",true);
        theme6 = themePanel.createPanel(new Color(250,147,26),new ImageIcon("C:\\Users\\GABSIA GAMUAH JULIUS\\Downloads\\GameFolder\\lionKing.jpg"),"HAKUNA MATATA",true);

        themes.add(theme1);
        themes.add(theme2);
        themes.add(theme3);
        themes.add(theme4);
        themes.add(theme5);
        themes.add(theme6);
        
        languageOption.add(themes);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(25, 5, 5, 5);
        
      
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        languageOption.add(button, gbc);
        
        GridBagConstraints gbcCloseIconLabel = new GridBagConstraints();
        gbcCloseIconLabel.anchor = GridBagConstraints.NORTHEAST;
        gbcCloseIconLabel.insets = new Insets(5, 20, 17, 5);
        
        closePanel.add(closeIconLabel,BorderLayout.EAST);
        contentPanel.add(closePanel, BorderLayout.NORTH);
        contentPanel.add(settingsLabel,BorderLayout.CENTER);
        contentPanel.add(languageOption, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private void layoutUI() {
        this.setSize(600, 550);
        
        this.setLocationRelativeTo(getParent());
    }

    private ImageIcon resizeIcon(int width, int height) {
        Image img = resizedSettingsIcon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(resizedImg);
    }

    private void updatePosition() {
        setLocationRelativeTo(getParent());
    }
}