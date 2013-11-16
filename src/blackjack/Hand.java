package blackjack;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: E
 * Date: 11/15/13
 * Time: 5:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hand {
    private ArrayList<Card> cardsPlayer = new ArrayList<Card>();
    private ArrayList<Card> cardsComputer = new ArrayList<Card>();
    private boolean hand;

    public Hand() {
        hand = true;
    }

    public void addPlayerCard(Card card){
        cardsPlayer.add(card);
    }

    public ArrayList<Card> getCardsPlayer() {
        return cardsPlayer;
    }

    public ArrayList<Card> getCardsComputer() {
        return cardsComputer;
    }

    public void addCompCard(Card card){
        cardsComputer.add(card);
    }

    public int hardScorePlayer(){
        int score = 0;
        for (int i = 0; i <cardsPlayer.size() ; i++) {
            score += 1;
        }
        if (score > 21){
            hand = false;
        }
        return score;
    }

    public int hardCompScore(){
        int score = 0;
        for (int i = 0; i < cardsComputer.size(); i++) {
            score +=1;
        }
        if (score > 21){
            hand = false;
        }
        return score;
    }

    public void resetHand(){
        cardsPlayer = new ArrayList<>();
        cardsComputer = new ArrayList<>();
    }
}
