/open Person.java
/open Test.java
/open ART.java
/open PCR.java

Test test = new ART("C")
test.isValid()
test.isPositive()
test = new ART("CT")
test.isValid()
test.isPositive()
test = new ART("T")
test.isValid()
test.isPositive()
test = new PCR("omicron")
test.isValid()
test.isPositive()
test = new PCR("decepticon")
test.isValid()
test.isPositive()