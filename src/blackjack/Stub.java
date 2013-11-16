package blackjack;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: ag
 * Date: 10/28/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stub {
    public static void main(String[] args) {
        CardShoe cardShoe = new CardShoe();
        for (int i = 0; i <52*6 ; i++) {
            System.out.println(cardShoe.getCard());
            Card card = cardShoe.getCard();
            System.out.println(card.getStrCardNum() + " of " + card.getCardSuit());
        }


    }
}
