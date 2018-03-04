#include "cat.h"

// since c++17
using Edu::Brooklyn::Cat; 

/**
 * heap storage because of "new"
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
    Cat *ginger = new Cat("ginger"), 
        *tuxedo = new Cat("tuxedo");
    ginger->tap(*tuxedo);
    delete ginger;
    delete tuxedo;
    return 0;
}
