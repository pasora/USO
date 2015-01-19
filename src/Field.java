public class Field {
	private Card card;
	private int nextColor;
	private int nextValue;

	public Field(Card icard) {
		card = icard;
		nextColor = card.getColor();
		nextValue = card.getValue();
	}

	public void showCard() {
		System.out.println("--------------------");
		System.out.print("Field: ");
		System.out.println(card.getCardAsString());
		System.out.println("--------------------");
	}

	public Boolean judgeNextComputerCard(UsoHand unohand) {
		for (int i = 0; i < unohand.showNumOfCards(); i++) {
			if ((unohand.showCard(i).getColor() == this.nextColor)
					|| (unohand.showCard(i).getValue() == this.nextValue)) {
				unohand.discardCard(i, this);
				return true;
			}
		}
		System.out.println("Computer:PASS");
		return false;
	}

	public Boolean judgeNextPlayerCard(UsoHand unohand, int i) {
		if ((unohand.showCard(i).getColor() == this.nextColor)
				|| (unohand.showCard(i).getValue() == this.nextValue)) {
			return true;
		} else {
			System.out.println("You CANNOT discard this card.");
			return false;
		}

	}

	public void changeFieldCard(Card card) {
		this.card = card;
		this.nextColor = card.getColor();
		this.nextValue = card.getValue();
	}

	public void dealCard(Deck deck) {
		this.card = deck.dealCard();
		this.nextColor = card.getColor();
		this.nextValue = card.getValue();
	}

	public Card getCard() {
		return this.card;
	}
}