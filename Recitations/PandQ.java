class P {
    /*private final*/ int x;

    P(int x){
        this.x = x;
    }

    P foo(){
        P newP = new P(this.x + 1);
        return newP;
    }

    P bar(P p){
        p.x = p.x + 1;
        return p;
    }
}

class Q {
    P baz(P p){
        return new P(p.x + 1);
    }
}
