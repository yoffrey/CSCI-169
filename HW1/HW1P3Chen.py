def print_function(input_arr):
    for i in range(len(input_arr)):
        print(str(input_arr[i]))
    print("\n")


def partition(input_arr, p, r):
    pivot = input_arr[r]
    while p < r:
        while input_arr[p] < pivot:
            p += 1
        while input_arr[r] > pivot:
            r -= 1
        if input_arr[p] == input_arr[r]:
            p += 1
        elif p < r:
            tmp = input_arr[p]
            input_arr[p] = input_arr[r]
            input_arr[r] = tmp
    return r


def quicksort(input_arr, p, r):
    if p < r:
        j = partition(input_arr, p, r)
        quicksort(input_arr, p, j - 1)
        quicksort(input_arr, j + 1, r)


inputArr = [500, 700, 800, 100, 300, 200, 900, 400, 1000, 600]
print("Input: ")
print_function(inputArr)
quicksort(inputArr, 0, 9)
print("Output: ")
print_function(inputArr)
