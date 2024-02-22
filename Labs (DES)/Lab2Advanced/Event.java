interface Event {
    public Event execute();

    public boolean equals(Object obj);
    
    public Customer getCustomer();
    
    public Server getServer();
}
