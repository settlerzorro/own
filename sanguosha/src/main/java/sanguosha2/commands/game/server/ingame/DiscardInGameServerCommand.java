package sanguosha2.commands.game.server.ingame;

import java.util.List;

import sanguosha2. cards.Card;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.TurnGameController;
import sanguosha2. exceptions.server.game.InvalidPlayerCommandException;

public class DiscardInGameServerCommand extends InGameServerCommand {

	private static final long serialVersionUID = -2110615050430784494L;

	private final List<Card> cards;
	
	public DiscardInGameServerCommand(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public void execute(Game game) {
		try {
			game.<TurnGameController>getGameController().getCurrentPlayer().discardCards(cards);
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
			return;
		}
		game.<TurnGameController>getGameController().proceed();
	}

}
