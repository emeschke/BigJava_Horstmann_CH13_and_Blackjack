package blackjack;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * Created with IntelliJ IDEA.
 * User: E
 * Date: 11/15/13
 * Time: 6:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImagePanel extends JPanel {

    private BufferedImage image;
    private int upper;
    private int right;
    public ImagePanel(int upper, int right, Card card) {
        String strImage = "\\proBlackjack\\src\\blackjack\\PNG-cards-1.3\\";
        strImage +=card.getStrCardNum() + "_of_"+card.getCardSuit()+".png";
        System.out.println(strImage);
        //image = SoundImageUtils.genBuffImage("\\proBlackjack\\src\\blackjack\\PNG-cards-1.3\\king_of_clubs.png");
        image = SoundImageUtils.genBuffImage(strImage);
        this.right = right;
        this.upper = upper;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, right, upper, 150,216, null); // see javadoc for more info on the parameters
    }

}
