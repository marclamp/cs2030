import java.util.List;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

abstract class Eight {
    
    static boolean isPrime(int n) {
        return n > 1 && IntStream
            .range(2, n)
            .noneMatch(x -> n%x == 0);
    }

    // static boolean isFactor(int factor, int n) {
    //     return n % factor == 0;
    // }

    // static boolean isPrimeFactor(int factor, int n) {
    //     return isPrime(factor) && isFactor(factor, n);
    // }

    // static IntStream primes(int n) {
    //     return IntStream.rangeClosed(1, n).filter(x -> isPrime(x));
    // }

    static IntStream factors(int n) {
        return IntStream.rangeClosed(1, n).filter(x -> n % x == 0);
    }

    static IntStream primeFactors(int n) {
        return factors(n).filter(x  -> isPrime(x));
    }

    static LongStream omega(int n) {
        return IntStream.rangeClosed(1, n).mapToLong(x -> primeFactors(x).count());
    }

    static Stream<Integer> fibSeq(int n) {
        Pair<Integer, Integer> seed = new Pair<Integer, Integer>(0, 1);
        UnaryOperator<Pair<Integer, Integer>> nextPair = x -> new Pair<Integer, Integer>(x.second(), x.first() + x.second());

        return Stream.iterate(seed, nextPair).limit(n).map(x -> x.second());
    }

    static <T,U,R> Stream<R> product(List<? extends T> list1, List<? extends U> list2, BiFunction<? super T, ? super U, ? extends R> func) {
        return list1.stream().flatMap(x -> list2.stream().map(y -> func.apply(x, y)));
    }

    
}