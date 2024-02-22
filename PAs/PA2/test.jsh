/open openall.jsh

Expr<Integer> expr = Expr.<Integer>of(2)
Operator<Integer> add = Operator.<Integer>of((x, y) -> {
    System.out.println("#");
    return x + y;
}, 4)

Operator<Integer> mul = Operator.<Integer>of((x, y) -> {
    System.out.println("#");
    return x * y;
}, 3)

Operator<Integer> sub = Operator.<Integer>of((x, y) -> {
    System.out.println("#");
    return x - y;
}, 4)

Operator<Integer> div = Operator.<Integer>of((x, y) -> {
    System.out.println("#");
    return x / y;
}, 3)

Operator<Integer> exp = Operator.<Integer>of((x, y) -> {
    System.out.println("#");
    return (int) Math.pow(x, y);
}, 2)

expr.op(exp, 2).op(add, 3).op(mul, 3).op(sub, 4).op(div, 2) // 2 ^ 2 + 3 * 3 - 4 / 2 == 4 + 9 - 2 == 11