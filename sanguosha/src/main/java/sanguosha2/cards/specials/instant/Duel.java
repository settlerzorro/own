package sanguosha2.cards.specials.instant;

import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.instants.DuelOperation;

public class Duel extends Instant {
	private static final long serialVersionUID = -1L;
	public static final String DUEL = "Duel";

	public Duel(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return DUEL;
	}

	@Override
	public Operation generateOperation() {
		return new DuelOperation();
	}
}
