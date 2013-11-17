package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: E
 * Date: 11/15/13
 * Time: 6:19 PM
 * To change this template use File | Settings | File Templates.
 * This project was very frustrating to get the cards to print.  I kept getting a null point exception and it wouldn't
 * work out so I tried adding a frame for each card.  That was a mess.  Eventually the program was written with a
 * textual interface.  It is complete and would not be hard to add a panel with cards that print, I just can't figure
 * out how to do it right.
 *
 * Structure is that there is a cardshoe that deals cards.  Cards have their information about the number, suit and
 * card value.  A player sits at the table and has bet values and total money, as well as information about whether
 * the game is in progress.  A hand is dealt, which has cards and can calculate a winner between the two hands.
 *
 * It would be easy to add a panel for the computer and for the player cards if the process were more straightforward.
 *
 */
public class TableLayout {
    private JPanel mPanel;
    private JButton $50BetButton;
    private JButton $10BetButton;
    private JButton $20BetButton;
    private JButton hitButton;
    private JButton $100BetButton;
    private JPanel player1;
    private JPanel player2;
    private JPanel player3;
    private JPanel player4;
    private JPanel player6;
    private JPanel player7;
    private JPanel player8;
    private JPanel player9;
    private JPanel player10;
    private JPanel comp1;
    private JPanel comp2;
    private JPanel comp3;
    private JPanel comp4;
    private JPanel comp5;
    private JPanel comp6;
    private JPanel comp7;
    private JPanel comp8;
    private JPanel comp9;
    private JPanel comp10;
    private JPanel player5;
    private JTextField playerBetField;
    private JTextField playerBankroll;
    private JTextArea textArea1;
    private JButton stayButton;
    private JButton startGameButton;

    public static void main(String[] args) {
        //Main.  Create the frame.
        //Create the Table Layout and add it to a JFrame.
        //TableLayout tableLayout = new TableLayout();
        JFrame frame = new JFrame("Blackjack Game");
        frame.setContentPane(new TableLayout().mPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1200, 600);
        frame.setVisible(true);
    }

    public TableLayout(){
        //Constructor for the TableLayout.  Create a player, cardshoe and hand variable for the table.
        Player player = new Player();
        CardShoe cardShoe = new CardShoe();
        Hand hand = new Hand();
        //Run some start-up to print in the window.
        startUp(player);

        //Class that constructs a betlistener that takes a player as input and a bet size.  It is attached to the
        //four different bet buttons with the various sizes.  Make sure the games is not in progress first.
        class BetListener implements ActionListener{
            int betSize;
            Player player = new Player();

            public BetListener(int betSize, Player player){
                this.betSize = betSize;
                this.player = player;
            }

            public void actionPerformed(ActionEvent e) {
                if (!player.isGameInProgress()){
                    player.addBet(betSize);
                    printMoney(player);
                }
            }
        }

        $10BetButton.addActionListener(new BetListener(10, player));
        $20BetButton.addActionListener(new BetListener(20, player));
        $50BetButton.addActionListener(new BetListener(50, player));
        $100BetButton.addActionListener(new BetListener(100, player));

        class StartGameListener implements ActionListener{
            Player player = new Player();
            Hand hand = new Hand();
            CardShoe cardShoe = new CardShoe();


            private StartGameListener(Player player, Hand hand, CardShoe cardShoe){
                this.player = player;
                this.hand = hand;
                this.cardShoe = cardShoe;
            }

            public void actionPerformed(ActionEvent e){
                //Check if there is a positive bet and a game is not already in progress.  If so, set the game in
                //progress to true, reset the hands and deal two cards to each, and print the results of the hand so far.
                // Check if the player has a blackjack, in which case you pay the player out.
                if(!player.isGameInProgress() && player.getBet()>0){
                    player.setGameInProgress(true);
                    textArea1.append("\n*****************\nDealing cards.");
                    hand.resetHand();
                    hand.addPlayerCard(cardShoe.getCard());
                    hand.addPlayerCard(cardShoe.getCard());
                    hand.addCompCard(cardShoe.getCard());
                    printCards(hand);
                    //Check that the player has been dealt a blackjack.  Game ends and player is paid out.
                    if(hand.hardScoreComp()==21){
                        textArea1.append("Blackjack!!  Pays " + (player.getBet()*2) + ".");
                        player.playerBlackjack();
                        printMoney(player);
                    }

                    else{
                        textArea1.append("\nDo you want to hit or stay?\n");
                    }
                    //printCards(hand);
                    //ImagePanel playerPanel1 = new ImagePanel(0,0,new Card(3));
                    //ImagePanel dealerPanel1 = new ImagePanel(0,0, new Card(17));
                    //player6.add(playerPanel1);
                    //comp1.add(dealerPanel1);
                    //Check blackjack
                    //Print cards

                }
            }
        }

        startGameButton.addActionListener(new StartGameListener(player, hand, cardShoe));

        class HitListener implements ActionListener{
            Player player = new Player();
            Hand hand = new Hand();
            CardShoe cardShoe = new CardShoe();


            private HitListener(Player player, Hand hand, CardShoe cardShoe){
                this.player = player;
                this.hand = hand;
                this.cardShoe = cardShoe;
            }

            public void actionPerformed(ActionEvent e){
                //If the game is in progress, deal the player another card.
                if(player.isGameInProgress()){
                        hand.addPlayerCard(cardShoe.getCard());
                        printCards(hand);
                    if(hand.softScorePlayer()>21){
                        //Check if the player has lost after the card was dealt.
                        textArea1.append("\nOver 21, you lose!");
                        player.playerLoses();
                        printMoney(player);
                    }
                    else{
                        textArea1.append("\nDo you want to hit or stay?");
                    }
                }
            }
        }

        hitButton.addActionListener(new HitListener(player, hand, cardShoe));

        class StayListener implements ActionListener{
            //Once the player has chosen to stay, the computer finishes the hand by drawing cards until it has
            // >17/soft 18.  At that point it checks to see who wins and takes the appropriate action.

            Player player = new Player();
            Hand hand = new Hand();
            CardShoe cardShoe = new CardShoe();


            private StayListener(Player player, Hand hand, CardShoe cardShoe){
                this.player = player;
                this.hand = hand;
                this.cardShoe = cardShoe;
            }

            public void actionPerformed(ActionEvent e){
                if(player.isGameInProgress()){

                    //While computer soft score is less than 17, computer takes a card.
                    while(hand.winner() == 0){
                        hand.addCompCard(cardShoe.getCard());
                        System.out.println("RUnning");
                        printCards(hand);
                        printScore(hand);
                    }
                    //Check to see who wins after the loop terminates.
                    if(hand.winner() == 3){
                        printCards(hand);
                        textArea1.append("\nPush--no winner.");
                        player.push();
                    }
                    else if(hand.winner() == 1){
                        printCards(hand);
                        textArea1.append("\nComputer wins -- sorry.");
                        player.playerLoses();
                    }
                    else {
                        printCards(hand);
                        textArea1.append("\nPlayer wins!!!!!  Casino pays out...");
                        player.playerWin();
                    }
                    printMoney(player);

                }
            }
        }

        stayButton.addActionListener(new StayListener(player, hand, cardShoe));

        //ImagePanel needs to take a string also, the name of the file.
        //Put in a for loop with the cards to add cards to the display.
        ImagePanel playerPanel1 = new ImagePanel(0,0,new Card(3));
        ImagePanel dealerPanel1 = new ImagePanel(0,0, new Card(17));
        //ImagePanel dealerPanel2 = new ImagePanel(0,0, new Card(50));
        //player6.add(playerPanel);
        //comp1.add(dealerPanel1);
        //comp2.add(dealerPanel2);


    }


