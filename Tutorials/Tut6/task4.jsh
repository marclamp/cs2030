/open Main.java

int[] arr = new int[63]
arr[31] = 1
List<Integer> list = Arrays.stream(arr).boxed().toList()
UnaryOperator<List<Integer>> rule = Main.generateRule()
Main.gameOfLife(list, Main.generateRule(), 32).forEach(System.out::println)