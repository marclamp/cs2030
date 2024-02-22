class IntExpr extends AbstractIntExpr<Integer> {
    private final int i;

    @Override
    public String toString() {
        return "(" + this.i + ")";
    }
}
