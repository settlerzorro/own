package sanguosha2.core.client.game.operations.basics;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.PlayerCardSelectionInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractCardReactionOperation;
import sanguosha2.core.player.PlayerCardZone;

public class ShowCardReactionOperation extends AbstractCardReactionOperation {

	public ShowCardReactionOperation(String message) {
		super(message);
	}

	@Override
	protected boolean isCardActivatable(Card card) {
		// any card can be shown
		return true;
	}
	
	@Override
	protected boolean isCancelEnabled() {
		return false;
	}

	@Override
	protected InGameServerCommand getCommand(Card card) {
		return new PlayerCardSelectionInGameServerCommand(card, PlayerCardZone.HAND);
	}
	
}
