public class Game {

  public static void PlayGame() {
    Deck deck = new Deck();

    deck.shuffleCard();

    System.out.println(deck.toString());

    System.out.println(deck.popCard().toString());

    System.out.println(deck.toString());

    Counting HiLo = new Counting(0, "HiLo");

    while(deck.getDeckSize() > 0) {
      HiLo.countCard(deck.popCard());
      System.out.println(HiLo.toString());
    }
  }

  public static void main(String args[]) {
    PlayGame();
  }
}
