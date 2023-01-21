def report(xs):
    count_max, count, apt_count, people_count, i = 0, 0, 0, 0, 0
    while i < len(xs):
        if isinstance(xs[i], int):
            apt_count += 1
            i += 1
            while i < len(xs) and isinstance(xs[i], str):
                people_count += 1
                count += 1
                count_max = max(count_max, count)
                i += 1
            count = 0
    return people_count / apt_count, count_max


print(report(
    [100, "Jill Johnson", "Billy Ray Cyrus", 110, "Shweta Agarwal", 120, "Miguel Rosas", "Elena Rosas", "Mateo Rosas",
     200, "Jason Chan", 210, "Rosalia Torres"]))
