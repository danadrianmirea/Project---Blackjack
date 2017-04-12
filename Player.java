import java.util.ArrayList;
import java.util.Arrays;

public class Player {
  
  ArrayList<Card> handCards;
  float money;
  boolean isHit;
  boolean isBust;

  public Player(float startMoney) {
    handCards = new ArrayList<>();
    money = startMoney;
    isHit = true;
    isBust = false;
  }

  public float getMoney() {
    return money;
  }

  public void betMoney(float _money) {
    money -= _money;
  }

  public boolean getIsHit() {
    return isHit;
  }

  public void setHit(boolean _isHit) {
    isHit = _isHit;
  }

  public void playing(Card card) {
    handCards.add(card);

    ArrayList<Integer> counts = getHand();
    for(int i = 0; i < counts.size(); i++) {
      if (counts.get(i) >= 17 && counts.size() == 1) {
        isHit = false;
      }
      else if (counts.get(i) > 21) {
        System.out.println("Player Bust!");
        isBust = true;
        isHit = false;
      }
    }
  }

  public ArrayList<Integer> getHand() {
    
    ArrayList<Integer> result = new ArrayList<>(Arrays.asList());;

    for (int i = 0; i < handCards.size(); i++) {
      Card card = handCards.get(i);
      ArrayList<Integer> cardCount = card.getCount();
      if (result.size() == 0)
        result = (ArrayList<Integer>) cardCount.clone();
      else {
        ArrayList<Integer> preResult = (ArrayList<Integer>) result.clone();
        for (int x = 0; x < cardCount.size(); x++) {
          for (int y = 0; y < preResult.size(); y++) {
            if (x == 0) {
              result.set(y, result.get(y) + cardCount.get(0));
            } else {
              result.add(result.get(y) + cardCount.get(x));
            }
          }
        }
      }
    }
    return result;
  }

  public void clearHand() {
    handCards = new ArrayList<>();
    isHit = true;
    isBust = false;
  }

  public String toString() {
    String text = "Player: ";
    text += "Hand Cards: ";
    for (int i = 0; i < handCards.size(); i++) {
      text += (handCards.get(i).toString() + " ");
    }
    text += (" || Hand: " + getHand());
    return text;
  }
}