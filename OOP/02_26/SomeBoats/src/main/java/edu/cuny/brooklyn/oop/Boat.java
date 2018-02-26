/*
 * This program is in page 196, chapter 7 from 
 * Sierra, Kathy. 2005. Head First Java (2nd ed.). O'Reilly Media, Inc
 */
package edu.cuny.brooklyn.oop;

class Boat {
    private int length;
    
    public void setLength(int len) {
        length = len;
    }
    
    public int getLength() {
        return length;
    }
    
    public void move() {
        System.out.print("Just drift.");
    }
}
