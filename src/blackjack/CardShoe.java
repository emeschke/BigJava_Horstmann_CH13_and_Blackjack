package blackjack;

/**
 * Created with IntelliJ IDEA.
 * User: E
 * Date: 11/15/13
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 This replicates a card Shoe that is used to deal the cards.  It is an array of zeros of the size of six decks.  A
 card is selected randomly (between 0-255.)  The card selector checks that the card has not already been selected
 (a 1 in the array of cards.)  If it hasn't been selected, the value is changed to 1 in the array and the card number
 is returned.  Also keep track of how many cards have been selected.
 Uses the default constructor.
 */
public class CardShoe {
    private int[] cardsDeck = new int[52*6];
    private int cardCount;

    //Shuffle the card, reset all values in the card deck to 0 and the card count to zero.
    public void shuffle(){
        for (int i = 0; i <cardsDeck.length; i++) {
            cardsDeck[i]  = 0;
        }
        cardCount = 0;
    }

    //Keep selecting cards until one comes up that hasn't been selected yet.  When a card is selected, the card count
    //is incremented and the space in the cardDeck is changed to 1.  The card number mod 52 is returned.
    public Card getCard(){
        while(true){
            int drawCard = (int) (Math.random()*52*6);
            if (cardsDeck[drawCard] == 0){
                cardsDeck[drawCard] = 1;
                cardCount += 1;
                return new Card(drawCard%52);
            }
        }
    }

    public int getCardCount(){
        return cardCount;
    }
}
