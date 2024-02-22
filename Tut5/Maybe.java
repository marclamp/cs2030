import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Maybe<T> {
    private final T thing;

    protected Maybe(T thing) {
        this.thing = thing;
    }

    static <U> Maybe<U> of(U thing) {
        if (thing == null) {
            throw new NullPointerException();
        }
        return new Maybe<U>(thing);
    }

    static <U> Maybe<U> ofNullable(U thing) {
        if (thing == null) {
            return Maybe.<U>empty();
        } else {
            return Maybe.<U>of(thing);
        }
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.thing == null) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.thing));
        }
    }

    <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
        if (this.thing == null) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.thing).orElse(null));
        }
    }

    Maybe<T> filter(Predicate<? super T> pred) {
        if (this.thing == null) {
            return Maybe.<T>empty();
        }
        if (pred.test(this.thing)) {
            return this;
        } else {
            return Maybe.<T>empty();
        }
    }

    void ifPresent(Consumer<? super T> action) {
        if (this.thing != null) {
            action.accept(this.thing);
        }
        return;
    }

    void ifPresentOrElse(Consumer<? super T> action, Runnable runnable) {
        if (this.thing != null) {
            action.accept(this.thing);
        } else {
            runnable.run();
        }
        return;
    }

    T orElse(T value) {
        if (this.thing != null) {
            return this.thing;
        } else {
            return value;
        }
    }

    T orElseGet(Supplier<? extends T> supp) {
        if (this.thing != null) {
            return this.thing;
        } else {
            return supp.get();
        }
    }

    Maybe<T> or(Supplier<? extends Maybe<? extends T>> suppMaybe) {
        if (this.thing != null) {
            return this;
        } else {
            Maybe<? extends T> value = suppMaybe.get();
            return Maybe.<T>of(value.orElse(null));
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Maybe<?> m) {
            if (this.thing == null || m.thing == null) {
                return this.thing == null && m.thing == null;
            } else {
                return this.thing.equals(m.thing);
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (this.thing == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.thing + "]";
        }
    }
}
