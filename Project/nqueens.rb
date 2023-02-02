def check_attack(i, j, arr)
  (0...arr.length).each { |k|
    # check left/right
    if arr[i][k] == 1
      return true
    end
    # check up/down
    if arr[k][j] == 1
      return true
    end
  }
  # check diagonals
  (0...arr.length).each { |k|
    (0...arr.length).each { |m|
      if (k + m == i + j) || (k - m == i - j)
        if arr[k][m] == 1
          return true
        end
      end
    }
  }
  false
end

def n_queen(n, i, arr)
  # If you reach n==0 then all queens are in place
  if n == 0
    (0...arr.length).each { |i|
      p arr[i]
    }
    return true
  end
  (i...arr.length).each { |i|
    (0...arr.length).each { |j|
      # Check if queen's attack and if there is already queen in that spot
      if (!check_attack(i, j, arr)) && (arr[i][j] != 1)
        # If spot is safe, set that spot to 1
        arr[i][j] = 1
        # Recurse on the rest of the board
        if n_queen(n - 1, i + 1, arr)
          return true
        end
        # If the function above returns false, then set the spot to 0 and try again
        arr[i][j] = 0
      end
    }
  }
  false
end

# first arg is the size of the board, second arg is the row of the board starting from the top
arr = Array.new(8) { Array.new(8,0) }
n_queen(arr.length, 0, arr)
