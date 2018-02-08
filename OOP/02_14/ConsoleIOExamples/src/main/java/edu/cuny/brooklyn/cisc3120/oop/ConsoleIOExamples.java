package edu.cuny.brooklyn.cisc3120.oop;

import java.util.Scanner;

public class ConsoleIOExamples 
{
	private Scanner scanner = new Scanner(System.in);
	
    public static void main( String[] args )  {
    	
        ConsoleIOExamples examples = new ConsoleIOExamples();
        
       examples.echo();
       
       examples.countWordsInLine();

       examples.sumIntegers(5);
       
       examples.sumDoubles(5);
        
    }
    
    void countWordsInLine() {
    	System.out.println("Enter a loop that reads line by line. To exit, enter \"Goodbye\"");
  	
    	String line = null;
    	do {
    		System.out.print("Enter a line (enter \"Goodbye\" to exit):" );
    		line = scanner.nextLine();
    		int numOfWords = line.split("\\s").length;
    		System.out.println("You entered: " + numOfWords + " word(s).");
    	} while (!line.equals("Goodbye"));
    	System.out.println("Goodbye");
    }
    
   
    void echo() {
    	System.out.println("Enter a loop that reads line by line. To exit, enter \"Goodbye\"");
  	
    	String line = null;
    	do {
    		System.out.print("Enter a line (enter \"Goodbye\" to exit):" );
    		line = scanner.nextLine();
    		System.out.println("You entered: " + line);
    	} while (!line.equals("Goodbye"));
    	System.out.println("Goodbye");
    }

	void sumIntegers(int numOfIntegers) {
		int sum = 0;
		for (int i = 0; i < numOfIntegers; i++) {
			System.out.print("Enter an integer:");
			int n = scanner.nextInt();
			sum += n;
		}
		System.out.println("The sum is " + sum);
	}
	
	void sumDoubles(int numOfDoubles) {
		double sum = 0.;
		for (int i = 0; i < numOfDoubles; i++) {
			System.out.print("Enter a double:");
			double d = scanner.nextDouble();
			sum += d;
		}
		System.out.println("The sum is " + sum);		
	}
}
