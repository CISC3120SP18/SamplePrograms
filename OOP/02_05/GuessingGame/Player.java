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
public class Player {
     private int number = 0;

     public void guess()
     {
          number = (int) (Math.random() * 10);
          System.out.println("I'm guessing " + number);
     }

     public int getGuessedNumber() {
         return number;
     }
}