    private void startUp(Player player){
        playerBankroll.setText(player.getPlayerTotalStr());
        textArea1.setText("Welcome to the table.  Max bet is $200.  \nBlackjack pays 2:1.  No split, double down \nor " +
                          "insurance.  Good luck!!");
    }

    public void printCards(Hand hand){
        //Method to print the player and computer cards.
        textArea1.setText("\nPlayer's cards are:\n");
        for (int i = 0; i < hand.getCardsPlayer().size(); i++) {
            textArea1.append(hand.getCardsPlayer().get(i).getStrCardNum().toUpperCase() + "of"  +
                             hand.getCardsPlayer().get(i).getCardSuit().substring(0,1).toUpperCase()+ " ");
        }

        textArea1.append("\nComputer's cards are:\n");
        for (int i = 0; i < hand.getCardsComputer().size(); i++) {
            textArea1.append(hand.getCardsComputer().get(i).getStrCardNum().toUpperCase() + "of" +
                    hand.getCardsComputer().get(i).getCardSuit().substring(0,1).toUpperCase() + " ");
        }

        printScore(hand);

        //System.out.println(hand.getCardsPlayer().get(0).getStrCardNum());
        //ImagePanel compPanel1 = new ImagePanel(0,0, new Card(3));
        //comp1.add(compPanel1);
        //ImagePanel playerPanel2 = new ImagePanel(0,0, hand.getCardsPlayer().get(1));
        //player2.add(playerPanel2);
        //ImagePanel playerPanel3 = new ImagePanel(0,0, hand.getCardsPlayer().get(2));
        /*
        comp1.add(playerPanel3);
        ImagePanel playerPanel4 = new ImagePanel(0,0, hand.getCardsPlayer().get(3));
        comp1.add(playerPanel4);
        ImagePanel playerPanel5 = new ImagePanel(0,0, hand.getCardsPlayer().get(4));
        comp1.add(playerPanel5);
        ImagePanel playerPanel6 = new ImagePanel(0,0, hand.getCardsPlayer().get(5));
        comp1.add(playerPanel6);
        ImagePanel playerPanel7 = new ImagePanel(0,0, hand.getCardsPlayer().get(6));
        comp1.add(playerPanel7);
        ImagePanel playerPanel8 = new ImagePanel(0,0, hand.getCardsPlayer().get(7));
        comp1.add(playerPanel8);
        ImagePanel playerPanel9 = new ImagePanel(0,0, hand.getCardsPlayer().get(8));
        comp1.add(playerPanel9);
        ImagePanel playerPanel10 = new ImagePanel(0,0, hand.getCardsPlayer().get(9));
        comp1.add(playerPanel10);
        */


    }

    public void printScore(Hand hand){
        //Print the score of the computer and player with appropriate methods for dealing with aces.
        if ((hand.softScorePlayer() != hand.hardScorePlayer()) && hand.hardScorePlayer()<22){
            textArea1.append("\n*******Player score is " + hand.hardScorePlayer() + " or " + hand.softScorePlayer() +
                             ".*******");

        }
        else{
            textArea1.append("\n*******Player score is " + hand.softScorePlayer() + ".********");
        }

        if (hand.hardScoreComp()>17){
            textArea1.append("\n#####Computer score is " + hand.hardScoreComp() + ".#####");

        }
        else{
            textArea1.append("\n#####Computer score is " + hand.softScoreComputer() + ".#####");
        }
    }

    public void printMoney(Player player){
        //Print the bankroll.
        playerBetField.setText(player.getBetStr());
        playerBankroll.setText(player.getPlayerTotalStr());
    }
}
