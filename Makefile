JCC = javac
JVM = java
.SUFFIXES: .java .class
.java.class: ; $(JC) $(JFLAGS) $*.java

JFLAGS = -g

default: classes

MAIN = Game

CLASSES = \
	Game.java \
	OneGame.java \
	OneDeckGame.java \
	Card.java \
	Deck.java \
	Counting.java \
	Dealer.java \
	Player.java

Game.class: Game.java
	$(JCC) $(JFLAGS) Game.java

OneGame.class: OneGame.java
	$(JCC) $(JFLAGS) OneGame.java

OneDeckGame.class: OneDeckGame.java
	$(JCC) $(JFLAGS) OneDeckGame.java

Card.class: Card.java
	$(JCC) $(JFLAGS) Card.java

Deck.class: Deck.java
	$(JCC) $(JFLAGS) Deck.java

Counting.class: Counting.java
	$(JCC) $(JFLAGS) Counting.java

Dealer.class: Dealer.java
	$(JCC) $(JFLAGS) Dealer.java

Player.class: Player.java
	$(JCC) $(JFLAGS) Player.java

classes: $(CLASSES:.java=.class)

test: Card.class OneGame.class OneDeckGame.java Deck.class Counting.class Dealer.class Player.class classes $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class