import java.util.List;

interface Host {

    public String getIdentifier();

    public ImList<Term> getListConnected();

    public boolean equals(Host host);

    public void broadcast();
}
