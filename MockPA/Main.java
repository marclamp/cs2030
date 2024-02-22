public class Main {
    public static void main(String[] args) {

        Case c = new Case(new Person("A123", "PP", 8), new PCR("omicron"));
        new ContactCase(new Person("B234", "M", 7), new ART("CT"), c);
        Test t = new ART("C");
        Case d = new ContactCase(new Person("B234", "M", 7), t, c);
        Protocol p = d.getCurrentProtocol();
        System.out.println(p instanceof P3);
        d = d.test(t);
        d = d.test(t);
        d = d.test(t);
        d = d.test(t);
        d = d.test(t);
        d = d.test(t);
        d = d.test(new PCR("delta"));
        System.out.println(d);
    }
}
