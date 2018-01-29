/**
 * This program is in page 18 from 
 *  Sierra, Kathy. 2005. Head First Java (2nd ed.). O'Reilly Media, Inc.
 *
 * To compile it from the Command Line:
 *     javac BeerSong.java
 * To run it from the Command Line:
 *     java BeerSong
 */
public class BeerSong {
    public static void main (String[] args) {
        int beerNum = 99;
        String word = "bottles";
        
        while (beerNum > 0 ) {
            if (beerNum ==1) {
                word = "bottle";
            }
            
            System.out.println(beerNum + " " + word + " of beer on the wall");
            System.out.println(beerNum + " " + word + " of beer.");
            System.out.println("Take one down.");
            System.out.println("Pass it around.") ;
            beerNum --;
            
            if (beerNum > 0) {
                System.out.println(beerNum + " " + word + " of beer on the wall");
            } else {
                System.out.println("No more bottles of beer on the wall.");
            } // end else
        } // end while loop
    } // end main method
} // end class

