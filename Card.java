package application;

public class Card {
	public int rank;
	public String suit;
    
	public Card(int rank, String suit){
		this.rank = rank;
		this.suit = suit;
	}
	

	public String getName() {
		if (rank <= 10) {
			return rank + "_of_" + suit + ".png";
		} else if (rank == 11) {
			return "jack" + "_of_" + suit + ".png";
		} else if (rank == 12) {
			return "queen" + "_of_" + suit + ".png";
		} else {
			return "king" + "_of_" + suit + ".png";
		}

	}

	public int getValue() {
		int num = 0;
		if (rank <= 10) {
			return num = rank;
		} else if (rank == 11 | rank == 12 || rank == 13) {
			return num = 10;
		} else {
			return num;
		}
	}
}
