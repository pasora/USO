public class Card {

	private int color;	//Red = 0, Blue = 1, Yellow = 2, Green = 3
	private int value; // Draw two = 10, Draw four = 11
	private Boolean function;

	public Card(int iColor, int iValue, Boolean iFanction) {
		color = iColor;
		value = iValue;
		function = iFanction;
	}

	public int getColor() {
		return color;
	}

	public int getValue() {
		return value;
	}
	
	public Boolean getFunction() {
		return function;
	}

	public String getColorAsString() {
		switch (color) {
			case 0:
				return "Red";
			case 1:
				return "Blue";
			case 2:
				return "Yellow";
			case 3:
				return "Green";
			default:
				return "?";
		}
	}

	public String getValueAsString() {
		switch (value) {
			case 10:
				return "Draw two";
			case 11:
				return "Draw four";
			default:
				return "" + value;
		}
	}

	public String getCardAsString() {
		return getValueAsString() + "(" + getColorAsString() + ")";
	}
	
	public void toggleFunctionStatus() {
		function = !function;
	}
}

