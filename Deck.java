package application;


import java.util.ArrayList;

public class Deck {
	Card[] deck = new Card[52];
	ArrayList<Integer> card =new ArrayList<Integer>();
	int lastcardindex = 0;
	int dealerSum = 0;
	int playerSum = 0;

	public void init() {
		for (int i = 0; i < 13; i++) {
			//Card c=new Card(i+1, "hearts");
			deck[i] = new Card(i+1, "hearts");
			deck[i+13] = new Card(i+1, "diamonds");
			deck[i+26] = new Card(i+1, "clubs");
			deck[i+39] = new Card(i+1, "spades");

		}
	}
 

	
	public Card getNextCard(){
		Card c = deck[lastcardindex];
		lastcardindex++;
		return c;
	}

	public void Shuffle() {
		for (int i = deck.length - 1; i > 0; i--) {
			int rand = (int) (Math.random() * (i + 1));
			Card shuffle = deck[i];
			deck[i] = deck[rand];
			deck[rand] = shuffle;
		}
	}

}
