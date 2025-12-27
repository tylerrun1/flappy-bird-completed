import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Represents a pipe obstacle in the Flappy Bird game.
 * Handles pipe movement, collision detection, and rendering.
 */
public class Pipe {
    private int x;
    private int pieceTop;
    private int pieceBot;
    private double speed;
    private int point;
    private Image pipeTop;
    private Image pipeBody;
    private Random random;
    
    private static final int SCREEN_WIDTH = 280;
    private static final int SCREEN_HEIGHT = 512;
    private static final int TILE_SIZE = 32;
    private static final int INITIAL_SPEED = 1;
    private static final int PIPE_WIDTH = 52;
    
    public Pipe(String imagePath) {
        this.x = SCREEN_WIDTH;
        this.random = new Random();
        this.pieceTop = random.nextInt(6) + 3; // 3 to 8
        this.pieceBot = 16 - 6 - pieceTop;
        this.speed = INITIAL_SPEED;
        this.point = 0;
        
        try {
            pipeTop = ImageIO.read(new File(imagePath + "/pipe_end.png"));
            pipeBody = ImageIO.read(new File(imagePath + "/pipe_body.png"));
        } catch (IOException e) {
            System.err.println("Error loading pipe images: " + e.getMessage());
        }
    }
    
    public void update() {
        x -= speed;
        
        if (x <= -80) {
            x = SCREEN_WIDTH;
            pieceTop = random.nextInt(6) + 3;
            pieceBot = 16 - 6 - pieceTop;
            speed += 0.2;
            point++;
            System.out.println("Speed of pipe: " + speed);
            System.out.println("Points: " + point);
        }
    }
    
    public void draw(Graphics g) {
        // Draw top pipe (from top going down)
        for (int i = 0; i < pieceBot; i++) {
            int posY = i * TILE_SIZE;
            if (pipeBody != null) {
                g.drawImage(pipeBody, x, posY, null);
            }
        }
        // Draw top pipe cap
        int topCapY = pieceBot * TILE_SIZE;
        if (pipeTop != null) {
            g.drawImage(pipeTop, x, topCapY, null);
        }
        
        // Draw bottom pipe (from bottom going up)
        for (int i = 0; i < pieceTop; i++) {
            int posY = SCREEN_HEIGHT - i * TILE_SIZE;
            if (pipeBody != null) {
                g.drawImage(pipeBody, x, posY, null);
            }
        }
        // Draw bottom pipe cap
        int botCapY = SCREEN_HEIGHT - pieceTop * TILE_SIZE;
        if (pipeTop != null) {
            g.drawImage(pipeTop, x, botCapY, null);
        }
    }
    
    public boolean checkCollision(Bird bird) {
        int birdX = bird.getX();
        int birdY = bird.getY();
        
        // Top pipe collision area
        int topPipeBottom = pieceBot * TILE_SIZE + TILE_SIZE;
        // Bottom pipe collision area
        int botPipeTop = SCREEN_HEIGHT - pieceTop * TILE_SIZE;
        
        if (birdX + 34 >= x && birdX <= x + PIPE_WIDTH) {
            if (birdY <= topPipeBottom || birdY + 24 >= botPipeTop) {
                return true;
            }
        }
        return false;
    }
    
    public int getPoint() {
        return point;
    }
    
    public void reset() {
        this.x = SCREEN_WIDTH;
        this.pieceTop = random.nextInt(6) + 3;
        this.pieceBot = 16 - 6 - pieceTop;
        this.speed = INITIAL_SPEED;
        this.point = 0;
    }
}
