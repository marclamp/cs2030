import java.util.function.Function;
import java.util.function.Predicate;

class IfElse<T, U> {
    public final Predicate<T> pred;
    public final Function<T, U> left;
    public final Function<T, U> right;

    IfElse(Predicate<T> pred, 
        Function<T, U> left, 
        Function<T, U> right) {
        this.pred = pred;
        this.left = left;
        this.right = right;
    }

    static boolean isLeap(int year) {
        if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    static <T, U> IfElse<T, U> of(Predicate<T> pred, Function<T, U> left, Function<T, U> right) {
        return new IfElse<>(pred, left, right);
    }

    U apply(T t) {
        return pred.test(t) ? left.apply(t) : right.apply(t);
    }

    IfElse<T, U> mapIf(IfElse<T, U> other) {
        Predicate<T> newPred = this.pred.and(other.pred);
        return of(newPred, other.left, 
        x -> of(this.pred, other.right, this.right).apply(x));
    }

    IfElse<T, U> mapElse(IfElse<T, U> other) {
        Predicate<T> newPred = this.pred.or(other.pred);
        return of(newPred,
        x -> of(this.pred, this.left, other.right).apply(x),
        other.right);
    }

    <R> IfElse<T, R> map(Function<? super U, ? extends R> func) {
        Function<T, R> newLeft = this.left.andThen(func);
        Function<T, R> newRight = this.right.andThen(func);
    
        return of(this.pred, newLeft, newRight);
    }

    <R> IfElse<T, R> flatMap(Function<? super U, ? extends IfElse<U, ? extends R>> ifelse) {
        Function<T, R> newLeft = 
            x -> ifelse.apply(this.left.apply(x)).apply(this.left.apply(x));
        Function<T, R> newRight = 
            x -> ifelse.apply(this.right.apply(x)).apply(this.right.apply(x));
        
        return of(this.pred, newLeft, newRight);
    }

    <R> IfElse<T, R> flatMapCorrect(Function<? super U, ? extends IfElse<U, ? extends R>> ifelse) {
        Function<T, R> f = 
            x -> ifelse.apply(this.apply(x)).apply(this.apply(x));
        
        return of(this.pred, f, f);
    }
}
