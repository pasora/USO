import java.util.InputMismatchException;
import java.util.Scanner;

public class UnoPlay {
	// Input method
	public static int input(UnoHand unohand) {
		System.out.println("Please input card number you want to discard.");
		try {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			if (0 <= i && i <= unohand.showNumOfCards()) {
				return i;
			} else {
				System.out.println("Invalid number.");
				return -1;
			}
		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			System.out.println("Not a number.");
			return -1;
		}
	}

	// Refresh field's card if no one can discard.
	public static void refresh(Field field, Deck deck) {
		field = new Field(deck.dealCard());
		System.out.println("********************");
		System.out.println("No player can discard, refresh field.");
		System.out.println("********************");
		field.showCard();
	}

	public static void main(String[] args) {
		Deck deck;
		Field field;
		UnoHand playerHands;
		UnoHand computerHands1;
		UnoHand computerHands2;
		int pass;

		playerHands = new UnoHand();
		computerHands1 = new UnoHand();
		computerHands2 = new UnoHand();

		deck = new Deck();
		deck.shuffle();

		playerHands.initializeHands(deck);
		computerHands1.initializeHands(deck);
		computerHands2.initializeHands(deck);

		field = new Field(deck.dealCard());

		// Player's first turn
		System.out.println("★★★★★★★★★★★★");
		System.out.println("★   USO GAME!!!  ★");
		System.out.println("★★★★★★★★★★★★");
		field.showCard();
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Your turn.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		System.out.println("0: PASS");
		playerHands.showAllCards();
		System.out.println("--------------------");
		// Prompt player's input.
		while (true) {
			int i = input(playerHands);
			if (i != -1) {
				if (i == 0) {
					System.out.println("Player PASS.");
					pass = 1;
					break;
				} else {
					if (field.judgeNextPlayerCard(playerHands, i - 1)) {
						playerHands.discardCard(i - 1, field);
						pass = 0;
						break;
					}
				}
			}
		}
		field.showCard();
		System.out.println("--------------------");
		System.out.println("You have " + (playerHands.showNumOfCards())
				+ " cards.");
		System.out.println("--------------------");

		while (true) {
			// Computer1's turn
			// Deal with Draw two or Draw four
			if (field.getCard().getFunction()) {
				if (field.getCard().getValue() == 10) {
					computerHands1.drawTwo(deck);
					field.getCard().toggleFunctionStatus();
				} else if (field.getCard().getValue() == 11) {
					computerHands1.drawFour(deck);
					field.getCard().toggleFunctionStatus();
				}
			}
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Computer1's turn.");
			System.out.println("~~~~~~~~~~~~~~~~~~~~");

			// Wait 3 seconds.
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Determine which card discard.
			if (!field.judgeNextComputerCard(computerHands1))
				pass++;
			else
				pass = 0;

			if (computerHands1.showNumOfCards() == 1) {
				// If Computer1 has only one card, call USO.
				System.out.println("<><><><><><><><><><><><>");
				System.out.println("Computer 1 calls UNO!");
				System.out.println("<><><><><><><><><><><><>");
			} else if (computerHands1.showNumOfCards() == 0) {
				// If Computer2 has no card, finish this game.
				System.out.println("★★★★★★★★★★★★");
				System.out.println("Computer 1 WIN!");
				System.out.println("★★★★★★★★★★★★");
				System.out.println("You lose.");
				System.exit(0);
			}

			field.showCard();

			System.out.println("--------------------");
			System.out.println("Computer1 has "
					+ computerHands1.showNumOfCards() + " cards.");
			System.out.println("--------------------");

			// If 3 players have passed consecutively, refresh field card.
			if (pass == 3) {
				refresh(field, deck);
				pass = 0;
			}

			// Computer2's turn
			// Deal with Draw two or Draw four
			if (field.getCard().getFunction()) {
				if (field.getCard().getValue() == 10) {
					computerHands2.drawTwo(deck);
					field.getCard().toggleFunctionStatus();
				} else if (field.getCard().getValue() == 11) {
					computerHands2.drawFour(deck);
					field.getCard().toggleFunctionStatus();
				}
			}

			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Computer2's turn.");
			System.out.println("~~~~~~~~~~~~~~~~~~~~");

			// Wait 3 seconds.
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Determine which card discard.
			if (!field.judgeNextComputerCard(computerHands2))
				pass++;
			else
				pass = 0;

			if (computerHands2.showNumOfCards() == 1) {
				// If Computer2 has only one card, call USO.
				System.out.println("<><><><><><><><><><><><>");
				System.out.println("Computer 2 calls USO!");
				System.out.println("<><><><><><><><><><><><>");
			} else if (computerHands2.showNumOfCards() == 0) {
				// If Computer2 has no card, finish this game.
				System.out.println("★★★★★★★★★★★★");
				System.out.println("Computer 2 WIN!");
				System.out.println("★★★★★★★★★★★★");
				System.out.println("You lose.");
				System.exit(0);
			}

			field.showCard();

			System.out.println("--------------------");
			System.out.println("Computer2 has "
					+ computerHands2.showNumOfCards() + " cards.");
			System.out.println("--------------------");

			// If 3 players have passed consecutively, refresh field card.
			if (pass == 3) {
				refresh(field, deck);
				pass = 0;
			}

			// Player's turn
			// Deal with Draw two or Draw four
			if (field.getCard().getFunction()) {
				if (field.getCard().getValue() == 10) {
					playerHands.drawTwo(deck);
					field.getCard().toggleFunctionStatus();
				} else if (field.getCard().getValue() == 11) {
					playerHands.drawFour(deck);
					field.getCard().toggleFunctionStatus();
				}
			}

			System.out.println();
			System.out.println("--------------------");
			System.out.println("Your turn.");
			System.out.println("--------------------");
			System.out.println("0: PASS");
			playerHands.showAllCards();
			System.out.println("--------------------");

			// Prompt player's input.
			while (true) {
				int i = input(playerHands);
				if (i != -1) {
					if (i == 0) {
						System.out.println("Player PASS.");
						pass++;
						break;
					} else {
						if (field.judgeNextPlayerCard(playerHands, i - 1)) {
							playerHands.discardCard(i - 1, field);
							pass = 0;
							break;
						}
					}
				}
			}

			if (playerHands.showNumOfCards() == 1) {
				// If player has only one card, call USO.
				System.out.println("<><><><><><><><><><><><>");
				System.out.println("You call USO!");
				System.out.println("<><><><><><><><><><><><>");
			} else if (playerHands.showNumOfCards() == 0) {
				// If player has no card, finish this game.
				System.out.println("★★★★★★★★★★★★");
				System.out.println("You WIN! Congratulation!");
				System.out.println("★★★★★★★★★★★★");
				System.exit(0);
			}

			field.showCard();

			System.out.println("--------------------");
			System.out.println("You have " + (playerHands.showNumOfCards())
					+ " cards.");
			System.out.println("--------------------");

			// If 3 players have passed consecutively, refresh field card.
			if (pass == 3) {
				refresh(field, deck);
				pass = 0;
			}
		}
	}
}
