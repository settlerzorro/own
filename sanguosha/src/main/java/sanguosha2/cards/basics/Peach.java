package sanguosha2.cards.basics;

import sanguosha2.core.GameState;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.basics.PeachOperation;

public class Peach extends Basic {

	private static final long serialVersionUID = -302256299684511401L;
	
	public static final String PEACH = "Peach";

	public Peach(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return PEACH;
	}

	@Override
	public boolean isActivatable(GameState game) {
		return game.getSelf().getHealthCurrent() < game.getSelf().getHealthLimit();
	}

	@Override
	public Operation generateOperation() {
		return new PeachOperation();
	}
}
