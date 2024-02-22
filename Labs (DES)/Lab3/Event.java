interface Event extends Comparable<Event> {
    public Event execute();

    public int compareTo(Event event);

    /*  public boolean equals(Object obj);
    
    public Customer getCustomer();
    
    public Server getServer(); */
}
