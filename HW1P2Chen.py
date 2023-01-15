def report(xs):
	countMax, count, aptCount, peopleCount, i = 0, 0, 0, 0, 0
	while i < len(xs):
		if isinstance(xs[i], int):
			aptCount += 1
			i+=1
			while i < len(xs) and isinstance(xs[i], str):
				peopleCount += 1
				count += 1
				countMax = max(countMax, count)
				i+=1
			count = 0 
	return (peopleCount/aptCount, countMax)

print(report([100, "Jill Johnson", "Billy Ray Cyrus", 110, "Shweta Agarwal", 120, "Miguel Rosas", "Elena Rosas", "Mateo Rosas", 200, "Jason Chan", 210, "Rosalia Torres"]))