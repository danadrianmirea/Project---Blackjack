# Project---Blackjack
Java Blackjack game<br /><br />

Make file<br />
```makefile
make test // running the file
make clean // cleaning the folder
```

Game.java<br />
Change below variable for condition of simulation
```java
int numberOfStat = 1000;
int numberOfOneGameDeck = 100;
float startingMoney = 1000;
// countMethod = none | HiLo | HiOpt1 | HiOpt2 | KO
String countMethod = "none";
boolean isDouble = false;
boolean isSplit = false;
boolean isFixedBetting = true;
float bettingFixedWinMoney = 100;
float bettingFixedLostMoney = 10;
int bettingWinRate = 0;
int bettingLoseRate = 0;
```

File List<br /><br />
Makefile<br />
Card.java<br />
Deck.java<br />
Dealer.java<br />
Player.java<br />
Counting.java<br />
OneDeckGame.java<br />
OneGame.java<br />
Game.java (Main)<br />