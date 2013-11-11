package yodaApps;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: E
 * Date: 11/11/13
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class YodaSpeak {
    public static void main(String[] args) {
        //Create string and string array to read in the text and to split the text.  Read in and split the text.
        String yodaSpeak;
        String[] yodaSplit;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a sentence to reformat as yoda speak:");
        yodaSpeak = in.nextLine();
        yodaSplit = yodaSpeak.split(" ");
        //Use a four loop to print the array list backward.  Add a space after each word to format correctly.
        for (int i = yodaSplit.length-1; i >= 0 ; i--) {
            System.out.print(yodaSplit[i]);
            System.out.print(" ");
        }
    }
}
