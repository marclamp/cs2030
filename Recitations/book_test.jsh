/open Page.java
/open Book.java

Page pg1 = new Page("Hello this is page 1.");
Page pg2 = new Page("I am Page 2.");
Page pg3 = new Page("Wo Shi San Hao");
List<Page> pages = List.of(pg1, pg2, pg3);

Book newBook = new Book(pages);
newBook.prevPage(); //should not change the page
newBook.nextPage();
newBook.nextPage(); //now is at page 3
newBook.nextPage(); //should not cahnge the page
newBook.gotoPage(2);