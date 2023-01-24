l1 = [0, 1,2,3,4,5,6,7,8,9]
l2 = l1[1::3]
print(l2)

l3 = [str(i) for i in l1 if type(i)==int]
print(l3)