//-----------------------------------------
//
// Counting.java
//
//-----------------------------------------

public class Counting {

  int count;
  String method;

  public Counting(int _count, String _method) {
    count = _count;
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

  public void countCard(Card card) {
    if (method.equals("HiLo"))
      HiLo(card);
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

  //-------------------------------------------------------------
}