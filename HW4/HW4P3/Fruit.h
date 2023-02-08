//
// Created by Jeffrey on 2/6/23.
//

#ifndef HW4P3_FRUIT_H
#define HW4P3_FRUIT_H

#include <iostream>
#include <ctime>
#include <string>
#include <algorithm>

class Fruit : virtual public Food {
public:
    std::time_t ripe;
    Fruit() {
        name = "Fruit";
        std::cout << "Fruit call" << std::endl;
    }
    Fruit(std::time_t r, std::string n) {
        name = n;
        ripe = r;
        std::cout << "Fruit call" << std::endl;
    }
    void ripeTime() {
        std::cout << "You can eat it on " << ctime(&ripe) << std::endl;
    }

    bool operator<(const Fruit& ft) const { return difftime(ripe, ft.ripe) < 0; }
};

#endif //HW4P3_FRUIT_H
