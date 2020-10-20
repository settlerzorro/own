package sanguosha2.cards.basics;

import sanguosha2.core.GameState;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.basics.WineOperation;
import sanguosha2.core.player.PlayerCompleteClient;

public class Wine extends Basic {

	private static final long serialVersionUID = 8731935586533627689L;

	public static final String WINE = "Wine";

	public Wine(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return WINE;
	}

	@Override
	public boolean isActivatable(GameState game) {
		PlayerCompleteClient player = game.getSelf();
		return player.getWineUsed() < player.getWineLimit() && !player.isWineUsed();
	}

	@Override
	public Operation generateOperation() {
		return new WineOperation();
	}
}
