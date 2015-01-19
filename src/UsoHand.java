public class UsoHand {
	private Card[] hand;
	private int numOfCards;

	UsoHand() {
		hand = new Card[108];
		numOfCards = 0;
	}

	public void initializeHands(Deck deck) {
		hand[0] = deck.dealCard();
		hand[1] = deck.dealCard();
		hand[2] = deck.dealCard();
		hand[3] = deck.dealCard();
		hand[4] = deck.dealCard();
		hand[5] = deck.dealCard();
		hand[6] = deck.dealCard();
		numOfCards = 7;
	}

	public Card showCard(int i) {
		return hand[i];
	}

	public void showAllCards() {
		for (int i = 0; i < numOfCards; i++) {
			System.out.print(i + 1 + ": ");
			System.out.println(hand[i].getCardAsString());
		}
	}

	public int showNumOfCards() {
		return numOfCards;
	}

	public void discardCard(int cardNumber, Field field) {
		field.changeFieldCard(hand[cardNumber]);
		System.out.println("====================");
		System.out.println("Discard: " + hand[cardNumber].getCardAsString());
		System.out.println("====================");
		for (int i = cardNumber; i < numOfCards + 1; i++) {
			hand[i] = hand[i + 1];
		}
		numOfCards--;
	}

	public void draw(Deck deck) {
		if (deck.cardsLeft() == 0) {
			System.out.println("No card to draw.");
		}
		hand[numOfCards] = deck.dealCard();
		numOfCards++;
	}

	public void drawTwo(Deck deck) {
		for (int i = 0; i < 2; i++) {
			if (deck.cardsLeft() == 0) {
				System.out.println("No card to draw.");
			}
			hand[numOfCards] = deck.dealCard();
			numOfCards++;
		}
	}

	public void drawFour(Deck deck) {
		for (int i = 0; i < 4; i++) {
			if (deck.cardsLeft() == 0) {
				System.out.println("No card to draw.");
			}
			hand[numOfCards] = deck.dealCard();
			numOfCards++;
		}
	}

}
