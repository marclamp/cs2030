/open Person.java

new Person("A123", "PP", 8)
new Person("A123", "PP", 8).isVaccinated()
new Person("A123", "PP", 8).isHighRisk()
new Person("B234", "M", 7)
new Person("B234", "M", 7).isVaccinated()
new Person("B234", "M", 7).isHighRisk()
new Person("Z999", "", 1)
new Person("Z999", "", 1).isVaccinated()
new Person("Z999", "", 1).isHighRisk()

// A123/PP/HIGH
// true
// true
// B234/M/LOW
// false
// false
// Z999//LOW
// false
// false