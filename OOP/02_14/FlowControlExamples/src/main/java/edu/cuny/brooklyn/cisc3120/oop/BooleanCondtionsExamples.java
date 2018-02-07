package edu.cuny.brooklyn.cisc3120.oop;

import java.util.Random;

public class BooleanCondtionsExamples 
{
    public static void main( String[] args )
    {
    	// TODO: uncomment each commented block (separated by blank lines) and observe compilation 
    	//       execution results (if it compiles). When done for a block, comment the block. 
    	//
    	//       To uncomment, you may use select/highlight the block and click Eclipse's 
    	//       "Source->Toggle Comment" menu. 
    	//
    	//       To observe, click the "Problems" tab in Eclipse or compile it in command line. 
    	// 
    	//       Be cautious about what Java compiler (javac) tells you and what Eclipse (an IDE) tells
    	//       you, and try to differentiate them.
    	
    	/*
    	 * Boolean data type and boolean values
    	 */
    	/* Exercise 0: boolean data type */
//    	System.out.println(true);
//    	System.out.println(false);
//    	boolean b = false;
//    	System.out.print("b = " + b);
    	
    	/* Exercise 1: does it compile?  */
//    	while (true);
//    	System.out.println("Hello, World!");

    	/* Exercise 2: does it compile? how much is your computer's CPU utlization? */
//    	while (true);

    	
    	/* Exercise 3: does it compile? */
//    	while (false);
//    	System.out.println("Hello, World!");
    	
    	/* Exercise 4: does it compile? */
//    	if (true) {
//    		System.out.println("Hello,  World!");
//    	}
    	
    	/* Exercise 5: does it compile? Where does the warning comes from, 
    	 * Eclipse or Java Compiler? Compare the compilation result with that of
    	 * Exercise 3. What is your reasoning about the difference? */
//    	if (false) {
//    		System.out.println("Hello, World!");
//    	}
    	
    	/* Exercise 6: does it compile? */
//    	while (1);
    	
    	/* Exercise 7: does it compile? */
//    	if (0);
  	
    	/*
    	 * Relational operators
    	 */
    	/* Exercise 8: test equivalence */
//    	int n1 = 3120;
//    	int n2 = 3120;
//    	System.out.println(n1 == n2);
//    	System.out.println(n1 != n2);
 	
    	
    	
    	/* Exercise 9: test object test equivalence. What does "==" or "!=" compare? 
    	 * What does the "equals" method compare? */
//    	Integer n1 = new Integer(3120);
//    	Integer n2 = new Integer(3120);
//    	System.out.println(n1 == n2);
//    	System.out.println(n1 != n2);
//    	System.out.println(n1.equals(n2));
    	
    	/* Exercise 10: test object test equivalence. in the TwoEqualDogs class */
//    	Dog buddy = new Dog(1, "buddy");
//    	Dog mellie = new Dog(1, "buddy");
//    	System.out.println(buddy == mellie);
//    	System.out.println(buddy != mellie);
//    	System.out.println(buddy.equals(mellie));
    	
    	/* Exercise 11: test equivalence of two String objects */
//    	String s1 = new String("CISC 3120"); // although you can, don't "new" String objects
//    	String s2 = new String("CISC 3120"); // unless you have a good reason to
//    	System.out.println(s1 == s2);
//    	System.out.println(s1 != s2);
//    	System.out.println(s1.equals(s2));
    	
    	/* Exercise 12a: test equivalence of "two" String objects. Any surprises? */
//    	String s1 = "CISC 3120";
//    	String s2 = "CISC 3120";
//    	System.out.println(s1 == s2);
//    	System.out.println(s1 != s2);
//    	System.out.println(s1.equals(s2));

    	/* Exercise 12b: test equivalence of "two" String objects. Just a repeat */
//    	String s1 = "CISC 3120"; // string literals allocated in run time constant pool
//    	String s2 = s1;
//    	System.out.println(s1 == s2);
//    	System.out.println(s1 != s2);
//    	System.out.println(s1.equals(s2));
    	
    	/* Exercise 13b: conditional operators */
//    	int i = 3;
//    	int j = 10;
//    	System.out.println("i = " + i);
//    	System.out.println("j = " + j);
//    	System.out.println("i > j is " + (i > j));
//    	System.out.println("i < j is " + (i < j));
//    	System.out.println("i >= j is " + (i >= j));
//    	System.out.println("i <= j is " + (i <= j));
//    	System.out.println("i == j is " + (i == j));
//    	System.out.println("i != j is " + (i != j));
//    	System.out.println("(i < 10) && (j < 10) is " + ((i < 10) && (j < 10)));
//    	System.out.println("(i < 10) || (j < 10) is " + ((i < 10) || (j < 10)));
    	
    	
    	/* Exercise 13b: conditional operators */
//    	Random rng = new Random();
//    	int i = rng.nextInt(100);
//    	int j = rng.nextInt(100);
//    	System.out.println("i = " + i);
//    	System.out.println("j = " + j);
//    	System.out.println("i > j is " + (i > j));
//    	System.out.println("i < j is " + (i < j));
//    	System.out.println("i >= j is " + (i >= j));
//    	System.out.println("i <= j is " + (i <= j));
//    	System.out.println("i == j is " + (i == j));
//    	System.out.println("i != j is " + (i != j));
//    	System.out.println("(i < 10) && (j < 10) is " + ((i < 10) && (j < 10)));
//    	System.out.println("(i < 10) || (j < 10) is " + ((i < 10) || (j < 10)));
    	
    	// Exercise 14a: short-circuiting; which method of the three (test1, test2, and test3)
    	// is being executed? 
//    	boolean b = test1(0) && test2(0) && test3(0);
//    	System.out.println("main(): the expression is " + b);  
    	
    	// Exercise 14b: short-circuiting; which method is being executed?
//    	boolean b = test1(0) && test2(0) && test3(3);
//    	System.out.println("main(): the expression is " + b);  

    	// Exercise 14c: short-circuiting; which method is being executed?
//    	boolean b = test1(0) && test2(2) && test3(3);
//    	System.out.println("main(): the expression is " + b);     	
    	
    	// Exercise 14d: short-circuiting; which method is being executed?
//    	boolean b = test1(1) && test2(2) && test3(3);
//    	System.out.println("main(): the expression is " + b);  
    	
    	// Exercise 14e: short-circuiting; which method of the three (test1, test2, and test3)
    	// is being executed? 
//    	boolean b = test1(0) || test2(0) || test3(0);
//    	System.out.println("main(): the expression is " + b);  
    	
    	// Exercise 14f: short-circuiting; which method is being executed?
//    	boolean b = test1(1) || test2(2) || test3(3);
//    	System.out.println("main(): the expression is " + b);   
    	
    	// Exercise 14g: short-circuiting; which method is being executed?
//    	boolean b = test1(1) || test2(2) && test3(3);
//    	System.out.println("main(): the expression is " + b);  
    	
    	// Exercise 14h: short-circuiting; which method is being executed?
//    	boolean b = test1(0) || test2(2) && test3(3);
//    	System.out.println("main(): the expression is " + b);   
    	
    	// Exercise 14i: the above may be confusing. add parenthesis!
//    	boolean b = test1(1) || (test2(2) && test3(3));
//    	System.out.println("main(): the expression is " + b);  
    	
    	// Exercise 14j: short-circuiting; which method is being executed?
//    	boolean b = test1(0) || (test2(2) && test3(3));
//    	System.out.println("main(): the expression is " + b);      	
    	
    	/* Exercise 15: Can you treat an integer as a boolean as C/C++ does? */
//    	int i = 10;
//    	int j = 20;
//    	System.out.println("i && j is " + (i && j));
//    	System.out.println("i || j is " + (i || j));
//    	System.out.println("!i is " + !i);     
    	
    	// Exercise 16: Don't confusing conditional & bit-wise operators, e.g., 
    	// && and & are different ...
//    	int i = 2;
//    	int j = 4;
//    	System.out.println("i & j is " + (i & j));
//    	System.out.println("i | j is " + (i | j));
//    	System.out.println("~i is " + ~i);     	

   
    	
    	

    	
    	

    		
    }
    
	static boolean test1(int val) {
		System.out.println("test1(" + val + ") ==> "  + (val < 1));
		return val < 1;
	}

	static boolean test2(int val) {
		System.out.println("test2(" + val + ") ==> " + (val < 2));
		return val < 2;
	}

	static boolean test3(int val) {
		System.out.println("test3(" + val + ") ==> " + (val < 3));
		return val < 3;
	}  
}
