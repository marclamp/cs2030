// 3a
int[] convert(int[] array, Function<Integer, Integer> fn) {
    return Arrays.stream(array).map(x -> fn.apply(x)).toArray();
}

// 3b
int[] convert2(int[] array, Function<int[], int[]> fn) {
    return fn.apply(array);
}

Function<int[], int[]> f = arr -> IntStream.iterate(0, x -> x < arr.length, x -> x + 2) .map(y -> arr[y] * 2).toArray();

int[] arr = {1, 2, 3, 4, 5}