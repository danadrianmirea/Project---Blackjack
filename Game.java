//-----------------------------------------
//
// Game.java
//
//-----------------------------------------

public class Game {

  float startingMoney = 0;

  public static int[] PlayGame(String countingMethod) {

    //----------------------------------------------------
    // Return values
    // 
    // Order
    // 0      Number Of Total Game
    // 1      Number Of Player Win
    // 2      Number Of Dealer Win
    // 3      Number Of Push
    // 4      Number Of Player Win With High Count
    // 5      Number Of Player Win With 21
    // 6      Number Of Player Win With Dealer Bust
    // 7      Number Of Dealer Win With High Count
    // 8      Number Of Dealer Win With Player Bust
    //----------------------------------------------------

    Deck deck = new Deck(6);
    Dealer dealer = new Dealer();
    Player player = new Player(1000);
    int dealerCount = 0, playerCount = 0;
    Counting counting = new Counting(0, countingMethod);

    int numTotalGame = 0, numPlayerWin = 0, numDealerWin = 0, numPush = 0;
    int numPlayerWinWithHighCount = 0, numPlayerWinWith21 = 0, numPlayerWinWithDealerBust = 0;
    int numDealerWinWithHighCount = 0, numDealerWinWithPlayerBust = 0;

    //Shuffling
    deck.shuffleCard();

    System.out.println(deck.toString());
    System.out.println("\n\n");

    while(deck.getDeckSize() > 10) {

      //----------------------------------------------------
      //Betting Money

      //----------------------------------------------------

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

      if(player.getIs21()) {
        numPlayerWin++;
        numPlayerWinWith21++;
      } else {
        while(player.getIsHit()) {
          Card card = deck.popCard();
          counting.countCard(card);
          playerCount = player.playing(card);
          System.out.println(player.toString());
        }
        System.out.println("Player turn over");
      }

      //Flip the hidden dealer card
      counting.countCard(hiddenCard);
      dealerCount = dealer.dealing(hiddenCard);
      System.out.println(dealer.toString());

      if(!player.getIsBust() || !player.getIs21()) {
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

      if(!player.getIs21()) {
        if(playerCount > dealerCount) {
          if(dealerCount == 0) {
            numPlayerWinWithDealerBust++;
          } else {
            numPlayerWinWithHighCount++;
          }
          numPlayerWin++;
        } else if(playerCount < dealerCount) {
          if(playerCount == 0) {
            numDealerWinWithPlayerBust++;
          } else {
            numDealerWinWithHighCount++;
          }
          numDealerWin++;
        } else {
          numPush++;
        }
      }

      if(!dealer.getIsHit() && !player.getIsHit()) {
        dealer.clearHand();
        System.out.println("Dealer Hand Clear");
        player.clearHand();
        System.out.println("Player Hand Clear");
      }

      numTotalGame++;

      System.out.println("\n\n");
    }
    System.out.println("Game Finished");
    return new int[]{numTotalGame, numPlayerWin, numDealerWin, numPush, numPlayerWinWithHighCount, numPlayerWinWith21, numPlayerWinWithDealerBust, numDealerWinWithHighCount, numDealerWinWithPlayerBust};
  }

  public static void main(String args[]) {

    int numTotalGame = 0;
    int numPlayerWin = 0;
    int numDealerWin = 0;
    int numPush = 0;

    int numPlayerWinWithHighCount = 0;
    int numPlayerWinWith21 = 0;
    int numPlayerWinWithDealerBust = 0;
    int numDealerWinWithHighCount = 0;
    int numDealerWinWithPlayerBust = 0;

    int numGame = 1000;

    int[] result;

    for(int i = 0; i < numGame; i++) {
      result = PlayGame("HiLo");
      numTotalGame += result[0];
      numPlayerWin += result[1];
      numDealerWin += result[2];
      numPush += result[3];
      numPlayerWinWithHighCount += result[4];
      numPlayerWinWith21 += result[5];
      numPlayerWinWithDealerBust += result[6];
      numDealerWinWithHighCount += result[7];
      numDealerWinWithPlayerBust += result[8];
    }

    System.out.println("Final Result\n");
    System.out.println("Total Game: \t" + numTotalGame);
    System.out.println("Player Win: \t" + numPlayerWin);
    System.out.println("Dealer Win: \t" + numDealerWin);
    System.out.println("Push: \t\t" + numPush);

    System.out.println("Player Winning Type:\tHigh Count:\t" + numPlayerWinWithHighCount + "\t Dealer Bust:\t" + numPlayerWinWithDealerBust + "\t 21:\t" + numPlayerWinWith21);
    System.out.println("Dealer Winning Type:\tHigh Count:\t" + numDealerWinWithHighCount + "\t Player Bust:\t" + numDealerWinWithPlayerBust);
  }
}
