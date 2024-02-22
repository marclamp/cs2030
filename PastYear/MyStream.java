import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;

abstract class MyStream<T> {
    static <T> MyStream<T> generate(Supplier<T> seed) {
        return new MyStream<T>() {
            T get() {
                return seed.get();
            }
        };
    }

    abstract T get();

    void forEach(Consumer <? super T> action, int n) {
        for (int i = 0; i < n ; i++) {
            action.accept(this.get());
        }
    }

    <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
        return new MyStream<R>() {
            R get() {
                return mapper.apply(MyStream.this.get());
            }
        };
    }
}
