#ifndef __HOUSE_H__
#define __HOUSE_H__

#include <string>
#include "cat.h"

namespace Edu {
    namespace Brooklyn {

        class House {
            private:
                std::string houseName;
                Cat cat;
            public: 
                House(std::string name);
                void dispCat() const;
        };

    };
};

#endif
