/open ImList.java
/open Person.java
/open Test.java
/open ART.java
/open PCR.java
/open Protocol.java
/open P1.java
/open P2.java
/open Case.java

new Case(new Person("A123", "PP", 8), new ART("C"))
Case c = new Case(new Person("A123", "PP", 8), new PCR("alpha"))
Protocol p = c.getCurrentProtocol()
p instanceof P1
c = c.test(new PCR("beta"))
c = c.test(new ART("T")) // invalid test not recorded
c = c.test(new ART("CT"))
c = c.test(new ART("C"))
c = c.test(new ART("C"))
c = c.test(new ART("CT"))
c = c.test(new ART("C"))
c.getTestHistory()
c.getTestHistory() instanceof ImList