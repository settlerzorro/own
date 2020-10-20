package sanguosha2.cards.specials.instant;

import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.instants.BrotherhoodOperation;

public class Brotherhood extends Instant {

	private static final long serialVersionUID = 1L;

	public static final String BROTHERHOOD = "Brotherhood";

	public Brotherhood(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return BROTHERHOOD;
	}

	@Override
	public Operation generateOperation() {
		return new BrotherhoodOperation();
	}
}
