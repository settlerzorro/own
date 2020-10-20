package sanguosha2.cards.specials.delayed;

import sanguosha2.core.GameState;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.delayed.OblivionOperation;

public class Oblivion extends Delayed {

	private static final long serialVersionUID = -2295098976558504164L;

	public Oblivion(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return "Oblivion";
	}

	@Override
	public boolean isActivatable(GameState game) {
		return true;
	}

	@Override
	public Operation generateOperation() {
		return new OblivionOperation();
	}

}
