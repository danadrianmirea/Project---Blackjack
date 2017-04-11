import java.util.ArrayList;
import java.util.Arrays;

public class Dealer {
  
  ArrayList<Card> handCards = new ArrayList<>();
  boolean isHit;
  boolean isBust;

  public Dealer () {
    isHit = true;
    isBust = false;
  }

  public boolean getIsHit() {
    return isHit;
  }

  public boolean getIsBust() {
    return isBust;
  }

  public void dealing(Card card) {
    handCards.add(card);

    ArrayList<Integer> counts = getHand();
    for(int i = 0; i < counts.size(); i++) {
      if (counts.get(i) >= 17 && counts.size() == 1) {
        isHit = false;
      }
      else if (counts.get(i) > 21) {
        System.out.println("Dealer Bust!");
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
    if(isBust)
      return "Dealer: Bust";
    else
      return "Dealer: ";
  }
}