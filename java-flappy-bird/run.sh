#!/bin/bash
# Script to compile and run the Flappy Bird Java game

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile Java files
echo "Compiling Java files..."
javac -d bin src/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Starting Flappy Bird..."
    java -cp bin FlappyBird
else
    echo "Compilation failed!"
    exit 1
fi
