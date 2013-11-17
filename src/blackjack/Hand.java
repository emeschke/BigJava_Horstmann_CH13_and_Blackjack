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
        //Count the score and number of aces.  If there are some aces, calculate a hard score that is a soft score + 10.
        int score = 0;
        int aces = 0;
        for (int i = 0; i < cardsPlayer.size(); i++) {
            score += cardsPlayer.get(i).getCardVal();
            if (cardsPlayer.get(i).getCardVal() == 1){
                aces += 1;
            }
        }
        if (aces>0){
            return score + 10;
        }
        else{
            return score;
        }
    }

    //Calculate the soft score, which is the score with no aces as 11s.
    public int softScorePlayer(){
        int score = 0;
        int aces = 0;
        for (int i = 0; i < cardsPlayer.size(); i++) {
            score += cardsPlayer.get(i).getCardVal();
        }
        return score;
    }

    //Count the score and number of aces.  If there are some aces, calculate a hard score that is a soft score + 10.
    public int hardScoreComp(){
        int score = 0;
        int aces = 0;
        for (int i = 0; i < cardsComputer.size(); i++) {
            score += cardsComputer.get(i).getCardVal();
            if (cardsComputer.get(i).getCardVal() == 1){
                aces += 1;
            }
        }
        if (aces>0){
            return score + 10;
        }
        else{
            return score;
        }
    }

    //Calculate the soft score, which is the score with no aces as 11s.
    public int softScoreComputer(){
        int score = 0;
        int aces = 0;
        for (int i = 0; i < cardsComputer.size(); i++) {
            score += cardsComputer.get(i).getCardVal();
        }
        return score;
    }

    public void resetHand(){
        cardsPlayer = new ArrayList<>();
        cardsComputer = new ArrayList<>();
    }


    public int winner(){
        int compScore;
        int playerScore;
        //Check to see if there is a winner.  Return 0 for no winner yet, 1 for computer winner, 2 for player winner
        // 3 for a push.
        //If player soft score of player is >21, player has lost.
        if(softScorePlayer() > 21){
            return 1;
        }
        //If the computer score is less than 18, there is no winner.
        else if(softScoreComputer()<17 && hardScoreComp()<18){
            return 0;
        }
        //If the computer's score is over 21, it has lost.
        else if (softScoreComputer() > 21){
            return 2;
        }
        //If the computer soft score is greater than 17, compare the scores.
        else{
            //Get computer score, the max of the hard/soft under 22.
            if(Math.max(softScoreComputer(),hardScoreComp()) < 22){
                compScore = Math.max(softScoreComputer(),hardScoreComp());
            }
            else{
                compScore = softScoreComputer();
            }
            //Get player score, the max of the hard/soft under 22.
            if(Math.max(softScorePlayer(),hardScorePlayer()) < 22){
                playerScore = Math.max(softScorePlayer(),hardScorePlayer());
            }
            else{
                playerScore = softScorePlayer();
            }
            //Compare the scores to see if they are even, computer loses, or computer wins.
            if(compScore == playerScore ){
                return 3;
            }
            else if(compScore < playerScore){
                return 2;
            }
            else if(compScore > playerScore){
                return 1;
            }

        }
        return 0;
    }
}
