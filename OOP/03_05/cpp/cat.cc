#include <iostream>
#include "cat.h"

namespace Edu {
    namespace Brooklyn {

        Cat::Cat() {
            this->name = "cat who does not yet to have a name";
        }

        Cat::Cat(std::string name) {
            this->name = name;
        }

        void Cat::tap(const Cat& other) const {
            std::cout << this->name << " is tapping " << other.name << std::endl;
        }

        std::string Cat::getName() const {
            return name;
        }
        
    }
}
