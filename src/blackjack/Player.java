package blackjack;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: E
 * Date: 11/15/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 The class represents a player.  Player has a name (which in the instance is generic) and a playerTotal which is the
 amount of money in the player's bankroll.  There are three methods that add a bet, subtract a bet or add 1.5*bet
 in the case of blackjack.  Can also reset the player if the game restarts.

 */
public class Player {
    private String player;
    private int playerTotal;
    private int bet;
    private final static int MAX_BET = 200;
    private boolean gameInProgress;
    //Initialize the player with a generic name and 1000 dollars.
    public Player(){
        player = "Player";
        playerTotal = 1000;
        bet = 0;
        gameInProgress = false;
    }

    public String getPlayer() {
        return player;
    }

    public double getPlayerTotal() {
        return playerTotal;
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }

    public String getPlayerTotalStr() {
        return "$" + (int) playerTotal;
    }

    //The player wins, the player total is incremented by the size of the bet and original money is returned.
    public void playerWin(){
        playerTotal += bet*2;
        bet = 0;
        gameInProgress = false;
    }

    //The player gets blackjack, the player total is incremented by 2 times the bet and the original bet is returned.
    public void playerBlackjack(){
        playerTotal += bet*3;
        bet = 0;
        gameInProgress = false;
    }

    //The player loses.  Total money has already been decremented, so just reset bet variable to 0.
    public void playerLoses(){
        bet = 0;
        gameInProgress = false;
    }

    //The game is a push.  No winner, reset the bet.
    public void push(){
        playerTotal += bet;
        gameInProgress = false;
        bet = 0;
    }

    //Add bet after checking that the player has enough money.  If the total bet will exceed the table limit, set the
    //total to the table limit.
    public void addBet(int bet){
        if(bet > playerTotal || this.bet == MAX_BET){
            return;
        }
        else if (this.bet + bet <= MAX_BET){
            this.bet += bet;
            this.playerTotal -= bet;
        }
        else{
            this.playerTotal -= (200 - this.bet);
            this.bet = MAX_BET;

        }
    }

    public int getBet(){
        return bet;
    }

    public String getBetStr(){
        return "$" + bet;
    }
}

