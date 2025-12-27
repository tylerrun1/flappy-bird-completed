import javax.swing.JPanel;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

/**
 * Main game panel that handles game loop, rendering, and input.
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private static final int SCREEN_WIDTH = 280;
    private static final int SCREEN_HEIGHT = 512;
    private static final int FPS = 60;
    private static final int DELAY = 1000 / FPS;
    
    private Timer timer;
    private Bird bird;
    private Pipe pipe;
    private Image background;
    private int backgroundX;
    private int backgroundX2;
    private boolean gameOver;
    private String imagePath;
    
    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.CYAN);
        setFocusable(true);
        addKeyListener(this);
        
        // Determine the image path - check multiple possible locations
        imagePath = findImagePath();
        
        loadImages();
        
        bird = new Bird(imagePath);
        pipe = new Pipe(imagePath);
        backgroundX = 0;
        backgroundX2 = SCREEN_WIDTH;
        gameOver = false;
        
        timer = new Timer(DELAY, this);
    }
    
    private String findImagePath() {
        // Try different possible paths for the images
        String[] possiblePaths = {
            "../images",
            "images",
            "../../images",
            "../../../images"
        };
        
        for (String path : possiblePaths) {
            File testFile = new File(path + "/background.png");
            if (testFile.exists()) {
                return path;
            }
        }
        
        // Default fallback
        return "../images";
    }
    
    private void loadImages() {
        try {
            background = ImageIO.read(new File(imagePath + "/background.png"));
        } catch (IOException e) {
            System.err.println("Error loading background image: " + e.getMessage());
        }
    }
    
    public void startGame() {
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            updateGame();
        }
        repaint();
    }
    
    private void updateGame() {
        // Update background scroll
        backgroundX--;
        backgroundX2--;
        
        if (backgroundX <= -SCREEN_WIDTH) {
            backgroundX = SCREEN_WIDTH;
        }
        if (backgroundX2 <= -SCREEN_WIDTH) {
            backgroundX2 = SCREEN_WIDTH;
        }
        
        bird.update();
        pipe.update();
        
        // Check boundaries
        if (bird.getY() >= SCREEN_HEIGHT) {
            System.out.println("You have gone to the bottom => Game over");
            gameOver = true;
        }
        if (bird.getY() <= 0) {
            System.out.println("You have gone to the top => Game over");
            gameOver = true;
        }
        
        // Check collision
        if (pipe.checkCollision(bird)) {
            System.out.println("Game over - collision with pipe");
            gameOver = true;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw background
        if (background != null) {
            g.drawImage(background, backgroundX, 0, null);
            g.drawImage(background, backgroundX2, 0, null);
        }
        
        // Draw pipe
        pipe.draw(g);
        
        // Draw bird
        bird.draw(g);
        
        // Draw points
        g.setColor(Color.RED);
        g.setFont(new Font("FreeSansBold", Font.BOLD, 20));
        g.drawString("Points: " + pipe.getPoint(), 5, 20);
        
        // Draw game over message
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("FreeSansBold", Font.BOLD, 30));
            g.drawString("GAME OVER", 50, SCREEN_HEIGHT / 2);
            g.setFont(new Font("FreeSansBold", Font.PLAIN, 16));
            g.drawString("Press R to restart", 70, SCREEN_HEIGHT / 2 + 30);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            if (!gameOver) {
                bird.jump();
            }
        }
        
        if (key == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
        
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }
    
    private void restartGame() {
        bird.reset();
        pipe.reset();
        backgroundX = 0;
        backgroundX2 = SCREEN_WIDTH;
        gameOver = false;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
