/open openall.jsh

Pager p1 = new Pager("pager1")
Transmitter r1 = new Transmitter("transmit1")
p1.snd(r1).rcv()
p1.snd(r1).rcv().equals(p1)
p1.snd(r1).equals(r1)
r1.rcv()
