# Flappy Bird - Java Version

A Java implementation of the classic Flappy Bird game using Java Swing/AWT.

## Requirements

- Java JDK 8 or higher

## How to Compile and Run

### From the java-flappy-bird directory:

```bash
# Navigate to the java-flappy-bird directory
cd java-flappy-bird

# Compile all Java files
javac -d bin src/*.java

# Run the game (from java-flappy-bird directory)
java -cp bin FlappyBird
```

### Quick Start (Unix/Linux/Mac):

```bash
cd java-flappy-bird
./run.sh
```

### Quick Start (Windows):

```bash
cd java-flappy-bird
run.bat
```

## Controls

- **SPACE / UP / W** - Jump (make the bird fly up)
- **R** - Restart game (when game is over)
- **ESC** - Exit the game

## Game Mechanics

- The bird continuously falls due to gravity
- Press jump to make the bird fly up
- Avoid hitting the pipes and screen boundaries
- Each pipe passed increases your score
- Pipe speed increases with each pipe passed

## Project Structure

```
java-flappy-bird/
├── src/
│   ├── FlappyBird.java    # Main entry point
│   ├── GamePanel.java     # Game loop and rendering
│   ├── Bird.java          # Bird sprite and physics
│   └── Pipe.java          # Pipe obstacle logic
├── README.md
├── run.sh                 # Unix run script
└── run.bat               # Windows run script
```

## Images

The game uses the same image assets as the Python version, located in the `../images` directory.
