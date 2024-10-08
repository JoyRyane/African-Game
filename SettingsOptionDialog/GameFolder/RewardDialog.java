package GameFolder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RewardDialog extends JDialog {

    private JPanel contentPanel, rewardPanel;
    private JLabel hintRewardLabel,coinsRewardLabel;
    private Timer animationTimer;
    private int currentWidth = 100;
    private int targetWidth = 500;
    private int increment = 5;
    private int hintsEarned, coinsEarned;
    private LevelTopBarPanel levelTopBarPanel;
    private ShapeMatchListener shapeMatchListener;
    private ShapeLevel shapeLevel, nextLevel;
    private ShapeLevelSelectDialog shapeLevelSelectDialog;

    
    public RewardDialog(JFrame parent, LevelTopBarPanel levelTopBarPanel,ShapeMatchListener shapeMatchListener,
    		ShapeLevel shapeLevel,ShapeLevel nextLevel,ShapeLevelSelectDialog shapeLevelSelectDialog, int hintsEarned, int coinsEarned) { 
        super(parent, "Popup Dialog", false);
        this.levelTopBarPanel = levelTopBarPanel;
        this.shapeMatchListener = shapeMatchListener;
        this.shapeLevelSelectDialog = shapeLevelSelectDialog;
        this.shapeLevel = shapeLevel;
        this.nextLevel = nextLevel;
        this.hintsEarned = hintsEarned;
        this.coinsEarned = coinsEarned;
        initUI(); 
        layoutUI();
        handleEventsUI();
        startAnimation();

        levelTopBarPanel.removeFromParent();
//        levelTopBarPanel.test();
        levelTopBarPanel.addReward(hintsEarned, coinsEarned);
        levelTopBarPanel.increaseProgressBar(17);
        levelTopBarPanel.increaseScore(10);
        
        shapeMatchListener.onAllShapesMatched();
        
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
        
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(12,12,12,12));
        contentPanel.setOpaque(false);
        
        rewardPanel = new JPanel();
        rewardPanel.setLayout(new FlowLayout());
        rewardPanel.setOpaque(false);
        
        ImageIcon hintRewardIcon = new ImageIcon("icon\\hint.png");
        Image hintRewardImg = hintRewardIcon.getImage();
        Image resizedHintRewardImg = hintRewardImg.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        ImageIcon resizedHintRewardIcon = new ImageIcon(resizedHintRewardImg);
        hintRewardLabel = new JLabel("+" + hintsEarned + " Hints", resizedHintRewardIcon, JLabel.CENTER);
        hintRewardLabel.setForeground(Color.WHITE);
        Font hintRewardLabelFont = hintRewardLabel.getFont();
        hintRewardLabel.setFont(new Font(hintRewardLabelFont.getName(), Font.BOLD | Font.ITALIC, 25));
        
//        levelTopBarPanel.addHints(hintsEarned);
        
        ImageIcon coinsRewardIcon = new ImageIcon("icon\\coins.png");
        Image coinsRewardImg = coinsRewardIcon.getImage();
        Image resizedCoinsRewardImg = coinsRewardImg.getScaledInstance(80, 70, Image.SCALE_DEFAULT);
        ImageIcon resizedCoinsRewardIcon = new ImageIcon(resizedCoinsRewardImg);
        coinsRewardLabel = new JLabel("+" + coinsEarned + " Coins", resizedCoinsRewardIcon, JLabel.CENTER);
        coinsRewardLabel.setForeground(Color.WHITE);
        Font coinsRewardLabelFont = coinsRewardLabel.getFont();
        coinsRewardLabel.setFont(new Font(coinsRewardLabelFont.getName(), Font.BOLD | Font.ITALIC, 25));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        
        rewardPanel.add(hintRewardLabel);
        rewardPanel.add(coinsRewardLabel);
        
        contentPanel.add(rewardPanel,gbc);

        setContentPane(contentPanel);
    }

    private void layoutUI() {
        this.setSize(500, 400);
        this.setMinimumSize(new Dimension(100,200));
        this.setMaximumSize(new Dimension(500,400));
        
        this.setLocationRelativeTo(getParent());
    }
    private void startAnimation() {
        animationTimer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWidth < targetWidth) {
                    currentWidth += increment;
                    setSize(currentWidth, currentWidth);
                    setLocationRelativeTo(getParent());
                } else {
                    animationTimer.stop();
                }
            }
        });
        animationTimer.start();
    }

    private void updatePosition() {
        setLocationRelativeTo(getParent()); // Update dialog's position relative to the parent frame
    }
}
