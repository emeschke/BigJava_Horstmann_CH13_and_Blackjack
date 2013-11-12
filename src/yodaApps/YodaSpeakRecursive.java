package yodaApps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: E
 * Date: 11/11/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class YodaSpeakRecursive {
    public static void main(String[] args) {
        //Create string and string array to read in the text and to split the text.  Read in and split the text.
        String yodaSpeak;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a sentence to reformat as yoda speak:");
        yodaSpeak = in.nextLine();
        ArrayList<String> yodaSplit = new ArrayList<String>(Arrays.asList(yodaSpeak.split(" ")));
        //Create a new stringArray that is the result of the recursive method.
        ArrayList<String> yodaRecursed = recurseYoda(yodaSplit);
        //Print the output.
        for (int i = 0; i < yodaRecursed.size(); i++) {
            System.out.print(yodaRecursed.get(i) + " ");
        }
    }

    public static ArrayList<String> recurseYoda(List<String> aList){
        //base case, return if it is null or length 0.
        if ((null == aList) || (aList.size() <= 1)) {
            return (ArrayList<String>) aList;
        }
        //recursive case(s)--Pull off the first element and store it.  Run the method recusively on the rest of the
        //array.  Add the first element back to the arrayList and return the whole arrayList.
        String first = aList.get(0);
        aList.remove(0);
        ArrayList<String> newList = recurseYoda(aList);
        newList.add(first);
        return newList;

    }
}
