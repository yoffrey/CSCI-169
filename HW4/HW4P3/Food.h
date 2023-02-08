//
// Created by Jeffrey on 2/6/23.
//

#ifndef HW4P3_FOOD_H
#define HW4P3_FOOD_H


class Food {
    public:
        std::string name;
        Food() {
            name = "";
            std::cout << "Food call" << std::endl;
        }
        Food(std::string n) {
            std::cout << n;
            name = n;
            std::cout << "Food call" << std::endl;
        }
        void prepare() { std::cout << "Prepare the " << name << std::endl; }
};


#endif //HW4P3_FOOD_H
