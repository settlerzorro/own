package sanguosha2.cards.specials.instant;

import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.instants.StealOperation;

public class Steal extends Instant {

	private static final long serialVersionUID = -1L;

	public Steal(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return "Steal";
	}

	@Override
	public Operation generateOperation() {
		return new StealOperation();
	}
}
