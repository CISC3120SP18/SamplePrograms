#ifndef __CAT_H__
#define __CAT_H__

#include <string>

namespace Edu {
    namespace Brooklyn {

        class Cat {
            private:
                std::string name;
            public: 
                Cat();
                Cat(std::string name);
                void tap(const Cat& other) const;
                std::string getName() const;
        };

    };
};

#endif
