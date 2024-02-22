/open func.java

Func f = new Func() {
    int apply(int x) {
        return 2 * x;
    }
}

Func g = new Func() {
    int apply(int x) {
        return 2 + x;
    }
}

f.apply(10)
g.apply(10)
f.compose(g).apply(10)

Func<String, Integer> d = new Func<String, Integer>() {
    @Override
    Integer apply(String s) {
        return s.length();
    }
}

Func<Integer, String> h = new Func<Integer, String>() {
    @Override
    String apply(Integer x) {
        return x + "";
    }
}

h.compose(d).apply("this") +
    h.compose(d).apply("is") +
    h.compose(d).apply("fun!!!")

