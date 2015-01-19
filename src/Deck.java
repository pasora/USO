/*
 * Deck contains...
 * 
 * Red, Blue, Yellow, Green
 * 0 * 1, 1 ~ 9 * 2, Draw two * 2, Draw four * 1
 * 
 */
public class Deck {
	private Card[] deck;
	private int topOfDeck;

	public Deck() {
		deck = new Card[88];
		int idx = 0;
		for (int c = 0; c < 4; c++) {
			deck[idx] = new Card(c, 0, true);
			idx++;
			deck[idx] = new Card(c, 11, true);
			idx++;
			for (int i = 0; i < 2; i++) {
				for (int v = 1; v < 11; v++) {
					deck[idx] = new Card(c, v, true);
					idx++;
				}
			}
		}
		topOfDeck = 0;
	}

	public void shuffle() {
		for (int idx = 87; idx > 0; idx--) {
			int rand  = (int) (Math.random() * (idx + 1));
			Card temp = deck[idx];
			deck[idx] = deck[rand];
			deck[rand] = temp;
		}
		topOfDeck = 0;
	}

	public Card dealCard() {
		if (topOfDeck == 87) {
			System.out.println("No card to draw.");
			System.exit(-1);
		}
		topOfDeck++;
		return deck[topOfDeck - 1];
	}

	public int cardsLeft() {
		return 87 - topOfDeck;
	}
}
