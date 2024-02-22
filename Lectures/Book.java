class Book{
    private int currPage;
    private final int totalPages;
    private Page[] pages;

    public Book(Page[] pages){
        this(pages, 1);
    }

    private Book(Page[] pages, int pageNum){
        this.pages = pages;
        this.currPage = pageNum;
        this.totalPages = pages.length;
    }

    public Book nextPage(){
        if (this.currPage < this.totalPages){
            currPage = currPage + 1;
        }
        return new Book(this.pages, currPage);
    }

    public Book prevPage(){
        if (this.currPage != 1){
            this.currPage = this.currPage - 1;
        }
        return new Book(this.pages, this.currPage);
    }

    public Book gotoPage(int pageNumber){
        if (pageNumber > 0 && pageNumber < this.totalPages){
            this.currPage = pageNumber;
        }
        return new Book(this.pages, this.currPage);
    }

    @Override
    public String toString(){
        return "Page " + this.currPage + "\n" + this.pages[currPage - 1].toString() + "\n";
    }
}
