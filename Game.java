//-----------------------------------------
//
// Game.java
//
//-----------------------------------------

public class Game {

  float startingMoney = 0;

  public static int[] PlayGame(String countingMethod) {
    
    Deck deck = new Deck(6);
    Dealer dealer = new Dealer();
    Player player = new Player(1000);
    int dealerCount = 0, playerCount = 0;
    Counting counting = new Counting(0, countingMethod);

    int numPlayerWin = 0, numDealerWin = 0, numPush = 0;

    //Shuffling
    deck.shuffleCard();

    System.out.println(deck.toString());
    System.out.println("\n\n");

    while(deck.getDeckSize() > 10) {

      //----------------------------------------------------
      //Initialize the Game
      if(dealer.getIsHit()) {
        Card card = deck.popCard();
        counting.countCard(card);
        dealerCount = dealer.dealing(card);
        System.out.println(dealer.toString());
      }
      System.out.println(counting.toString());

      Card hiddenCard = deck.popCard();

      if(player.getIsHit()) {
        Card card1 = deck.popCard();
        Card card2 = deck.popCard();
        counting.countCard(card1);
        counting.countCard(card2);
        playerCount = player.playing(card1);
        playerCount = player.playing(card2); 
        System.out.println(player.toString());
      }

      System.out.println(counting.toString());
      System.out.println("Initialize the game");
      //----------------------------------------------------

      while(player.getIsHit()) {
        Card card = deck.popCard();
        counting.countCard(card);
        playerCount = player.playing(card);
        System.out.println(player.toString());
      }
      System.out.println("Player turn over");

      //Flip the hidden dealer card
      counting.countCard(hiddenCard);
      dealerCount = dealer.dealing(hiddenCard);
      System.out.println(dealer.toString());

      if(!player.getIsBust()) {
        while(dealer.getIsHit()) {
          Card card = deck.popCard();
          counting.countCard(card);
          dealerCount = dealer.dealing(card);
          System.out.println(dealer.toString());
        }
      }
      dealer.setIsHit(false);
      System.out.println("Dealer turn over");

      System.out.println("Player: " + playerCount);
      System.out.println("Dealer: " + dealerCount);

      if(playerCount > dealerCount) {
        numPlayerWin++;
      } else if(playerCount < dealerCount) {
        numDealerWin++;
      } else {
        numPush++;
      }

      if(!dealer.getIsHit() && !player.getIsHit()) {
        dealer.clearHand();
        System.out.println("Dealer Hand Clear");
        player.clearHand();
        System.out.println("Player Hand Clear");
      }

      System.out.println("\n\n");
    }
    System.out.println("Game Finished");
    return new int[]{numPlayerWin, numDealerWin, numPush};
  }

  public static void main(String args[]) {

    int numPlayerWin = 0;
    int numDealerWin = 0;
    int numPush = 0;

    int numGame = 1000;

    int[] result;

    for(int i = 0; i < numGame; i++) {
      result = PlayGame("HiLo");
      numPlayerWin += result[0];
      numDealerWin += result[1];
      numPush += result[2];
    }

    System.out.println("Final Result\n");
    System.out.println("Player Win: \t" + numPlayerWin);
    System.out.println("Dealer Win: \t" + numDealerWin);
    System.out.println("Push: \t\t" + numPush);
  }
}
