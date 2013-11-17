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
    private String[] names = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen",
                      "king"};
    private String[] suits = {"clubs", "diamonds", "spades", "hearts"};

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
        //Return the value of the card as an int.
        if (cardVal<10){
            return cardVal;
        }
        else{
            return 10;
        }

    }
}

