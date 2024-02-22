/open ImList.java

ImList<Integer> list = new ImList<Integer>(List.of(1,2,3))
list.limit(-1)
list.limit(0)
list.limit(10)
list.limit(2)

ImList<String> list = new ImList<String>(List.of("one","two","three"))
Function<String,Integer> f = x -> x.length()
Function<Object,Integer> g = x -> x.hashCode()
list.map(f)
ImList<Number> newList = list.map(g)
newList.add(0.1)

ImList<String> list2 = new ImList<String>(List.of("one","two","three"))
Predicate<String> pred = x -> x.length() == 3
list2.filter(x -> x.length() == 3)
Predicate<Object> pred = x -> x.hashCode() < 1_000_000
list2.filter(pred)
list.map(f).filter(x -> x == 3)

Consumer<String> consumer = x -> System.out.println("[" + x + "]")
ImList<String> list = new ImList<String>(List.of("one","two","three"))
list.forEach(x -> System.out.print(x + " "))
list.forEach(consumer)

BiFunction<String,Integer,Integer> bif = (x,y) -> x.length() + y
list.reduce(1, (x,y) -> x * y.length())
list.map(x -> x.length()).reduce(1, (x,y) -> x * y)
BiFunction<Object,Object,Integer> bif = (x,y) -> x.hashCode() + y.hashCode()
Number n = list.reduce(1, bif)

Function<String, ImList<String>> f = x -> new ImList<String>(List.<String>of("+","-","X")).map(y -> x + y)
new ImList<String>(List.<String>of("A", "P")).flatMap(f)

ImList<String> strings = new ImList<String>(List.of("one", "two", "three"))
Function<Object, ImList<Integer>> f1 = x -> new ImList<Integer>(List.of(1,2,3))
ImList<Number> list1 = strings.flatMap(f1)
class MyImList<T> extends ImList<T> {
     MyImList(List<? extends T> list) {
         super(list);
     }
}
Function<Object, MyImList<Integer>> f2 = x -> new MyImList<Integer>(List.of(1,2,3))
ImList<Number> list2 = strings.flatMap(f2)

new ImList<String>(List.<String>of("A", "P")).map(f)