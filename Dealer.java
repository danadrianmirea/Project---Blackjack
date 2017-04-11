import java.util.ArrayList;

public class Dealer {
  
  ArrayList<Card> handCards = new ArrayList<>();
  boolean isHit;
  boolean isBust;
  boolean isSoft;

  public Dealer () {
    isHit = true;
    isBust = false;
    isSoft = false;
  }

  public void dealing(Card card) {
    handCards.add(card);
  }

  public int getHand() {
    
    int result = 0;

    for (int i = 0; i < handCards.size(); i++) {
      Card card = handCards.get(i);
      System.out.println(card.getCount());
    }
    return 0;
  }

  public String toString() {
    if(isBust)
      return "Dealer: Bust";
    else
      return "Dealer: ";
  }
}