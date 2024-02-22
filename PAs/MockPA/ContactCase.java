import java.util.List;

class ContactCase extends Case {
    private final Case covidCase;

    ContactCase(Person person, Test test, Case covidCase) {
        super(person, test, new ImList<Test>(List.of(test)), 1);
        this.covidCase = covidCase;
    }

    private ContactCase(Case c, Case covidCase) {
        super(c);
        this.covidCase = covidCase;
    }

    public boolean hasContact() {
        return true;
    }

    public ImList<Case> getLineage() {
        return this.covidCase.getLineage().add(this);
    }

    @Override
    public Case test(Test test) {
        Case c = super.test(test);
        return new ContactCase(c, this.covidCase);
    }
}




