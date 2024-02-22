import java.util.Optional;

class Fraction extends AbstractNum<Frac> {

    private Fraction(Optional<Frac> opt) {
        super(opt);
    }
    
    static Fraction of(int a, int b) {
        Num x = Num.of(a);
        Num y = Num.of(b);
        if (x.isValid() && y.isValid() && !y.equals(zero())) {
            return new Fraction(Optional.<Frac>of(Frac.of(x, y)));
        } else {
            return invalid();
        }
    }

    static Fraction invalid() {
        return new Fraction(Optional.empty());
    }

    Fraction add(Fraction frac) {
        if (this.isValid() && frac.isValid()) {
            Optional<Num> a = this.opt.map(x -> x.first());
            Optional<Num> b = this.opt.map(x -> x.second());
            Optional<Num> c = frac.opt.map(x -> x.first());
            Optional<Num> d = frac.opt.map(x -> x.second());

            Optional<Num> ad = a.flatMap(x -> d.map(y -> y.mul(x)));
            Optional<Num> bc = b.flatMap(x -> c.map(y -> y.mul(x))); 
            Optional<Num> bd = b.flatMap(x -> d.map(y -> y.mul(x)));
            Optional<Num> adbc = ad.flatMap(x -> bc.map(y -> y.add(x)));

            Optional<Frac> value = adbc.flatMap(x -> bd.map(y -> Frac.of(x,y)));
            return new Fraction(value);
        }
        return invalid();
    }
    
    Fraction sub(Fraction frac) {
        if (this.isValid() && frac.isValid()) {
            Optional<Num> a = this.opt.map(x -> x.first());
            Optional<Num> b = this.opt.map(x -> x.second());
            Optional<Num> c = frac.opt.map(x -> x.first());
            Optional<Num> d = frac.opt.map(x -> x.second());

            Optional<Num> ad = a.flatMap(x -> d.map(y -> y.mul(x)));
            Optional<Num> bc = b.flatMap(x -> c.map(y -> y.mul(x))); 
            Optional<Num> bd = b.flatMap(x -> d.map(y -> y.mul(x)));
            Optional<Num> adbc = ad.flatMap(x -> bc.map(y -> x.sub(y)));

            if (!adbc.equals(Optional.<Num>of(Num.invalid()))) {
                Optional<Frac> value = adbc.flatMap(x -> bd.map(y -> Frac.of(x,y)));
                return new Fraction(value);
            } 
        }
        return invalid();
    }

    Fraction mul(Fraction frac) {
        if (this.isValid() && frac.isValid()) {
            Optional<Num> a = this.opt.map(x -> x.first());
            Optional<Num> b = this.opt.map(x -> x.second());
            Optional<Num> c = frac.opt.map(x -> x.first());
            Optional<Num> d = frac.opt.map(x -> x.second());

            Optional<Num> ac = a.flatMap(x -> c.map(y -> y.mul(x)));
            Optional<Num> bd = b.flatMap(x -> d.map(y -> y.mul(x))); 
            
            Optional<Frac> value = ac.flatMap(x -> bd.map(y -> Frac.of(x,y)));
            return new Fraction(value);
        }
        return invalid();
    }
}
