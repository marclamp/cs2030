/open Term.java
/open Pager.java
/open Host.java
/open Transmitter.java

Pager p1 = new Pager("pager1")
Term t1 = p1
new Term()
p1 instanceof Pager
p1 instanceof Term
t1.equals(p1)
p1.equals(t1)
p1.equals(new Pager("pager1"))
t1.equals(new Pager("pager1"))
p1.equals("pager1")

