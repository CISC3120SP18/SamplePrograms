#include "cat.h"

/**
 * automatic storage:
 *  the context where the Cat objects are determines that they are allocated in
 *  the stack.
 */
int main(int argc, char* argv[]) {
    /* similar to Java, you may use fully qualified class name without
     * importing the class from the namespace */
    Edu::Brooklyn::Cat ginger("ginger"), tuxedo("tuxedo");
    // sometimes use "using namespace" may be more convenient
    /*
    using namespace Edu::Brooklyn;
    Cat ginger("ginger"), tuxedo("tuxedo");
    */
    ginger.tap(tuxedo);
    return 0;
}
