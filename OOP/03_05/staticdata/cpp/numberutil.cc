#include "numberutil.h"

namespace Edu {
    namespace Brooklyn {

        int NumberUtil::sumToNumberWithAuto(int number) const {
            int sum = 0;
            for (int i=0; i<=number; i++) {
                sum += i;
            }
            return sum;
        }

        int NumberUtil::sumToNumberWithStatic(int number) const {
            static int sum = 0;
            for (int i=0; i<=number; i++) {
                sum += i;
            }
            return sum;
        }

    }

}

