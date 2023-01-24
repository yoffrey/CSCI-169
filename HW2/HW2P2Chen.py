x = 10 # global
c=10 # global

def spam ():
    c=14 # c is local to spam
    bbeans = 15 # bbeans is local to spam

    def inner ():
        c="funtimes!" # this c is local to inner
        def innerinner():
            global a # since there is no global a to access, this will create the global variable a
            a=54 # global a is set to 54
            nonlocal c # since there is a nonlocal c which is 14, it will use that value
            c=54 # changes the nonlocal c to 54
            nonlocal bbeans # since there is a nonlocal bbeans which is 15, it will use that value
            bbeans=68 # changes nonlocal bbeans to 68
        innerinner()
        print(c) # since innerinner() is called before this print, this will print 54
        return bbeans # this will return 68, since innerinner() is called before this return
    print(x) # prints the global x, 10, since there is no local x
    print(c) # prints the local c, 14, since there is a local c
    return inner() # this returns the value of bbeans 68

print(spam()) # This will print what spam returned, which is the value of bbeans, 68.
