public class Game {

  public static void PlayGame() {
    Deck deck = new Deck();

    deck.shuffleCard();

    System.out.println(deck.toString());
  }

  public static void main(String args[]) {
    PlayGame();
  }
}
