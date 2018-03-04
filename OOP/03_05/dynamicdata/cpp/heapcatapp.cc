#include "cat.h"

/**
 * heap storage:
 *  the context where the Cat objects indicates  they are allocated in the
 *  stack.
 */
int main(int argc, char* argv[]) {
    /*
    Edu::Brooklyn::Cat *ginger = new Edu::Brooklyn::Cat("ginger"), 
        *tuxedo = new Edu::Brooklyn::Cat("tuxedo");
    */
    // sometimes more conveniently 
    /*
    using namespace Edu::Brooklyn; 
    Cat *ginger = new Cat("ginger"), 
        *tuxedo = new Cat("tuxedo");
    */
    // since c++17
    using Edu::Brooklyn::Cat; 
    Cat *ginger = new Cat("ginger"), 
        *tuxedo = new Cat("tuxedo");
    ginger->tap(*tuxedo);
    delete ginger;
    delete tuxedo;
    return 0;
}
