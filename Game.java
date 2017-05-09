//-----------------------------------------
//
// Game.java
//
//-----------------------------------------

public class Game {
  public static void main(String args[]) {

    //----------------------------------------------------
    // Non-static variables
    int numberOfStat = 100;
    int numberOfGameDeck = 100;
    float startingMoney = 1000;
    String countMethod = "HiLo";
    //----------------------------------------------------

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
        OneGame oneGame = new OneGame(countMethod, startingMoney, num, numberOfGameDeck);
        result[i] = oneGame.PlayOneGame();
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