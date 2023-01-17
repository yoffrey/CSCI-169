def binarySearch(a, n, t)
	l = 0
	r = n-1
	while l<=r
		m = ((l+r)/2).floor()
		if a[m]<t
			l+=1
		elsif a[m]>t
			r-=1
		else
			return m
		end
	end
end

a=[ 0, 2, 4, 6, 8, 10, 12, 14, 16 ]
n=a.length()
t=10

# should print 5
puts(binarySearch(a,n,t))
