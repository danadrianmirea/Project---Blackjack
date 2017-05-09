//-----------------------------------------
//
// OneGame.java
//
//-----------------------------------------

public class OneGame {

  String countMethod = "";
  float startingMoney = 0;
  int minCount = 0;
  int numGame = 0;

  public OneGame (String _countMethod, float _startingMoney, int _minCount, int _numGame){
    countMethod = _countMethod;
    startingMoney = _startingMoney;
    minCount = _minCount;
    numGame = _numGame;
  }

  public float[] PlayOneGame() {

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
      OneDeckGame oneDeckGame = new OneDeckGame(countMethod, money, maxMoney, minCount);
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

      if(money < 1) {
        break;
      }
    }

    return new float[]{numTotalGame, numPlayerWin, numDealerWin, numPush, numPlayerWinWithHighCount, numPlayerWinWith21, numPlayerWinWithDealerBust, numDealerWinWithHighCount, numDealerWinWithPlayerBust, money, maxMoney};
  }
}