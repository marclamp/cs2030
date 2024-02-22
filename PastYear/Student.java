import java.util.List;

abstract class Student implements AdminOffice, BursarOffice {
    protected final String id;

    Student(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}

interface AdminOffice {
    String getId();
    double getCAP();
}

interface BursarOffice {
    String getId();
    double getLoan();
}

class PG extends Student {

    private final double cap;
    private final double loan;

    PG(String id) {
        super(id);
        this.cap = 5.0;
        this.loan = 111.11;
    }

    PG(String id, double cap, double loan) {
        super(id);
        this.cap = cap;
        this.loan = loan;
    }

    public double getCAP() {
        return this.cap;
    }

    public double getLoan() {
        return this.loan;
    }

}

class UG extends Student {
    private final double cap;
    private final double loan;

    UG(String id) {
        super(id);
        this.cap = 5.0;
        this.loan = 99.99;
    }

    UG(String id, double cap, double loan) {
        super(id);
        this.cap = cap;
        this.loan = loan;
    }

    public double getCAP() {
        return this.cap;
    }

    public double getLoan() {
        return this.loan;
    }

}

class Admin {
    void process(List<? extends AdminOffice> students) {
        for (AdminOffice student : students) {
            System.out.println(student.getId() + " : " + student.getCAP());
        }
    }
}

class Bursar {
    void process(List<? extends BursarOffice> students) {
        for (BursarOffice student : students) {
            System.out.println(student.getId() + " : " + student.getLoan());
        }
    }
}