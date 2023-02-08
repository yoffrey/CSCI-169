//
// Created by Jeffrey on 2/6/23.
//

#include <iostream>
#include <ctime>
#include <string>
#include "Food.h"
#include "Fruit.h"
#include "Orange.h"
#include "Apple.h"
#include "Vegetable.h"
#include "Tomato.h"

int main()
{
    std::time_t d = 2000000000;
    std:time_t e = 5000000000;
    Tomato tm(e, d, "tomate");

    tm.prepare();
    std::cout << tm.expireTime() << std::endl;
    tm.ripeTime();


    return 0;
}

