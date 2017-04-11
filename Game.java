public class Game {

  float startingMoney = 0;

  public static void PlayGame(int number) {
    
    Deck deck = new Deck();
    Dealer dealer = new Dealer();
    Card card;

    //Shuffling
    deck.shuffleCard();

    System.out.println(deck.toString());

    Counting HiLo = new Counting(0, "HiLo");

    while(deck.getDeckSize() > 0) {

      card = deck.popCard();
      HiLo.countCard(card);
      if(dealer.getIsHit()) {
        dealer.dealing(card);
        System.out.println(dealer.getHand());
      } else {
        System.out.println(dealer.getHand());
        dealer.clearHand();
        System.out.println("Dealer Hand Clear");
      }

      

      System.out.println(HiLo.toString());
    }
  }

  public static void main(String args[]) {
    PlayGame(1);
  }
}
