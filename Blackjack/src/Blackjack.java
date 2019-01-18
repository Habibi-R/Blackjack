import java.util.Scanner;
public class Blackjack {
		private static final int HEARTS = 0;
		private static final int CLUBS = 0;
		private static final int DIAMONDS = 0;
		private static final int JACK = 11;
		private static final int KING = 12;
		private static final int QUEEN = 13;
		private static final int ACE = 1;				//Variable Declarations
		static Scanner input = new Scanner(System.in);
		static int cardType;
		static int cardSuit;
		static int cardValue = 0;
		static int DealercardValue = 0;
		static int numAces = 0;
		static int numAcesDealer = 0;
		static int totalNoAces = 0;
		static int numSuits = 4;
		static int numCardTypes = 13;
		static int wallet =500;
		static int currentBet;
		static boolean onlyTwo = true;
		static boolean wasDD;
	
	public static void main(String[] args) {
		boolean playAgain = true;
		
		System.out.println("What is your name?");
		String name = input.nextLine();				//Asking user for name, finding first name.
		
		while(wallet > 0 && playAgain){
			int currentBet = getBet(wallet);
			boolean isWinner = playHand(wallet, onlyTwo );
			displayWorL(isWinner, wasDD, name, currentBet);
			
			if(wallet>0){
			playAgain = playAgain();
			}else{
				System.out.println("You lost all your money! Do not gamble!");
			}
		}
		System.out.println("Play again sometime!");	
			
	}
		private static boolean playAgain() {
           String answer = "";
			cardValue= 0;
			DealercardValue = 0;
			while(!(answer.equals("y") || answer.equals("yes") || (answer.equals("n") || answer.equals("no")))){
				System.out.println("Want to play again?");
				answer = input.nextLine();
				answer.toLowerCase();
			}
				if(answer.equals("n") || answer.equals("no")){
					return false;
			}else if(answer.equals("y") || answer.equals("yes")){
				return true;
			}
		return false;
	
		}
		
		private static void displayWorL(boolean isWinner,boolean wasDD, String name, int currentBet){
			if(isWinner && wasDD){
				wallet += currentBet*2;
				System.out.println(name + " wins! You won " + currentBet*2 + " dollars.");
			}else if(!isWinner && wasDD){
				wallet -= currentBet*2;
				System.out.println(name + " loses! You lost " + currentBet*2 + " dollars.");
			}else if(isWinner){
				wallet += currentBet;
				System.out.println(name + " wins! You won " + currentBet + " dollars.");
			}else{
				wallet -= currentBet;
				System.out.println(name + " loses! You lost " + currentBet + " dollars.");
			}
		}
		
		private static String getChoice(boolean onlyTwo){
			String hOrT = "";
			while( !(hOrT.equals("hit") || hOrT.equals("stand") || hOrT.equals("double down") || hOrT.equals("double"))){
				
				
				System.out.print("Do you want to hit or stand?");
				if(onlyTwo){
					System.out.println("\b or perhaps double down?");
				}
				hOrT = input.nextLine();
				hOrT.toLowerCase();
			}
			return hOrT;
		}
		
		static String dealerUpdate(String dealerHand){
			while(DealercardValue < 17 && DealercardValue < cardValue){
				String newCardDealer = getCardDealer();
				dealerHand+=" " + newCardDealer;
			}
			return dealerHand;
		}
		
