import java.util.Comparator;

class SelfCheckoutComp implements Comparator<SelfCheckoutOne> {
    @Override
    public int compare(SelfCheckoutOne sc1, SelfCheckoutOne sc2) {
        return sc1.compareTo(sc2);
    }
}