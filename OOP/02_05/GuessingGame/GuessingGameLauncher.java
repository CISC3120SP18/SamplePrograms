/*
 * this program a revision of the example program in page 39 from 
 *  Sierra, Kathy. 2005. Head First Java (2nd ed.). O'Reilly Media, Inc.
 *
 * To compile
 *  javac Player.java
 *  javac GuessingGame.java
 *  javac GuessingGameLauncher.java
 * To run
 *  java GuessingGameLauncher
 */
public class GuessingGameLauncher {
    public static void main (String[] args) {
        GuessingGame game = new GuessingGame();
        game.startGame();
    }
}
