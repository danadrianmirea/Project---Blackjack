public class Dealer {
  
  ArrayList<Integer> handCards = new ArrayList<Integer>();
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
    }
  }

  public String toString() {
    if(isBust)
      return "Dealer: Bust";
    else
      return "Dealer: ";
  }
}