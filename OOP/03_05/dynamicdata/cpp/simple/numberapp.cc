#include <iostream>
#include "numberutil.h"

using Edu::Brooklyn::NumberUtil;
using std::cout;
using std::endl;

int main(int argc, char* argv[]) {
    NumberUtil numberUtil;

    cout << "The sum from 0 to 5 is " << numberUtil.sumToNumber(5) << endl;

    return 0;
}
