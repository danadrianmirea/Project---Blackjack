//-----------------------------------------
//
// Game.java
//
//-----------------------------------------

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

  float startingMoney = 0;

  public static float[] PlayGame(String countingMethod, float _money, float _maxMoney, int _minCount) {

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
    Player player = new Player(_money);
    int dealerCount = 0, playerCount = 0;
    Counting counting = new Counting(0, countingMethod);

    int numTotalGame = 0, numPlayerWin = 0, numDealerWin = 0, numPush = 0;
    int numPlayerWinWithHighCount = 0, numPlayerWinWith21 = 0, numPlayerWinWithDealerBust = 0;
    int numDealerWinWithHighCount = 0, numDealerWinWithPlayerBust = 0;

    int minCount = _minCount;
    int bettingWinRate = 20, bettingLoseRate = 10;

    float maxMoney = _maxMoney;

    float bettingMoney = 0;
    float bettingWinMoney = _money / bettingWinRate;
    float bettingLoseMoney = _money / bettingWinRate / bettingLoseRate;

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

      if(!player.getIsBust() && !player.getIs21()) {
        while(dealer.getIsHit()) {
          Card card = deck.popCard();
          counting.countCard(card);
          dealerCount = dealer.dealing(card);
        }
      }
      dealer.setIsHit(false);

      System.out.print("Player: " + playerCount + " ");
      System.out.print("Dealer: " + dealerCount + " ");

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
        System.out.println("Finish the game");
        System.out.println(dealer.toString());
        System.out.println(player.toString());
        System.out.print("\n");

        dealer.clearHand();
        player.clearHand();
      }

      System.out.print("Player Money: " + player.getMoney());

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

  public static float[] GameState(int minCount) {

    int numTotalGame = 0;
    int numPlayerWin = 0;
    int numDealerWin = 0;
    int numPush = 0;

    int numPlayerWinWithHighCount = 0;
    int numPlayerWinWith21 = 0;
    int numPlayerWinWithDealerBust = 0;
    int numDealerWinWithHighCount = 0;
    int numDealerWinWithPlayerBust = 0;

    float money = 1000;
    float maxMoney = money;

    int numGame = 100;

    float[] result;

    for(int i = 0; i < numGame; i++) {
      result = PlayGame("HiLo", money, maxMoney, minCount);
      numTotalGame += result[0];
      numPlayerWin += result[1];
      numDealerWin += result[2];
      numPush += result[3];
      numPlayerWinWithHighCount += result[4];
      numPlayerWinWith21 += result[5];
      numPlayerWinWithDealerBust += result[6];
      numDealerWinWithHighCount += result[7];
      numDealerWinWithPlayerBust += result[8];
      money = result[9];
      maxMoney = result[10];

      if(money < 1) {
        break;
      }
    }

    return new float[]{numTotalGame, numPlayerWin, numDealerWin, numPush, numPlayerWinWithHighCount, numPlayerWinWith21, numPlayerWinWithDealerBust, numDealerWinWithHighCount, numDealerWinWithPlayerBust, money, maxMoney};
  }

  public static void main(String args[]) {

    int numberOfStat = 10;
    float result[][] = new float[numberOfStat][11];
    float finalResult[][] = new float[15][5];
    float totalNumberGame = 0;
    float totalPlayerWinning = 0;
    float totalDealerWinning = 0;
    float totalPush = 0;
    float totalMaxMoney = 0;

    //----------------------------------------------------
    // Final Result
    //
    // Order
    // 0      Average Total Game
    // 1      Average Player Winning Rate
    // 2      Average Dealer Winning Rate
    // 3      Average Push
    // 4      Average Max Money
    //
    //----------------------------------------------------

    for(int num = 0; num < 15; num++) {
      for(int i = 0; i < numberOfStat; i++) {
        result[i] = GameState(num);
        totalNumberGame += result[i][0];
        totalPlayerWinning += (result[i][1] / result[i][0]);
        totalDealerWinning += (result[i][2] / result[i][0]);
        totalPush += (result[i][3] / result[i][0]);
        totalMaxMoney += result[i][10];
      }

      System.out.println("Result " + num);
      //System.out.println("Total Game\tPlayer Win\tDealer Win\tPush\tPlayer Winning Type:\tHigh Count:\t Dealer Bust:\t 21:\tDealer Winning Type:\t\t\tHigh Count:\tPlayer Bust:\tMoney:\tMax Money:");
      
      for(int i = 0; i < numberOfStat; i++) {
        System.out.println(Integer.toString((int)result[i][0]) + "\t\t" + Integer.toString((int)result[i][1]) + "\t\t" + Integer.toString((int)result[i][2]) + "\t\t" + Integer.toString((int)result[i][3]) + "\t\t\t\t" + Integer.toString((int)result[i][4]) + "\t\t" + Integer.toString((int)result[i][5]) + "\t\t" + Integer.toString((int)result[i][6]) + "\t\t\t\t" + Integer.toString((int)result[i][7]) + "\t\t" + Integer.toString((int)result[i][8]) + "\t\t" + String.format("%.2f", result[i][9]) + "\t" + String.format("%.2f", result[i][10]));
      }

      finalResult[num][0] = (float)totalNumberGame / numberOfStat;
      finalResult[num][1] = (float)totalPlayerWinning / numberOfStat;
      finalResult[num][2] = (float)totalDealerWinning / numberOfStat;
      finalResult[num][3] = (float)totalPush / numberOfStat;
      finalResult[num][4] = (float)totalMaxMoney / numberOfStat;

      totalNumberGame = 0;
      totalPlayerWinning = 0;
      totalDealerWinning = 0;
      totalPush = 0;
      totalMaxMoney = 0;
    }
    System.out.println("Final Result");
    //System.out.println("Min Count\tAvg Game\tAvg Player Win\tAvg Dealer Win\tAvg Push\tAvg Max Money");
    
    for(int i = 0; i < 15; i++) {
      System.out.println(i + "\t\t" + String.format("%.8f", finalResult[i][0]) + "\t" + String.format("%.8f", finalResult[i][1]) + "\t" + String.format("%.8f", finalResult[i][2]) + "\t" + String.format("%.8f", finalResult[i][3]) + "\t" + String.format("%.8f", finalResult[i][4]));
    }
  }
}
