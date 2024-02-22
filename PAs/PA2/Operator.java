import java.util.function.BinaryOperator;

class Operator<T> implements Comparable<Operator<T>> {
    private final BinaryOperator<T> binOp;
    private final int precedence;
    private static final int LOWEST_PRECENDENCE = 10;

    private Operator() {
        this.binOp = (x,y) -> x;
        this.precedence = LOWEST_PRECENDENCE;
    }
    
    private Operator(BinaryOperator<T> binOp, int precedence) {
        this.binOp = binOp;
        this.precedence = precedence;
    }

    static <T> Operator<T> of(BinaryOperator<T> binOp, int precedence) {
        return new Operator<T>(binOp, precedence);
    }

    static <T> Operator<T> empty() {
        return new Operator<T>();
    }

    T apply(T t1, T t2) {
        return this.binOp.apply(t1, t2);
    }

    @Override
    public int compareTo(Operator<T> op) {
        return Integer.compare(this.precedence, op.precedence);
    }

    @Override
    public String toString() {
        return "Operator of precedence " + this.precedence;
    }
}
