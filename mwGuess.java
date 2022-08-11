//Montana Williams
//CS 145
//7/1/2022
//
//This program is for a number guessing game where the user must guess a number in a defined range.
//Extra things: I allow the user to choose the range of their game (see instructions method)
//              I also made it so the program can't be crashed by a mistyped response
//              from user (see integerChecker method)

import java.util.*;

public class mwGuess {

    public static void main(String[] args) {
        
        Scanner input=new Scanner(System.in);
        Random rando=new Random();//this could be moved to method

        //Initialize
        boolean gameGo=true;
        int gameCount=0;
        int bestGame=20000;//Silly answer to begin
        int totalGuess=0;
        int numberTries;

        int range=instructions(input);//User selects the range during the instructions.

        do{
           numberTries=gamePlay(range,rando,input);
           totalGuess=+numberTries;
           gameCount++;

           if(numberTries<bestGame){
            bestGame=numberTries;
           } //end if

           gameGo=playAgain(input);
        }while(gameGo==true);

        summary(range, totalGuess, bestGame, gameCount);

    }//end main

    //The following method provides instructions for the game and asks the user to set a guessing range.
    //Requires a scanner object and returns the upper integer in the range.
    public static int instructions(Scanner input) {

        int range=-1;

        System.out.println("Greetings and salutations.\nWelcome to the Number Guessing Game where you guess numbers.");
        System.out.println("Here's how it works. I'll pick a number in a range you choose, and you guess until you get it right.\n");
        System.out.print("How wide do you want the guessing range to be?\n1-");

        range=integerChecker(input);
     
        System.out.println("\nPerfect. Let's begin!");
        System.out.println();

        return range;
    }//end instructions

    //This method ensures the user is inputting an integer, preventing the program from crashing.
    //Requires an input of a scanner.
    public static int integerChecker(Scanner input) {
        int number=-1;

        do{
            try{
                number=input.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Please type an integer.");
                input.next();
            }
        }while(number==-1);

        return number;
    }//end integerChecker

    //The following method allows the user to play one round of the guessing game.
    //Requires the top number of the range, the random number object,
    //and a scanner object, and returns the guesses in that round.
    public static int gamePlay(int range, Random rando, Scanner input) {
        
        System.out.println("I'm thinking of a number between 1 and "+range);
        int guess=0;
        int randomNumber=rando.nextInt(range)+1;
        int tries=0;
        
        do{
            guess=integerChecker(input);

            if(guess>randomNumber){
                System.out.println("Nope! Try a lower number.");
                tries++;
            }else if(guess<randomNumber){
                System.out.println("Nope! Try a higher number.");
                tries++;
            }//end if/else

        }while(randomNumber!=guess);
        System.out.println("You got it! It only took you "+tries+" tries!");

        return tries;

    }//end gamePlay

    //The following method asks the user if they'd like to play again.
    //Requires a scanner input, and returns a boolean of whether they'd like to play or not.
    public static boolean playAgain(Scanner input) {
        System.out.println("Do you want to play again?");
        boolean gameGo;
        
        String response=input.next();
        response=response.substring(0,1);

        switch(response){
            case "y":   case "Y":   
                gameGo=true;
                System.out.println("Let's go!\n");
                break;
            case "n":   case "N":
                gameGo=false;
                System.out.println("Aw, okay!\n");
                break;
            default:
                System.out.println("I'm sorry, I don't understand. I will take that as a no.\n");
                gameGo=false;
                break;
        }//end switch/case

        return gameGo;
    }//end playAgain

    //The following method provides stats on the games played when the user quits.
    //Requires top number of the range, how many guesses across all the games,
    //how many guesses in their best game, and how many games played.
    public static void summary(int range, int totalGuess, int bestGame, int gameCount) {

        double average=totalGuess/gameCount;

        System.out.println("Great job! Here are your results with a range of 1 to "+range);
        System.out.println("Total games: "+gameCount);
        System.out.println("Total guesses: "+totalGuess);
        System.out.printf("Average guess per game: %.2f\n", average);
        System.out.printf("Best game: %d guesses\n", bestGame);
        System.out.println("Good game!");
    
    }//end summary

}//end class