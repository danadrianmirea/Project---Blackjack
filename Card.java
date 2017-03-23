public class Card {

  int cardSuit, cardNumber;

  /*
  cardSuit
    0 : Spade
    1 : Heart
    2 : Diamond
    3 : Club
  cardNumber
    1 : Ace
    2 ~ 10 : Number
    11 : Jack
    12 : Queen
    13 : King
  */

  public Card(int _cardSuit, int _cardNumber) {
    cardSuit = _cardSuit;
    cardNumber = _cardNumber;
  }

  public int getCardSuit() {
    return cardSuit;
  }

  public int getCardNumber() {
    return cardNumber;
  }

  public String toString() {

    String cardSuitStr = "", cardNumberStr = "";

    switch(cardSuit) {
      case 0:
        cardSuitStr = "Spade";
        break;
      case 1:
        cardSuitStr = "Heart";
        break;
      case 2:
        cardSuitStr = "Diamond";
        break;
      case 3:
        cardSuitStr = "Club";
    }

    switch(cardNumber) {
      case 11:
        cardNumberStr = "Jack";
        break;
      case 12:
        cardNumberStr = "Queen";
        break;
      case 13:
        cardNumberStr = "King";
        break;
      default:
        cardNumberStr = Integer.toString(cardNumber);
    }

    return cardSuitStr + "-" + cardNumberStr;
  }
}
