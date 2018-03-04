#include <iostream>
#include "numberutil.h"

using Edu::Brooklyn::NumberUtil;
using std::cout;
using std::endl;

int main(int argc, char* argv[]) {
    NumberUtil numberUtilOne;

    cout << "Automatic Data: The sum from 0 to 5 is " << numberUtilOne.sumToNumberWithAuto(5) << endl;
    cout << "Static Data: The sum from 0 to 5 is " << numberUtilOne.sumToNumberWithStatic(5) << endl;

    NumberUtil numberUtilTwo;

    cout << "Automatic Data: The sum from 0 to 5 is " << numberUtilTwo.sumToNumberWithAuto(5) << endl;
    cout << "Static Data: The sum from 0 to 5 is " << numberUtilTwo.sumToNumberWithStatic(5) << endl;

    return 0;
}
