#include "numberutil.h"

namespace Edu {
    namespace Brooklyn {

        int NumberUtil::sumToNumber(int number) const {
            int sum = 0;
            for (int i=0; i<=number; i++) {
                sum += i;
            }
            return sum;
        }

    }

}

