@echo off
REM Script to compile and run the Flappy Bird Java game

REM Create bin directory if it doesn't exist
if not exist bin mkdir bin

REM Compile Java files
echo Compiling Java files...
javac -d bin src\*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo Starting Flappy Bird...
    java -cp bin FlappyBird
) else (
    echo Compilation failed!
    exit /b 1
)
