/open IfElse.java

IfElse<Integer, Boolean> div4 = IfElse.of(x -> x % 4 == 0, x -> true, x -> false)
IfElse<Integer, Boolean> not100 = IfElse.of(x -> x % 100 != 0, x -> true, x -> false)
IfElse<Integer, Boolean> div4Not100 = div4.mapIf(not100)

IfElse<Integer, Boolean> div400 = IfElse.of(x -> x % 400 == 0, x -> true, x -> false)
IfElse<Integer, Boolean> leap = div400.mapElse(div4Not100)

Function<Boolean, String> f = x -> x ? "Leap" : "Not leap"

Function<Integer, IfElse<Integer, Integer>> g = x -> IfElse.of(y -> x % 2 == 0, y -> y + 2, y -> y * 2)
IfElse.<String, Integer>of(x -> x.compareTo("cs2030") > 0, x -> x.length(), x -> 0).flatMap(g).apply("CS2030S")