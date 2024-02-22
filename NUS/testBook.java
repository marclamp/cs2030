class testBook{
    public void testRun() {
        Page pg1 = new Page(1, "Hello this is page 1.");
        Page pg2 = new Page(2, "I am Page 2.");
        Page pg3 = new Page(3, "Wo Shi San Hao");
        Page[] pages = {pg1, pg2, pg3};
        
        Book newBook = new Book(pages);

        newBook.toString();
        newBook.prevPage(); //should not change the page
        newBook.toString();
        newBook.nextPage();
        newBook.nextPage();
        newBook.toString(); //now is at page 3
        newBook.nextPage(); //should not cahnge the page
        newBook.toString();
        newBook.gotoPage(2);
        newBook.toString();

        System.out.println(newBook.toString());
    }
}
