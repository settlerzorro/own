package sanguosha2.cards.specials.delayed;

import sanguosha2.cards.specials.Special;

public abstract class Delayed extends Special {

	private static final long serialVersionUID = -8220927012748306449L;
	
	public Delayed(int num, Suit suit, int id) {
		super(num, suit, false, id);
	}
}
