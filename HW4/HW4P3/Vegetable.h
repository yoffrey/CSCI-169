//
// Created by Jeffrey on 2/6/23.
//

#ifndef HW4P3_VEGETABLE_H
#define HW4P3_VEGETABLE_H
#include <iostream>
#include <ctime>
#include <string>
#include <algorithm>

class Vegetable : virtual public Food {
public:
    std::time_t expiration;
    Vegetable() {
        name = "Vegetable";
        expiration = 0;
        std::cout << "Veg call"<< std::endl;
    }

    Vegetable(std::time_t e, std::string n) : Food(n) {
        expiration = e;
        name = n;
        std::cout << "Veg call" << std::endl;
    }

    std::time_t expireTime() {
        return expiration;
    }
};

#endif //HW4P3_VEGETABLE_H

