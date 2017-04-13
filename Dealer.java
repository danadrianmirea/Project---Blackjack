import java.util.ArrayList;
import java.util.Arrays;

public class Dealer {
  
  ArrayList<Card> handCards;
  boolean isHit;
  boolean isBust;

  public Dealer () {
    handCards = new ArrayList<>();
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

    if(isAllHigher(counts, 21)) {
      isBust = true;
      isHit = false;
    } else {
      if (isAnyHigher(counts, 17)) {
        isHit = false;
      } else {
        if (counts.contains(17)) {
          if(counts.size() != 2) {
            isHit = false;
          }
        }
      }
    }
  }

  public static boolean isAllHigher(ArrayList<Integer> array, int num) {
    for (int i = 0; i < array.size(); i++) {
      if(array.get(i) <= num)
        return false;
    }
    return true;
  }

  public static boolean isAnyHigher(ArrayList<Integer> array, int num) {
    for (int i = 0; i < array.size(); i++) {
      if(array.get(i) > num)
        return true;
    }
    return false;
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
    String text = "Dealer: ";
    text += "Hand Cards: ";
    for (int i = 0; i < handCards.size(); i++) {
      text += (handCards.get(i).toString() + " ");
    }
    text += ("|| Hand: " + getHand());
    if(isBust)
      return text + "\n" + "Dealer Bust";
    return text;
  }
}