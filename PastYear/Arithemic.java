import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

class Pair<T, U> {
    private final T t;
    private final U u;

    Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    T first() {
        return this.t;
    }

    U second() {
        return this.u;
    }

    @Override
    public String toString() {
        return "[" + this.first() + "," + this.second() + "]";
    }
}

class Stack<T> {
    private final List<T> list;

    Stack() {
        this.list = new ArrayList<T>();
    }

    private Stack(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }

    Stack<T> push(T elem) {
        Stack<T> newStack = new Stack<T>(this.list);
        newStack.list.add(0, elem);
        return newStack;
    }

    Pair<Optional<T>, Stack<T>> pop() {
        Stack<T> newStack = new Stack<T>(this.list);
        Optional<T> value = !this.isEmpty() ? Optional.<T>of(newStack.list.remove(0)) : Optional.<T>empty();
        return new Pair<Optional<T>, Stack<T>>(value, newStack);
    }

    boolean isEmpty() {
        return this.list.isEmpty();
    }

    static int evaluate(String expr) {
        Scanner sc = new Scanner(expr);
        Stack<CompletableFuture<Integer>> stack = new Stack<CompletableFuture<Integer>>();
        Pair<Optional<CompletableFuture<Integer>>, Stack<CompletableFuture<Integer>>> pair = 
            new Pair<Optional<CompletableFuture<Integer>>, Stack<CompletableFuture<Integer>>>(
                Optional.<CompletableFuture<Integer>>empty(), 
                new Stack<CompletableFuture<Integer>>());
        
        while (sc.hasNext()) {
            String term = sc.next();
            //System.out.println(stack);
            
            if (term.equals("+") || term.equals("*")) {
                pair = stack.pop();
                Optional<CompletableFuture<Integer>> a = pair.first();
                stack = pair.second();
                pair = stack.pop();
                Optional<CompletableFuture<Integer>> b = pair.first();
                stack = pair.second();

                Optional<CompletableFuture<Integer>> value = 
                    a.flatMap(x -> b.map(y -> term.equals("+") 
                        ? CompletableFuture.supplyAsync(() -> x.thenCombine(y, (i, j) -> add(i, j)).join())
                        : CompletableFuture.supplyAsync(() -> x.thenCombine(y, (i, j) -> mul(i ,j)).join())));
                stack = stack.push(value.orElseThrow());
            } else {
                Integer value = Integer.parseInt(term);
                stack = stack.push(CompletableFuture.completedFuture(value));
            }
        }
        sc.close();
        return stack.pop().first().orElseThrow().join();
    }

    static void sleep(int t) {
        try {
            Thread.currentThread();
            Thread.sleep(t * 1000);
        } catch (InterruptedException e) {}
    }

    static int add(int x, int y) {
        System.out.println(x + " + " + y);
        sleep(x + y);
        return x + y;
    }

    static int mul(int x, int y) {
        System.out.println(x + " * " + y);
        sleep(x + y);
        return x * y;
    }

    @Override
    public String toString() {
        return "Top -> " + this.list;
    }
}
