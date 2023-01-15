INPUT_SIZE = 10
def printFunction(inputArr):
	for i in range(len(inputArr)):
		print(str(inputArr[i]) + " ")
	print("\n")

def partition(inputArr, p ,r):
	pivot = inputArr[r]
	while(p<r):
		while(inputArr[p]<pivot):
			p+=1
		while(inputArr[r]>pivot):
			r-=1
		if(inputArr[p]==inputArr[r]):
			p+=1
		elif(p<r):
			tmp = inputArr[p]
			inputArr[p] = inputArr[r]
			inputArr[r] = tmp
	return r

def quicksort(inputArr, p, r):
	if (p<r):
		j = partition(inputArr, p , r)
		quicksort(inputArr, p, j-1)
		quicksort(inputArr, j+1, r)

inputArr = [500, 700, 800, 100, 300, 200, 900, 400, 1000, 600]
print("Input: ")
printFunction(inputArr)
quicksort(inputArr, 0, 9)
print("Output: ")
printFunction(inputArr)

