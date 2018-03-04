#include "house.h"

using Edu::Brooklyn::House;  
// complie it in C++17 or replace the above with using namespace
// Edu:Brooklyn;

/**
 * heap storage:
 *  the context where the Cat objects are determines that  they are allocated
 *  in the heap because the House object is in the heap and the cat is part
 *  of the House.
 */
int main(int argc, char* argv[]) { 
    
    // catHouse is the Heap, so is the cat in catHouse 
    House *catHouse = new House("Cat house in the heap"); catHouse->dispCat();
    delete catHouse;

    // another house is created, but it is in the Stack, and so is the cat in the House
    House gingerHouse("Ginger's house");
    gingerHouse.dispCat();

    return 0;
}
