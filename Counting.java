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

  //-------------------------------------------------------------
}