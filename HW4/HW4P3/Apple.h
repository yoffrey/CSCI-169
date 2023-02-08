//
// Created by Jeffrey on 2/6/23.
//

#ifndef HW4P3_APPLE_H
#define HW4P3_APPLE_H

#include <iostream>
#include <ctime>
#include <string>

class Apple : public Fruit {
public:
    Apple() {
        name = "apple";
        std::cout << "Apple call" << std::endl;
    }
    Apple(std::time_t r) {
        name = "apple";
        ripe = r;
        std::cout << "Apple call" << std::endl;
    }
    void prepare() { std::cout << "Core the apple" << std::endl; }
};

#endif //HW4P3_APPLE_H
