import java.util.function.Supplier;
import java.util.Optional;
import java.util.stream.Stream;

class Expr<T> {
    private final Supplier<T> supp;
    //private final Optional<T> optional; 
    private final Stream<Expr<T>> streamOfExpr;

    protected Expr(T t) {
        this.supp = () -> t;
        //this.optional = Optional.<T>of(t);
        this.streamOfExpr = Stream.<Expr<T>>of();
    }

    protected Expr(Supplier<T> supp) {
        this.supp = supp;
        //this.optional = Optional.<T>of(t);
        this.streamOfExpr = Stream.<Expr<T>>of();
    }
    
    protected Expr(Expr<T> expr) {
        this.supp = expr.supp;
        this.streamOfExpr = expr.streamOfOperators;
    }

    //protected Expr(Supplier<T> supp, Optional<T> optional, Stream<Operator<T>> stream) {
    //    this.supp = supp;
    //    this.optional = optional;
    //    this.streamOfExpr = stream;
    //}
    
    static <T> Expr<T> of(T t) {
        return new Expr<T>(t);
    }

    Expr<T> op(Operator<T> oper, T t) {
        return new Expr<T>(() -> oper.apply(this.supp.get(), t));
        
        //Stream<Operator<T>> newStream = 
        //    this.streamOfExpr.concat(Stream.<Operator<T>>of(oper));
    }   

    T evaluate() {
        this.streamOfExpr.sorted().reduce(this.supp.get(), (x, y) -> x.op(y));
    }

    @Override
    public String toString() {
        return "(" + this.evaluate() + ")";
    }
}   