		 static boolean playHand(int wallet, boolean onlyTwo) {
			wasDD = false;
			boolean playerDone = false;
			System.out.println("Your hand is:");
			String playerHand = getCard() + " " + getCard();
			System.out.println(playerHand);
			System.out.println("Dealer's hand is:");
			String dealerHand = getCardDealer();
			System.out.println(dealerHand + " XX");
			
			while(!(wasDD && playerDone)){
				if(cardValue == 21){
					System.out.println("***************************");
					System.out.println("Final Result: \n Player: " + playerHand + " ... " + cardValue + "\n Dealer: " + dealerHand + " ... " + DealercardValue);
					System.out.println("***************************");
					return true;
				}else if(cardValue > 21){
					System.out.println("***************************");
					System.out.println("Final Result: \n Player: " + playerHand + " ... " + cardValue + "\n Dealer: " + dealerHand + " ... " + DealercardValue);
					System.out.println("***************************");
					return false;
				}else if(DealercardValue > 21){
					System.out.println("***************************");
					System.out.println("Final Result: \n Player: " + playerHand + " ... " + cardValue + "\n Dealer: " + dealerHand + " ... " + DealercardValue);
					System.out.println("***************************");
					return true;
				}
				String hOrT = getChoice(onlyTwo);
				if(hOrT.equals("hit")){
				String newCard = getCard();
				System.out.println("Your new hand is:");
				playerHand+=" " + newCard;
				System.out.println(playerHand);
				System.out.println("Dealer's hand is: ");
				System.out.println(dealerHand + " " + "XX");
				hOrT = "";
				onlyTwo = false;
				}
				else if(hOrT.equals("stand")){
				playerDone = true;
				dealerHand = dealerUpdate(dealerHand);
				hOrT = "";
				break;
			}                                        
				else{
					wasDD = true;
					playerDone = true;
					dealerHand = dealerUpdate(dealerHand);
					hOrT = "";
				}
			}
			if(cardValue > 21 && numAces ==1){
				cardValue -= 10;
			}else if(DealercardValue >17 && numAcesDealer == 1){
				DealercardValue-=10;
			}
			
			if(cardValue > 21){
				System.out.println("***************************");
				System.out.println("Final Result: \n Player: " + playerHand + " ... " + cardValue + "\n Dealer: " + dealerHand + " ... " + DealercardValue);
				System.out.println("***************************");
				return false; 
			}else if(cardValue == 21){
				System.out.println("***************************");
				System.out.println("Final Result: \n Player: " + playerHand + " ... " + cardValue + "\n Dealer: " + dealerHand + " ... " + DealercardValue);
				System.out.println("***************************");
				return true;
			}else if(cardValue >= DealercardValue){
				System.out.println("***************************");
				System.out.println("Final Result: \n Player: " + playerHand + " ... " + cardValue + "\n Dealer: " + dealerHand + " ... " + DealercardValue);
				System.out.println("***************************");
				return true; 
			}else if(DealercardValue > 21){
				System.out.println("***************************");
				System.out.println("Final Result: \n Player: " + playerHand + " ... " + cardValue + "\n Dealer: " + dealerHand + " ... " + DealercardValue);
				System.out.println("***************************");
				return true;
			}
		System.out.println("***************************");
		System.out.println("Final Result: \n Player: " + playerHand + " ... " + cardValue + "\n Dealer: " + dealerHand + " ... " + DealercardValue);
		System.out.println("***************************");	
		return false;
			}

		static int getBet(int wallet){
			boolean validBet = false;
			while(!validBet){
				System.out.print("Please enter your bet, you have " + wallet + " dollars...");
				String strBet = input.nextLine();
				int bet = -1;
				try{
					bet=Integer.parseInt(strBet);
					
					if(bet>0 && bet<=wallet){
						return bet;
					}else if(bet<=0){
						System.out.println("You need to bet something");
					}else{
						System.out.println("You don't have that much");
					}
				}catch(Exception ex){
					System.out.println("Please enter a valid bet...");
				}
			}
			return 0;
}
		
	 static String getCard(){
		 String suit = "";
		 String type = "";
		 cardType = (int) (numCardTypes*Math.random())+1;
		 cardSuit = (int) (numSuits*Math.random());
		 
		 if(cardSuit == HEARTS){
			suit = "H";
		 }else if(cardSuit == CLUBS){
			 suit = "C";
		 }else if(cardSuit == DIAMONDS){	//Finds Card Suit
			 
				 suit = "D";
		 }else{
			 suit = "S";
		 }
		 
		 if(cardType == ACE){
			 type = "A";
			 cardValue += 11;				 
			 numAces++;
			 return type + suit+" ";
		 }else if(cardType == JACK){
			 type = "J";
			 cardValue += 10;	 
			 return type + suit+" ";
		 }else if(cardType == QUEEN){	//Finds Card Type, Value
			 type = "Q";
			 cardValue += 10;	 
			 return type + suit+" ";
		 }else if(cardType == KING){
			 type = "K";
			 cardValue += 10;	 
			 return type + suit+" ";
		 }else{
		 cardValue += cardType;	 
		 return cardType+suit+ " ";
		 }
	 }
		
	 static String getCardDealer(){
		 String suit = "";
		 String type = "";
		 cardType = (int) (numCardTypes*Math.random())+1;
		 cardSuit = (int) (numSuits*Math.random());
		 
		 if(cardSuit == HEARTS){
			suit = "H";
		 }else if(cardSuit == CLUBS){
			 suit = "C";
		 }else if(cardSuit == DIAMONDS){	//Finds Card Suit ( For dealer )
				 suit = "D";
		 }else{
			 suit = "S";
		 }		 
		 if(cardType == ACE){
			 type = "A";
			 DealercardValue+=11;
			 numAcesDealer++;
			 return type + suit+" ";
		 }else if(cardType == JACK){
			 type = "J";
			 DealercardValue += 10;	 
			 return type + suit+" ";
		 }else if(cardType == QUEEN){	//Finds Card Type, Value ( For Dealer ) 
			 type = "Q";
			 DealercardValue += 10;	 	 
			 return type + suit+" ";
		 }else if(cardType == KING){
			 type = "K";
			 DealercardValue += 10;	  
			 return type + suit+" ";
		 }else{
		 DealercardValue += cardType;	 
		 return cardType+suit+ " ";
		 }
	 }
}
