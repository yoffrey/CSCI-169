//
// Created by Jeffrey on 2/6/23.
//

#ifndef HW4P3_TOMATO_H
#define HW4P3_TOMATO_H

#include "Vegetable.h"
#include "Fruit.h"

class Tomato : public Vegetable, public Fruit {
public:
    Tomato(std::time_t e, std::string n) : Vegetable(e, n){
        expiration = e;
        std::cout << "Tomato call" << std::endl;
    }
    Tomato(std::time_t e, std::time_t r, std::string n) : Vegetable(e, n), Fruit(r, n) {
        name = n;
        expiration = e;
        ripe = r;
        std::cout << "Tomato call" << std::endl;
    }
};

#endif //HW4P3_TOMATO_H
