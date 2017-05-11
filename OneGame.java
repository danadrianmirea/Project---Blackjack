//-----------------------------------------
//
// OneGame.java
//
//-----------------------------------------

public class OneGame {

  String countMethod = "none";
  float startingMoney = 0;
  int minCount = 0;
  int numGame = 0;
  boolean isDouble = false;
  boolean isSplit = false;
  boolean isFixedBetting = false;
  float bettingFixedWinMoney = 0;
  float bettingFixedLoseMoney = 0;
  int bettingWinRate = 0;
  int bettingLoseRate = 0;
  float totalWinMoney = 0;
  float totalLoseMoney = 0;
  int totalWinWithWinBetting = 0;
  int totalLoseWithLoseBetting = 0;

  public OneGame (String _countMethod, float _startingMoney, int _minCount, int _numGame, boolean _isDouble, boolean _isSplit, boolean _isFixedBetting, float _bettingFixedWinMoney, float _bettingFixedLoseMoney, int _bettingWinRate, int _bettingLoseRate) {
    //----------------------------------------------------
    // Parameters
    //
    // Order
    // 0      Counting Method
    // 1      Starting Money
    // 2      Minimum Count for Start Betting
    // 3      Number of Deck to Play
    // 4      Rule Possible for Double
    // 5      Rule Possible for Split
    // 6      Fixed Betting
    // 7      Fixed Win Betting (Non-Counting Game -> WinBetting)
    // 8      Fixed Lose Betting
    // 9      Win Betting Rate
    // 10     Lose Betting Rate
    //----------------------------------------------------
    countMethod = _countMethod;
    startingMoney = _startingMoney;
    minCount = _minCount;
    numGame = _numGame;
    isDouble = _isDouble;
    isSplit = _isSplit;
    isFixedBetting = _isFixedBetting;
    bettingFixedWinMoney = _bettingFixedWinMoney;
    bettingFixedLoseMoney = _bettingFixedLoseMoney;
    bettingWinRate = _bettingWinRate;
    bettingLoseRate = _bettingLoseRate;
  }

  public float[] PlayOneGame() {

    //----------------------------------------------------
    // Return values
    //
    // Order
    // 0      Number of Total Games
    // 1      Number of Player Wins
    // 2      Number of Dealer Wins
    // 3      Number of Pushes
    // 4      Number of Player Wins by High Card
    // 5      Number of Player Wins by 21
    // 6      Number of Plyaer Wins by Dear Bust
    // 7      Number of Dealer Wins by Hish Card
    // 8      Number of Dealer Wins by Player Bust
    // 9      Remained Money
    // 10     Maximum Money Player Reach
    // 11     Total Money Player Win
    // 12     Total Money Player Lose
    // 13     Total Win with Winning Betting
    // 14     Total Lose with Losing Betting
    //----------------------------------------------------

    int numTotalGame = 0;
    int numPlayerWin = 0;
    int numDealerWin = 0;
    int numPush = 0;

    int numPlayerWinWithHighCount = 0;
    int numPlayerWinWith21 = 0;
    int numPlayerWinWithDealerBust = 0;
    int numDealerWinWithHighCount = 0;
    int numDealerWinWithPlayerBust = 0;

    float money = startingMoney;
    float maxMoney = money;

    float[] result;

    for(int i = 0; i < numGame; i++) {
      OneDeckGame oneDeckGame = new OneDeckGame(countMethod, money, maxMoney, minCount, isDouble, isSplit, isFixedBetting, bettingFixedWinMoney, bettingFixedLoseMoney, bettingWinRate, bettingLoseRate);
      result = oneDeckGame.PlayOneDeckGame();
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
      totalWinMoney += result[11];
      totalLoseMoney += result[12];
      totalWinWithWinBetting += result[13];
      totalLoseWithLoseBetting += result[14];

      if (money < 1) {
        break;
      }

      if (money > 3000) {
        break;
      }
    }

    return new float[]{numTotalGame, numPlayerWin, numDealerWin, numPush, numPlayerWinWithHighCount, numPlayerWinWith21, numPlayerWinWithDealerBust, numDealerWinWithHighCount, numDealerWinWithPlayerBust, money, maxMoney, totalWinMoney, totalLoseMoney, totalWinWithWinBetting, totalLoseWithLoseBetting};
  }
}