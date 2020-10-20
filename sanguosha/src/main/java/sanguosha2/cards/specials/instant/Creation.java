package sanguosha2.cards.specials.instant;

import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.instants.CreationOperation;

public class Creation extends Instant {
	
	private static final long serialVersionUID = -5695736855862242617L;

	public static final String CREATION = "Creation";

	public Creation(int num, Suit suit, int id) {
		super(num, suit, id);
	}
	
	public Creation() {}

	@Override
	public String getName() {
		return CREATION;
	}

	@Override
	public Operation generateOperation() {
		return new CreationOperation();
	}
}
