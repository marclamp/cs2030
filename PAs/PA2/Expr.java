import java.util.Optional;
import java.util.function.Supplier;

class Expr<T> {
    private final Supplier<T> supp;
    private final Optional<Operator<T>> optional;
    private final Supplier<Optional<Expr<T>>> t2;

    protected Expr(T t) {
        this.supp = () -> t;
        this.optional = Optional.<Operator<T>>empty();
        this.t2 = () -> Optional.<Expr<T>>empty();
    }

    protected Expr(Supplier<T> t, Supplier<Optional<Expr<T>>> t2, Optional<Operator<T>> optional) {
        this.supp = t;
        this.t2 = t2;
        this.optional = optional;
    }

    protected Expr(Expr<T> expr) {
        this.supp = expr.supp;
        this.t2 = expr.t2;
        this.optional = expr.optional;
    }
    
    static <T> Expr<T> of(T t) {
        return new Expr<T>(t);
    }

    Expr<T> op(Operator<T> oper, T t) {
        return op(oper, Expr.<T>of(t));
    }
    
    Expr<T> op(Operator<T> oper, Expr<T> t) {
        return op(oper, 
            () -> Optional.<Expr<T>>of(Expr.<T>of(t.evaluate())));
    }
    
    Expr<T> op(Operator<T> oper, Supplier<Optional<Expr<T>>> t) {
        return this.optional.map(x -> {
            if (x.compareTo(oper) > 0) { // oper has precedence over this
                return new Expr<T>(
                    this.supp, 
                    () -> this.t2.get().map(y -> y.op(oper, t)), 
                    this.optional);
            } else { // this has equal or lower precedence than oper (lower means go first)
                return new Expr<T>(
                    () -> this.evaluate(), 
                    t, 
                    Optional.<Operator<T>>of(oper));
            } 
        }).orElse(new Expr<T>(//returns itself
            this.supp, 
            t, 
            Optional.<Operator<T>>of(oper)));
    }

    T evaluate() {
        return this.optional.map(
            x -> x.apply(this.supp.get(), this.t2.get().map(y -> y.evaluate()).orElseThrow()))
            .orElseGet(this.supp);
    }

    @Override
    public String toString() {
        return "(" + this.evaluate() + ")";
    }
}   
