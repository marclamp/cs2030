import java.util.function.Predicate

<T> Predicate<T> and(Predicate<T> p1, Predicate<T> p2) {
    return new Predicate<T>() {
        public boolean test(T t) {
            return p1.test(t) && p2.test(t);
        }
    }    
    // or return x -> p1.test(x) && p2.test(x);
}