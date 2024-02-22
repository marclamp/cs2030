/open Log.java

Log.of(5)
Log.of(5, "five")

Log.of(5, "five").map(x -> x + 1)

// Monad Identity Law
Log.of(5).map(x -> x).equals(Log.of(5)) // true

// Monad Associative Law
Function<Integer, Integer> f = x -> x + 1
Function<Integer, Integer> g = x -> x * 2
Log.of(5,"five").map(f).map(g).equals(Log.of(5,"five").map(g.compose(f))) //true

Function<Integer, Log<Integer>> f = x -> Log.of(x + 1, "add 1")
Function<Integer, Log<Integer>> g = x -> Log.of(x, "mul 2").map(y -> y * 2)
Log.of(5, "five").flatMap(f)
Log.of(5, "five").flatMap(f).flatMap(g)

// Right Identity
Log.of(5,"five").flatMap(x -> Log.of(x)).equals(Log.of(5,"five")) // true

// Left Identity
Function<Integer, Log<Integer>> f = x -> Log.of(x + 1, "add 1")
Function<Integer,Boolean> idleft = x -> Log.of(x).flatMap(f).equals(f.apply(x))
idleft.apply(5) // true

Log<Integer> sum(int n) {
    if (n == 0) {
        return Log.of(n, "hit base case\n");
    } else {
        return sum(n - 1).flatMap(x -> Log.of(n + x, "adding " + n + "\n"));
    }
}

sum(5)

Log<Integer> f(int n) {
    if (n == 1) {
        return Log.of(1, "1\n");
    } else if (n % 2 == 0) {
        return Log.of((n / 2),  n + " / 2\n").flatMap(x -> f(x));
    } else {
        return Log.of((3 * n + 1), "3(" + n + ") + 1\n").flatMap(x -> f(x));
    }
}

f(11)