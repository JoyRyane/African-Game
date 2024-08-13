package GameFolder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.List;

public class LoadingPageFrame extends JFrame {

    private BackgroundPanel pageContainer;
    private LoadingLanguageBackgroundDialog dialog;
    JLabel imageLabel;
    RoundedProgressBar progressBar;
    private Clip backgroundMusicClip;

    public LoadingPageFrame() {
        initUI();
        addBackgroundMusic();
    }

    private void initUI() {
        try {
            BufferedImage backgroundImg = ImageIO.read(new File("icon\\scenery.jpeg"));
            pageContainer = new BackgroundPanel(backgroundImg);
        } catch (IOException e) {
            e.printStackTrace();
            pageContainer = new BackgroundPanel(null);
        }

        setTitle("Item Sort");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 700);
        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(null);

        try {
            BufferedImage iconImage = ImageIO.read(new File("icon\\logo.png"));
            setIconImage(iconImage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setContentPane(pageContainer);

        try {
            BufferedImage iconImage = ImageIO.read(new File("icon\\logo.png"));

            Image resizedIconImage1 = iconImage.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon1 = new ImageIcon(resizedIconImage1);

            Image resizedIconImage2 = iconImage.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon2 = new ImageIcon(resizedIconImage2);

            Image resizedIconImage3 = iconImage.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon3 = new ImageIcon(resizedIconImage3);

            imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);

            pageContainer.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 1;
            pageContainer.add(imageLabel, gbc);

            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    int frameWidth = getWidth();
                    if (frameWidth <= 500) {
                        imageLabel.setIcon(resizedIcon1);
                    } else if (frameWidth <= 800) {
                        imageLabel.setIcon(resizedIcon2);
                    } else {
                        imageLabel.setIcon(resizedIcon3);
                    }
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        progressBar = new RoundedProgressBar(0, 100, 200, 17);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(50, 205, 50));
        progressBar.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 0, 70, 0);
        pageContainer.add(progressBar, gbc);

        // Start the progress bar
        startProgressInSteps(17000);        
    }
    
    public void displayLanguageDialog() {
    	dialog = new LoadingLanguageBackgroundDialog(this);
        dialog.setVisible(true);
    }

    private void playBackgroundMusic(String filePath) {
    	
    	try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(audioStream);
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusicClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    class BackgroundPanel extends JPanel {
        private BufferedImage image;

        public BackgroundPanel(BufferedImage img) {
            this.image = img;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    public void startProgressInSteps(int durationMs) {
        int totalSteps = 100;
        int stepDuration = durationMs / totalSteps;

        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                long startTime = System.currentTimeMillis();
                long endTime = startTime + durationMs;
                int progressValue = 0;

                while (System.currentTimeMillis() <= endTime) {
                    publish(progressValue);
                    progressValue += 1;
                    Thread.sleep(stepDuration);
                }

                if (progressValue <= 100) {
                    publish(100);
                }

                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                for (Integer value : chunks) {
                    progressBar.setValue(value);
                }
            }

            @Override
            protected void done() {
            	Timer timer = new Timer(3000, e -> {
                  displayLanguageDialog();
              });
              timer.setRepeats(false);
              timer.start();
            }
        };

        worker.execute();
    }


    public void removeImagePanel() {
        if (imageLabel != null) {
            pageContainer.remove(imageLabel);
            pageContainer.revalidate();
            pageContainer.repaint();
        }
    }
    
    public void removeProgressBar() {
        if (progressBar != null) {
            pageContainer.remove(progressBar);
            pageContainer.revalidate();
            pageContainer.repaint();
        }
    }
    public void addBackgroundMusic() {
    	playBackgroundMusic("audio\\game_sound.wav");
    }

    public void removeBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoadingPageFrame().setVisible(true));
    }
}