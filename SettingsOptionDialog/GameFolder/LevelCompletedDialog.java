package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LevelCompletedDialog extends JDialog {
	
    private RoundedPanel contentPanel;
    private JButton button;
    private JLabel label, hintRewardLabel, coinsRewardLabel;
    private ImageIcon resizedIcon;
    private JPanel rewardPanel, buttonPanel, languageOption;
    Timer hintTimer, coinsTimer;
    private RewardDialog dialog;
    private int levelNumber, index, catIndex;
    private LevelTopBarPanel levelTopBarPanel;
    private ShapeLevelSelectDialog shapeLevelSelectDialog;
    private ShapeMatchListener shapeMatchListener;
    private ShapeLevel shapeLevel, nextLevel;
    private LandingPageFrame landingPageFrame;
    private GameSelectBoardPanel gameSelectBoardPanel;

    public LevelCompletedDialog(JFrame parent,ShapeLevelSelectDialog shapeLevelSelectDialog,
    		LevelTopBarPanel levelTopBarPanel,ShapeMatchListener shapeMatchListener,ShapeLevel shapeLevel,
    		ShapeLevel nextLevel,int levelNumber, int index, LandingPageFrame landingPageFrame,int catIndex, GameSelectBoardPanel gameSelectBoardPanel) {
        super(parent, "Popup Dialog", true);
        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
        this.levelTopBarPanel = levelTopBarPanel;
        this.levelNumber = levelNumber;
        this.shapeMatchListener = shapeMatchListener;
        this.shapeLevel = shapeLevel;
        this.nextLevel = nextLevel;
        this.index = index;
        this.landingPageFrame = landingPageFrame;
        this.gameSelectBoardPanel = gameSelectBoardPanel;
        this.catIndex = catIndex;
        initUI();
        layoutUI();
        handleEventsUI();
        initializeAnimation();
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
        setBackground(new Color(0, 0, 0, 0));

        contentPanel = new RoundedPanel(10, new Color(96, 151, 95));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        contentPanel.setOpaque(false);
        contentPanel.setBackground(null);
        
        languageOption = new JPanel();
        languageOption.setOpaque(false);
        languageOption.setLayout(new GridBagLayout());
        languageOption.setBorder(new EmptyBorder(2, 5, 10, 10));
        languageOption.setPreferredSize(new Dimension(200, 400));
        
        label = new JLabel("Level " + levelNumber + " Completed!",JLabel.CENTER);
        label.setForeground(Color.WHITE);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.BOLD, 22));

        ImageIcon completionImageIcon = new ImageIcon("icon\\kids.png");
        Image completionImg = completionImageIcon.getImage();
        Image resizedCompletionImg = completionImg.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedCompletionImageIcon = new ImageIcon(resizedCompletionImg);

        JLabel CompletionImageIconLabel = new JLabel(resizedCompletionImageIcon);
        
        rewardPanel = new JPanel();
        rewardPanel.setLayout(new GridBagLayout());
        rewardPanel.setSize(new Dimension(200,100));
        rewardPanel.setBorder(new EmptyBorder(0,0,0,0));
        rewardPanel.setOpaque(false);
        ImageIcon hintRewardIcon = new ImageIcon("icon\\hint.png");
        Image hintRewardImg = hintRewardIcon.getImage();
        Image resizedHintRewardImg = hintRewardImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon resizedHintRewardIcon = new ImageIcon(resizedHintRewardImg);
        hintRewardLabel = new JLabel("+1", resizedHintRewardIcon, JLabel.CENTER);
        hintRewardLabel.setForeground(Color.WHITE);
        Font hintRewardLabelFont = hintRewardLabel.getFont();
        hintRewardLabel.setFont(new Font(hintRewardLabelFont.getName(), Font.BOLD | Font.ITALIC, 15));
        hintRewardLabel.setHorizontalTextPosition(JLabel.LEFT);
        hintRewardLabel.setVerticalTextPosition(JLabel.CENTER);
        
        ImageIcon coinsRewardIcon = new ImageIcon("icon\\coins.png");
        Image coinsRewardImg = coinsRewardIcon.getImage();
        Image resizedCoinsRewardImg = coinsRewardImg.getScaledInstance(30, 20, Image.SCALE_DEFAULT);
        ImageIcon resizedCoinsRewardIcon = new ImageIcon(resizedCoinsRewardImg);
        coinsRewardLabel = new JLabel("+10", resizedCoinsRewardIcon, JLabel.CENTER);
        coinsRewardLabel.setForeground(Color.WHITE);
        Font coinsRewardLabelFont = coinsRewardLabel.getFont();
        coinsRewardLabel.setFont(new Font(coinsRewardLabelFont.getName(), Font.BOLD | Font.ITALIC, 15));
        coinsRewardLabel.setHorizontalTextPosition(JLabel.LEFT);
        coinsRewardLabel.setVerticalTextPosition(JLabel.CENTER);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(5,5,5,5));
        buttonPanel.setPreferredSize(new Dimension(80,20));        
        
        button = new RoundedButton("Claim");
        button.setBackground(new Color(0, 0, 0, 0));
        button.setPreferredSize(new Dimension(80, 20));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
            	JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LevelCompletedDialog.this);
            	
                SwingUtilities.invokeLater(() -> {
                    
                    SwingUtilities.invokeLater(() -> {
                        dialog = new RewardDialog(parentFrame, levelTopBarPanel,shapeMatchListener,shapeLevel,nextLevel, shapeLevelSelectDialog, 1,10);
                        dialog.setVisible(true);
                        scheduleDialogClose();
                    });
                    
                    JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
                    dialog.dispose();
                    
                    Timer timer = new Timer(7000, evt -> {
                    	NextLevelBackgroundDialog nextLevelBackgroundDialog = new NextLevelBackgroundDialog(parentFrame,
                    			shapeLevelSelectDialog,levelTopBarPanel,shapeLevel,nextLevel, shapeMatchListener, levelNumber, index, landingPageFrame,
                    			catIndex,gameSelectBoardPanel);
                        nextLevelBackgroundDialog.setVisible(true);
                    });
                    timer.setRepeats(false);
                    timer.start();
                });
                
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 0, 0, 0); 
        
        rewardPanel.add(hintRewardLabel,gbc);
        gbc.gridy++;
        rewardPanel.add(coinsRewardLabel,gbc);
        
        buttonPanel.add(button);
        
        languageOption.add(CompletionImageIconLabel, gbc);
        gbc.gridy++;
        languageOption.add(rewardPanel, gbc);
        gbc.gridy++;
        
        Timer timer = new Timer(3000, event -> {
        	 gbc.gridy++; 
        	    languageOption.add(buttonPanel, gbc); 
        	    languageOption.revalidate(); 
        	    languageOption.repaint();
        });
        timer.setRepeats(false);
        timer.start();
        
        contentPanel.add(label, BorderLayout.NORTH);
        contentPanel.add(languageOption, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }
    
    
    private void initializeAnimation() {
        hintTimer = new Timer(200, new ActionListener() {
            int deltaY = 2; 

            @Override
            public void actionPerformed(ActionEvent e) {
                int newY = hintRewardLabel.getY() + deltaY;
                if (newY <= 0 || newY + hintRewardLabel.getHeight() >= rewardPanel.getHeight() * -1) {
                    deltaY *= -1; 
                }
                hintRewardLabel.setLocation(hintRewardLabel.getX(), newY);
            }
        });

        coinsTimer = new Timer(200, new ActionListener() {
            int deltaY = 2; 

            @Override
            public void actionPerformed(ActionEvent e) {
                int newY = coinsRewardLabel.getY() + deltaY;
                if (newY <= 0 || newY + coinsRewardLabel.getHeight() >= rewardPanel.getHeight()) {
                    deltaY *= -1; 
                }
                coinsRewardLabel.setLocation(coinsRewardLabel.getX(), newY);
            }
        });

        hintTimer.start();
        coinsTimer.start();
    }

    private void layoutUI() {
        this.setSize(400, 500);
        this.setMinimumSize(new Dimension(100, 200));
        this.setMaximumSize(new Dimension(400, 500));

        this.setLocationRelativeTo(getParent());
    }

    private ImageIcon resizeIcon(int width, int height) {
        Image img = resizedIcon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(resizedImg);
    }
    
    private void scheduleDialogClose() {
        Timer closeTimer = new Timer(5000, new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dialog != null && dialog.isVisible()) { 
                    dialog.dispose(); 
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                    	JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LevelCompletedDialog.this);
                        if (window instanceof JDialog && window.getOwner() == parentFrame) {
                            JDialog dialog = (JDialog) window;
                            if (dialog instanceof LevelCompletedBackgroundDialog) {
                                dialog.dispose(); 
                            }
                        }
                    }
                }
            }
        });
        closeTimer.setRepeats(false); 
        closeTimer.start();
    }

    private void updatePosition() {
        setLocationRelativeTo(getParent()); 
    }
}
