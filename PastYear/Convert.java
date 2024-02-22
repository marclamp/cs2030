import java.util.Arrays;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Convert {
    // 3a
    static int[] convert(int[] array, Function<Integer, Integer> fn) {
        return Arrays.stream(array).map(x -> fn.apply(x)).toArray();
    }

    // 3b
    static int[] convert2(int[] array, Function<int[], int[]> fn) {
        return fn.apply(array);
    }

    static Function<int[], int[]> f = arr -> IntStream.iterate(0, x -> x < arr.length, x -> x + 2)
    .map(y -> arr[y] * 2).toArray();

    int[] arr = {1, 2, 3, 4, 5};
}

class Conway {

    static UnaryOperator<int[]> generateRule() {
        return arr -> {
            return IntStream.range(0, arr.length)
                .map(y -> arr[y] == 1 ? 0 :
                ((((y == 0) ? 0 : arr[y - 1]) + ((y == arr.length - 1) ? 0 : arr[y + 1]))) % 2)
            .toArray();
        };
    }

    static void conway(int[] arr, 
        UnaryOperator<int[]> rule, 
        int n) {
        Stream.iterate(arr, rule)
            .limit(n)
            .map(x -> Arrays.stream(x)
            .boxed()
            .map(y -> y == 1 ? "*" : " ").reduce("", (a,b) -> a + b))
            .forEach(System.out :: println);;
    }


}