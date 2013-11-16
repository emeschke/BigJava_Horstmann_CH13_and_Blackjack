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
        Player player = new Player();
        CardShoe cardShoe = new CardShoe();
        Hand hand = new Hand();
        startUp(player);

        //Class that constructs a betlistener that takes a player as input and a bet size.  It is attached to the
        //four different bet buttons with the various sizes.
        class BetListener implements ActionListener{
            int betSize;
            Player player = new Player();

            public BetListener(int betSize, Player player){
                this.betSize = betSize;
                this.player = player;
            }

            public void actionPerformed(ActionEvent e) {
                player.addBet(betSize);
                playerBetField.setText(player.getBetStr());
                playerBankroll.setText(player.getPlayerTotalStr());
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
                System.out.println(player.isGameInProgress());
                if(!player.isGameInProgress() && player.getBet()>0){
                    player.setGameInProgress(true);
                    hand.resetHand();
                    for (int i = 0; i < 2; i++) {
                        hand.addPlayerCard(cardShoe.getCard());
                        hand.addCompCard(cardShoe.getCard());
                    }
                    printCards(hand);
                    System.out.println(player.isGameInProgress());
                    //Check blackjack
                    //Print cards
                }
            }
        }

        startGameButton.addActionListener(new StartGameListener(player, hand, cardShoe));



        //ImagePanel needs to take a string also, the name of the file.
        //Put in a for loop with the cards to add cards to the display.
        ImagePanel playerPanel = new ImagePanel(0,0,new Card(3));
        //ImagePanel dealerPanel1 = new ImagePanel(0,0);
        //ImagePanel dealerPanel2 = new ImagePanel(0,0);
        this.player1.add(playerPanel);
        this.comp1.add(playerPanel);
        this.comp2.add(playerPanel);


    }


    private void startUp(Player player){
        playerBankroll.setText(player.getPlayerTotalStr());
        textArea1.setText("Welcome to the table.  Max bet is $200.  \nBlackjack pays 2:1.  No split or double down \nor " +
                          "insurance.  Good luck!!");
    }

    private void printCards(Hand hand){
        ArrayList<Card> playerCards = hand.getCardsPlayer();
        ArrayList<Card> dealerCards = hand.getCardsComputer();
        for (int i = 1; i <= playerCards.size() ; i++) {
            System.out.println(hand.getCardsPlayer().get(i));
            String panelName = "player" + i;
            ImagePanel playerPanel = new ImagePanel(0,0, hand.getCardsPlayer().get(i));
            this.player1.add(playerPanel);

        }


    }
}
