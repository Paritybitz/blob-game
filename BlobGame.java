import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlobGame extends JPanel implements KeyListener
{
    Blob playerBlob; // player blob
    ArrayList<Blob> evilBlobs; // ArrayList to store the evil blobs
    int delay; // Time delay between game updates
    boolean gameOver; // flag to indicate if game is over

    public BlobGame(){
        gameOver  = false;
        delay = 100; // Initial delay for game updates
        evilBlobs = new ArrayList<Blob>();
        playerBlob = new Blob(15, Color.blue, 500, 500, 0, 0); // Create the player's blob
        for(int i = 0; i < 20; i++) {
            createEvilBlob(); // Create 20 evil blobs
        }
    }

    // Handle key presses to move player's blob
    public void keyPressed(KeyEvent e) {
        char c;
        c = e.getKeyChar();

        if (c == 'w') {
            playerBlob.moveUp();
        } else if (c == 'a') {
            playerBlob.moveLeft();
        } else if (c == 's') {
            playerBlob.moveDown();
        } else if (c == 'd') {
            playerBlob.moveRight();
        }
    }

    // Check for collisions between the player's blob and evil blobs
    public void checkForCollision(){
        int numEvilBlobsEaten = 0;
        if (playerBlob.getX() < -100 || playerBlob.getX() > 900 || playerBlob.getY() < -100 || playerBlob.getY() > 900) {
            gameOver = true; // If the player's blob goes out of bounds, the game is over
        }
        for (int i = 0; i < evilBlobs.size(); i++) {
            Blob currentBlob = evilBlobs.get(i);
            if (currentBlob.getX() < -100 || currentBlob.getX() > 900 || currentBlob.getY() < -100 || currentBlob.getY() > 900) {
                evilBlobs.remove(i);
                createEvilBlob(); // Replace out-of-bounds evil blobs with new ones
            }
        }
        for (int i = 0; i < evilBlobs.size(); i++) {
            Blob currentBlob = evilBlobs.get(i);
            int distance = distance(playerBlob.getX(), playerBlob.getY(), currentBlob.getX(), currentBlob.getY());
            if (distance < playerBlob.getRadius() + currentBlob.getRadius()) {
                if (playerBlob.getRadius() > currentBlob.getRadius()) {
                    evilBlobs.remove(i);
                    createEvilBlob(); // Remove and replace the eaten evil blob
                    playerBlob.eat(); // Increase the player's blob size
                    numEvilBlobsEaten ++;
                } else {
                    gameOver = true; // If the player's blob is smaller, the game is over
                }
            }
        }
        if (playerBlob.getRadius() >= 115) {
            gameOver = true; // If the player's blob reaches a certain size, the game is over
            System.out.println("Final Blob Size - " + playerBlob.getRadius());
        }
        if (numEvilBlobsEaten >= 35) {
            playerBlob.theColor = (Color.MAGENTA); // Change player's blob color
        }
    }

    // Create a new evil blob with random properties
    public void createEvilBlob(){
        int side = (int)(Math.random() * 4);
        int x, y, deltaX, deltaY;
        // Choose a random side of the screen for the evil blob to appear
        if (side == 0) {
            // Left side
            x = 0;
            y = (int)(Math.random() * getHeight());
            deltaX = (int)(Math.random() * 5) + 1;
            deltaY = (int)(Math.random() * 5) - 2;
        }
        else if (side == 1) {
            // Right side
            x = getWidth();
            y = (int)(Math.random() * getHeight());
            deltaX = (int)(Math.random() * -5) - 1;
            deltaY = (int)(Math.random() * 5) - 2;
        }
        else if (side == 2) {
            // Top side
            x = (int)(Math.random() * getWidth());
            y = 0;
            deltaX = (int)(Math.random() * 5) - 2;
            deltaY = (int)(Math.random() * 5) + 1;
        }
        else {
            // Bottom side
            x = (int)(Math.random() * getWidth());
            y = getHeight();
            deltaX = (int)(Math.random() * 5) - 2;
            deltaY = (int)(Math.random() * -5) - 1;
        }
        int radius = (int) (Math.random() * 60) + 1; // Random radius for the evil blob
        Color color = Color.RED; // Color of the evil blob
        evilBlobs.add(new Blob(radius, color, x, y, deltaX, deltaY)); // Add the evil blob to the list
    }

    // Calculate the Euclidean distance between two points
    public static int distance(int x1, int y1, int x2, int y2){
        return (int) Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
    }

    // The paint method to render the game on the screen
    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 800, 800);
        playerBlob.draw(g); // Draw the player's blob
        for(int i = 0; i < evilBlobs.size(); i++){
            evilBlobs.get(i).draw(g); // Draw all the evil blobs
        }
    }

    // Handle the key released event from the text field.
    public void keyReleased(KeyEvent e) {
        char c;
        c = e.getKeyChar();
    }

    // Handle the key typed event from the text field.
    public void keyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
    }

    // The main game loop
    public void run(){
        while(!gameOver){
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {}

            playerBlob.move();
            for(int i = 0; i < evilBlobs.size(); i++){
                evilBlobs.get(i).move();
            }

            // Check for collision between the player's blob and evil blobs
            checkForCollision();

            paintImmediately(0, 0, 1000, 1000); // Redraw the game

        }
        System.out.println("GAME OVER");
        System.out.println("Final Blob Size - " + playerBlob.getRadius());
    }
}