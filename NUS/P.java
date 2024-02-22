class P {
    private final int x;

    P(int x){
        this.x = x;
    }

    P foo(){
        P newP = new P(this.x + 1);
    }

    P bar(P p){
        p.x = p.x + 1;
    }
}

class Q {
    P baz(P p){
        return new P(p.x + 1);
    }
}
