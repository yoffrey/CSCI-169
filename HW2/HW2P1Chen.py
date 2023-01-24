def scope_test():
    def do_local():
        spam = "local spam"
    def do_nonlocal():
        nonlocal spam
        spam = "nonlocal spam"
    def do_global():
        global spam
        spam = "global spam"
    # When the function scope_test() is executed, we first assign the local variable spam to "test spam"
    spam = "test spam"

    # When the function do_local is executed, the local variable spam is set to "local spam"
    # If we were to print(spam) in side do_local, local spam would be printed.
    do_local()

    # After changing local assignment, the local spam variable is still equal to "test spam"
    print("After local assignment:", spam)

    # When the function do_nonlocal() is executed, there is a line that says "nonlocal spam"
    # This means that within the function do_nonlocal(), if spam were to be used, use the nonlocal variable
    # The nonlocal variable spam is then set to "nonlocal spam"
    do_nonlocal()

    # After the function is complete, the variable spam holds the string to "nonlocal spam".
    print("After nonlocal assignment:", spam)

    # When the function do_global is executed, it tells the function to use the global verison of spam
    # Since there is no global variable spam yet, it is created
    # The nonlocal spam does not change
    do_global()

    # This will still print "nonlocal spam" since it was not changed
    print("After global assignment:", spam)

scope_test()

# Since the global spam was created, spam equals "global spam"
print("In global scope:", spam)
