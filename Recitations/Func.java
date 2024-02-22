abstract class Func<T,R> {
    abstract R apply(T x);

    <U> Func<U,R> compose(Func<U,T> f) {
        return new Func<U,R>() {
            R apply(U x) {
                return Func.this.apply(f.apply(x));
            }
        };
    }
}
