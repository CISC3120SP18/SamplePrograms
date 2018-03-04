#include <iostream>
#include "house.h"

namespace Edu {
    namespace Brooklyn {

        House::House(std::string name) {
            this->houseName = name;
        }

        void House::dispCat() const {
            std::cout << "This is " << cat.getName() << "'s house" << std::endl;
        }
    }
}
