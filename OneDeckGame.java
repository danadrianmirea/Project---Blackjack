//-----------------------------------------
//
// OneDeckGame.java
//
//-----------------------------------------

import java.util.ArrayList;
import java.util.Arrays;

public class OneDeckGame {

  String countingMethod ="";
  float money = 0;
  float maxMoney = 0;
  int minCount = 0;

  public OneDeckGame(String _countingMethod, float _money, float _maxMoney, int _minCount) {
    countingMethod = _countingMethod;
    money = _money;
    maxMoney = _maxMoney;
    minCount = _minCount;
  }

  public float[] PlayOneDeckGame() {

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
    // 9      Money Of Player Left
    // 10     Money of Player Max
    //----------------------------------------------------

    Deck deck = new Deck(6);
    Dealer dealer = new Dealer();
    Player player = new Player(money);
    int dealerCount = 0, playerCount = 0;
    Counting counting = new Counting(0, countingMethod);

    int numTotalGame = 0, numPlayerWin = 0, numDealerWin = 0, numPush = 0;
    int numPlayerWinWithHighCount = 0, numPlayerWinWith21 = 0, numPlayerWinWithDealerBust = 0;
    int numDealerWinWithHighCount = 0, numDealerWinWithPlayerBust = 0;

    int bettingWinRate = 10, bettingLoseRate = 1;

    float bettingMoney = 0;
    float bettingWinMoney = money / bettingWinRate;
    float bettingLoseMoney = money / bettingWinRate / bettingLoseRate;

    boolean isPlaying = false;

    //Shuffling
    deck.shuffleCard();

    System.out.println("\n");

    while(deck.getDeckSize() > 12) {

      //----------------------------------------------------
      //Playing Status
      if(!isPlaying) {
        if(counting.getCount() >= minCount) {
          isPlaying = true;
        }
      }
      //----------------------------------------------------

      //----------------------------------------------------
      //Betting Money
      if(isPlaying) {
        if(counting.getCount() >= minCount) {
          bettingMoney = bettingWinMoney;
        } else {
          bettingMoney = bettingLoseMoney;
        }

        if(player.getMoney() < bettingMoney) {
          bettingMoney = player.getMoney();
        }

      } else {
        bettingMoney = 0;
      }
      player.betMoney(bettingMoney);

      System.out.println("Player Money: " + player.getMoney());
      System.out.println("Betting Money: " + bettingMoney);

      //----------------------------------------------------

      //----------------------------------------------------
      //Initialize the Game
      System.out.println("Initialize the Game");
      if(dealer.getIsHit()) {
        Card card = deck.popCard();
        counting.countCard(card);
        dealerCount = dealer.dealing(card);
        System.out.println(dealer.toString() + " ");
      }
      System.out.println(counting.toString() + " ");

      Card hiddenCard = deck.popCard();

      if(player.getIsHit()) {
        Card card1 = deck.popCard();
        Card card2 = deck.popCard();
        counting.countCard(card1);
        counting.countCard(card2);
        playerCount = player.playing(card1);
        playerCount = player.playing(card2); 
        System.out.println(player.toString() + " ");
      }
      //----------------------------------------------------

      if(player.getIs21()) {
        numPlayerWin++;
        numPlayerWinWith21++;

        player.addMoney(bettingMoney + (float)(bettingMoney * 1.5));

      } else {
        while(player.getIsHit()) {
          //------------------------------
          //Counting exception
          if(counting.getCount() >= minCount) {
            player.setIsHit(false);
            break;
          }
          //------------------------------
          Card card = deck.popCard();
          counting.countCard(card);
          playerCount = player.playing(card);
        }
      }

      //----------------------------------------------------
      // Double Method
      /*ArrayList<Integer> dealerHand = dealer.getHand();
      if(!(dealerHand.size() == 2 || dealerHand.get(0) == 10) && counting.getCount() >= minCount) {
        if(player.getMoney() >= bettingMoney) {
          bettingMoney += bettingMoney;
          player.betMoney(bettingMoney);
        }
      }*/
      //----------------------------------------------------

      //Flip the hidden dealer card
      counting.countCard(hiddenCard);
      dealerCount = dealer.dealing(hiddenCard);

      //if(!player.getIsBust() && !player.getIs21()) {
        while(dealer.getIsHit()) {
          Card card = deck.popCard();
          counting.countCard(card);
          dealerCount = dealer.dealing(card);
        }
      //}
      dealer.setIsHit(false);

      System.out.println("Player: " + playerCount + " ");
      System.out.println("Dealer: " + dealerCount + " ");

      if(!player.getIs21()) {
        if(playerCount > dealerCount) {
          if(dealerCount == 0) {
            numPlayerWinWithDealerBust++;
          } else {
            numPlayerWinWithHighCount++;
          }
          numPlayerWin++;
          player.addMoney(bettingMoney + bettingMoney);
        } else if(playerCount < dealerCount) {
          if(playerCount == 0) {
            numDealerWinWithPlayerBust++;
          } else {
            numDealerWinWithHighCount++;
          }
          numDealerWin++;
        } else {
          numPush++;
          player.addMoney(bettingMoney);
        }
      }

      if(!dealer.getIsHit() && !player.getIsHit()) {
        System.out.println(dealer.toString());
        System.out.println(player.toString());
        System.out.println("Finish the game");

        dealer.clearHand();
        player.clearHand();
      }

      System.out.println("Player Money: " + player.getMoney());

      if(player.getMoney() > maxMoney) {
        maxMoney = player.getMoney();
      }

      numTotalGame++;
      if(player.getMoney() < 1) {
        break;
      }

      System.out.print("\n");
    }
    System.out.println("Game Finished");
    return new float[]{numTotalGame, numPlayerWin, numDealerWin, numPush, numPlayerWinWithHighCount, numPlayerWinWith21, numPlayerWinWithDealerBust, numDealerWinWithHighCount, numDealerWinWithPlayerBust, player.getMoney(), maxMoney};
  }
}
