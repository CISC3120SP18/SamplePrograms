#include "cat.h"

using Edu::Brooklyn::Cat; 

int main(int argc, char* argv[]) {
    Cat ginger("ginger");
    Cat tuxedo("tuxedo");
    Cat *catPtr;

    catPtr = &ginger;
    catPtr->tap(tuxedo);

    /*
     * if you uncomment the delete statement below, though the program
     * compiles, the program will crash because you can only "delete"
     * an object from the Heap, but not from the Stack and note that
     * catePtr points to an object in the Stack.
     */
    // delete catPtr; 
    return 0;
}
