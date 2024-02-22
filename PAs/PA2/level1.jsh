/open openall.jsh

Operator<Integer> add = Operator.<Integer>of((x, y) -> x + y, 4)
Operator<Integer> mul = Operator.<Integer>of((x, y) -> x * y, 3)

add.apply(5, 6)
mul.apply(5, 6)
add instanceof Comparable
List.of(add, mul).stream().sorted().map(op -> op.apply(5, 6)).toList()