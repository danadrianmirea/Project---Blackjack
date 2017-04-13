public class Game {

  float startingMoney = 0;

  public static void PlayGame(int number, String countingMethod) {
    
    Deck deck = new Deck(6);
    Dealer dealer = new Dealer();
    Player player = new Player(1000);
    Counting counting = new Counting(0, countingMethod);

    //Shuffling
    deck.shuffleCard();

    System.out.println(deck.toString());
    System.out.println("\n\n");

    while(deck.getDeckSize() > 8) {

      //----------------------------------------------------
      //Initialize the Game
      if(dealer.getIsHit()) {
        Card card = deck.popCard();
        counting.countCard(card);
        dealer.dealing(card);
        System.out.println(dealer.toString());
      }
      System.out.println(counting.toString());

      Card hiddenCard = deck.popCard();

      if(player.getIsHit()) {
        Card card1 = deck.popCard();
        Card card2 = deck.popCard();
        counting.countCard(card1);
        counting.countCard(card2);
        player.playing(card1);
        player.playing(card2); 
        System.out.println(player.toString());
      }
      
      System.out.println(counting.toString());
      System.out.println("Initialize the game");
      //----------------------------------------------------

      while(player.getIsHit()) {
        Card card = deck.popCard();
        counting.countCard(card);
        player.playing(card);
        System.out.println(player.toString());
      }
      System.out.println("Player turn over");

      //Flip the hidden dealer card
      counting.countCard(hiddenCard);
      dealer.dealing(hiddenCard);
      System.out.println(dealer.toString());

      while(dealer.getIsHit()) {
        Card card = deck.popCard();
        counting.countCard(card);
        dealer.dealing(card);
        System.out.println(dealer.toString());
      }
      System.out.println("Dealer turn over");

      if(!dealer.getIsHit() && !player.getIsHit()) {
        dealer.clearHand();
        System.out.println("Dealer Hand Clear");
        player.clearHand();
        System.out.println("Player Hand Clear");
      }

      System.out.println("\n\n");
    }
    System.out.println("Game Finished");
  }

  public static void main(String args[]) {
    PlayGame(1, "HiLo");
  }
}
