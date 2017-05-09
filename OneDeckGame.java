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
  boolean isDouble = false;
  boolean isSplit = false;
  boolean isFixedBetting = false;
  float bettingFixedWinMoney = 0;
  float bettingFixedLoseMoney = 0;
  int bettingWinRate = 0;
  int bettingLoseRate = 0;

  public OneDeckGame(String _countingMethod, float _money, float _maxMoney, int _minCount, boolean _isDouble, boolean _isSplit, boolean _isFixedBetting, float _bettingFixedWinMoney, float _bettingFixedLoseMoney,int _bettingWinRate, int _bettingLoseRate) {
    //----------------------------------------------------
    // Parameters
    //
    // Order
    // 0      Counting Method
    // 1      Starting Money
    // 2      Maximum Money Player Reach
    // 3      Minimum Count for Start Betting
    // 4      Rule Possible for Double
    // 5      Rule Possible for Split
    // 6      Fixed Betting
    // 7      Fixed Win Betting (Non-Counting Game -> WinBetting)
    // 8      Fixed Lose Betting
    // 9      Win Betting Rate
    // 10     Lose Betting Rate
    //----------------------------------------------------

    countingMethod = _countingMethod;
    money = _money;
    maxMoney = _maxMoney;
    minCount = _minCount;
    isDouble = _isDouble;
    isSplit = _isSplit;
    isFixedBetting = _isFixedBetting;
    bettingFixedWinMoney = _bettingFixedWinMoney;
    bettingFixedLoseMoney = _bettingFixedLoseMoney;
    bettingWinRate = _bettingWinRate;
    bettingLoseRate = _bettingLoseRate;
  }

  public float[] PlayOneDeckGame() {

    //----------------------------------------------------
    // Return values
    // 
    // Order
    // 0      Number Of Total Games
    // 1      Number Of Player Wins
    // 2      Number Of Dealer Wins
    // 3      Number Of Pushes
    // 4      Number Of Player Wins by High Count
    // 5      Number Of Player Wins by 21
    // 6      Number Of Player Wins by Dealer Bust
    // 7      Number Of Dealer Wins by High Count
    // 8      Number Of Dealer Wins by Player Bust
    // 9      Remained Money
    // 10     Maximum Money Player Reach
    //----------------------------------------------------

    Deck deck = new Deck(6);
    Dealer dealer = new Dealer();
    Player player = new Player(money);
    int dealerCount = 0, playerCount = 0;
    Counting counting = new Counting(0, countingMethod);

    int numTotalGame = 0, numPlayerWin = 0, numDealerWin = 0, numPush = 0;
    int numPlayerWinWithHighCount = 0, numPlayerWinWith21 = 0, numPlayerWinWithDealerBust = 0;
    int numDealerWinWithHighCount = 0, numDealerWinWithPlayerBust = 0;

    float bettingMoney = 0;
    float bettingWinMoney = 0; 
    float bettingLoseMoney = 0;

    if(isFixedBetting) {
      bettingWinMoney = bettingFixedWinMoney;
      bettingLoseMoney = bettingFixedLoseMoney;
    } else {
      bettingWinMoney = bettingWinMoney = money / bettingWinRate;
      bettingLoseMoney = money / bettingWinRate / bettingLoseRate;
    }

    boolean isPlaying = false;

    //Shuffling
    deck.shuffleCard();

    System.out.println("\n");

    while(deck.getDeckSize() > 12) {

      //----------------------------------------------------
      //Playing Status
      if(!isPlaying && counting.getIsCounting()) {
        if(counting.getCount() >= minCount) {
          isPlaying = true;
        }
      }
      //----------------------------------------------------

      //----------------------------------------------------
      //Betting Money

      //Counting
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

      //Non-Counting
      if(!counting.getIsCounting()) {
        bettingMoney = bettingWinMoney;
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
      if (isDouble) {
        ArrayList<Integer> dealerHand = dealer.getHand();
        if(!(dealerHand.size() == 2 || dealerHand.get(0) == 10) && counting.getCount() >= minCount) {
          if(player.getMoney() >= bettingMoney) {
            bettingMoney += bettingMoney;
            player.betMoney(bettingMoney);
          }
        }
      }
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
