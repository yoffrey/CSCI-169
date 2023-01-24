def foo(x):
    x[0]=55
    x=11
    return x
def bar(xs):
    xs=[1, 2, 3]
    xs[0]="squeegee"
    return xs
ys=[[1, 2, 3], 2, 3]

# Since lists are mutable, x[0]=55 in foo(ys) will change ys to [55,2,3]
# Since the next lines sets x to 11 and returns x, print(foo(ys)) will print 11
print(foo(ys))

# print(ys) will print [55,2,3] for the reason explained above.
print(ys)

# When bar(ys) is called, xs is mapped to a new list [1,2,3]. Since ys is a global variable.
# xs[0] is then set to "squeegee"
# So this print will print out ["squeegee", 2, 3]
# ys is untouched
print(bar(ys))

# This will still print out [55,2,3] for the reason explained above
print(ys)
zs=range(0, 10)

# Since tuples are immutable, this will return an error.
# If we comment out this line, the program will continue to run.
print(foo(zs))

# This will print range(0,10) since nothing has changed
print(zs)

# Since zs is a global variable, xs=[1,2,3] will not change za and will create a new local variable xs
# This will print ["squeegee",2,3]
print(bar(zs))

# This will print range(0,10) since nothing has changed
print(zs)

