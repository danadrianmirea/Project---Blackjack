//-----------------------------------------
//
// Counting.java
//
//-----------------------------------------

public class Counting {

  int count;
  boolean isCounting = false;
  String method;

  public Counting(int _count, String _method) {
    count = _count;
    if(_method.equals("none"))
      isCounting = false;
    else
      isCounting = true;
    method = _method;
  }

  public int getCount() {
    return count;
  }

  public void setMethod(String newMethod) {
    method = newMethod;
  }

  public String getMethod() {
    return method;
  }

  public boolean getIsCounting() {
    return isCounting;
  }

  public void countCard(Card card) {
    if(isCounting) {
      if (method.equals("HiLo")) {
        HiLo(card);
      } else if (method.equals("HiOpt1")) {
        HiOpt1(card);
      } else if (method.equals("HiOpt2")) {
        HiOpt2(card);
      } else if (method.equals("KO")) {
        KO(card);
      }
    }
  }

  public String toString() {
    return "Method : " + method + "\tCount: " + count;
  }

  //-------------------------------------------------------------
  //Methods

  //HiLo
  public void HiLo(Card card) {
    int cardNumber = card.cardNumber;

    if(cardNumber > 1 && cardNumber < 7)
      count++;
    else if(cardNumber > 9 || cardNumber == 1)
      count--;
    if(count < 0)
      count = 0;
  }

  public void HiOpt1(Card card) {
    int cardNumber = card.cardNumber;

    if(cardNumber >= 3 && cardNumber <= 6)
      count++;
    else if(cardNumber > 9)
      count--;
    if(count < 0)
      count = 0;
  }

  public void HiOpt2(Card card) {
    int cardNumber = card.cardNumber;

    if(cardNumber >= 2 && cardNumber <= 6) {
      count++;
      if (cardNumber >= 4 && cardNumber <= 5)
        count++;
    }
    else if(cardNumber > 9)
      count -= 2;
    if(count < 0)
      count = 0;
  }

  public void KO(Card card) {
    int cardNumber = card.cardNumber;

    if(cardNumber > 1 && cardNumber < 8)
      count++;
    else if(cardNumber > 9 || cardNumber == 1)
      count--;
    if(count < 0)
      count = 0;
  }

  //-------------------------------------------------------------
}