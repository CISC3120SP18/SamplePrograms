#include <iostream>
#include "cat.h"

namespace Edu {
    namespace Brooklyn {

        using std::string;
        using std::cout;
        using std::endl;

        Cat::Cat() {
            this->name = "cat who does not yet to have a name";
        }

        Cat::Cat(string name) {
            this->name = name;
        }

        void Cat::tap(const Cat& other) const {
            cout << this->name << " is tapping " << other.name << endl;
        }

        string Cat::getName() const {
            return name;
        }
        
    }
}
