//-----------------------------------------
//
// Game.java
//
//-----------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Game {
    public static void main(String args[]) throws FileNotFoundException {

    //----------------------------------------------------
    // Non-static variables
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
    //----------------------------------------------------

    float result[][] = new float[numberOfStat][15];
    float finalResult[][] = new float[15][5];
    float totalNumberGame = 0;
    float totalPlayerWinning = 0;
    float totalDealerWinning = 0;
    float totalPush = 0;
    float totalMaxMoney = 0;

    PrintWriter printWriter = new PrintWriter(new File("result.csv"));
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("Total Game");
    stringBuilder.append(',');
    stringBuilder.append("Player Win");
    stringBuilder.append(',');
    stringBuilder.append("Dealer Win");
    stringBuilder.append(',');
    stringBuilder.append("Push");
    stringBuilder.append(',');
    stringBuilder.append("Player Winning Type");
    stringBuilder.append(',');
    stringBuilder.append("High Card");
    stringBuilder.append(',');
    stringBuilder.append("Dealer Bust");
    stringBuilder.append(',');
    stringBuilder.append("21");
    stringBuilder.append(',');
    stringBuilder.append("Dealer Winning Type");
    stringBuilder.append(',');
    stringBuilder.append("High Card");
    stringBuilder.append(',');
    stringBuilder.append("Player Bust");
    stringBuilder.append(',');
    stringBuilder.append("Money");
    stringBuilder.append(',');
    stringBuilder.append("Max Money");
    stringBuilder.append(',');
    stringBuilder.append("Toal Win Money");
    stringBuilder.append(',');
    stringBuilder.append("Total Lose Money");
    stringBuilder.append(',');
    stringBuilder.append("Total Win with Winning Betting");
    stringBuilder.append(',');
    stringBuilder.append("Total Lose with Losing Betting");
    stringBuilder.append("\n");

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

    for(int num = 10; num < 11; num++) {
        stringBuilder.append("Minimum count " + num);
        stringBuilder.append("\n");
      for(int i = 0; i < numberOfStat; i++) {
        OneGame oneGame = new OneGame(countMethod, startingMoney, num, numberOfOneGameDeck, isDouble, isSplit, isFixedBetting, bettingFixedWinMoney, bettingFixedLostMoney, bettingWinRate, bettingLoseRate);
        result[i] = oneGame.PlayOneGame();
        totalNumberGame += result[i][0];
        totalPlayerWinning += (result[i][1] / result[i][0]);
        totalDealerWinning += (result[i][2] / result[i][0]);
        totalPush += (result[i][3] / result[i][0]);
        totalMaxMoney += result[i][10];
      }

      System.out.println("Result " + num);
      System.out.println("Total Game\tPlayer Win\tDealer Win\tPush\tPlayer Winning Type:\tHigh Card:\t Dealer Bust:\t 21:\tDealer Winning Type:\t\t\tHigh Card:\tPlayer Bust:\tMoney:\tMax Money:");
      
      for(int i = 0; i < numberOfStat; i++) {
        System.out.println(Integer.toString((int)result[i][0]) + "\t\t" + Integer.toString((int)result[i][1]) + "\t\t" + Integer.toString((int)result[i][2]) + "\t\t" + Integer.toString((int)result[i][3]) + "\t\t\t\t" + Integer.toString((int)result[i][4]) + "\t\t" + Integer.toString((int)result[i][5]) + "\t\t" + Integer.toString((int)result[i][6]) + "\t\t\t\t" + Integer.toString((int)result[i][7]) + "\t\t" + Integer.toString((int)result[i][8]) + "\t\t" + String.format("%.2f", result[i][9]) + "\t" + String.format("%.2f", result[i][10]));
        stringBuilder.append(Integer.toString((int)result[i][0]));
        stringBuilder.append(',');
        stringBuilder.append(Integer.toString((int)result[i][1]));
        stringBuilder.append(',');
        stringBuilder.append(Integer.toString((int)result[i][2]));
        stringBuilder.append(',');
        stringBuilder.append(Integer.toString((int)result[i][3]));
        stringBuilder.append(',');
        stringBuilder.append("");
        stringBuilder.append(',');
        stringBuilder.append(Integer.toString((int)result[i][4]));
        stringBuilder.append(',');
        stringBuilder.append(Integer.toString((int)result[i][5]));
        stringBuilder.append(',');
        stringBuilder.append(Integer.toString((int)result[i][6]));
        stringBuilder.append(',');
        stringBuilder.append("");
        stringBuilder.append(',');
        stringBuilder.append(Integer.toString((int)result[i][7]));
        stringBuilder.append(',');
        stringBuilder.append(Integer.toString((int)result[i][8]));
        stringBuilder.append(',');
        stringBuilder.append(String.format("%.2f", result[i][9]));
        stringBuilder.append(',');
        stringBuilder.append(String.format("%.2f", result[i][10]));
        stringBuilder.append(',');
        stringBuilder.append(String.format("%.2f", result[i][11]));
        stringBuilder.append(',');
        stringBuilder.append(String.format("%.2f", result[i][12]));
        stringBuilder.append(',');
        stringBuilder.append(Integer.toString((int)result[i][13]));
        stringBuilder.append(',');
        stringBuilder.append(Integer.toString((int)result[i][14]));
        stringBuilder.append("\n");
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
    printWriter.write(stringBuilder.toString());
    printWriter.close();
    System.out.println("Final Result");
    System.out.println("Min Count\tAvg Game\tAvg Player Win\tAvg Dealer Win\tAvg Push\tAvg Max Money");
    
    for(int i = 0; i < 15; i++) {
      System.out.println(i + "\t\t" + String.format("%.8f", finalResult[i][0]) + "\t" + String.format("%.8f", finalResult[i][1]) + "\t" + String.format("%.8f", finalResult[i][2]) + "\t" + String.format("%.8f", finalResult[i][3]) + "\t" + String.format("%.8f", finalResult[i][4]));
    }
  }
}