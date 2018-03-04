#include "cat.h"

/**
 * automatic storage:
 *  the context where the Cat objects are determines that they are allocated in
 *  the stack.
 */
using Edu::Brooklyn::Cat;

int main(int argc, char* argv[]) {

    Cat ginger("ginger"); 
    Cat tuxedo("tuxedo");

    ginger.tap(tuxedo);

    return 0;

}
