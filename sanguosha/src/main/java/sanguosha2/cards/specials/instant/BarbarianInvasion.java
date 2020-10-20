package sanguosha2.cards.specials.instant;

import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.instants.BarbarianInvasionOperation;

public class BarbarianInvasion extends Instant {

	private static final long serialVersionUID = 8054906715946205031L;
	
	public static final String BARBARIAN_INVASION = "Barbarian Invasion";

	public BarbarianInvasion(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return BARBARIAN_INVASION;
	}

	@Override
	public Operation generateOperation() {
		return new BarbarianInvasionOperation();
	}

}
