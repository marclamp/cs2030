import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

class Num extends AbstractNum<Integer> {

    private Num(Integer i) {
        super(i);
    }

    private Num(Optional<Integer> opt) {
        super(opt);
    }

    private Num(AbstractNum<Integer> abs) {
        super(abs.opt);
    }

    static Num of(int i) {
        if (AbstractNum.valid.test(i)) {
            return new Num(i);
        } else {
            return invalid();
        }
    }

    static Num invalid() {
        return new Num(Optional.empty());
    }
    
    static Num zero() {
        return new Num(AbstractNum.zero());
    }

    static Num one() {
        return new Num(zero().map(AbstractNum.s));
    }

    Num succ() {
        if (isValid()) {
            return new Num(this.map(AbstractNum.s));
        } else {
            return this;
        }
    }

    Num add(Num n) {
        if (n.isValid() && this.isValid()) {
            Num count = zero();
            Num sum = this;
            while (!count.equals(n)) {
                sum = sum.succ();
                count = count.succ();
            }
            return sum;
        } else {
            return invalid();
        }
    }

    Num mul(Num n) {
        if (n.isValid() && this.isValid()) {
            if (n.equals(zero()) || this.equals(zero())) {
                return zero();
            }

            Num count = one();
            Num product = this;
            Num addition = this;
            while (!count.equals(n)) {
                product = product.add(addition);
                count = count.succ();
            }
            return product;
        } else {
            return invalid();
        }    
    }

    Num sub(Num n) {
        if (n.isValid() && this.isValid()) {
            Num subtraction = 
                new Num(n.opt.map(AbstractNum.n));
            Num value = subtraction.add(this);
            return new Num(value.filter(AbstractNum.valid));
        } else {
            return invalid();
        }    
    }

    Num map(Function<Integer, Integer> mapper) {
        return new Num(this.opt.map(x -> mapper.apply(x)));
    }

    Num filter(Predicate<Integer> pred) {
        return new Num(this.opt.filter(x -> pred.test(x)));
    }
}
