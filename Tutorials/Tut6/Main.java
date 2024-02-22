import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {

    static boolean isPrime(int n) {
        return n > 1 && IntStream
                .range(2, n)
                .noneMatch(x -> n % x == 0);
    }

    static boolean isTwinPrime(int n) {
        return (isPrime(n) && isPrime(n + 2)) || (isPrime(n) && isPrime(n - 2));
    }

    static IntStream primes(int n) {    
        return IntStream.range(1, n).filter(x -> isPrime(x));
    }   

    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(1, n).filter(x -> isTwinPrime(x));
    }

    static String reverse(String str) {
        return Stream.<String>of(str.split("")).reduce("", (x,y) -> y + x);
    }

    static boolean isRepeat(List<Integer> list, int index) {
        if (list.size() == 2) {
            return list.get(0) == list.get(1);
        }

        int curr = list.get(index);
        if (index == 0) {
            int next = list.get(index + 1);
            int next2 = list.get(index + 2);
            return (curr == next && curr != next2);
        } else if (index == list.size() - 1) {
            int prev = list.get(index - 1);
            int prev2 = list.get(index - 2);
            return (curr == prev && curr != prev2);
        } else {
            int prev = list.get(index - 1);
            int next = list.get(index + 1);
            return (curr == prev && curr != next);
        }
    }

    static long countRepeats(List<Integer> list) {
        if (list.size() > 1 && list.stream().distinct().count() == 1) {
            return 1;
        }

        return IntStream.range(1, list.size())
            .filter(x -> isRepeat(list, x)).map(x -> list.get(x)).count();
    }

    static boolean onlyOneNeighbour(List<Integer> list, int index) {
        if (index == 0) {
            return list.get(index + 1) == 1;
        } else if (index == list.size() - 1) {
            return list.get(index - 1) == 1;
        } else {
            return (list.get(index - 1) == 1 && list.get(index + 1) != 1) ||
                (list.get(index - 1) != 1 && list.get(index + 1) == 1);
        }
    }

    static UnaryOperator<List<Integer>> generateRule() {
        return x -> {
            return IntStream.range(0, x.size()).map(y -> {
                if (x.get(y) == 1) {
                    return 0;
                } else {
                    if (onlyOneNeighbour(x, y)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }).boxed().toList();
        };
    }

    static Stream<String> gameOfLife(List<Integer> list, 
        UnaryOperator<List<Integer>> rule, int n) {
        return Stream.iterate(list, rule).limit(n).map(x -> 
            x.stream().map(y -> y == 1 ? "x" : ".").reduce("", (a,b) -> a + b));
    }
}
