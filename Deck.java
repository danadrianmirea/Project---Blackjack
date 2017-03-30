import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Deck {

  LinkedList<Card> cardList = new LinkedList<Card>();

  public Deck() {

    for(int i = 0; i < 4; i++) {
      for(int j = 1; j < 14; j++) {
        Card card = new Card(i, j);
        cardList.add(card);
      }
    }
  }

  public void shuffleCard() {

    Collections.shuffle(cardList);
  }

  public Card popCard() {
    return cardList.pop();
  }

  public int getDeckSize() {
    return cardList.size();
  }

  public String toString() {

    String str = "";
    for(int i = 0; i < cardList.size(); i++) {
      str += (cardList.get(i).toString() + " ");
    }
    return str;
  }
}
