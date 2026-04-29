import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class blackjack {

    public static int blackJack(int money) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();

        ArrayList<Integer> cards = new ArrayList<Integer>();
        int cardTotal = 0;
        String play;
        boolean nb = false;

        int nextCard = rand.nextInt(2, 12);     // get first card value, 2-11
        cardTotal += nextCard;
        cards.add(nextCard);                                 // add first value to array

        while (cardTotal < 21) {    // player's hand
            System.out.println("Current cards: " + cards + ", Total = " + cardTotal);
            System.out.print("Would you like to hit or stay? (h/s): ");
            play = scnr.next();
            System.out.println();

            if (play.equals("h")) {
                nextCard = rand.nextInt(2, 12);
                cardTotal += nextCard;
                cards.add(nextCard);
            }
            else {
                break;

            }
            if ((cards.contains(11)) && (cardTotal > 21)) {                                     // if cards array contains 11 and cardTotal exceeds 21, subtract 10 to covert the 11 to 1
                System.out.println("Current cards: " + cards + ", Total = " + cardTotal);
                cardTotal -= 10;
                System.out.println("Total exceeded 21, 11 converted to 1\n");
                int x = cards.indexOf(11);      // finds the index of the 11 card
                cards.set(x, 1);                // replaces 11 with 1 at given index
            }
            if ((cards.size() == 2) && (cards.contains(10)) && (cards.contains(11))) {          // if cards array only contains 10 and 11, so cardTotal equals 21, natural blackjack win
                System.out.println("Current cards: " + cards + ", Total = " + cardTotal);
                System.out.println("\nNatural blackjack!");
                nb = true;
            }
            if (cardTotal == 21) {                                                              // if cardTotal equals 21, prints "Blackjack!" and ends the loop
                System.out.println("Current cards: " + cards + ", Total = " + cardTotal);
                System.out.println("Blackjack!");
            }
            if (cardTotal > 21) {                                                               // if cardTotal exceeds 21, prints "Bust!" and ends the loop
                System.out.println("Current cards: " + cards + ", Total = " + cardTotal);
                System.out.println("Bust!");
            }
        }

        System.out.println("\nDealers turn: \n");
        int dealerTotal = dealerHand();                 // call dealerHand method to return the dealer's hand value

        if (cardTotal > 21) {                           // cardTotal exceeds 21, automatic loss
            return 0;
        }
        if (dealerTotal > 21) {                         // cases where dealerTotal exceeds 21
            if (nb) {                                   // natural blackjack, 3:2 payout
                System.out.println("Natural blackjack! 3:2 payout! +$" + (money + money + money / 2));
                return money + money + (money / 2);
            }
            System.out.println("Win! 1:1 payout! +$" + (money + money));
            return money + money;                       // normal win, 1:1 payout
        }
        if (cardTotal == dealerTotal) {
            System.out.println("Tie! Returning bet. +$" + money);
            return money;
        }
        if (cardTotal == 21) {
            if (nb) {
                System.out.println("Natural blackjack! 3:2 payout! +$" + (money + money + money / 2));
                return money + money + (money / 2);
            }
            System.out.println("Win! 1:1 payout! +$" + (money + money));
            return money + money;
        }
        if (cardTotal > dealerTotal) {
            System.out.println("Win! 1:1 payout! +$" + (money + money));
            return money + money;
        }
        else {
            System.out.println("You lose! No payout. +$0");
            return 0;
        }

    }

    public static int dealerHand() {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();

        ArrayList<Integer> cards = new ArrayList<Integer>();
        int dealerTotal = 0;

        int nextCard = rand.nextInt(2, 12);             // exact same initialization as player hand
        dealerTotal += nextCard;
        cards.add(nextCard);

        int dealerPulls = rand.nextInt(3, 5);           // dealer will draw a random number of times, 3 - 4

        for (int i = 0; i < dealerPulls; ++i) {
            System.out.println("Dealer's current cards: " + cards + ", Total = " + dealerTotal);
            System.out.println();

            if (dealerTotal >= 21) {
                break;
            }

            nextCard = rand.nextInt(2, 12);
            dealerTotal += nextCard;
            cards.add(nextCard);


        }
        return dealerTotal;
    }

    public static void main(String [] args) {
        Scanner scnr = new Scanner(System.in);
        int money_total = 100;
        int bet = 0;
        String playing = "y";

        System.out.println("\n\t\t\t\t   --Welcome to blackjack!--");
        System.out.println("(Aces are automatically converted to 1 if card total exceeds 21)");
        System.out.println("(Normal wins are 1:1 payout, natural blackjack is 3:2 (Ace + Face card))\n");
        System.out.println("Starting balance: $" + money_total + "\n");


        while (!playing.equals("n")) {

            System.out.print("How much would you like to bet?: ");
            bet = scnr.nextInt();

            while (true) {
                if ((bet > money_total) || (bet <= 0)) {
                    System.out.print("\nEnter a valid bet: ");
                    bet = scnr.nextInt();
                } else {
                    money_total -= bet;
                    break;
                }

            }

            System.out.println(); // line spacing
            money_total += blackJack(bet);
            System.out.println("Current money total: $" + money_total);


            System.out.print("Would you like to play another hand? (y/n): ");
            playing = scnr.next();
            System.out.println(); // line spacing

            if (money_total <= 0) {
                System.out.println("You're out of money!");
                System.out.println("\n\t\t\t\t--Thanks for playing! Goodbye!--");
                break;
            }
            else if (playing.equals("n")) {
                System.out.println("\t\t\t\t--Thanks for playing! Goodbye!--");
                break;
            }
        }
    }
}