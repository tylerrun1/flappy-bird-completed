import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Represents the bird in the Flappy Bird game.
 * Handles bird movement, jumping, and rendering.
 */
public class Bird {
    private int x;
    private int y;
    private boolean jumping;
    private int jumpCount;
    private int imageCount;
    private Image birdUp;
    private Image birdDown;
    
    private static final int INITIAL_X = 40;
    private static final int INITIAL_Y = 256;
    private static final int JUMP_HEIGHT = 2;
    private static final int FALL_SPEED = 1;
    private static final int MAX_JUMP_COUNT = 20;
    private static final int BIRD_WIDTH = 34;
    private static final int BIRD_HEIGHT = 24;
    
    public Bird(String imagePath) {
        this.x = INITIAL_X;
        this.y = INITIAL_Y;
        this.jumping = false;
        this.jumpCount = 0;
        this.imageCount = 0;
        
        try {
            birdUp = ImageIO.read(new File(imagePath + "/bird_wing_up.png"));
            birdDown = ImageIO.read(new File(imagePath + "/bird_wing_down.png"));
        } catch (IOException e) {
            System.err.println("Error loading bird images: " + e.getMessage());
        }
    }
    
    public void update() {
        if (jumping) {
            y -= JUMP_HEIGHT;
            jumpCount++;
        } else {
            y += FALL_SPEED;
        }
        
        if (jumpCount >= MAX_JUMP_COUNT) {
            jumping = false;
            jumpCount = 0;
        }
    }
    
    public void draw(Graphics g) {
        Image currentImage = (imageCount == 0) ? birdUp : birdDown;
        if (currentImage != null) {
            g.drawImage(currentImage, x, y, null);
        }
        imageCount = (imageCount + 1) % 2;
    }
    
    public void jump() {
        if (!jumping) {
            jumping = true;
        }
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, BIRD_WIDTH, BIRD_HEIGHT);
    }
    
    public void reset() {
        this.x = INITIAL_X;
        this.y = INITIAL_Y;
        this.jumping = false;
        this.jumpCount = 0;
    }
}
