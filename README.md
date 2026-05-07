# java-semester-project
Blackjack program explanation

This program uses a variety of randomly generated numbers as well as user input to simulate games of blackjack, or 21. 

A main function and two external functions are utilized in the program. Main contains a few variables, the welcome message, and the primary while loop where the other two functions are called repeatedly.
The user is prompted to enter their first bet, which is then subtracted from their total starting money. If the amount entered is greater than their starting money, they are prompted to re-enter the amount.

The blackJack function is then called and the player begins playing a hand of blackjack, where they are given their first set of cards. Another loop is entered where the user is prompted to hit or stay.
This loop continues until the user either gets a total card value greater than or equal to 21 or chooses to stay. 

The dealerHand function is then called in which a random number is generated that acts as the amount of times the dealer will draw. After drawing a random number of times between 3 and 4, the dealer's card total is returned.

Back in the blackJack function, the player and dealer's card totals are compared to see who won, lost, or both tied. A money value is then returned accordingly based on the outcome of each hand and added to the player's total money.

The original primary while loop is continued, and only ends if the user either runs out of money or chooses to stop the program, and in both cases, exits.
