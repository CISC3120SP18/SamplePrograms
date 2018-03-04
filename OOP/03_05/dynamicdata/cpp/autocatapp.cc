#include "cat.h"

/**
 * automatic storage:
 *  the context where the Cat objects indicates  they are allocated in the
 *  stack.
 */
int main(int argc, char* argv[]) {
    Edu::Brooklyn::Cat ginger("ginger"), tuxedo("tuxedo");
    // sometimes use "using namespace" may be more convenient
    /*
    using namespace Edu::Brooklyn;
    Cat ginger("ginger"), tuxedo("tuxedo");
    */
    ginger.tap(tuxedo);
    return 0;
}
