import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

class ImList<E> {
    private final ArrayList<E> elems;

    ImList() {
        this.elems = new ArrayList<E>();
    }

    ImList(List<E> list) {
        this.elems = new ArrayList<E>(list);
    }

    ImList<E> add(E elem) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.add(elem);
        return newList;
    }

    ImList<E> set(int index, E elem) {
        ImList<E> newList = new ImList<E>(this.elems);
        return newList;
    }

    ImList<E> remove(int index) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.remove(index);
        return newList;
    }

    ImList<E> addAll(List<? extends E> list) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.addAll(list);
        return newList;
    }

    ImList<E> sort(Comparator<? super E> cmp) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.sort(cmp);
        return newList;
    }

    E get(int index) {
        return this.elems.get(index);
    }

    int size() {
        return this.elems.size();
    }

    boolean isEmpty() {
        return this.elems.isEmpty();
    }

    @Override 
    public String toString() {
        return this.elems.toString();
    }
}
