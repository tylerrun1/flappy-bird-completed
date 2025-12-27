import javax.swing.JFrame;

/**
 * Main entry point for the Flappy Bird game.
 * Creates the game window and initializes the game panel.
 */
public class FlappyBird {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        GamePanel gamePanel = new GamePanel();
        
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        gamePanel.startGame();
    }
}
