import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class ImList<T> {
    private final List<T> list;

    ImList() {
        this.list = new ArrayList<T>();
    }

    private ImList(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }

    ImList<T> add(T elem) {
        return update(x -> x.list.add(elem));
    }

    ImList<T> set(int index, T elem) {
        return update(x -> x.list.set(index, elem));
    }

    ImList<T> remove(int index) {
        return update(x -> x.list.remove(index));
    }

    ImList<T> update(Consumer<ImList<T>> consumer) {
        ImList<T> newList = new ImList<T>(this.list);
        consumer.accept(newList);
        return newList;
    }

    // other methods omitted for brevity
}
