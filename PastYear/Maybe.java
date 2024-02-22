import java.util.function.Function;

abstract class Maybe<T> {
    static <T> Maybe<T> of(T t) {
        return new Maybe<T>() {
            private final T thing = t;
        
            <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                return Maybe.<R>of(mapper.apply(this.thing));
            }
        
            @Override
            public String toString() {
                return "Maybe[" + thing + "]";
            }
        };
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>() {
            <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                return Maybe.<R>empty();
            }
        
            @Override
            public String toString() {
                return "Maybe.empty";
            }
        };
    }

    abstract <R> Maybe<R> map(Function<? super T, ? extends R> mapper);
}

class MaybeValid<T> extends Maybe<T> {
    private final T thing;

    protected MaybeValid(T thing) {
        this.thing = thing;
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        return Maybe.<R>of(mapper.apply(this.thing));
    }

    @Override
    public String toString() {
        return "Maybe[" + thing + "]";
    }
}

class MaybeInvalid<T> extends Maybe<T> {

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        return Maybe.<R>empty();
    }

    @Override
    public String toString() {
        return "Maybe.empty";
    }
}
