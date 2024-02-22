/open openall.jsh

Num zero = Num.zero()
Num one = Num.of(1)
Num invalid = Num.of(-1)

zero.add(zero)
zero.add(one)
one.add(zero)
one.add(one)
one.add(invalid)
invalid.add(zero)
Num result = zero.add(one).add(zero).add(one)
result.add(result)
zero.add(invalid).add(zero).add(one)
