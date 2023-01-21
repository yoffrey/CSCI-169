import numpy as np

# Create an empty board
arr = np.zeros((8, 8), dtype=int)
currentRow = len(arr) - 1


def check_attack(i, j):
    for k in range(len(arr)):
        # check left/right
        if arr[i][k] == 1:
            return True
        # check up/down
        if arr[k][j] == 1:
            return True
    # check diagonals
    for k in range(len(arr)):
        for l in range(len(arr)):
            if (k + l == i + j) or (k - l == i - j):
                if arr[k][l] == 1:
                    return True
    return False


def n_queen(n, i):
    # If you reach n==0 then all queens are in place
    if n == 0:
        print(arr)
        return True
    for i in range(i, len(arr)):
        for j in range(0, len(arr)):
            # Check if queen's attack and if there is already queen in that spot
            if (check_attack(i, j) is False) and (arr[i][j] != 1):
                # If spot is safe, set that spot to 1
                arr[i][j] = 1
                # Recurse on the rest of the board
                if n_queen(n - 1, i + 1):
                    return True
                # If the function above returns False, then set the spot to 0 and try again
                arr[i][j] = 0
    return False


n_queen(len(arr), 0)
