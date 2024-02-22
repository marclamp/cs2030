class IntExpr extends AbstractIntExpr {
    private static final Operator<Integer> subtraction = 
        Operator.<Integer>of((x, y) -> x - y, 4);
    private static final Operator<Integer> division = 
        Operator.<Integer>of((x, y) -> x / y, 3);
    private static final Operator<Integer> exponential = 
        Operator.<Integer>of((x, y) -> {
            int z = 1;
            for (int i = 0; i < y; i++) {
                z = z * x;
            }
            return z;
        }, 2);

    private IntExpr(Expr<Integer> expr) {
        super(expr);
    }

    static IntExpr of(int i) {
        return new IntExpr(new Expr<Integer>(i));
    }

    IntExpr add(int i) {
        return new IntExpr(op(addition, i));
    } 

    IntExpr sub(int i) {
        return new IntExpr(op(subtraction, i));
    }
    
    IntExpr mul(int i) {
        return new IntExpr(op(multiplication, i));
    } 

    IntExpr div(int i) {
        return new IntExpr(op(division, i));
    }

    IntExpr exp(int i) {
        return new IntExpr(op(exponential, i));
    } 
}
