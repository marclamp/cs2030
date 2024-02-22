class Page {
    private final String pageText;
    
    Page(String pageText) {
        this.pageText = pageText;
    }
    
    @Override
    public String toString(){
        return pageText;
    }
}
