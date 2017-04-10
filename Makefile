JCC = javac
JVM = java
.SUFFIXES: .java .class
.java.class: ; $(JC) $(JFLAGS) $*.java

JFLAGS = -g

default: classes

MAIN = Game

CLASSES = \
	Game.java \
	Card.java \
	Deck.java \
	Counting.java \
	Dealer.java

Game.class: Game.java
	$(JCC) $(JFLAGS) Game.java

Card.class: Card.java
	$(JCC) $(JFLAGS) Card.java

Deck.class: Deck.java
	$(JCC) $(JFLAGS) Deck.java

Counting.class: Counting.java
	$(JCC) $(JFLAGS) Counting.java

Dealer.class: Dealer.java
	$(JCC) $(JFLAGS) Dealer.java

classes: $(CLASSES:.java=.class)

test: classes $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class