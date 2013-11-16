package blackjack;

/**
 * Created with IntelliJ IDEA.
 * User: E
 * Date: 11/16/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Card {
    private String strCardNum;
    private String cardSuit;
    private int cardVal;
    String[] names = {"ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen",
                      "king"};
    String[] suits = {"clubs", "diamonds", "spades", "hearts"};

    public Card(int i){
        //0 is position of an ace, 1-9 is position of a number card, 10-13 is a value of 10 for 10/facecard
        int num = i%13;
        int suit = i%4;
        cardVal = num + 1;
        strCardNum = names[num];
        cardSuit = suits[suit];

        }

    public String getStrCardNum() {
        return strCardNum;
    }

    public String getCardSuit() {
        return cardSuit;
    }

    public int getCardVal() {
        return cardVal;
    }
}

