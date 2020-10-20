package sanguosha2.cards.specials.instant;

import sanguosha2.cards.specials.Special;
import sanguosha2.core.GameState;

public abstract class Instant extends Special {
	
	private static final long serialVersionUID = -9048643013272059145L;

	public Instant(int num, Suit suit, int id) {
		super(num, suit, true, id);
	}
	
	public Instant() {
		super(true);
	}

	@Override
	public boolean isActivatable(GameState game) {
		return true;
	}
}
