//
// Created by Jeffrey on 2/6/23.
//

#ifndef HW4P3_ORANGE_H
#define HW4P3_ORANGE_H

#include <iostream>
#include <ctime>
#include <string>

class Orange : public Fruit {
public:
    Orange() {
        name = "orange";
        std::cout << "Orange call " << std::endl;
    }
    Orange(std::time_t r) {
        name = "orange";
        ripe = r;
        std::cout << "Orange call " << std::endl;
    }
    void prepare() { std::cout << "Peel the orange" << std::endl; }
};

#endif //HW4P3_ORANGE_H
