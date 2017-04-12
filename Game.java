public class Game {

  float startingMoney = 0;

  public static void PlayGame(int number, String countingMethod) {
    
    Deck deck = new Deck(6);
    Dealer dealer = new Dealer();
    Player player = new Player(1000);
    Card card;

    //Shuffling
    deck.shuffleCard();

    System.out.println(deck.toString());

    Counting HiLo = new Counting(0, countingMethod);

    while(deck.getDeckSize() > 0) {

      if(dealer.getIsHit()) {
        card = deck.popCard();
        HiLo.countCard(card);
        dealer.dealing(card);
        System.out.println(dealer.toString());
      }

      if(player.getIsHit()) {
        card = deck.popCard();
        HiLo.countCard(card);
        player.playing(card); 
        System.out.println(player.toString());
      }

      System.out.println(HiLo.toString() + "\n\n");

      if(!dealer.getIsHit() && !player.getIsHit()) {
        System.out.println(dealer.toString());
        dealer.clearHand();
        System.out.println("Dealer Hand Clear");
        player.clearHand();
        System.out.println("Player Hand Clear");
        if(deck.getDeckSize() < 10) {
          System.out.println("Game Finished");
          break;
        }
      }
    }
  }

  public static void main(String args[]) {
    PlayGame(1, "HiLo");
  }
}
