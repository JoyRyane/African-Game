package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DailyChallengeDialog extends JDialog {

    private RoundedPanel contentPanel, languageOptionPanel, languageOption;
    private JButton button;
    private JLabel label;
    private ImageIcon resizedIcon;
    
    public DailyChallengeDialog(JFrame parent) { 
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
        
        ImageIcon closeIcon = new ImageIcon("icon\\close.png");
        Image closeImg = closeIcon.getImage();
        Image resizedCloseImg = closeImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedCloseIcon = new ImageIcon(resizedCloseImg);

        JLabel closeIconLabel = new JLabel(resizedCloseIcon);
        closeIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(DailyChallengeDialog.this);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof DailyChallengeBackgroundDialog) {
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

        ImageIcon imageIcon = new ImageIcon("icon\\dailyChallenge.png");
        Image img = imageIcon.getImage();
        Image resizedImg = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        resizedIcon = new ImageIcon(resizedImg);
        label = new JLabel("Daily Challenge", resizedIcon, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.BOLD | Font.ITALIC, 20));

        languageOption = new RoundedPanel(10, new Color(107, 218, 105));
        languageOption.setLayout(new GridBagLayout());
        languageOption.setBorder(new EmptyBorder(10, 10, 10, 10));
        languageOption.setPreferredSize(new Dimension(200,300));
        
        languageOptionPanel = new RoundedPanel(10, new Color(0, 0, 0));
        languageOptionPanel.setOpaque(false);
        
        JLabel challengeText = new JLabel("Play Today's Challenge");
        challengeText.setForeground(Color.WHITE);
        Font challengeFont = challengeText.getFont();
        challengeText.setFont(new Font(challengeFont.getName(), Font.BOLD, 20));
        
        button = new RoundedButton("OK");
        button.setBackground(new Color(240, 164, 75));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(DailyChallengeDialog.this);
                JDialog parentDialog = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                JFrame grandParentFrame = (JFrame) SwingUtilities.getWindowAncestor(parentDialog);
                
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JDialog && window.getOwner() == parentFrame) {
                        JDialog dialog = (JDialog) window;
                        if (dialog instanceof DailyChallengeBackgroundDialog) {
                            dialog.dispose();
                        }
                    }
                }
                
//                if (parentFrame instanceof LandingPageFrame) {
//                    LandingPageFrame landingPageFrame = (LandingPageFrame) parentFrame;
//                    landingPageFrame.removeTopRightPanel();
//                    landingPageFrame.removeImageLabelPanel();
////                    landingPageFrame.removeGameBoardDialog();
//                    landingPageFrame.removeGameSelectBoardPanel();
//                    
//                    
//                  
////                    ShapeModel shapeModel = ShapeModelSingleton.getInstance(gameBoardDialog, landingPageFrame, this,
////                            shapeMatchListener, levelTopBarPanel, levelNumber, currentIndex, level, nextLevel,catIndex, gameSelectBoardPanel);
//
////                    public static ShapeModel getInstance(GameBoardDialog gameBoardDialog, LandingPageFrame landingPageFrame, 
////                     		ShapeLevelSelectDialog shapeLevelSelectDialog, ShapeMatchListener shapeMatchListener, 
////                     		LevelTopBarPanel levelTopBarPanel,int levelNumber, int index, ShapeLevel shapeLevel, ShapeLevel nextLevel,
////                     		int catIndex,GameSelectBoardPanel gameSelectBoardPanel)
////                    ShapeModel shapeModel = ShapeModelSingleton.getInstance(gameBoardDialog, landingPageFrame, shapeLevelSelectDialog,
////                            shapeMatchListener, levelTopBarPanel, 5, null, shapeLevel, shapeLevel,null, gameSelectBoardPanel);
////                    LevelTopBarPanel levelTopBarPanel = new LevelTopBarPanel(null,landingPageFrame,gameBoardDialog,
////                            shapeLevelSelectDialog, 5, null, null,shapeLevel,shapeLevel,shapeModel,musicController,
////                            null, gameSelectBoardPanel);
////                    gameBoardDialog = new GameBoardDialog(parentFrame, landingPageFrame, this, levelTopBarPanel, null, null, null, 
////                    		null,null, gameSelectBoardPanel);
////
////                    landingPageFrame.setGameBoardDialog(gameBoardDialog);
////
////                    landingPageFrame.add(levelTopBarPanel, BorderLayout.NORTH);
////                    gameBoardDialog.setVisible(true);
//                }
                
                JDialog set = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                set.dispose();
            }
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        languageOption.add(challengeText,gbc);
        
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0);
        languageOption.add(button, gbc);
        
        
        GridBagConstraints gbcCloseIconLabel = new GridBagConstraints();
        gbcCloseIconLabel.anchor = GridBagConstraints.NORTHEAST; 
        gbcCloseIconLabel.insets = new Insets(5, 15, 5, 5); 
        
        closePanel.add(closeIconLabel,BorderLayout.EAST);
        contentPanel.add(closePanel,BorderLayout.NORTH);
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.add(languageOption, BorderLayout.SOUTH); 

        setContentPane(contentPanel);
    }

    private void layoutUI() {
        this.setSize(300, 400);
        this.setMinimumSize(new Dimension(100,200));
        this.setMaximumSize(new Dimension(300,400));
        
        this.setLocationRelativeTo(getParent());
    }

    private ImageIcon resizeIcon(int width, int height) {
        Image img = resizedIcon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(resizedImg);
    }

    private void updatePosition() {
        setLocationRelativeTo(getParent()); // Update dialog's position relative to the parent frame
    }
}