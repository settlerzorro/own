package sanguosha2.cards.specials.instant;

import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.instants.HarvestOperation;

public class Harvest extends Instant {

	private static final long serialVersionUID = -1L;

	public Harvest(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return "Harvest";
	}

	@Override
	public Operation generateOperation() {
		return new HarvestOperation();
	}
}
