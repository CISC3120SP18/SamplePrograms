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
class GuessingGame {
    public void startGame() {
         Player[] player = new Player[3];
         for (int i=0; i<player.length; i++) {
             player[i] = new Player();
         }
         boolean[] playerIsRight = new boolean[player.length];

         int targetNumber = (int) (Math.random() * 10);

         System.out.println("I'm thinking of a number between 0 and 9...");

         while(true) {
              // A secrete shouldn't be revealed. Print it out for simulation
              System.out.println("Number to guess is " + targetNumber);
              
              // Each player makes a guess
              for (int i=0; i<player.length; i++) {
                  player[i].guess();
              }

              // Each player reveals her or his guess
              for (int i=0; i<player.length; i++) {
                System.out.println("Player " 
                        + Integer.toString(i+1) + " guessed " + player[i].getGuessedNumber());
              }

              // Determine if there is a winner
              boolean havingWinner = false;
              for (int i=0; i<player.length; i++) {
                  playerIsRight[i] = player[i].getGuessedNumber() == targetNumber;
                  if (!havingWinner && playerIsRight[i]) {
                      havingWinner = true;
                  }
              }
              
              // If there is no winner, the game continues
              if (havingWinner) {
                    // Print out the result of this round
                    System.out.println("We have a winner!");
                    for (int i=0; i<player.length; i++) {
                        System.out.println("Player "
                                + Integer.toString(i+1) + " got it right? " 
                                + playerIsRight[i]);
                    }
                    System.out.println("Game is over");
                    break;
              } else {
                    System.out.println("Players will have to try again.");
              }
         }
    }
}
