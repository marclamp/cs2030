/open ImList.java
/open Pair.java
/open Service.java
/open JustRide.java
/open ShareARide.java
/open TakeACab.java
/open Request.java
/open Driver.java
/open NormalCab.java
/open PrivateCar.java 
/open Booking.java

//level 1
new JustRide()
new JustRide().computeFare(20, 3, 1000)
new JustRide().computeFare(10, 1, 900)

//level 2
new Request(20, 3, 1000)
new Request(20, 3, 1000).computeFare(new JustRide())
new Request(10, 1, 900)
new Request(10, 1, 900).computeFare(new JustRide())

new TakeACab()
new Request(20, 3, 1000).computeFare(new TakeACab())
new Request(10, 1, 900).computeFare(new TakeACab())

//level 3
new NormalCab("SHA1234", 5)
new Booking(new NormalCab("SHA1234", 5), new Request(20, 3, 1000))
new NormalCab("SHA2345", 10)
new Booking(new NormalCab("SHA2345", 10), new Request(10, 1, 900))
Booking b1 = new Booking(new NormalCab("SHA2345", 10), new Request(10, 1, 900))
Booking b2 = new Booking(new NormalCab("SHA2345", 10), new Request(10, 1, 900))
Comparable<Booking> cmp = b1
b1.compareTo(b2) == 0

//level 4
new ShareARide()
new PrivateCar("SMA7890", 5)
new Booking(new PrivateCar("SMA7890", 5), new Request(20, 3, 1000))
new PrivateCar("SLA5678", 10)
new Booking(new PrivateCar("SLA5678", 10), new Request(10, 1, 900))
Booking b1 = new Booking(new PrivateCar("SMA7890", 5), new Request(10, 1, 900))
Booking b2 = new Booking(new PrivateCar("SLA5678", 10), new Request(10, 1, 900))
b1.compareTo(b2) < 0




