#include "house.h"

/**
 * heap storage:
 *  the context where the Cat objects indicates  they are allocated in the
 *  stack.
 */
int main(int argc, char* argv[]) {
    using Edu::Brooklyn::House;  
    // complie it in C++17 or replace the above with
    // using namespace Edu:Brooklyn;
    
    // catHouse is the Heap, so is the cat in catHouse 
    House *catHouse = new House("Cat house in the heap");
    catHouse->dispCat();
    delete catHouse;

    // another house is created, but it is in the Stack, and so is the cat in the House
    House gingerHouse("Ginger's house");
    gingerHouse.dispCat();

    return 0;
}
