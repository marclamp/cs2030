import java.util.Comparator;

class CustComp implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return c1.compareTo(c2);
    }
}