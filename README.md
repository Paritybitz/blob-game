# Blob Game
## Blob.java
### Description
Blob.java is a Java class file representing the Blob entity within the Blob Game. It manages the attributes and behavior of the Blob character, including its position, size, color, and movement functionalities.
### Contents
The Blob.java file contains the following methods and functionalities:

Methods:
public Blob(int aRadius, Color aColor, int aX, int aY, int aDeltaX, int aDeltaY): Initializes the Blob with specific attributes.\
#### public int **getX()**: 
Returns the x-coordinate of the Blob.\
#### public int **getY()**: 
Returns the y-coordinate of the Blob.\
#### public int **getRadius()**: 
Returns the radius of the Blob.\
#### public void **eat()**: 
Increases the size (radius) of the Blob and adjusts its color.\
#### Movement methods: 
move, moveRight, moveLeft, moveUp, moveDown.\
public void draw(Graphics g): 
Renders the Blob on the screen.\

### Usage
This class file represents the Blob entity and can be utilized as a part of a larger Java project, managing the Blob's attributes and movement within the game.

## BlobGame.java
### Description
BlobGame.java is the primary Java class file defining the Blob Game. It controls game mechanics, including player and enemy blob management, collisions, game loop, and rendering.

### Contents
The BlobGame.java file contains the following:

Player and Enemy Blobs:
Initializes the player's blob and manages an array list for enemy blobs.
#### Game Mechanics:
Handles key presses for player movement.\
Detects collisions between the player's blob and enemy blobs.\
Manages game-over conditions.\
#### Rendering:
Implements the paint method to display game components on the screen.\
#### Game Loop:
Manages the primary game loop.\
### Usage
This class file serves as the core of the Blob Game, orchestrating game mechanics, rendering, and player interactions within the game environment.

#### Example
```java
// Example usage of Blob and BlobGame classes

Blob playerBlob = new Blob(15, Color.BLUE, 500, 500, 0, 0); // Create a player's Blob

BlobGame blobGame = new BlobGame(); // Create a BlobGame object

// Start the game loop
blobGame.run();
```
