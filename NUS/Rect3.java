class A {
    int x;
        A(int x) {
        this.x = x;
    }

    @Override
    B method() {
    return new B(x);
    }
}

class B extends A {
    B(int x) {
        super(x);
    }

    A method() {
        return new A(x);
    }
}
